package com.skad.myapplication.Activity.MenuNavDEtailsStatic.About;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.skad.myapplication.R;


public class AboutUsActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    View ouratoryAboutUsLayout, ourmissionAboutUsLayout, responsiblesourcingAboutUsLayout, blogAboutUsLayout, sustainabilitygoalsAboutUsLayout, givebackAboutUsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us);


        mainLayout = findViewById(R.id.main);

        // ========================= Back button to close the activity =================================
        ImageView aboutBackBtn = findViewById(R.id.aboutBackBtn);
        aboutBackBtn.setOnClickListener(v -> finish());

        // =============================  Find TextViews for sections in HorizontalScrollView ============================
        TextView ourStory = findViewById(R.id.ouratoryAboutUs);
        TextView ourMission = findViewById(R.id.ourmissionAboutUs);
        TextView responsibleSourcing = findViewById(R.id.responsiblesourcingAboutUs);
        TextView blog = findViewById(R.id.blogAboutUs);
        TextView sustainabilityGoals = findViewById(R.id.sustainabilitygoalsAboutUs);
        TextView giveBack = findViewById(R.id.givebackAboutUs);

        // ============================ Inflate the different layouts in advance =========================
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        ouratoryAboutUsLayout = inflater.inflate(R.layout.ourstories, null); // Layout for "Our Story"
        ourmissionAboutUsLayout = inflater.inflate(R.layout.ourmission, null); // Layout for "Our Mission"
        responsiblesourcingAboutUsLayout = inflater.inflate(R.layout.transparancy, null); // Layout for "Responsible Sourcing"
        blogAboutUsLayout = inflater.inflate(R.layout.blog, null); // Layout for "Blog"
        sustainabilitygoalsAboutUsLayout = inflater.inflate(R.layout.suitanibility, null); // Layout for "Sustainability Goals"
        givebackAboutUsLayout = inflater.inflate(R.layout.compassion, null); // Layout for "How We Give Back"

        //       ============================    Initially, add all layouts but make only one visible  ======================================
        mainLayout.addView(ouratoryAboutUsLayout);
        mainLayout.addView(ourmissionAboutUsLayout);
        mainLayout.addView(responsiblesourcingAboutUsLayout);
        mainLayout.addView(blogAboutUsLayout);
        mainLayout.addView(sustainabilitygoalsAboutUsLayout);
        mainLayout.addView(givebackAboutUsLayout);

        // ==========================================  Initially, make only one layout visible ==================================
        showLayout(ouratoryAboutUsLayout);

        // =============================  Set listeners for each section button =============================
        ourStory.setOnClickListener(v -> showLayout(ouratoryAboutUsLayout));
        ourMission.setOnClickListener(v -> showLayout(ourmissionAboutUsLayout));
        responsibleSourcing.setOnClickListener(v -> showLayout(responsiblesourcingAboutUsLayout));
        blog.setOnClickListener(v -> showLayout(blogAboutUsLayout));
        sustainabilityGoals.setOnClickListener(v -> showLayout(sustainabilitygoalsAboutUsLayout));
        giveBack.setOnClickListener(v -> showLayout(givebackAboutUsLayout));
    }

    //  ================================== Method to show a specific layout and hide all others ===============================
    private void showLayout(View layoutToShow) {
        ouratoryAboutUsLayout.setVisibility(View.GONE);
        ourmissionAboutUsLayout.setVisibility(View.GONE);
        responsiblesourcingAboutUsLayout.setVisibility(View.GONE);
        blogAboutUsLayout.setVisibility(View.GONE);
        sustainabilitygoalsAboutUsLayout.setVisibility(View.GONE);
        givebackAboutUsLayout.setVisibility(View.GONE);

        //  ============================ Only show the selected layout ==============================
        layoutToShow.setVisibility(View.VISIBLE);
    }
}
