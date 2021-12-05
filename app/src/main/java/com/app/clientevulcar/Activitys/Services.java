package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.clientevulcar.R;

public class Services extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtNameServices;
    public TextView txtCategory;
    public TextView txtDesc;
    public TextView txtPrice;
    public ListView lvVehicles;
    public AppCompatButton btnRequestService;

    public String idClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Services.this, Business.class);
                it.putExtra("id", idClient);
                startActivity(it);
                finish();
            }
        });

        btnRequestService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getIds() {
        idClient = getIntent().getStringExtra("id");

        imgBack = findViewById(R.id.img_back);
        txtNameServices = findViewById(R.id.txt_name_services);
        txtCategory = findViewById(R.id.txt_category_services);
        txtDesc = findViewById(R.id.txt_desc_services);
        txtPrice = findViewById(R.id.txt_price_services);
        lvVehicles = findViewById(R.id.lv_vehicles);
        btnRequestService = findViewById(R.id.btn_request_service);
    }
}