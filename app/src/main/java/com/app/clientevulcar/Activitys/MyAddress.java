package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyAddress extends AppCompatActivity {

    public ImageView imgBack;
    public AppCompatButton btnEdit;
    public TextView txtAddress;
    public TextView txtNeighborhood;
    public TextView txtCity;
    public TextView txtUF;
    public TextView txtCep;
    public String id;

    //Connection MySQL
    //String HOST = "http://192.168.15.122/Vulcar--Syncmysql/Client/";
    //String HOST = "http://172.20.10.5/vulcar_database/Client/";
    String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();

        montaObj();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, Home.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, EditAddress.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
    }

    private void montaObj() {
        String url = HOST + "Select/select_profile.php";

        client.setId(id);
        params.put("id", client.getId());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonarray = new JSONObject(new String (responseBody));
                    String address = jsonarray.getString("CLIENTE_ENDERECO");
                    String num = jsonarray.getString("CLIENTE_NUM");
                    String neighborhood = jsonarray.getString("CLIENTE_BAIRRO");
                    String city = jsonarray.getString("CLIENTE_CIDADE");
                    String uf = jsonarray.getString("CLIENTE_UF");
                    String cep = jsonarray.getString("CLIENTE_CEP");

                    txtAddress.setText(address+", "+num);
                    txtNeighborhood.setText(neighborhood);
                    txtCity.setText(city);
                    txtUF.setText(uf);
                    txtCep.setText(cep);

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

        imgBack = findViewById(R.id.img_back);
        btnEdit = findViewById(R.id.btn_edit);
        txtAddress = findViewById(R.id.txt_address);
        txtNeighborhood = findViewById(R.id.txt_neighborhood);
        txtCity = findViewById(R.id.txt_city);
        txtUF = findViewById(R.id.txt_uf);
        txtCep = findViewById(R.id.txt_cep);
    }
}