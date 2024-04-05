package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ktg.cni.Adapter.ApplianceAdapter;
import com.ktg.cni.Model.Appliance;

import java.util.ArrayList;
import java.util.List;

public class ViewDetails extends AppCompatActivity {
    ApplianceAdapter adapter;
    private List<Appliance> applianceList=new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        recyclerView = findViewById(R.id.recyclerView);

        databaseHelper = new DatabaseHelper(this);
        applianceList = databaseHelper.getAllAppliances();
        if(applianceList.size()>0) {
            Log.e("appliance", applianceList.toString());
            adapter = new ApplianceAdapter(applianceList, getApplicationContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

    public void goto_admin(View view) {
        onBackPressed();
        finish();
    }
}