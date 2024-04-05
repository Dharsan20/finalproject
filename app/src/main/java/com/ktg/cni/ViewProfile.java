package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ViewProfile extends AppCompatActivity {
    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        imgViewBack=findViewById(R.id.imgViewBack);
        imgViewBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    public void goto_edit_profile(View view) {
        Intent intent = new Intent(getApplicationContext(), EditProfile.class);
        startActivity(intent);
    }
}