package com.app.clientevulcar.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Home extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public ImageView imgGoToAddress;
    public TextView txtAddress;
    public LinearLayout llGoAddress;
    String id;

    //Connection MySQL
    //String HOST = "http://192.168.15.126/vulcar_database/Client/";
    String HOST = "http://172.20.10.5/vulcar_database/Client/";
    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Client client = new Client();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();

        montaObj();
        id = getIntent().getStringExtra("id");
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

    }
}