package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyVistorsReport extends AppCompatActivity {
    Button cniref,chref,chreff;
    LinearLayout l1,l2,l3;
    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vistors_report);
        imgViewBack=findViewById(R.id.imgViewBack);
        chreff=findViewById(R.id.chreff);
        cniref=findViewById(R.id.cniref);
        chref=findViewById(R.id.chref);
        l1=findViewById(R.id.line1);
        l2=findViewById(R.id.line2);
        l3=findViewById(R.id.line3);
        imgViewBack.setOnClickListener(v -> {
            onBackPressed();
        });  chref.setOnClickListener(v -> {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            l3.setVisibility(View.GONE);

            cniref.setBackgroundColor(Color.parseColor("#ffffff"));
            chreff.setBackgroundColor(Color.parseColor("#ffffff"));
            chref.setBackgroundColor(Color.parseColor("#1f000000"));
        });

        cniref.setOnClickListener(v -> {
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
            l3.setVisibility(View.GONE);
            chref.setBackgroundColor(Color.parseColor("#ffffff"));
            chreff.setBackgroundColor(Color.parseColor("#ffffff"));

            cniref.setBackgroundColor(Color.parseColor("#1f000000"));
        });

        chreff.setOnClickListener(v -> {
            l3.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.GONE);
            chref.setBackgroundColor(Color.parseColor("#ffffff"));
            chreff.setBackgroundColor(Color.parseColor("#1f000000"));
            cniref.setBackgroundColor(Color.parseColor("#ffffff"));
        });
    }
}