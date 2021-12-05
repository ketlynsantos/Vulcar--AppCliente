package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.R;
import com.google.android.material.textfield.TextInputEditText;

public class EditVehicles extends AppCompatActivity {

    public ImageView imgBack;
    public TextInputEditText edtModel;
    public TextInputEditText edtColor;
    public Spinner spinnerCategory;
    public Spinner spinnerBrand;
    public AppCompatButton btnAlterVehicles;

    public String idVehicle, idCliente;
    public String model;
    public String brand, brandId;
    public String category, categoryId;
    public String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicles);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditVehicles.this, MyVehicle.class);
                intent.putExtra("idVehicle", idVehicle);
                intent.putExtra("modelo", model);
                intent.putExtra("marca", brand);
                intent.putExtra("categoria", category);
                intent.putExtra("cor", color);
                intent.putExtra("id", idCliente);
                startActivity(intent);
                finish();
            }
        });

        btnAlterVehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextColor(Color.WHITE);
                switch(adapterView.getItemAtPosition(i).toString()){
                    case "MARCA":
                        spinnerBrand.requestFocus();
                        Toast.makeText(EditVehicles.this, "Selecione uma marca!", Toast.LENGTH_SHORT).show();
                        break;
                    case "HONDA":
                        brandId = "1";
                        break;
                    case "FIAT":
                        brandId = "2";
                        break;
                    case "FORD":
                        brandId = "3";
                        break;
                    case "CHEVROLET":
                        brandId = "4";
                        break;
                    case "CHERY":
                        brandId = "5";
                        break;
                    case "DODGE":
                        brandId = "6";
                        break;
                    case "JAC":
                        brandId = "7";
                        break;
                    case "JAGUAR":
                        brandId = "8";
                        break;
                    case "LAND ROVER":
                        brandId = "9";
                        break;
                    case "LEXUS":
                        brandId = "10";
                        break;
                    case "MCLAREN":
                        brandId = "11";
                        break;
                    case "MASERATI":
                        brandId = "12";
                        break;
                    case "MINI":
                        brandId = "13";
                        break;
                    case "ROLLS-ROYCE":
                        brandId = "14";
                        break;
                    case "JEEP":
                        brandId = "15";
                        break;
                    case "NISSAN":
                        brandId = "16";
                        break;
                    case "MERCEDES-BENZ":
                        brandId = "17";
                        break;
                    case "PEUGEOT":
                        brandId = "18";
                        break;
                    case "VOLKSWAGEM":
                        brandId = "19";
                        break;
                    case "KIA":
                        brandId = "20";
                        break;
                    case "MITSUBISHI":
                        brandId = "21";
                        break;
                    case "HYUNDAI":
                        brandId = "22";
                        break;
                    case "BMW":
                        brandId = "23";
                        break;
                    case "RENAULT":
                        brandId = "24";
                        break;
                    case "TOYOTA":
                        brandId = "25";
                        break;
                    case "AUDI":
                        brandId = "26";
                        break;
                    case "BUGATTI":
                        brandId = "27";
                        break;
                    case "FERRARI":
                        brandId = "28";
                        break;
                    case "SUBARU":
                        brandId = "29";
                        break;
                    case "ASTON MARTIN":
                        brandId = "30";
                        break;
                    case "TESLA":
                        brandId = "31";
                        break;
                    case "TROLLER":
                        brandId = "32";
                        break;
                    case "VOLVO":
                        brandId = "33";
                        break;
                    case "PORSCHE":
                        brandId = "34";
                        break;
                    case "LAMBORGHINI":
                        brandId = "35";
                        break;
                    case "CITROEN":
                        brandId = "36";
                        break;
                    case "YAMAHA":
                        brandId = "37";
                        break;
                    case "DUCATI":
                        brandId = "38";
                        break;
                    case "KAWASAKI":
                        brandId = "39";
                        break;
                    case "HARLEY-DAVIDSON":
                        brandId = "40";
                        break;
                    case "SHINERAY":
                        brandId = "41";
                        break;
                    case "SUZUKI":
                        brandId = "42";
                        break;
                    case "TRIUMPH":
                        brandId = "43";
                        break;
                    case "OUTRA":
                        brandId = "44";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) view).setTextColor(Color.WHITE);
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "CATEGORIA":
                        spinnerCategory.requestFocus();
                        Toast.makeText(EditVehicles.this, "Selecione uma categoria!", Toast.LENGTH_SHORT).show();
                        break;
                    case "RACING":
                        categoryId = "1";
                        break;
                    case "SEDAN":
                        categoryId = "2";
                        break;
                    case "PICKUP":
                        categoryId = "3";
                        break;
                    case "UTILITÁRIO":
                        categoryId = "4";
                        break;
                    case "WAGON":
                        categoryId = "5";
                        break;
                    case "HATCH":
                        categoryId = "6";
                        break;
                    case "MOTOCICLETA":
                        categoryId = "7";
                        break;
                    case "COUPÊ":
                        categoryId = "8";
                        break;
                    case "SUV":
                        categoryId = "9";
                        break;
                    case "OFF-ROAD":
                        categoryId = "10";
                        break;
                    case "OUTRO":
                        categoryId = "11";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getIds() {
        idVehicle = getIntent().getStringExtra("idVehicle");
        idCliente = getIntent().getStringExtra("id");
        model = getIntent().getStringExtra("modelo");
        brand = getIntent().getStringExtra("marca");
        category = getIntent().getStringExtra("categoria");
        color = getIntent().getStringExtra("cor");

        imgBack = findViewById(R.id.img_back);
        edtModel = findViewById(R.id.edt_model);
        edtColor = findViewById(R.id.edt_color);
        spinnerBrand = findViewById(R.id.spinner_brand);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnAlterVehicles = findViewById(R.id.btn_alter_vehicle);
    }
}