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


public class EngagementfeaturedActivity extends AppCompatActivity {

    private View customRingsLayout;
    private View top10RingsLayout;
    ImageView featureEngagementBackBtn;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engagement_featured); // Initial layout

        featureEngagementBackBtn = findViewById(R.id.featureEngagementBackBtn);
        featureEngagementBackBtn.setOnClickListener(
                v -> finish());


        LinearLayout mainLayout = findViewById(R.id.main);
        // ============ Find your TextViews by their IDs =========
        TextView customEngagementRingsTextView = findViewById(R.id.customEngegementRingFeatured);
        TextView top10EngagementRingsTextView = findViewById(R.id.top10EngagementRingFeatured);

        // ======== Inflate the custom and top 10 rings layouts beforehand =========
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customRingsLayout = inflater.inflate(R.layout.customring, null);
        top10RingsLayout = inflater.inflate(R.layout.top10ring, null);

        //  ============ Initially add both layouts to the main layout but set one to be invisible ===================
        mainLayout.addView(customRingsLayout);
        mainLayout.addView(top10RingsLayout);

        //======= Make custom rings visible initially, hide the top 10 rings ============
        customRingsLayout.setVisibility(View.GONE);
        top10RingsLayout.setVisibility(View.VISIBLE);

        //=============== Set OnClickListener for Custom Engagement Rings ===============
        customEngagementRingsTextView.setOnClickListener(v -> {
            // Toggle visibility of custom rings layout
            customRingsLayout.setVisibility(View.VISIBLE);
            top10RingsLayout.setVisibility(View.GONE); // Hide top 10 rings layout
        });

        // ================= Set OnClickListener for Top 10 Engagement Rings ==================
        top10EngagementRingsTextView.setOnClickListener(v -> {
            // Toggle visibility of top 10 rings layout
            top10RingsLayout.setVisibility(View.VISIBLE);
            customRingsLayout.setVisibility(View.GONE); // Hide custom rings layout
        });

        featureEngagementBackBtn = findViewById(R.id.featureEngagementBackBtn);
        featureEngagementBackBtn.setOnClickListener(v -> finish());
    }
}
