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

public class EditContactData extends AppCompatActivity {

    public ImageView imgBack;
    public TextInputEditText edtEmail;
    public TextInputEditText edtPhone;
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
        setContentView(R.layout.activity_edit_contact_data);

        getSupportActionBar().hide();
        getIds();
        maskFormat();
        cliente = new AsyncHttpClient();

        montaObj();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(EditContactData.this, MyData.class);
                itI.putExtra("id", id);
                startActivity(itI);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();

                Boolean checkValidations = validationContact(email, phone);
                Intent intent = new Intent(EditContactData.this, MyData.class);

                if(checkValidations == true){
                    client.setId(id);
                    client.setEmail(email);
                    client.setPhone(phone);
                    updateContact(client);
                }
            }
        });

    }

    private void updateContact(Client client) {
        String url = HOST+"update_contact.php";

        params.put("id", client.getId());
        params.put("email", client.getEmail());
        params.put("phone", client.getPhone());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Toast.makeText(EditContactData.this, "Dados atualizados!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditContactData.this, MyData.class);
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
                        String email = jsonarray.getString("CLIENTE_EMAIL");
                        edtEmail.setText(email);
                        String tel = jsonarray.getString("CLIENTE_TEL");
                        edtPhone.setText(tel);
                        if (jsonarray.getString("STATUS_ID").equals("5")) {
                            Intent intent = new Intent(EditContactData.this, Login.class);
                            Toast.makeText(EditContactData.this, "Estabelecimento banido!", Toast.LENGTH_SHORT).show();
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
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        btnEdit = findViewById(R.id.btn_edit_contact);
    }

    private void maskFormat() {
        SimpleMaskFormatter mask_tel = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw_tel = new MaskTextWatcher(edtPhone, mask_tel);
        edtPhone.addTextChangedListener(mtw_tel);
    }

    private Boolean validationContact(String email, String phone){
        if(email.length() == 0){
            edtEmail.requestFocus();
            edtEmail.setError("Campo vazio.");
            return false;
        } else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edtEmail.requestFocus();
            edtEmail.setError("Email inválido!");
            return false;
        } else if(phone.length() == 0){
            edtPhone.requestFocus();
            edtPhone.setError("Campo vazio.");
            return false;
        } else if(phone.length() != 15){
            edtPhone.requestFocus();
            edtPhone.setError("Telefone inválido!");
            return false;
        }else {
            return true;
        }
    }
}