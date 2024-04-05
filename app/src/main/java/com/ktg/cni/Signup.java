package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText nameEditText, emailEditText, mobileEditText, passwordEditText;
    Button saveButton;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        mobileEditText = findViewById(R.id.mob);
        passwordEditText = findViewById(R.id.password);
        saveButton = findViewById(R.id.save);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
    }

    private void saveUserData() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (name.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Prepare data to insert
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_MOBILE, mobile);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        try {
            // Insert data into the database
            long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);
            if (newRowId != -1) {
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {

            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    public void goto_login(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }
}