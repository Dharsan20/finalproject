package com.ktg.cni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ktg.cni.Model.Appliance;
import com.ktg.cni.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appliance2Adapter extends RecyclerView.Adapter<Appliance2Adapter.MyViewHolder> {

    private List<Appliance> applianceList;
    private Context context;
    private Map<Integer, Integer> timerCounts;
    private Map<Integer, Long> lastUpdatedTimes;
    private Handler handler;

    public Appliance2Adapter(List<Appliance> applianceList, Context context) {
        this.applianceList = applianceList;
        this.context = context;
        timerCounts = new HashMap<>();
        lastUpdatedTimes = new HashMap<>();
        handler = new Handler();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_two, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Appliance appliance = applianceList.get(position);
        Log.e("ApplianceAdapter", "Binding item at position: " + position);
        Log.e("ApplianceAdapter", "Appliance details: " + appliance.toString());

        holder.applianceNameTextView.setText(appliance.getName());
        holder.ampsTextView.setText("Amps: " + appliance.getAmps());
        holder.voltsTextView.setText("Volts: " + appliance.getVolts());

        // Set OnClickListener for the delete button
        holder.deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent("View_details");
            intent.putExtra("name", appliance.getName());
            intent.putExtra("id", String.valueOf(appliance.getId()));
            intent.putExtra("Volts", String.valueOf(appliance.getVolts()));
            intent.putExtra("Amps", String.valueOf(appliance.getAmps()));
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        });

        // Set OnClickListener for the "On" button
        holder.onButton.setOnClickListener(v -> {
            // Start timer for this specific position
            startTimerForPosition(position, holder);
        });

        // Set OnClickListener for the "Off" button
        holder.OffButton.setOnClickListener(v -> {
            // Pause timer for this specific position
            pauseTimerForPosition(position, holder);
        });
    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    private void startTimerForPosition(int position, MyViewHolder holder) {
        if (!timerCounts.containsKey(position)) {
            timerCounts.put(position, 0);
        }

        long lastUpdatedTime = System.currentTimeMillis();
        lastUpdatedTimes.put(position, lastUpdatedTime);
        holder.startTimer(lastUpdatedTime);
    }

    private void pauseTimerForPosition(int position, MyViewHolder holder) {
        holder.pauseTimer();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView applianceNameTextView;
        public TextView ampsTextView;
        public TextView voltsTextView;
        public Button deleteButton, OffButton, onButton;
        public EditText textCountdown;
        private Handler handler;
        private int timerCount = 0;
        private boolean timerRunning = false;
        private long lastUpdatedTime = 0;

        public MyViewHolder(View itemView) {
            super(itemView);
            applianceNameTextView = itemView.findViewById(R.id.applianceNameTextView);
            ampsTextView = itemView.findViewById(R.id.ampsTextView);
            voltsTextView = itemView.findViewById(R.id.voltsTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            onButton = itemView.findViewById(R.id.onButton);
            OffButton = itemView.findViewById(R.id.OffButton);
            textCountdown = itemView.findViewById(R.id.textCountdown);
            handler = new Handler();
        }

        // Method to start the timer
        public void startTimer(long lastUpdatedTime) {
            this.lastUpdatedTime = lastUpdatedTime;
            timerRunning = true;
            handler.postDelayed(timerRunnable, 1000);
        }

        // Method to pause the timer
        public void pauseTimer() {
            timerRunning = false;
            handler.removeCallbacks(timerRunnable);
        }

        // Method to get the timer count
        public int getTimerCount() {
            return timerCount;
        }

        // Method to increment the timer count
        private void incrementTimer() {
            timerCount++;
            updateTimerDisplay();
        }

        // Method to update the timer display
        private void updateTimerDisplay() {
            textCountdown.setText(String.valueOf(timerCount));
        }

        // Runnable for the timer
        private Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                if (timerRunning) {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - lastUpdatedTime;
                    int elapsedSeconds = (int) (elapsedTime / 1000);
                    timerCount += elapsedSeconds;
                    lastUpdatedTime = currentTime;
                    updateTimerDisplay();
                    handler.postDelayed(this, 1000);
                }
            }
        };
    }
}
