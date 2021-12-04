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
    public String id = "5";

    //Connection MySQL
    //String HOST = "http://192.168.15.122/Vulcar--Syncmysql/Client/";
    //String HOST = "http://172.20.10.6/Vulcar--Syncmysql/Client/";
    String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

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
        String oldPassword = edtPassword.getText().toString();
        String newPassword = edtNewPassword.getText().toString();

        client.setId(id);
        client.setPassword(oldPassword);
        updatePassword(newPassword);
    }

    private void updatePassword(String newPassword) {
        String url = HOST + "update_password.php";

        params.put("id", client.getId());
        params.put("old_senha", client.getPassword());
        params.put("new_senha", newPassword);

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        JSONObject result = new JSONObject(new String(responseBody));
                        if(result.getString("UPDATE").equals("true")) {
                            Toast.makeText(EditPassword.this, "Sucess!", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(EditPassword.this, MyData.class);
                            startActivity(it);
                            finish();
                        } else {
                            Toast.makeText(EditPassword.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(EditPassword.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getIds() {
        //id = getIntent().getStringExtra("id");
        imgBack = findViewById(R.id.img_back);
        edtPassword = findViewById(R.id.edt_password);
        edtNewPassword = findViewById(R.id.edt_new_password);
        btnAlter = findViewById(R.id.btn_alter_password);
    }
}