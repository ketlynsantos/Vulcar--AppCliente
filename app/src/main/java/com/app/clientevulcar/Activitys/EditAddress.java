package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.clientevulcar.MyAddress;
import com.app.clientevulcar.R;

import java.net.Inet4Address;

public class EditAddress extends AppCompatActivity {

    ImageView imgBack;
    EditText edtNewAddress;
    EditText edtNewNeighborhood;
    EditText edtNewComplement;
    EditText edtNewNumber;
    EditText edtNewCity;
    EditText edtNewUF;
    EditText edtNewCep;
    AppCompatButton btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditAddress.this, MyAddress.class));
            }
        });
    }

    private void getIds() {
        imgBack = findViewById(R.id.img_back);
        edtNewAddress = findViewById(R.id.edt_new_endereco);
        edtNewNeighborhood = findViewById(R.id.edt_new_bairro);
        edtNewComplement = findViewById(R.id.edt_new_complemento);
        edtNewNumber = findViewById(R.id.edt_new_num);
        edtNewCity = findViewById(R.id.edt_new_cidade);
        edtNewUF = findViewById(R.id.edt_new_uf);
        edtNewCep = findViewById(R.id.edt_new_cep);
        btnEditar = findViewById(R.id.btn_edit_address);
    }
}