package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.clientevulcar.R;

public class MyAddress extends AppCompatActivity {

    public ImageView imgBack;
    public AppCompatButton btnEdit;
    public TextView txtAddress;
    public TextView txtNumber;
    public TextView txtNeighborhood;
    public TextView txtCity;
    public TextView txtUF;
    public TextView txtCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, EditAddress.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getIds() {
        imgBack = findViewById(R.id.img_back);
        btnEdit = findViewById(R.id.btn_edit);
        txtAddress = findViewById(R.id.txt_address);
        txtNumber = findViewById(R.id.txt_number);
        txtNeighborhood = findViewById(R.id.txt_neighborhood);
        txtCity = findViewById(R.id.txt_city);
        txtUF = findViewById(R.id.txt_uf);
        txtCep = findViewById(R.id.txt_cep);
    }
}