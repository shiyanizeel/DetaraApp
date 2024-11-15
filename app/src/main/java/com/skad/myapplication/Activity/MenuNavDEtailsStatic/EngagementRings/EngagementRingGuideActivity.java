package com.skad.myapplication.Activity.MenuNavDEtailsStatic.EngagementRings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skad.myapplication.R;


public class EngagementRingGuideActivity extends AppCompatActivity {
     LinearLayout mainLayout;
    private View ringGuideLayout, spendRingsLayout, freeSizeLayout, trendsRingLayout;
    ImageView engagementRingGuideBackBtn;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engagement_ring_guide);

        //==================== Initialize back button and set up click listener==============
        engagementRingGuideBackBtn = findViewById(R.id.engagementRingGuideBackBtn);
        engagementRingGuideBackBtn.setOnClickListener(v -> finish());


        mainLayout = findViewById(R.id.main);

        //======== Find TextViews for the options in HorizontalScrollView ============
        TextView ringStyleGuide = findViewById(R.id.ringStyleGuide);
        TextView spendRingGuide = findViewById(R.id.spendRingGuide);
        TextView freeSizeGuide = findViewById(R.id.freeRingSizeGuide);
        TextView trendsRingGuide = findViewById(R.id.trendRingsGuide);

        // ================ Inflate the different layouts in advance =================
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        ringGuideLayout = inflater.inflate(R.layout.ringguide, null);
        spendRingsLayout = inflater.inflate(R.layout.spendring, null);
        freeSizeLayout = inflater.inflate(R.layout.ringsize, null);
        trendsRingLayout = inflater.inflate(R.layout.trendsring, null);

        //============= Initially, add all layouts but make only one visible ================
        mainLayout.addView(ringGuideLayout);
        mainLayout.addView(spendRingsLayout);
        mainLayout.addView(freeSizeLayout);
        mainLayout.addView(trendsRingLayout);

        //=================== Make only one layout visible at a time =============
        ringGuideLayout.setVisibility(View.VISIBLE);
        spendRingsLayout.setVisibility(View.GONE);
        freeSizeLayout.setVisibility(View.GONE);
        trendsRingLayout.setVisibility(View.GONE);

        //============ Set OnClickListener for Ring Style Guide ========
        ringStyleGuide.setOnClickListener(v -> {
            // Show Ring Style Guide layout, hide others
            ringGuideLayout.setVisibility(View.VISIBLE);
            spendRingsLayout.setVisibility(View.GONE);
            freeSizeLayout.setVisibility(View.GONE);
            trendsRingLayout.setVisibility(View.GONE);
        });

        spendRingGuide.setOnClickListener(v -> {
            ringGuideLayout.setVisibility(View.GONE);
            spendRingsLayout.setVisibility(View.VISIBLE);
            freeSizeLayout.setVisibility(View.GONE);
            trendsRingLayout.setVisibility(View.GONE);
        });

        freeSizeGuide.setOnClickListener(v -> {
            ringGuideLayout.setVisibility(View.GONE);
            spendRingsLayout.setVisibility(View.GONE);
            freeSizeLayout.setVisibility(View.VISIBLE);
            trendsRingLayout.setVisibility(View.GONE);
        });

        trendsRingGuide.setOnClickListener(v -> {
            ringGuideLayout.setVisibility(View.GONE);
            spendRingsLayout.setVisibility(View.GONE);
            freeSizeLayout.setVisibility(View.GONE);
            trendsRingLayout.setVisibility(View.VISIBLE);
        });
    }
}
