package com.app.clientevulcar.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.clientevulcar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.koushikdutta.ion.builder.Builders;

public class Requests extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public TextView txtServices;
    public ImageView imgLogoBusiness;
    public AppCompatButton btnAddBag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        getSupportActionBar().hide();
        getIds();
        bottomNavigationView.setSelectedItemId(R.id.requests);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.requests:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }

        });

        btnAddBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getIds(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        txtServices = findViewById(R.id.txt_services);
        imgLogoBusiness = findViewById(R.id.img_logo);
        btnAddBag = findViewById(R.id.btn_add_bag);
    }
}