package com.app.clientevulcar.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.Adapter.AdapterLojas;
import com.app.clientevulcar.Adapter.AdapterVehicles;
import com.app.clientevulcar.Adapter.RecyclerAdapterVehicles;
import com.app.clientevulcar.Model.Business;
import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Home extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public ImageView imgGoToAddress;
    public TextView txtAddress;
    public LinearLayout llGoAddress;
    public ImageButton btnRegisterCar;
    public ListView lvBusiness, lvVehicle;
    public String id;
    Client client;
    Vehicle vehicle;

    //Connection MySQL
    //String HOST = "http://192.168.15.127/vulcar_database/Client/";
    //String HOST = "http://172.20.10.5/vulcar_database/Client/";
    String HOST = "http://192.168.0.107/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;

    Activity context;

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cliente = new AsyncHttpClient();
        context = Home.this;

        getSupportActionBar().hide();
        getIds();
        getModels();
        montaObj();
        carregarLojas();
        carregarVehicles();

        //lvVehicle.setRotation(-90);
        Intent intent_address = new Intent(Home.this, MyAddress.class);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.search:
                        Intent intent_s = new Intent(Home.this, Search.class);
                        intent_s.putExtra("id", id);
                        startActivity(intent_s);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.requests:
                        Intent intent_r = new Intent(Home.this, Requests.class);
                        intent_r.putExtra("id", id);
                        startActivity(intent_r);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent intent_p = new Intent(Home.this, Profile.class);
                        intent_p.putExtra("id", id);
                        startActivity(intent_p);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        imgGoToAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_address.putExtra("id", id);
                startActivity(intent_address);
            }
        });

        llGoAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_address.putExtra("id", id);
                startActivity(intent_address);
            }
        });

        btnRegisterCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, RegisterVehicles.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

    private void carregarVehicles() {
        String url = HOST + "Select/select_vehicle.php";
        vehicle.setClienteId(id);
        params.put("id", vehicle.getClienteId());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    //listarVehicles(new String(responseBody));
                    listarTeste(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarTeste(String resposta) {
        final ArrayList<Vehicle> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                Vehicle v = new Vehicle();

                v.setId(jsonarray.getJSONObject(i).getString("id"));
                v.setModelo(jsonarray.getJSONObject(i).getString("modelo"));
                v.setMarca(jsonarray.getJSONObject(i).getString("marca"));
                v.setCor(jsonarray.getJSONObject(i).getString("cor"));
                v.setCategoria(jsonarray.getJSONObject(i).getString("categoria"));

                lista.add(v);

            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            RecyclerAdapterVehicles adapter = new RecyclerAdapterVehicles(this, lista);
            recyclerView.setAdapter(adapter);

        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

//    private void listarVehicles(String resposta) {
//        final ArrayList<Vehicle> lista = new ArrayList<>();
//
//        try {
//            JSONArray jsonarray = new JSONArray(resposta);
//
//            for (int i = 0; i < jsonarray.length(); i++){
//                Vehicle v = new Vehicle();
//
//                v.setId(jsonarray.getJSONObject(i).getString("id"));
//                v.setModelo(jsonarray.getJSONObject(i).getString("modelo"));
//                v.setMarca(jsonarray.getJSONObject(i).getString("marca"));
//                v.setCor(jsonarray.getJSONObject(i).getString("cor"));
//                v.setCategoria(jsonarray.getJSONObject(i).getString("categoria"));
//
//                lista.add(v);
//
//            }
//
//            AdapterVehicles adapter = new AdapterVehicles(context, R.layout.adapter_vehicles, R.id.txt_id, lista);
//            lvVehicle.setAdapter(adapter);
//
//        } catch(Exception erro) {
//            Log.d("erro", "erro"+erro);
//        }
//    }

    private void carregarLojas() {
        String url = HOST + "Select/select_business.php";

        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarLojas(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarLojas(String resposta) {
        final ArrayList<Business> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                Business b = new Business();

                b.setId(jsonarray.getJSONObject(i).getString("TB_LOJA_ID"));
                b.setNome(jsonarray.getJSONObject(i).getString("TB_LOJA_NOME"));
                //b.setImg(jsonarray.getJSONObject(i).getString("TB_LOJA_IMG"));

                lista.add(b);

            }

            AdapterLojas adapter = new AdapterLojas(context, R.layout.adapter_lojas, R.id.txt_id, lista);
            lvBusiness.setAdapter(adapter);

        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void montaObj() {
        String url = HOST+"Select/select_profile.php";
        client.setId(id);
        params.put("id", client.getId());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonarray = new JSONObject(new String (responseBody));
                    String address = jsonarray.getString("CLIENTE_ENDERECO");
                    String num = jsonarray.getString("CLIENTE_NUM");

                    txtAddress.setText(address+", "+num);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getIds() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        imgGoToAddress = findViewById(R.id.img_go_address);
        txtAddress = findViewById(R.id.txt_address);
        llGoAddress = findViewById(R.id.ll_go_address);
        id = getIntent().getStringExtra("id");
        lvBusiness = findViewById(R.id.lv_business);
        //lvVehicle = findViewById(R.id.lv_vehicles);
        btnRegisterCar = findViewById(R.id.btn_register_car);
        recyclerView = findViewById(R.id.recycler);
    }

    private void getModels(){
        client = new Client();
        vehicle = new Vehicle();
    }
}