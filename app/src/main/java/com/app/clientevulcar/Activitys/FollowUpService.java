package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.clientevulcar.R;

public class FollowUpService extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtStatus;
    public TextView txtNameEmployee;
    public TextView txtNameBusiness;
    public TextView txtNameServices;
    public TextView txtPrice;
    public TextView txtVehicle;

    public String idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_service);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FollowUpService.this, Requests.class);
                intent.putExtra("id", idCliente);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getIds() {
        imgBack = findViewById(R.id.img_back);
        txtStatus = findViewById(R.id.txt_status_request);
        txtNameEmployee = findViewById(R.id.txt_name_employee);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        txtNameServices = findViewById(R.id.txt_services);
        txtPrice = findViewById(R.id.txt_price);
        txtVehicle = findViewById(R.id.txt_vehicle);

    }
}