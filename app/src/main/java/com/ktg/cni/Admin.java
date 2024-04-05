package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {
    AppCompatButton add,viewdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        add=findViewById(R.id.first);
        viewdetail=findViewById(R.id.overall);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAppliances.class);
                startActivity(intent);

            }
        });
        viewdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewDetails.class);
                startActivity(intent);

            }
        });
    }

    public void goto_login(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();

    }
}