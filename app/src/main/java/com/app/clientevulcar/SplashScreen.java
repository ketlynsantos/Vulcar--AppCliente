package com.app.clientevulcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity implements Runnable{

    public Thread thread;
    public Handler handler;
    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Remove a status bar da tela
        getSupportActionBar().hide();

        handler = new Handler();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        i = 1;
        try {
            //Duração definida pelo while
            while(i < 100) {
                Thread.sleep(15);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                    }
                });
            }
        } catch (Exception e){

        }

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}