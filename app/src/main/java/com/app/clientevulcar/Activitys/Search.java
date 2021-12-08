package com.app.clientevulcar.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.app.clientevulcar.Adapter.AdapterSearchBusiness;
import com.app.clientevulcar.Adapter.AdapterSearchServices;
import com.app.clientevulcar.Model.Services;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.app.clientevulcar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Search extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public TextInputEditText edtSearch;
    public String id;
    public ListView lvServices, lvBusiness;
    AppCompatButton btnBusiness, btnServices;
    ImageButton btnSearch;

    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/Client/";
    //String HOST = "http://192.168.0.106/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;

    Activity context;

    com.app.clientevulcar.Model.Business business = new com.app.clientevulcar.Model.Business();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cliente = new AsyncHttpClient();
        context = Search.this;

        getIds();
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.search:
                        return true;
                    case R.id.home:
                        Intent intent_h = new Intent(Search.this, Home.class);
                        intent_h.putExtra("id", id);
                        startActivity(intent_h);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.requests:
                        Intent intent_r = new Intent(Search.this, Requests.class);
                        intent_r.putExtra("id", id);
                        startActivity(intent_r);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent intent_p = new Intent(Search.this, Profile.class);
                        intent_p.putExtra("id", id);
                        startActivity(intent_p);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvBusiness.setVisibility(View.VISIBLE);
                btnBusiness.setTextColor(getResources().getColor(R.color.clear_blue));
                btnServices.setTextColor(getResources().getColor(R.color.white));
                lvServices.setVisibility(View.GONE);
            }
        });

        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvBusiness.setVisibility(View.GONE);
                btnBusiness.setTextColor(getResources().getColor(R.color.white));
                btnServices.setTextColor(getResources().getColor(R.color.clear_blue));
                lvServices.setVisibility(View.VISIBLE);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvBusiness.getVisibility() == View.VISIBLE && lvServices.getVisibility() == View.GONE){
                    montaObjBusiness();
                } else {
                    montaObjServices();
                }
            }
        });
    }

    private void montaObjBusiness() {
        String url = HOST + "Select/select_search_business.php";
        String nome = edtSearch.getText().toString();
        params.put("name", nome);

        cliente.post(url, params, new AsyncHttpResponseHandler() {
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
        final ArrayList<com.app.clientevulcar.Model.Business> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                com.app.clientevulcar.Model.Business b = new com.app.clientevulcar.Model.Business();

                b.setId(jsonarray.getJSONObject(i).getString("TB_LOJA_ID"));
                b.setNome(jsonarray.getJSONObject(i).getString("TB_LOJA_NOME"));
                //b.setImg(jsonarray.getJSONObject(i).getString("TB_LOJA_IMG"));

                lista.add(b);

            }

            AdapterSearchBusiness adapter = new AdapterSearchBusiness(context, R.layout.adapter_lojas, R.id.txt_id, lista);
            lvBusiness.setAdapter(adapter);

            lvBusiness.setClickable(true);
            lvBusiness.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                    Intent it = new Intent(Search.this, com.app.clientevulcar.Activitys.Business.class);
                    it.putExtra("idBusiness", lista.get(position).getId());
                    it.putExtra("id", id);
                    startActivity(it);
                }
            });


        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void montaObjServices() {
        String url = HOST + "Select/select_search_service.php";
        String nome = edtSearch.getText().toString();
        params.put("name", nome);

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarServicos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarServicos(String resposta) {
        final ArrayList<Services> lista = new ArrayList<>();

        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++) {
                com.app.clientevulcar.Model.Services s = new com.app.clientevulcar.Model.Services();

                s.setId(jsonarray.getJSONObject(i).getString("id"));
                s.setNome(jsonarray.getJSONObject(i).getString("nome"));
                s.setDesc(jsonarray.getJSONObject(i).getString("desc"));
                s.setId_loja(jsonarray.getJSONObject(i).getString("id_loja"));
                s.setNome_loja(jsonarray.getJSONObject(i).getString("nome_loja"));
                s.setValor(jsonarray.getJSONObject(i).getString("valor"));
                s.setId_categoria(jsonarray.getJSONObject(i).getString("id_categoria"));
                s.setCategoria(jsonarray.getJSONObject(i).getString("nome_categoria"));

                lista.add(s);

            }

            AdapterSearchServices adapter = new AdapterSearchServices(context, R.layout.adapter_search_services, R.id.txt_id, lista);
            lvServices.setAdapter(adapter);

            lvServices.setClickable(true);
            lvServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                    Intent it = new Intent(Search.this, com.app.clientevulcar.Activitys.Business.class);
                    //it.putExtra("idBusiness", lista.get(position).getId());
                    it.putExtra("id", id);
                    startActivity(it);
                }
            });
        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void getIds() {
        id = getIntent().getStringExtra("id");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        lvServices = findViewById(R.id.lv_services);
        lvBusiness = findViewById(R.id.lv_business);
        btnBusiness = findViewById(R.id.btn_business);
        btnServices = findViewById(R.id.btn_services);
        edtSearch = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btn_search);
    }
}