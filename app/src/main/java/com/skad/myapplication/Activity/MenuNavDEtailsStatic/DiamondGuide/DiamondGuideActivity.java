package com.skad.myapplication.Activity.MenuNavDEtailsStatic.DiamondGuide;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skad.myapplication.R;


public class DiamondGuideActivity extends AppCompatActivity {
    private View diamondCutLayout, diamondColorLayout, diamondClarityLayout, diamondCaratLayout, labDiamondGuideLayout, customCutLabLayout;
    ImageView diamondGuideBackBtn;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond_guide);

        // ========================  Initialize back button and set up click listener ==================
        diamondGuideBackBtn = findViewById(R.id.diamondGuideBackBtn);
        diamondGuideBackBtn.setOnClickListener(v -> {
            finish(); // Close the activity on back button press
        });

        // ================== Main layout container where the views will be inflatedn ========================
        LinearLayout mainLayout = findViewById(R.id.main);

        // ============================ Find TextViews for the options in HorizontalScrollView ===============
        TextView diamondCutGuide = findViewById(R.id.diamondCutGuide);
        TextView diamondColorGuide = findViewById(R.id.diamondColorGuide);
        TextView diamondClarityGuide = findViewById(R.id.diamondClarityGuide);
        TextView diamondCaratGuide = findViewById(R.id.diamondCaratGuide);
        TextView labDiamondGuide = findViewById(R.id.labDiamondGuide);
        TextView customCutLabGuide = findViewById(R.id.customCutLab);

        // ======================= Inflate the different layouts in advance ====================
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        diamondCutLayout = inflater.inflate(R.layout.diamondcutguide,
                null);
        diamondColorLayout = inflater.inflate(R.layout.diamondcolorguide, null);
        diamondClarityLayout = inflater.inflate(R.layout.diamondclarityguide, null);
        diamondCaratLayout = inflater.inflate(R.layout.diamondcaratguide, null);
        labDiamondGuideLayout = inflater.inflate(R.layout.labdiamondguide, null);
        customCutLabLayout = inflater.inflate(R.layout.diamondguidelabcut, null);

        //========================== Initially, add all layouts but make only one visible =====================
        mainLayout.addView(diamondCutLayout);
        mainLayout.addView(diamondColorLayout);
        mainLayout.addView(diamondClarityLayout);
        mainLayout.addView(diamondCaratLayout);
        mainLayout.addView(labDiamondGuideLayout);
        mainLayout.addView(customCutLabLayout);

        //======================= Initially set diamondCutLayout visible, hide others ====================
        diamondCutLayout.setVisibility(View.VISIBLE);
        diamondColorLayout.setVisibility(View.GONE);
        diamondClarityLayout.setVisibility(View.GONE);
        diamondCaratLayout.setVisibility(View.GONE);
        labDiamondGuideLayout.setVisibility(View.GONE);
        customCutLabLayout.setVisibility(View.GONE);

        // =====================  Set OnClickListeners for the different options ====================
        diamondCutGuide.setOnClickListener(v -> {
            // Show Diamond Cut layout, hide others
            showLayout(diamondCutLayout);
        });

        diamondColorGuide.setOnClickListener(v -> {
            // Show Diamond Color layout, hide others
            showLayout(diamondColorLayout);
        });

        diamondClarityGuide.setOnClickListener(v -> {
            // Show Diamond Clarity layout, hide others
            showLayout(diamondClarityLayout);
        });

        diamondCaratGuide.setOnClickListener(v -> {
            // Show Diamond Carat layout, hide others
            showLayout(diamondCaratLayout);
        });

        labDiamondGuide.setOnClickListener(v -> {
            // Show Lab Diamond Guide layout, hide others
            showLayout(labDiamondGuideLayout);
        });

        customCutLabGuide.setOnClickListener(v -> {
            // Show Custom Lab Cut Guide layout, hide others
            showLayout(customCutLabLayout);
        });
    }

    //======================  Helper method to show the selected layout and hide the others ====================
    private void showLayout(View visibleLayout) {
        diamondCutLayout.setVisibility(View.GONE);
        diamondColorLayout.setVisibility(View.GONE);
        diamondClarityLayout.setVisibility(View.GONE);
        diamondCaratLayout.setVisibility(View.GONE);
        labDiamondGuideLayout.setVisibility(View.GONE);
        customCutLabLayout.setVisibility(View.GONE);

        visibleLayout.setVisibility(View.VISIBLE);
    }
}
