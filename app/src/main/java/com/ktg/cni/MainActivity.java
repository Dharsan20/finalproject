package com.ktg.cni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.ktg.cni.Adapter.newsAdapterUpdate;
import com.ktg.cni.Model.newsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout otolayout,gtlayout,grlayout;
    private List<newsModel> newsModels=new ArrayList<>();
    com.ktg.cni.Adapter.newsAdapterUpdate newsAdapterUpdate;
    RecyclerView AddTwo;
    int scrollCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        otolayout=findViewById(R.id.otolayout);
        gtlayout=findViewById(R.id.gtlayout);
        grlayout=findViewById(R.id.grlayout);
        AddTwo=findViewById(R.id.AddTwo);
        loadNews();
        grlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GiveReferral.class);
                startActivity(intent);
            }
        });
        gtlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GiveThank.class);
                startActivity(intent);
            }
        });
        otolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OneToOne.class);
                startActivity(intent);
            }
        });
    }

    private void loadNews() {
        newsModel ns1=new newsModel("1","https://bl-i.thgim.com/public/incoming/ekjuxa/article66964781.ece/alternates/LANDSCAPE_1200/Videocon_mobile_Plant_2.jpg","The government is likely to assess the need for course correction for “laggard sectors’’ under the ₹1.97-lakh crore Production Linked Incentive (PLI) scheme, such as white goods, automobiles, auto parts, textiles, solar PV modules, and ACC batteries, after the end of the current financial year, official sources have said. ","‘Adaptive measures’. Govt to assess need for course correction for PLI laggard sectors next year, say officials ","‘Adaptive measures’. Govt to assess need for course correction for PLI laggard sectors next year, say officials","10","03/06/2023 11:15:46","03/06/2023 11:15:46");
        newsModel ns2=new newsModel("2","https://bl-i.thgim.com/public/incoming/uui5ym/article66963777.ece/alternates/LANDSCAPE_1200/PTI01_31_2023_000294B.jpg","The total domestic passenger vehicle (PV) wholesale (dispatches to dealers) grew by around 13.53 per cent year-on-year (y-o-y) to 3,34,247 units in May compared with 2,94,392 units in the corresponding month last year.","Trend to continue supported by the prevalent economic environment: SIAM ","Sales data. Total passenger vehicle sales up 13.5% in May at 3.34 lakh units ","137","22/05/2023 17:22:22","22/05/2023 17:22:22");
        newsModel ns3=new newsModel("3","https://bl-i.thgim.com/public/incoming/nxzjfm/article66965274.ece/alternates/LANDSCAPE_1200/IMG_IMG_PO03_Rupee_sack__2_1_7IA6L7G7.jpg","Eleven States on Tuesday collectively raised ₹22,600 crore by issuing State government securities (SGS) in the auction. This is up about 63 per cent from the indicated ₹13,900 crore for this week in the Q1 (April-June) FY24 auction calendar.","This is about 63 per cent more than the indicated ₹13,900 cr for this week in the Q1 auction calendar ","11 States collectively raised ₹22,600 cr at weekly auction  ","789","27/05/2023 17:22:22","22/05/2023 17:22:22");
        newsModel ns4=new newsModel("4","https://bl-i.thgim.com/public/incoming/ab0elb/article66964766.ece/alternates/LANDSCAPE_1200/CCI_UDHindu_KSL_UKP6G87D6_R1569510792_2_4dd8f71c-45ca-429c-8418-928b0ac3efbe.jpg","The Steel Ministry is looking into the need for imposing countervailing duties (CVD) or import duties on Chinese stainless steel shipments coming to India. And accordingly, the departmental secretary has been asked to prepare a “brief note” and “submit” the same to the Union Steel Minister, Jyotiraditya Scindia.","From a mere 5.5 per cent in 2017-20, the Chinese imports now account for 32.9 per cent of the usage in Indian markets "," Steel Ministry to look into rising Chinese stainless steel imports ","12355","06/06/2023 14:35:33","06/06/2023 14:35:33");
        newsModels.add(ns1);
        newsModels.add(ns2);
        newsModels.add(ns3);
        newsModels.add(ns4);
        newsAdapterUpdate=new newsAdapterUpdate(newsModels,getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        AddTwo.setLayoutManager(mLayoutManager);
        AddTwo.setAdapter(newsAdapterUpdate);
        autoScrollAnother();
    }
    private void autoScrollAnother() {
          scrollCount = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AddTwo.smoothScrollToPosition((scrollCount++));
                if (scrollCount == newsAdapterUpdate.getItemCount()-1 ) {
                    newsModels.addAll(newsModels);
                    newsAdapterUpdate.notifyDataSetChanged();
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }
    public void goto_bus(View view) {
        Intent intent = new Intent(getApplicationContext(), MyBusinessReport.class);
        startActivity(intent);
    }
    public void goto_ref(View view) {
        Intent intent = new Intent(getApplicationContext(), MyReferralLists.class);
        startActivity(intent);
    }
    public void goto_visit(View view) {

        Intent intent = new Intent(getApplicationContext(), MyVistorsReport.class);
        startActivity(intent);
    }
    public void goto_attendance(View view) {
        Intent intent = new Intent(getApplicationContext(), AttendanceReport.class);
        startActivity(intent);
    }
    public void goto_one_report(View view) {
        /
        Intent intent = new Intent(getApplicationContext(), OneToOneReports.class);
        startActivity(intent);
    }
    public void goto_overall_report(View view) {
        Intent intent = new Intent(getApplicationContext(), MyOverallReport.class);
        startActivity(intent);
    }
    public void goto_chapter_report(View view) {
        //MyChapterReport
        Intent intent = new Intent(getApplicationContext(), MyChapterReport.class);
        startActivity(intent);
    }
    public void goto_Open_cate(View view) {
        Intent intent = new Intent(getApplicationContext(), OpenCategory.class);
        startActivity(intent);
    }
    public void goto_Inactive_Member(View view) {
        Intent intent = new Intent(getApplicationContext(), InactiveMember.class);
        startActivity(intent);
    }
    public void goto_More(View view) {
        //MoreDetails
        Intent intent = new Intent(getApplicationContext(), MoreDetails.class);
        startActivity(intent);
    }
    public void goto_core(View view) {
        Intent intent = new Intent(getApplicationContext(), CoreCommittes.class);
        startActivity(intent);
    }
    public void goto_news(View view) {
        Intent intent = new Intent(getApplicationContext(), CNI_News.class);
        startActivity(intent);
    }
}