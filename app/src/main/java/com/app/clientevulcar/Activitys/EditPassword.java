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
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EditPassword extends AppCompatActivity {

    public ImageView imgBack;
    public TextInputEditText edtPassword;
    public TextInputEditText edtNewPassword;
    public AppCompatButton btnAlter;
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
        setContentView(R.layout.activity_edit_password);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(EditPassword.this, MyData.class);
                itI.putExtra("id", id);
                startActivity(itI);
                finish();
            }
        });

        btnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montaObj();
            }
        });

    }

    private void montaObj() {
        String newPass, oldPass;
        newPass = edtNewPassword.getText().toString();
        oldPass = edtPassword.getText().toString();
        client.setId(id);
        client.setPassword(oldPass);
        updatePassword(client, newPass);
    }

    private void updatePassword(Client client, String newPass) {
        String url = HOST+"update_pass.php";

        params.put("id", client.getId());
        params.put("old_pass", client.getPassword());
        params.put("new_pass", newPass);

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        JSONObject result = new JSONObject(new String(responseBody));
                        if(result.getString("UPDATE").equals("true")){
                            Toast.makeText(EditPassword.this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditPassword.this, MyData.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditPassword.this, "Senha atual incorreta", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(EditPassword.this, "Erro ao trocar senha!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditPassword.this, "Erro ao trocar senha!", Toast.LENGTH_SHORT).show();
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
        edtPassword = findViewById(R.id.edt_password);
        edtNewPassword = findViewById(R.id.edt_new_password);
        btnAlter = findViewById(R.id.btn_alter_password);
    }
}