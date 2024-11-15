package com.skad.myapplication.Activity.OnBoardingActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.skad.myapplication.Activity.HomeActivity.MainActivity;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.onboradingadapter.ViewPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class ViewPagerActivity extends AppCompatActivity {
    ViewPagerAdapter viewAdapter;
    ViewPager viewPager;
    DotsIndicator dotsIndicator;
    ImageView viewPagerArrowForwordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if it's the first launch using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OnBoardingPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true);

        // If not the first launch, skip the onboarding activity
        if (!isFirstLaunch) {
            startActivity(new Intent(ViewPagerActivity.this, MainActivity.class));  // Redirect to MainActivity (or SignIn/SignUp)
            finish();  // Close the current activity
            return;
        }

        // If first launch, set EdgeToEdge and proceed to onboarding layout
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewpager);

        // Find layout views
        viewPager = findViewById(R.id.view_pager);
        dotsIndicator = findViewById(R.id.dot1);
        viewPagerArrowForwordBtn = findViewById(R.id.viewpageArrowForwordBtn);

        // Set up ViewPager with adapter and DotsIndicator
        viewAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewAdapter);
        dotsIndicator.setViewPager(viewPager);

        // Button click listener to proceed to SignUpActivity
        viewPagerArrowForwordBtn.setOnClickListener(v -> {
            // Set isFirstLaunch to false as onboarding is completed
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();
            // Start SignUpActivity
            startActivity(new Intent(ViewPagerActivity.this, SignUpActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();// Close the app completely
    }
}
