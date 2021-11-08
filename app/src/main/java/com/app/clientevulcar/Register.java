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
import android.widget.Toast;

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String password = edtPassword.getText().toString();

                boolean checkValidations = validationRegister(name, email, phone, password);

              //Testando validação
               /* if (checkValidations == true) {
                   Toast.makeText(getApplicationContext(), "Deu certo!", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(getApplicationContext(), "Deu errado!", Toast.LENGTH_SHORT).show();
               }*/
            }
        });

    }

    private void getIds() {
        txtLogin = this.findViewById(R.id.txt_login);
        edtName = this.findViewById(R.id.edt_name);
        edtEmail = this.findViewById(R.id.edt_email);
        edtPhone = this.findViewById(R.id.edt_phone);
        edtPassword = this.findViewById(R.id.edt_password);
        btnRegister = this.findViewById(R.id.btn_register);
        imgArrowBack = this.findViewById(R.id.img_back);
    }

    private Boolean validationRegister(String name, String email, String phone, String password) {
        if (name.length() == 0) {
            edtName.requestFocus();
            edtName.setError("Campo vazio.");
            return false;
        } else if(!name.matches("[a-zA-Z]+")) {
            edtName.requestFocus();
            edtName.setError("Apenas letras, por favor!");
            return false;
        } else if(email.length() == 0) {
            edtEmail.requestFocus();
            edtEmail.setError("Campo vazio.");
            return false;
        } else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edtEmail.requestFocus();
            edtEmail.setError("Email inválido!");
            return false;
        } else if(phone.length() == 0) {
            edtPhone.requestFocus();
            edtPhone.setError("Campo vazio.");
            return false;
        } else if(!phone.matches("^\\s*(\\d{2}|\\d{0})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$")) {
            edtPhone.requestFocus();
            edtPhone.setError("Formato correto: xx xxxxx-xxxx");
            return false;
        } else if (password.length() < 5) {
            edtPassword.requestFocus();
            edtPassword.setError("Mínino 6 caracteres.");
            return false;
        }  else {
            return true;
        }
    }
}