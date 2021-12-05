package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;

public class MyVehicle extends AppCompatActivity {

    public ImageView imgBack;
    public AppCompatButton btnEditVehicles;
    public TextView txtModel;
    public TextView txtBrand;
    public TextView txtCategory;
    public TextView txtColor;

    public String idVehicle, idCliente;
    public String model;
    public String brand;
    public String category;
    public String color;

    public Vehicle vehicle = new Vehicle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicle);

        getSupportActionBar().hide();
        getIds();
        montaObj();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyVehicle.this, Home.class);
                intent.putExtra("id", vehicle.getClienteId());
                startActivity(intent);
                finish();
            }
        });

        btnEditVehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itV = new Intent(MyVehicle.this, EditVehicles.class);
                itV.putExtra("idVehicle", vehicle.getId());
                itV.putExtra("modelo", vehicle.getModelo());
                itV.putExtra("marca", vehicle.getMarca());
                itV.putExtra("categoria", vehicle.getCategoria());
                itV.putExtra("cor", vehicle.getCor());
                itV.putExtra("id", vehicle.getClienteId());
                startActivity(itV);
                finish();
            }
        });

    }

    private void montaObj() {
        vehicle.setId(idVehicle);
        vehicle.setModelo(model);
        vehicle.setMarca(brand);
        vehicle.setCategoria(category);
        vehicle.setCor(color);
        vehicle.setClienteId(idCliente);

        txtModel.setText(vehicle.getModelo());
        txtBrand.setText(vehicle.getMarca());
        txtCategory.setText(vehicle.getCategoria());
        txtColor.setText(vehicle.getCor());
    }

    private void getIds() {
        idVehicle = getIntent().getStringExtra("idVehicle");
        idCliente = getIntent().getStringExtra("id");
        model = getIntent().getStringExtra("modelo");
        brand = getIntent().getStringExtra("marca");
        category = getIntent().getStringExtra("categoria");
        color = getIntent().getStringExtra("cor");

        imgBack = findViewById(R.id.img_back);
        txtModel = findViewById(R.id.txt_model);
        txtBrand = findViewById(R.id.txt_brand);
        txtCategory = findViewById(R.id.txt_category);
        txtColor = findViewById(R.id.txt_color);
        btnEditVehicles = findViewById(R.id.btn_edit);
    }
}