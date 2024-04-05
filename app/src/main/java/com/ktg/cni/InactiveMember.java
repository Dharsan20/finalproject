package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class InactiveMember extends AppCompatActivity {
    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inactive_member);
        imgViewBack=findViewById(R.id.imgViewBack);
        imgViewBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}