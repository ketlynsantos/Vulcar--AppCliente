package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EditPersonData extends AppCompatActivity {

    public ImageView imgBack;
    public TextInputEditText edtName;
    public TextInputEditText edtCPF;
    public AppCompatButton btnEdit;
    public String id;

    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/Client/";
    //String HOST = "http://192.168.0.106/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;

    Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_data);

        getSupportActionBar().hide();
        getIds();
        maskFormat();
        cliente = new AsyncHttpClient();

        montaObj();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(EditPersonData.this, MyData.class);
                itI.putExtra("id", id);
                startActivity(itI);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String cpf = edtCPF.getText().toString();

                Boolean checkValidations = validationData(name, cpf);
                Intent intent = new Intent(EditPersonData.this, MyData.class);

                if(checkValidations == true){
                    client.setId(id);
                    client.setNome(name);
                    client.setCpf(cpf);
                    updateData(client);
                }
            }
        });
    }

    private void updateData(Client client) {
        String url = HOST+"update_data.php";

        params.put("id", client.getId());
        params.put("name", client.getNome());
        params.put("cpf", client.getCpf());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Toast.makeText(EditPersonData.this, "Dados atualizados!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditPersonData.this, MyData.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void montaObj() {
        String url = HOST + "Select/select_profile.php";

        client.setId(id);
        params.put("id", client.getId());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    try {
                        JSONObject jsonarray = new JSONObject(new String(responseBody));
                        String nome = jsonarray.getString("CLIENTE_NOME");
                        edtName.setText(nome);
                        String cpf = jsonarray.getString("CLIENTE_CPF");
                        edtCPF.setText(cpf);
                        if (jsonarray.getString("STATUS_ID").equals("5")) {
                            Intent intent = new Intent(EditPersonData.this, Login.class);
                            Toast.makeText(EditPersonData.this, "Estabelecimento banido!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getIds() {
        id = getIntent().getStringExtra("id");

        imgBack = findViewById(R.id.img_back);
        edtName = findViewById(R.id.edt_name);
        edtCPF = findViewById(R.id.edt_cpf);
        btnEdit = findViewById(R.id.btn_edit_data);
    }

    public void maskFormat() {
        SimpleMaskFormatter mask_cpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw_cpf = new MaskTextWatcher(edtCPF, mask_cpf);
        edtCPF.addTextChangedListener(mtw_cpf);
    }

    private Boolean validationData(String name, String cpf) {
        if (name.length() == 0) {
            edtName.requestFocus();
            edtName.setError("Campo vazio.");
            return false;
        } else if(!name.matches("[a-zA-Z]+")) {
            edtName.requestFocus();
            edtName.setError("Apenas letras, por favor!");
            return false;
        }   else if (cpf.length() == 0) {
            edtCPF.requestFocus();
            edtCPF.setError("Campo vazio");
            return false;
        } else if (cpf.length() != 14) {
            edtCPF.requestFocus();
            edtCPF.setError("CPF inválido!");
            return false;
        } else {
            return true;
        }
    }
}