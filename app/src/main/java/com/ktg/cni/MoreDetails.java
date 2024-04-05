package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MoreDetails extends AppCompatActivity {
    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
        imgViewBack=findViewById(R.id.imgViewBack);
        imgViewBack.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    public void goto_view_profile(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewProfile.class);
        startActivity(intent);
    }
}