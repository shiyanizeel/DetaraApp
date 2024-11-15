package com.skad.myapplication.Activity.MenuNavDEtailsStatic.Gemstone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.skad.myapplication.R;


public class GemstoneGuideActivity extends AppCompatActivity {
     LinearLayout mainLayout;
    private View moissanitegemstoneLayout, sapphiregemstoneLayout, emeraldgemstoneLayout, alexandritegemstoneLayout, pearlgemstoneLayout,topazgemstoneLayout;
    ImageView gemstoneGuideBackBtn;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gemstone_guide);

        //========================== Initialize back button and set up click listener ====================
        gemstoneGuideBackBtn = findViewById(R.id.gemstoneGuideBackBtn);
        gemstoneGuideBackBtn.setOnClickListener(v -> {
            finish(); // Close the activity on back button press
        });


        //====================== Main layout container where the views will be inflated =======================
        mainLayout = findViewById(R.id.main);

        //================== Find TextViews for the options in HorizontalScrollView ===================
        TextView moissanitegemstoneGuide = findViewById(R.id.moissanitegemstoneGuide);
        TextView sapphiregemstoneGuide = findViewById(R.id.sapphiregemstoneGuide);
        TextView emeraldgemstoneGuide = findViewById(R.id.emeraldgemstoneGuide);
        TextView alexandritegemstoneGuide = findViewById(R.id.alexandritegemstoneGuide);
        TextView pearlgemstoneGuide = findViewById(R.id.pearlgemstoneGuide);
        TextView topazgemstoneGuide = findViewById(R.id.topazgemstoneGuide);

//=================== Inflate the different layouts in advance ===================
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        moissanitegemstoneLayout = inflater.inflate(R.layout.moisatantgemstone, null);
        sapphiregemstoneLayout = inflater.inflate(R.layout.shapphiregemstone, null);
        emeraldgemstoneLayout = inflater.inflate(R.layout.emeraldgemstone, null);
        alexandritegemstoneLayout = inflater.inflate(R.layout.alexdrintgemstone, null);
        pearlgemstoneLayout = inflater.inflate(R.layout.pearlgemstone, null);
        topazgemstoneLayout = inflater.inflate(R.layout.topazgemstone, null);

        //==================== Initially, add all layouts but make only one visible ==================
        mainLayout.addView(moissanitegemstoneLayout);
        mainLayout.addView(sapphiregemstoneLayout);
        mainLayout.addView(emeraldgemstoneLayout);
        mainLayout.addView(alexandritegemstoneLayout);
        mainLayout.addView(pearlgemstoneLayout);
        mainLayout.addView(topazgemstoneLayout);

        //=====================  Initially set diamondCutLayout visible, hide others =================
        moissanitegemstoneLayout.setVisibility(View.VISIBLE);
        sapphiregemstoneLayout.setVisibility(View.GONE);
        emeraldgemstoneLayout.setVisibility(View.GONE);
        alexandritegemstoneLayout.setVisibility(View.GONE);
        pearlgemstoneLayout.setVisibility(View.GONE);
        topazgemstoneLayout.setVisibility(View.GONE);

        // ============== Set OnClickListeners for the different options ================
        moissanitegemstoneGuide.setOnClickListener(v -> showLayout(moissanitegemstoneLayout));

        sapphiregemstoneGuide.setOnClickListener(v -> showLayout(sapphiregemstoneLayout));

        emeraldgemstoneGuide.setOnClickListener(v -> showLayout(emeraldgemstoneLayout));

        alexandritegemstoneGuide.setOnClickListener(v -> showLayout(alexandritegemstoneLayout));

        pearlgemstoneGuide.setOnClickListener(v -> showLayout(pearlgemstoneLayout));

        topazgemstoneGuide.setOnClickListener(v -> showLayout(topazgemstoneLayout));
    }

    // =============== Helper method to show the selected layout and hide the others ====================
    private void showLayout(View visibleLayout) {
        moissanitegemstoneLayout.setVisibility(View.GONE);
        sapphiregemstoneLayout.setVisibility(View.GONE);
        emeraldgemstoneLayout.setVisibility(View.GONE);
        alexandritegemstoneLayout.setVisibility(View.GONE);
        pearlgemstoneLayout.setVisibility(View.GONE);
        topazgemstoneLayout.setVisibility(View.GONE);

        visibleLayout.setVisibility(View.VISIBLE);
    }
}