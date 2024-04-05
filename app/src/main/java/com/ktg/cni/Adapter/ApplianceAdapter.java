package com.ktg.cni.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ktg.cni.Model.Appliance;
import com.ktg.cni.R;

import java.util.List;

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.MyViewHolder> {

    private List<Appliance> applianceList;
    Context context;

    public ApplianceAdapter(List<Appliance> applianceList, Context context) {
        this.applianceList = applianceList;
        this.context = context;

    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  final int position) {
        final Appliance appliance = applianceList.get(position);
        Log.e("ApplianceAdapter", "Binding item at position: " + position);
        Log.e("ApplianceAdapter", "Appliance details: " + appliance.toString());

        holder.applianceNameTextView.setText(appliance.getName());
        holder.ampsTextView.setText("Amps: " + appliance.getAmps());
        holder.voltsTextView.setText("Volts: " + appliance.getVolts());


    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView applianceNameTextView;
        public TextView ampsTextView;
        public TextView voltsTextView;


        public MyViewHolder(View itemView) {
            super(itemView);
            applianceNameTextView = itemView.findViewById(R.id.applianceNameTextView);
            ampsTextView = itemView.findViewById(R.id.ampsTextView);
            voltsTextView = itemView.findViewById(R.id.voltsTextView);

        }
    }


}


