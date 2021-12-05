package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.clientevulcar.R;

public class MyData extends AppCompatActivity {

    public ImageView imgBack;
    public RelativeLayout rlPersonData;
    public RelativeLayout rlContact;
    public RelativeLayout rlPassword;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(MyData.this, Profile.class);
                itI.putExtra("id", id);
                startActivity(itI);
            }
        });

        rlPersonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itD = new Intent(MyData.this, EditPersonData.class);
                itD.putExtra("id", id);
                startActivity(itD);
            }
        });

        rlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itC = new Intent(MyData.this, EditContactData.class);
                itC.putExtra("id", id);
                startActivity(itC);
            }
        });

        rlPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itP = new Intent(MyData.this, EditPassword.class);
                itP.putExtra("id", id);
                startActivity(itP);
            }
        });

    }

    private void getIds() {
        id = getIntent().getStringExtra("id");

        imgBack = findViewById(R.id.img_back);
        rlPersonData = findViewById(R.id.rl_person_data);
        rlContact = findViewById(R.id.rl_data_contact);
        rlPassword = findViewById(R.id.rl_alter_password);
    }
}