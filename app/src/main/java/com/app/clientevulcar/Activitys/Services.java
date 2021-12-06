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
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Services extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtNameServices, txtNameBusiness;
    public TextView txtCategory;
    public TextView txtDesc;
    public TextView txtPrice;
    public ListView lvVehicles;

    public String id, idBusiness, idCat;

    public Activity context;

    //Connection MySQL
    //String HOST = "http://192.168.15.122/Vulcar--Syncmysql/Client/";
    String HOST = "http://192.168.15.135/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;

    Vehicle vehicle = new Vehicle();
    com.app.clientevulcar.Model.Services services = new com.app.clientevulcar.Model.Services();

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

        carregarVehicles();

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
        String url = HOST + "Select/select_vehicle_where.php";
        vehicle.setClienteId(id);
        services.setId_categoria(idCat);
        params.put("id", vehicle.getClienteId());
        params.put("idCat", services.getId_categoria());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarVehicles(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    private void listarVehicles(String resposta) {
        final ArrayList<Vehicle> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                Vehicle v = new Vehicle();
                v.setId(jsonarray.getJSONObject(i).getString("id_auto"));
                v.setModelo(jsonarray.getJSONObject(i).getString("nome_auto"));

                lista.add(v);

            }

            AdapterVehiclesCategory adapter = new AdapterVehiclesCategory(context, R.layout.adapter_vehicles_check, R.id.txt_id, lista);
            lvVehicles.setAdapter(adapter);

            lvVehicles.setClickable(true);
            lvVehicles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent it = new Intent(Services.this, FinishRequest.class);
                    it.putExtra("idVehicle", lista.get(position).getId());
                    it.putExtra("id", id);
                    startActivity(it);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getIds() {
        id = getIntent().getStringExtra("id");
        idBusiness = getIntent().getStringExtra("idBusiness");
        idCat = getIntent().getStringExtra("id_cate");

        imgBack = findViewById(R.id.img_back);
        txtNameServices = findViewById(R.id.txt_name_services);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        txtCategory = findViewById(R.id.txt_category_services);
        txtDesc = findViewById(R.id.txt_desc_services);
        txtPrice = findViewById(R.id.txt_price_services);
        lvVehicles = findViewById(R.id.lv_vehicles);
    }
}