package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.HttpDelete;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    public TextView txtLogin;
    public TextInputEditText edtName;
    public TextInputEditText edtEmail;
    public TextInputEditText edtPhone;
    public TextInputEditText edtPassword;
    public TextInputEditText edtCpf;
    public ImageButton btnRegister;
    public ImageView imgArrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        getIds();
        maskFormat();

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
                String cpf = edtCpf.getText().toString();
                boolean checkValidations = validationRegister(name, email, phone, password, cpf);

                Intent intent = new Intent(Register.this, Register2.class);

              //Testando validação
               if (checkValidations == true) {

                   intent.putExtra("name", name);
                   intent.putExtra("email", email);
                   intent.putExtra("phone", phone);
                   intent.putExtra("password", password);
                   intent.putExtra("cpf", cpf);
                   startActivity(intent);

               } else {
                   //Toast.makeText(getApplicationContext(), phone.length(), Toast.LENGTH_SHORT).show();
               }
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
        edtCpf = this.findViewById(R.id.edt_cpf);
    }

    private void maskFormat() {
        SimpleMaskFormatter mask_tel = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw_tel = new MaskTextWatcher(edtPhone, mask_tel);
        edtPhone.addTextChangedListener(mtw_tel);

        SimpleMaskFormatter mask_cpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw_cpf = new MaskTextWatcher(edtCpf, mask_cpf);
        edtCpf.addTextChangedListener(mtw_cpf);
    }

    private Boolean validationRegister(String name, String email, String phone, String password, String cpf) {
        if (name.length() == 0) {
            edtName.requestFocus();
            edtName.setError("Campo vazio.");
            return false;
        } else if(!name.matches("[a-zA-Z]+")) {
            edtName.requestFocus();
            edtName.setError("Apenas letras, por favor!");
            return false;
        }   else if (cpf.length() == 0) {
            edtCpf.requestFocus();
            edtCpf.setError("Campo vazio");
            return false;
        } else if (cpf.length() != 14) {
            edtCpf.requestFocus();
            edtCpf.setError("CPF inválido!");
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
        } else if(phone.length() != 15) {
            edtPhone.requestFocus();
            edtPhone.setError("Número incorreto.");
            return false;
        } else if (password.length() < 5) {
            edtPassword.requestFocus();
            edtPassword.setError("Mínino 6 caracteres.");
            return false;
        } else {
            return true;
        }
    }
}