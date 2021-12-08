package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.clientevulcar.Model.Budget;
import com.app.clientevulcar.Model.Business;
import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.Model.ItemsBudget;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FollowUpService extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtStatus;
    public TextView txtNameEmployee;
    public TextView txtNameBusiness;
    public TextView txtNameServices;
    public TextView txtPrice;
    public TextView txtVehicle;

    public String id, idBusiness, idServ, idVehicle, idCate, itensId, nomeLoja, nomeServ, nomeAuto,
            descServ, nomeCate, valor, idPag, thing, nomeStatus, nomeFunc, idStatus;

    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/";
    //String HOST = "http://192.168.0.106/vulcar_database/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;

    ItemsBudget ibudget = new ItemsBudget();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_service);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();

        montaObj();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FollowUpService.this, Home.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

    }

    private void montaObj() {
        String url = HOST + "Client/Select/select_itens_orc.php";

        ibudget.setIdCliente(id);
        params.put("id", ibudget.getIdCliente());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonarray = new JSONObject(new String (responseBody));
                    for (int i = 0; i < jsonarray.length(); i++) {
                        ItemsBudget b = new ItemsBudget();

                        nomeLoja = jsonarray.getString("TB_LOJA_NOME");
                        nomeFunc = jsonarray.getString("FUNCIONARIO");
                        nomeServ = jsonarray.getString("SERVICO");
                        nomeAuto = jsonarray.getString("AUTOMOVEL");
                        valor = jsonarray.getString("VALOR");
                        nomeStatus = jsonarray.getString("STATUS");

                        txtNameBusiness.setText(nomeLoja);
                        txtNameEmployee.setText(nomeFunc);
                        txtNameServices.setText(nomeServ);
                        txtStatus.setText(nomeStatus);
                        txtPrice.setText("R$" + valor);
                        txtVehicle.setText(nomeAuto);
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

    private void getIds() {
        id = getIntent().getStringExtra("id");
        idBusiness = getIntent().getStringExtra("idBusiness");
        idServ = getIntent().getStringExtra("idServ");
        idVehicle = getIntent().getStringExtra("idVehicle");
        idCate = getIntent().getStringExtra("idCate");
        itensId = getIntent().getStringExtra("itensId");
        nomeLoja = getIntent().getStringExtra("nomeLoja");
        nomeServ = getIntent().getStringExtra("nomeServ");
        nomeAuto = getIntent().getStringExtra("nomeAuto");
        descServ = getIntent().getStringExtra("descServ");
        nomeCate = getIntent().getStringExtra("nomeCate");
        valor = getIntent().getStringExtra("valor");
        idPag = getIntent().getStringExtra("idPag");
        thing = getIntent().getStringExtra("thing");

        imgBack = findViewById(R.id.img_back);
        txtStatus = findViewById(R.id.txt_status_request);
        txtNameEmployee = findViewById(R.id.txt_name_employee);
        txtNameBusiness = findViewById(R.id.txt_name);
        txtNameServices = findViewById(R.id.txt_services);
        txtPrice = findViewById(R.id.txt_price);
        txtVehicle = findViewById(R.id.txt_vehicle);
    }
}