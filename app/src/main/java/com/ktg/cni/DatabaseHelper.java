package com.ktg.cni;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ktg.cni.Model.Appliance;
import com.ktg.cni.Model.BillItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EB_new4.db";
    private static final int DATABASE_VERSION = 3;

    // Table name
    public static final String TABLE_NAME = "users";
    public static final String TABLE_NAME2 = "appliances";
    public static final String TABLE_BILL = "bill";

    // Column names for users table
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_PASSWORD = "password";

    // Column names for appliances table
    public static final String COLUMN_AID = "aid";
    public static final String COLUMN_APPLIANCE_NAME = "appliance_name";
    public static final String COLUMN_AMPS = "amps";
    public static final String COLUMN_VOLTS = "volts";

    // Column names for bill table
    public static final String COLUMN_IDs = "_id";
    public static final String COLUMN_NAMEs = "name";
    public static final String COLUMN_VOLTSs = "volts";
    public static final String COLUMN_AMPSs = "amps";
    public static final String COLUMN_WATTS = "watts";
    public static final String COLUMN_KILOWATT = "kilowatt";
    public static final String COLUMN_COST = "cost";

    // Create table SQL query for users
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_MOBILE + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT"
                    + ")";

    // Create table SQL query for appliances
    private static final String CREATE_TABLE_APPLIANCES =
            "CREATE TABLE " + TABLE_NAME2 + "("
                    + COLUMN_AID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_APPLIANCE_NAME + " TEXT,"
                    + COLUMN_AMPS + " REAL,"
                    + COLUMN_VOLTS + " INTEGER"
                    + ")";

    // Create table SQL query for bill
    private static final String CREATE_TABLE_BILL = "CREATE TABLE "
            + TABLE_BILL + "(" + COLUMN_IDs
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAMEs
            + " TEXT NOT NULL, " + COLUMN_VOLTSs
            + " INTEGER NOT NULL, " + COLUMN_AMPSs
            + " REAL NOT NULL, " + COLUMN_WATTS
            + " TEXT NOT NULL, " + COLUMN_KILOWATT
            + " TEXT NOT NULL, " + COLUMN_COST
            + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_APPLIANCES);
        db.execSQL(CREATE_TABLE_BILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);

        // Create tables again
        onCreate(db);
    }

    public long insertAppliance(String name, double amps, int volts) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the appliance name already exists
        if (applianceNameExists(name)) {
            // Appliance name already exists, handle the situation accordingly
            db.close();
            return -1; // Return -1 to indicate failure
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_APPLIANCE_NAME, name);
        values.put(COLUMN_AMPS, amps);
        values.put(COLUMN_VOLTS, volts);
        long id = db.insert(TABLE_NAME2, null, values);
        db.close();
        return id;
    }

    public boolean applianceNameExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + COLUMN_APPLIANCE_NAME + "=?", new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void deleteAppliance(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, COLUMN_AID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Appliance> getAllAppliances() {
        List<Appliance> applianceList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_AID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_APPLIANCE_NAME));
                double amps = cursor.getDouble(cursor.getColumnIndex(COLUMN_AMPS));
                int volts = cursor.getInt(cursor.getColumnIndex(COLUMN_VOLTS));

                Appliance appliance = new Appliance(id, name, amps, volts);
                applianceList.add(appliance);
            } while (cursor.moveToNext());
        }

        // Close cursor and database
        cursor.close();
        db.close();

        return applianceList;
    }

    public long insertBill(String name, String volts, String amps, String watts, String kilowatt, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMEs, name);
        values.put(COLUMN_VOLTSs, volts);
        values.put(COLUMN_AMPSs, amps);
        values.put(COLUMN_WATTS, watts);
        values.put(COLUMN_KILOWATT, kilowatt);
        values.put(COLUMN_COST, cost);

        long id = db.insert(TABLE_BILL, null, values);
        db.close();
        return id;
    }
    public List<BillItem> getBillItems() {
        List<BillItem> billItems = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_NAMEs + ", " + COLUMN_COST + " FROM " + TABLE_BILL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAMEs));
                @SuppressLint("Range") String cost = cursor.getString(cursor.getColumnIndex(COLUMN_COST));
                billItems.add(new BillItem(name, Double.parseDouble(cost)));
            } while (cursor.moveToNext());
        }

        // Close cursor and database
        cursor.close();
        db.close();

        return billItems;
    }public List<BillItem> getBillItems(String watts) {
        List<BillItem> billItems = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_NAMEs + ", " + COLUMN_COST +
                " FROM " + TABLE_BILL +
                " WHERE " + COLUMN_WATTS + " = ?";
        Log.e("selectQuery",selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(watts)});

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAMEs));
                @SuppressLint("Range") String cost = cursor.getString(cursor.getColumnIndex(COLUMN_COST));
                billItems.add(new BillItem(name, Double.parseDouble(cost)));
            } while (cursor.moveToNext());
        }

        // Close cursor and database
        cursor.close();
        db.close();

        return billItems;
    }
    @SuppressLint("Range")
    public double calculateAverageCost() {
        List<BillItem> billItems = getBillItems();
        double totalCost = 0;

        // Calculate total cost
        for (BillItem item : billItems) {
            totalCost += item.getCost();
        }

        // Calculate average cost
        if (billItems.size() > 0) {
            return totalCost / billItems.size();
        } else {
            return 0; // Avoid division by zero
        }
    }
    @SuppressLint("Range")
    public double calculateAverageCost(String dates) {
        List<BillItem> billItems = getBillItems(dates);
        double totalCost = 0;

        // Calculate total cost
        for (BillItem item : billItems) {
            totalCost += item.getCost();
        }

        // Calculate average cost
        if (billItems.size() > 0) {
            return totalCost / billItems.size();
        } else {
            return 0; // Avoid division by zero
        }
    }
    public List<String> getDistinctWattValues() {
        List<String> wattValues = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get distinct values of "watts" column
        String selectQuery = "SELECT DISTINCT " + COLUMN_WATTS + " FROM " + TABLE_BILL;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through the cursor and add distinct values to the list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String watt = cursor.getString(cursor.getColumnIndex(COLUMN_WATTS));
                wattValues.add(watt);
            } while (cursor.moveToNext());
        }

        // Close cursor and database
        cursor.close();
        db.close();

        return wattValues;
    }
}
