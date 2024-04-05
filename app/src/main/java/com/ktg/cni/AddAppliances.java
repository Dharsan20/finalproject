package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAppliances extends AppCompatActivity {
    EditText applianceEditText, ampsEditText, voltsEditText;
    Button saveButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appliances);
        applianceEditText = findViewById(R.id.applience);
        ampsEditText = findViewById(R.id.amps);
        voltsEditText = findViewById(R.id.volts);
        saveButton = findViewById(R.id.save);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAppliance();
            }
        });
    }
    private void saveAppliance() {
        // Get user input values
        String name = applianceEditText.getText().toString().trim();
        String ampsText = ampsEditText.getText().toString().trim();
        String voltsText = voltsEditText.getText().toString().trim();

        // Check if any field is empty
        if (name.isEmpty() || ampsText.isEmpty() || voltsText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert amps and volts to appropriate data types
        double amps = Double.parseDouble(ampsText);
        int volts = Integer.parseInt(voltsText);

        // Check if the appliance name already exists in the database
        if (databaseHelper.applianceNameExists(name)) {
            Toast.makeText(this, "Appliance name already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert appliance into database
        long id = databaseHelper.insertAppliance(name, amps, volts);

        if (id != -1) {
            Toast.makeText(this, "Appliance saved successfully", Toast.LENGTH_SHORT).show();
            onBackPressed();
            finish();
        } else {
            Toast.makeText(this, "Failed to save appliance", Toast.LENGTH_SHORT).show();
        }
    }
    public void goto_admin(View view) {
       onBackPressed();
       finish();
    }
}