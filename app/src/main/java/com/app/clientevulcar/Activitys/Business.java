package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.clientevulcar.R;

public class Business extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtNameBusiness;
    public ListView lvServices;
    public String idClient, idBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Business.this, Home.class);
                intent.putExtra("id", idClient);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getIds() {
        idClient = getIntent().getStringExtra("id");
        idBusiness = getIntent().getStringExtra("idBusiness");

        imgBack = findViewById(R.id.img_back);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        lvServices = findViewById(R.id.lv_services);
    }
}