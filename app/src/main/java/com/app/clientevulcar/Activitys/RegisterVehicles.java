package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class RegisterVehicles extends AppCompatActivity {

    public ImageView imgBack;
    public TextInputEditText edtModel, edtColor;
    public Spinner spinnerCategory, spinnerBrand;
    public AppCompatButton btnRegister;

    public String id, brandId, categoryId;

    //Connection MySQL
    //String HOST = "http://192.168.15.122/Vulcar--Syncmysql/Client/";
    //String HOST = "http://172.20.10.5/vulcar_database/Client/";
    String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Client client = new Client();
    Vehicle vehicle = new Vehicle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicles);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterVehicles.this, Home.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                montaObj();
            }
        });

        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextColor(Color.WHITE);
                switch(adapterView.getItemAtPosition(i).toString()){
                    case "MARCA":
                        spinnerBrand.requestFocus();
                        Toast.makeText(RegisterVehicles.this, "Selecione uma marca!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RegisterVehicles.this, "Selecione uma categoria!", Toast.LENGTH_SHORT).show();
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

    private void montaObj() {
        String model = edtModel.getText().toString();
        String color = edtColor.getText().toString();
        String brand = brandId;
        String category = categoryId;

        vehicle.setClienteId(id);
        vehicle.setModelo(model);
        vehicle.setCor(color);
        vehicle.setMarca(brand);
        vehicle.setCategoria(category);
        registerVehicle(vehicle);
    }

    private void registerVehicle(Vehicle vehicle) {
        String url = HOST + "register_vehicle.php";

        params.put("id", vehicle.getClienteId());
        params.put("model", vehicle.getModelo());
        params.put("brand", vehicle.getMarca());
        params.put("color", vehicle.getCor());
        params.put("category", vehicle.getCategoria());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    Intent intent = new Intent(RegisterVehicles.this, Home.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getIds() {
        id = getIntent().getStringExtra("id");

        imgBack = findViewById(R.id.img_back);
        edtModel = findViewById(R.id.edt_model);
        edtColor = findViewById(R.id.edt_color);
        spinnerBrand = findViewById(R.id.spinner_brand);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnRegister = findViewById(R.id.btn_register_vehicle);
    }
}