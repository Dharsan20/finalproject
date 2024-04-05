package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.ktg.cni.Model.BillItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GenerateGraph extends AppCompatActivity {
    ImageView btnback;
    BarChart barChart;
    DatabaseHelper databaseHelper;
    TextView amount;
    Button Prediction;
    Spinner sputype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_graph);
        btnback = (ImageView) findViewById(R.id.imgViewBack);
        barChart = (BarChart)  findViewById(R.id.chart1);
        amount = (TextView)  findViewById(R.id.amount);
        Prediction = (Button)  findViewById(R.id.Prediction);
        databaseHelper = new DatabaseHelper(this);
        sputype=findViewById(R.id.sputype);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        populateSpinner();
        sputype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                // Convert the selected item to a string


                generateGraphAndDisplayAverageCost(sputype.getSelectedItem().toString());
                generateGraphAndDisplayAverageCost(sputype.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

    }

    private void populateSpinner() {
        List<String> wattValues = databaseHelper.getDistinctWattValues(); // Fetch distinct watt values from database
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, wattValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sputype.setAdapter(adapter);
    }

    private void generateGraphAndDisplayAverageCost(String selectedWattAsString) {
       // List<BillItem> billItems = databaseHelper.getBillItems();
        List<BillItem> billItems = databaseHelper.getBillItems(sputype.getSelectedItem().toString());
        double averageCost = databaseHelper.calculateAverageCost(sputype.getSelectedItem().toString());

        List<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < billItems.size(); i++) {
            BillItem item = billItems.get(i);
            entries.add(new BarEntry(i, (float) item.getCost()));
            labels.add(item.getName());
        }

        BarDataSet dataSet = new BarDataSet(entries, "Bill Items");
        dataSet.setColors(getRandomColors(billItems.size())); // Random colors for each item
        dataSet.setDrawValues(false); // Hide default values

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getDescription().setEnabled(false); // Hide chart description
        barChart.invalidate();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        amount.setText("Average Cost: " + decimalFormat.format(averageCost));
    }

    public class MyXAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {
        private List<BillItem> billItems;

        public MyXAxisValueFormatter(List<BillItem> billItems) {
            this.billItems = billItems;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int index = (int) value;
            if (index >= 0 && index < billItems.size()) {
                return billItems.get(index).getName();
            }
            return "";
        }
    }
    private ArrayList<Integer> getRandomColors(int count) {
        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            colors.add(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        }
        return colors;
    }
}