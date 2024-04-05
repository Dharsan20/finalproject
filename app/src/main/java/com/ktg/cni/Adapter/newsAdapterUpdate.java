package com.ktg.cni.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ktg.cni.Model.newsModel;
import com.ktg.cni.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

;

public class newsAdapterUpdate extends RecyclerView.Adapter<newsAdapterUpdate.MyViewHolder>{
    private List<newsModel> newsModels;
    Context ctx;
    int selectedItemPosition =0;
    public newsAdapterUpdate(List<newsModel> newsModels, Context ctx) {
        this.newsModels = newsModels;
        this.ctx = ctx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        newsModel cm = newsModels.get(position);
        holder.title.setText(cm.getTitle());
        String dd="";

        if(cm.getUpdateDate().equalsIgnoreCase("")){
            dd=getNewsDetailsDateTime(cm.getCreateDate());
        }
        else{
            dd=getNewsDetailsDateTime(cm.getUpdateDate());
        }

        holder.updatetime.setText(dd);

        holder.count.setText(cm.getViews());
        String url= cm.getName();
        Glide.with(holder.itemView)
                .load(url)
                .fitCenter()
                .into(holder.uima);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("News_Event");
                intent.putExtra("id", cm.getId());
                intent.putExtra("date",holder.updatetime.getText().toString());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("count", holder.count.getText().toString());
                intent.putExtra("dis", cm.getDescription());
                intent.putExtra("url", url);
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
            }
        });
        holder.updatetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("News_Event");
                intent.putExtra("id", cm.getId());
                intent.putExtra("date",holder.updatetime.getText().toString());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("count", holder.count.getText().toString());
                intent.putExtra("dis", cm.getDescription());
                intent.putExtra("url", url);
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent("News_Event");
                intent.putExtra("id", cm.getId());
                intent.putExtra("date",holder.updatetime.getText().toString());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("count", holder.count.getText().toString());
                intent.putExtra("dis", cm.getDescription());
                intent.putExtra("url", url);
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
            }
        });

        holder.uima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("News_Event");
                intent.putExtra("id", cm.getId());
                intent.putExtra("date",holder.updatetime.getText().toString());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("count", holder.count.getText().toString());
                intent.putExtra("dis", cm.getDescription());
                intent.putExtra("url", url);
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("News_Eventshare");
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("dis", cm.getDescription());
                LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,updatetime,count;
        LinearLayout share;
        ImageView uima;
        CardView cv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titel);
            uima = (ImageView) itemView.findViewById(R.id.uima);
            updatetime = (TextView) itemView.findViewById(R.id.updatetime);
            count = (TextView) itemView.findViewById(R.id.viewscount);
            cv = (CardView) itemView.findViewById(R.id.cv);

            share = (LinearLayout) itemView.findViewById(R.id.sharemsg);
        }
    }

    public static String getNewsDetailsDateTime(String dateTime) {

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String strPublishDateTime = new SimpleDateFormat("d MMM, yyyy h:mm a").format(date);
        return strPublishDateTime;
    }
}
