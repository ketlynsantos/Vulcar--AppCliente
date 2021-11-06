package com.app.clientevulcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    public TextView txtLogin;
    public EditText edtName;
    public EditText edtEmail;
    public EditText edtPhone;
    public EditText edtPassword;
    public AppCompatButton btnRegister;
    public ImageView imgArrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        getIds();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }

    public void getIds() {
        txtLogin = this.findViewById(R.id.txt_login);
        edtName = this.findViewById(R.id.edt_name);
        edtEmail = this.findViewById(R.id.edt_email);
        edtPhone = this.findViewById(R.id.edt_phone);
        edtPassword = this.findViewById(R.id.edt_password);
        btnRegister = this.findViewById(R.id.btn_register);
        imgArrowBack = this.findViewById(R.id.img_back);
    }
}