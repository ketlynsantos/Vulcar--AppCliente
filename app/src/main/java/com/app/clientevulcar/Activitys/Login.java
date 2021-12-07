package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity {

    public TextView txtRegister;
    public TextView txtForgetPassword;
    public TextInputEditText edtEmail;
    public TextInputEditText edtPassword;
    public AppCompatButton btnLogin;

    //Connection MySQL
    String HOST = "http://192.168.15.137/vulcar_database/Client/";
    //String HOST = "http://192.168.0.106/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente.setTimeout(2000);
                cliente.setMaxRetriesAndTimeout(1, 5000);
                montaObj();
            }
        });
    }

    private void montaObj() {
        String email, pass;
        email = edtEmail.getText().toString();
        pass = edtPassword.getText().toString();

        boolean checkValidations = validationLogin(email, pass);
        if(checkValidations == true){
            client.setEmail(email);
            client.setPassword(pass);
            funcLogin(client);
        }
    }

    private void funcLogin(Client client) {
        String url = HOST + "login.php";

        params.put("email", client.getEmail());
        params.put("pass", client.getPassword());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        JSONObject result = new JSONObject(new String(responseBody));
                        if (!result.getString("LOGIN").equals(null)) {
                            String id = result.getString("LOGIN");
                            String status = result.getString("STATUS");
                            if(status == "5"){
                                Toast.makeText(Login.this, "Conta banida!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(Login.this, "Erro na conexão!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(Login.this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void getIds() {
        txtRegister = this.findViewById(R.id.txt_register);
        txtForgetPassword = this.findViewById(R.id.txt_forget_password);
        edtEmail = this.findViewById(R.id.edt_email);
        edtPassword = this.findViewById(R.id.edt_password);
        btnLogin = this.findViewById(R.id.btn_login);
    }

    public Boolean validationLogin(String email, String pass) {
        if (email.length() == 0) {
            edtEmail.requestFocus();
            edtEmail.setError("Campo vazio.");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edtEmail.requestFocus();
            edtEmail.setError("Email inválido!");
            return false;
        } else if (pass.length() == 0) {
            edtPassword.requestFocus();
            edtPassword.setError("Campo vazio.");
            return false;
        } else {
            return true;
        }
    }
}