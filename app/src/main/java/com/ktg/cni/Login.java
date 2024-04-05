package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Login extends AppCompatActivity {
String[] dataInsideSpinner={"Select Login Type","Admin","User"};
Spinner sputype;
EditText uname,password;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sputype=findViewById(R.id.sputype);
        uname=findViewById(R.id.phEt);
        password=findViewById(R.id.phpassword);
        databaseHelper = new DatabaseHelper(this);
        ArrayAdapter<String> spinnerAdapter= new ArrayAdapter<>(getApplicationContext(), R.layout.custom_spinner_dropdown_item, dataInsideSpinner); // here dataInsideSpinner is a List<>
        spinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        sputype.setAdapter(spinnerAdapter);
    }

    public void goto_main(View view) {
        String a=uname.getText().toString().trim();
        String b=password.getText().toString().trim();
        String c=sputype.getSelectedItem().toString().trim();
       if(c.equalsIgnoreCase("admin")){
           if(a.equalsIgnoreCase("admin")&&b.equalsIgnoreCase("admin")){
               Intent intent = new Intent(getApplicationContext(), Admin.class);
               startActivity(intent);
               finish();

           }else{
               Toast.makeText(getApplicationContext(),"Wrong Details",Toast.LENGTH_SHORT).show();
           }
       }
       else  if(c.equalsIgnoreCase("User")){
           String email = uname.getText().toString().trim();
           String pass = password.getText().toString().trim();

           // Get readable database
           SQLiteDatabase db = databaseHelper.getReadableDatabase();

           // Query to check if user exists
           Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME +
                   " WHERE " + DatabaseHelper.COLUMN_EMAIL + " = ? AND " +
                   DatabaseHelper.COLUMN_PASSWORD + " = ?", new String[]{email, pass});

           if (cursor.moveToFirst()) {
               @SuppressLint("Range") String userId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
               @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));

               Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getApplicationContext(), UserHome.class);
               intent.putExtra("userId", userId);
               intent.putExtra("userName", userName);
               startActivity(intent);
               finish();
           } else {
               // User does not exist or incorrect credentials
               Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
           }

           // Close cursor and database
           cursor.close();
           db.close();
       }
       else{
           Toast.makeText(getApplicationContext(),"Please Select Valid Type",Toast.LENGTH_SHORT).show();
       }

    }

    public void goto_signup(View view) {
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
        finish();
    }
}