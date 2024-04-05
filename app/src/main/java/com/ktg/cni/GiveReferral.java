package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GiveReferral extends AppCompatActivity {
    int b=2;
    Button cniref,chref;
    LinearLayout l1,l2;
    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_referral);
        imgViewBack=findViewById(R.id.imgViewBack);
        cniref=findViewById(R.id.cniref);
        chref=findViewById(R.id.chref);
        l1=findViewById(R.id.line1);
        l2=findViewById(R.id.line2);
        imgViewBack.setOnClickListener(v -> {
           onBackPressed();
        });  chref.setOnClickListener(v -> {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            cniref.setBackgroundColor(Color.parseColor("#ffffff"));
            chref.setBackgroundColor(Color.parseColor("#1f000000"));
        });

        cniref.setOnClickListener(v -> {
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
            chref.setBackgroundColor(Color.parseColor("#ffffff"));
            cniref.setBackgroundColor(Color.parseColor("#1f000000"));
        });
    }

    public void goto_main(View view) {
        Intent intent = new Intent(getApplicationContext(), GiveReferralDetails.class);
        startActivity(intent);
    }
}