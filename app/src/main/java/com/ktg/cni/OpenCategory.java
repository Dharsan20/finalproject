package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class OpenCategory extends AppCompatActivity {
    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_category);
        imgViewBack=findViewById(R.id.imgViewBack);

        imgViewBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}