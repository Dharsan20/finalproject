package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ktg.cni.Adapter.Appliance2Adapter;
import com.ktg.cni.Adapter.ApplianceAdapter;
import com.ktg.cni.Model.Appliance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserHome extends AppCompatActivity {
    String userId,userName;
    TextView ename;
    Appliance2Adapter adapter;
    private List<Appliance> applianceList=new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    Button bottomButton,calculateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        ename=findViewById(R.id.ename);
        bottomButton=findViewById(R.id.generateGraphButton);
        calculateButton=findViewById(R.id.calculateButton);
        Intent intent = getIntent();
        if (intent != null) {
            String userId = intent.getStringExtra("userId");
            String userName = intent.getStringExtra("userName");
            ename.setText(userName);
            // Use userId and userName as needed
        }
        recyclerView = findViewById(R.id.recyclerView);
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), GenerateGraph.class);
                startActivity(intent);

            }
        });
        calculateButton.setOnClickListener(v -> {
            for (int i = 0; i < adapter.getItemCount(); i++) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
                if (viewHolder instanceof Appliance2Adapter.MyViewHolder) {
                    Appliance2Adapter.MyViewHolder holder = (Appliance2Adapter.MyViewHolder) viewHolder;
                      String pattern = "dd-MM-yyyy";
                    String dateInString =new SimpleDateFormat(pattern).format(new Date());
                    // Retrieve relevant data from the current item
                    String name = holder.applianceNameTextView.getText().toString();

                    int id = (int) applianceList.get(i).getId();
                    int volts = applianceList.get(i).getVolts();
                    int amps = (int) applianceList.get(i).getAmps();
                    String countdown = holder.textCountdown.getText().toString();
                    if(!countdown.equalsIgnoreCase("")){
                        Log.e("name", name);
                        Log.e("id", id+"");
                        Log.e("volts", volts+"");
                        Log.e("amps", amps+"");
                        Log.e("countdown", countdown);
                       String Watts = Double.parseDouble(String.valueOf(volts)) * Double.parseDouble(String.valueOf(amps)) + "";
                        String Kilowatt = Double.parseDouble(Watts) * Double.parseDouble(countdown) + "";
                        String Cost = Double.parseDouble(Kilowatt) * 0.27 + "";
                        Log.e("result","Watts:" + Watts + "\n" + "Kilowatt:" + Kilowatt + "\n" + "Cost:" + Cost + "\n");
                        long ida = databaseHelper.insertBill(name,volts+"",amps+"",dateInString,Kilowatt,Cost);
                        if (ida != -1) {
                            Toast.makeText(getApplicationContext(), "successfully Saved: "+name, Toast.LENGTH_SHORT).show();

                        } else {
                            // Insertion failed
                            Toast.makeText(getApplicationContext(), "failed to Saved", Toast.LENGTH_SHORT).show();

                        }
                    }
                    // Send the data to the desired activity



                }
            }
        });
        databaseHelper = new DatabaseHelper(this);
        applianceList = databaseHelper.getAllAppliances();
        if(applianceList.size()>0) {
            Log.e("appliance", applianceList.toString());
            adapter = new Appliance2Adapter(applianceList, getApplicationContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver1,
                new IntentFilter("View_details"));
    }
    private BroadcastReceiver mMessageReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String id = intent.getStringExtra("id");
            String Amps = intent.getStringExtra("Amps");
            String Volts = intent.getStringExtra("Volts");
            String name = intent.getStringExtra("name");
            intent = new Intent(getApplicationContext(), BillCalculator.class);
            intent.putExtra("name", name);
            intent.putExtra("id", id);
            intent.putExtra("Volts", Volts);
            intent.putExtra("Amps", Amps);
            startActivity(intent);

        }
    };
    public void goto_admin(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }
}