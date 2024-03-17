package com.ragu.ebmontoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {


                try {

                    checkUpdate();




                    // checkPermissions();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

            }
        }, 2000);
    }

    private void checkUpdate() {
    }
}