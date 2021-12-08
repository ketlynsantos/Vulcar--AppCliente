package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.clientevulcar.Adapter.AdapterServices;
import com.app.clientevulcar.Adapter.RecyclerAdapterVehicles;
import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.Model.Services;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Business extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtNameBusiness;
    public ListView lvServices;
    public String id, idBusiness;

    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/Business/";
    //String HOST = "http://192.168.0.106/vulcar_database/Business/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Business/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Activity context;

    com.app.clientevulcar.Model.Business business = new com.app.clientevulcar.Model.Business();
    com.app.clientevulcar.Model.Services services = new com.app.clientevulcar.Model.Services();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();
        context = Business.this;
        montaObj();
        carregarNome();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Business.this, Home.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

    }

    private void montaObj() {
        String url = HOST + "Select/select_services_where.php";

        services.setId_loja(idBusiness);
        params.put("id", services.getId_loja());

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
            //txtNameBusiness.setText(jsonarray.getJSONObject(0).getString("loja_nome"));
            for (int i = 0; i < jsonarray.length(); i++){
                Services s = new Services();

                s.setId(jsonarray.getJSONObject(i).getString("id_serv"));
                s.setNome(jsonarray.getJSONObject(i).getString("nome_serv"));
                s.setDesc(jsonarray.getJSONObject(i).getString("desc_serv"));
                s.setId_categoria(jsonarray.getJSONObject(i).getString("id_cate"));
                s.setCategoria(jsonarray.getJSONObject(i).getString("nome_cate"));
                s.setValor(jsonarray.getJSONObject(i).getString("valor"));

                lista.add(s);

            }

            AdapterServices adapter = new AdapterServices(context, R.layout.adapter_services, R.id.txt_id, lista);
            lvServices.setAdapter(adapter);
            lvServices.setClickable(true);
            lvServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                    Intent it = new Intent(Business.this, com.app.clientevulcar.Activitys.Services.class);
                    it.putExtra("idBusiness", idBusiness);
                    it.putExtra("id", id);
                    it.putExtra("idServ", lista.get(position).getId());
                    it.putExtra("nomeServ", lista.get(position).getNome());
                    it.putExtra("nomeLoja", txtNameBusiness.getText());
                    it.putExtra("descServ", lista.get(position).getDesc());
                    it.putExtra("idCate", lista.get(position).getId_categoria());
                    it.putExtra("nomeCate", lista.get(position).getCategoria());
                    it.putExtra("valor", lista.get(position).getValor());
                    startActivity(it);
                }
            });

        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void carregarNome() {
        String url = HOST + "Select/select_business.php";

        business.setId(idBusiness);
        params.put("id", business.getId());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonarray = new JSONObject(new String (responseBody));
                    String nameBusiness = jsonarray.getString("LOJA_NOME");

                    txtNameBusiness.setText(nameBusiness);
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
        id = getIntent().getStringExtra("id");
        idBusiness = getIntent().getStringExtra("idBusiness");

        imgBack = findViewById(R.id.img_back);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        lvServices = findViewById(R.id.lv_services);
    }
}