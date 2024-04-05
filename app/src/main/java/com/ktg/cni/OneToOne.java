package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class OneToOne extends AppCompatActivity {
    ImageView imgViewBack;
    ImageView imgreport,dtime,ddate;
    private int mYear, mMonth, mDay, mHour, mMinute, mSec;
    TextView ndate, ntime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_one);
        imgViewBack=findViewById(R.id.imgViewBack);
        imgreport=findViewById(R.id.imgreport);
        dtime=findViewById(R.id.dtime);
        ddate=findViewById(R.id.ddate);
        ndate = findViewById(R.id.nextmeet);
        ntime = findViewById(R.id.ntime);
        imgViewBack.setOnClickListener(v -> {
            onBackPressed();
        });imgreport.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), OnetoOneReport.class);
            startActivity(intent);
        });
        ndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(OneToOne.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                ndate.setText(String.format("%02d", dayOfMonth)+ "-" +  String.format("%02d", (monthOfYear + 1))  + "-" +  year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        ntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mSec = c.get(Calendar.SECOND);
                TimePickerDialog timePickerDialog = new TimePickerDialog(OneToOne.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                ntime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", mSec));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });
        ddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(OneToOne.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                ndate.setText(String.format("%02d", dayOfMonth)+ "-" +  String.format("%02d", (monthOfYear + 1))  + "-" +  year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        dtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mSec = c.get(Calendar.SECOND);
                TimePickerDialog timePickerDialog = new TimePickerDialog(OneToOne.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                ntime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", mSec));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });
    }
}