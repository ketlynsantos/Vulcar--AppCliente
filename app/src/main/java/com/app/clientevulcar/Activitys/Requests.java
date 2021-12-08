package com.app.clientevulcar.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.clientevulcar.Adapter.AdapterLojas;
import com.app.clientevulcar.Adapter.AdapterRequests;
import com.app.clientevulcar.Adapter.AdapterServices;
import com.app.clientevulcar.Adapter.RecyclerAdapterVehicles;
import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.Model.ItemsBudget;
import com.app.clientevulcar.Model.Services;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.koushikdutta.ion.builder.Builders;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Requests extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public TextView txtServices, txtName;
    public ImageView imgLogoBusiness;
    public AppCompatButton btnAddBag;
    public ListView lvRequests;
    public RelativeLayout rLayout2;
    public String id;

    public String idOrc, nomeLoja, nomeServ, idStatus;

    public Activity context;

    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/Client/";
    //String HOST = "http://192.168.0.106/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Services services = new Services();
    ItemsBudget ibudget = new ItemsBudget();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        getSupportActionBar().hide();
        getIds();

        cliente = new AsyncHttpClient();
        context = Requests.this;

        carregarServices();
        montaObj();

        bottomNavigationView.setSelectedItemId(R.id.requests);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.requests:
                        return true;
                    case R.id.home:
                        Intent intent_h = new Intent(Requests.this, Home.class);
                        intent_h.putExtra("id", id);
                        startActivity(intent_h);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        Intent intent_s = new Intent(Requests.this, Search.class);
                        intent_s.putExtra("id", id);
                        startActivity(intent_s);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent intent_p = new Intent(Requests.this, Profile.class);
                        intent_p.putExtra("id", id);
                        startActivity(intent_p);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });

        btnAddBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Requests.this, FollowUpService.class);
                intent.putExtra("id", id);
                intent.putExtra("idOrc", idOrc);
                startActivity(intent);
            }
        });
    }

    private void montaObj() {
        String url = HOST + "Select/select_itens_orc.php";

        ibudget.setIdCliente(id);
        params.put("id", ibudget.getIdCliente());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonarray = new JSONObject(new String (responseBody));
                    for (int i = 0; i < jsonarray.length(); i++) {
                        ItemsBudget b = new ItemsBudget();

                        idOrc = jsonarray.getString("ID_ORC");
                        nomeLoja = jsonarray.getString("TB_LOJA_NOME");
                        nomeServ = jsonarray.getString("SERVICO");
                        idStatus = jsonarray.getString("ID_STATUS");

                        txtName.setText(nomeLoja);
                        txtServices.setText(nomeServ);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void carregarServices() {
        String url = HOST + "Select/select_service_completed.php";
        services.setId_cliente(id);
        params.put("id", services.getId_cliente());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarServices(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarServices(String resposta) {
        final ArrayList<Services> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                Services s = new Services();

                s.setId(jsonarray.getJSONObject(i).getString("id_serv"));
                s.setNome(jsonarray.getJSONObject(i).getString("nome_serv"));
                s.setDesc(jsonarray.getJSONObject(i).getString("desc_serv"));
                s.setId_loja(jsonarray.getJSONObject(i).getString("id_loja"));
                s.setNome_loja(jsonarray.getJSONObject(i).getString("nome_loja"));
                s.setId_categoria(jsonarray.getJSONObject(i).getString("id_cat"));
                s.setCategoria(jsonarray.getJSONObject(i).getString("nome_cat"));

                lista.add(s);

            }

            AdapterRequests adapter = new AdapterRequests(context, R.layout.adapter_historic_requests, R.id.txt_id, lista);
            lvRequests.setAdapter(adapter);

        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void getIds(){
        id = getIntent().getStringExtra("id");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        txtName = findViewById(R.id.txt_name_loja);
        txtServices = findViewById(R.id.txt_services);
        imgLogoBusiness = findViewById(R.id.img_logo);
        btnAddBag = findViewById(R.id.btn_add_bag);
        lvRequests = findViewById(R.id.lv_historic_requests);
        rLayout2 = findViewById(R.id.relative_layout_2);
    }
}