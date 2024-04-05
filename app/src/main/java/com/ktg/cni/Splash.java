package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {


                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();



                    // checkPermissions();

            }
        }, 2000);

    }
}