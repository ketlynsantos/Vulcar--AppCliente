package com.app.clientevulcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.clientevulcar.R;

public class MyAddress extends AppCompatActivity {

    public ImageView imgBack;
    public AppCompatButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAddress.this, Home.class));
            }
        });

    }

    private void getIds() {
        imgBack = findViewById(R.id.img_back);
        btnEdit = findViewById(R.id.btn_edit);
    }
}