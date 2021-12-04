package com.app.clientevulcar.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class Profile extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public TextView txtProfile;
    public String id;
    public RelativeLayout rlData;
    public RelativeLayout rlCars;
    public RelativeLayout rlLogout;

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
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();
        montaObj();

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        Intent intent_h = new Intent(Profile.this, Home.class);
                        intent_h.putExtra("id", id);
                        startActivity(intent_h);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.requests:
                        Intent intent_r = new Intent(Profile.this, Requests.class);
                        intent_r.putExtra("id", id);
                        startActivity(intent_r);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        Intent intent_s = new Intent(Profile.this, Search.class);
                        intent_s.putExtra("id", id);
                        startActivity(intent_s);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        rlData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itD = new Intent(Profile.this, MyData.class);
                startActivity(itD);
            }
        });

//        rlCars.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent itC = new Intent(Profile.this, MyCars.class);
//                startActivity(itC);
//            }
//        });

        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itL = new Intent(Profile.this, Login.class);
                startActivity(itL);
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
                    String nome = jsonarray.getString("CLIENTE_NOME");
                    txtProfile.setText(nome);
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
        txtProfile = findViewById(R.id.txt_name_profile);
        id = getIntent().getStringExtra("id");
        rlData = findViewById(R.id.rl_edit_data);
        rlCars = findViewById(R.id.rl_cars);
        rlLogout = findViewById(R.id.rl_logout);
    }
}