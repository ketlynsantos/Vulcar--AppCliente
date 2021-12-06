package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.clientevulcar.Adapter.AdapterLojas;
import com.app.clientevulcar.Adapter.AdapterVehiclesCategory;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Services extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtNameServices, txtNameBusiness;
    public TextView txtCategory;
    public TextView txtDesc;
    public TextView txtPrice;
    public ListView lvVehicles;

    public String id, idBusiness, nomeServ, descServ, idCategoria, nomeCategoria, valor;

    public Activity context;

    //Connection MySQL
    //String HOST = "http://192.168.15.122/Vulcar--Syncmysql/Client/";
    String HOST = "http://192.168.15.135/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();
        context = Services.this;

        txtNameBusiness.setText(getIntent().getStringExtra("nome_loja"));
        txtNameServices.setText(getIntent().getStringExtra("nome_serv"));
        txtDesc.setText(getIntent().getStringExtra("desc_serv"));
        txtCategory.setText(getIntent().getStringExtra("nome_cate"));
        txtPrice.setText(getIntent().getStringExtra("valor"));

        //carregarVehicles();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Services.this, Business.class);
                it.putExtra("id", id);
                it.putExtra("idBusiness", idBusiness);
                startActivity(it);
                finish();
            }
        });
    }

    private void carregarVehicles() {

    }

    private void listarVehicles(String resposta) {
        final ArrayList<Vehicle> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                Vehicle vehicle = new Vehicle();

                vehicle.setId(jsonarray.getJSONObject(i).getString("TB_AUTOMOVEL_ID"));
                vehicle.setModelo(jsonarray.getJSONObject(i).getString("TB_AUTOMOVEL_MODELO"));

                lista.add(vehicle);

            }

            AdapterVehiclesCategory adapter = new AdapterVehiclesCategory(context, R.layout.adapter_vehicles_check, lista);
            lvVehicles.setAdapter(adapter);

            lvVehicles.setClickable(true);
            lvVehicles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent it = new Intent(Services.this, FinishRequest.class);
//                    it.putExtra("idVehicle", lista.get(position).getId());
//                    it.putExtra("id", id);
//                    startActivity(it);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getIds() {
        id = getIntent().getStringExtra("id");
        idBusiness = getIntent().getStringExtra("idBusiness");

        imgBack = findViewById(R.id.img_back);
        txtNameServices = findViewById(R.id.txt_name_services);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        txtCategory = findViewById(R.id.txt_category_services);
        txtDesc = findViewById(R.id.txt_desc_services);
        txtPrice = findViewById(R.id.txt_price_services);
        lvVehicles = findViewById(R.id.lv_vehicles);
    }
}