package com.app.clientevulcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    public TextView txtRegister;
    public TextView txtForgetPassword;
    public EditText edtEmail;
    public EditText edtPassword;
    public AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        getIds();

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        //ir para home
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Home.class));
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
}