package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class BillCalculator extends AppCompatActivity {
    private AppCompatButton switchCountdown, calculate, save;
    private TextView name, result;
    EditText textCountdown;
    String isChecked = "on";
    String id, nm, Volts, Amps, Watts, Kilowatt, Cost;
    Timer timer;
    int secondsRemaining = 0;
    DatabaseHelper databaseHelper;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_calculator);
        switchCountdown = findViewById(R.id.switchCountdown);
        calculate = findViewById(R.id.calculate);
        btnback = (ImageView) findViewById(R.id.imgViewBack);
        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        result = findViewById(R.id.result);
        textCountdown = findViewById(R.id.textCountdown);
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        if (intent != null) {
            nm = intent.getStringExtra("name");
            id = intent.getStringExtra("id");
            Volts = intent.getStringExtra("Volts");
            Amps = intent.getStringExtra("Amps");
            name.setText(nm);
            // Use userId and userName as needed
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "please calculate before Saving", Toast.LENGTH_SHORT).show();
                }
                else {
                    //    String id, nm, Volts, Amps, Watts, Kilowatt, Cost;
                    long id = databaseHelper.insertBill(name.getText().toString(),Volts,Amps,Watts,Kilowatt,Cost);
                    if (id != -1) {
                        Toast.makeText(getApplicationContext(), "successfully Saved", Toast.LENGTH_SHORT).show();

                    } else {
                        // Insertion failed
                        Toast.makeText(getApplicationContext(), "failed to Saved", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textCountdown.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Not any Switch on", Toast.LENGTH_SHORT).show();

                }
                else{
                    Watts = Double.parseDouble(Volts) * Double.parseDouble(Amps) + "";
                    Kilowatt = Double.parseDouble(Watts) * Double.parseDouble(textCountdown.getText().toString().trim()) + "";
                    Cost = Double.parseDouble(Kilowatt) * 0.2 + "";
                    result.setText("Watts:" + Watts + "\n" + "Kilowatt:" + Kilowatt + "\n" + "Cost:" + Cost + "\n");
                    pausetimer();
                }

            }
        });
        switchCountdown.setOnClickListener(v -> {
            if (isChecked.equalsIgnoreCase("on")) {
                switchCountdown.setText("off");
                isChecked = "off";
                startCountdown();
            } else {
                switchCountdown.setText("on");
                isChecked = "on";
                pausetimer();
            }
        });
    }

    private void pausetimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void startCountdown() {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    secondsRemaining++;
                    updateTimerTextView(secondsRemaining);
                }
            }, 1000, 1000); // Start the timer with 1 second delay, repeat every 1 second
        }
    }

    private void updateTimerTextView(int seconds) {
        runOnUiThread(() -> textCountdown.setText(String.valueOf(seconds)));
    }
}
