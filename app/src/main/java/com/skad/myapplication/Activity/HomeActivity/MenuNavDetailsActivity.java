package com.skad.myapplication.Activity.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.skad.myapplication.Activity.OnBoardingActivity.SignInActivity;
import com.skad.myapplication.Models.HomeModel.DrawerRingModel;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.HomeActivity.DrawerNavRingAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuNavDetailsActivity extends AppCompatActivity {
    RecyclerView navmenurecycleView;
    LinearLayout logoutContain;
    private DrawerNavRingAdapter adapter;
    private ArrayList<DrawerRingModel> drawerRingModelArrayList;
    TextView  menuNavDetailsUserName;
    TextView uderDpImageMain;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_nav_details);
        menuNavDetailsUserName = findViewById(R.id.menuNavDetailsUserName);  // Make sure this ID is correct
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        menuNavDetailsUserName = findViewById(R.id.menuNavDetailsUserName);  // Make sure this ID is correct
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            fetchUserData(userId);
        } else {
            Toast.makeText(MenuNavDetailsActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
        }

//        ========================  set recycleview ==============================
        loadMainList();


//         ================  set top dp contaim =========================
        uderDpImageMain = findViewById(R.id.userDpImgMain);


//        =============  logout Contain ==================
        logoutContain = findViewById(R.id.logoutContain);
        logoutContain.setOnClickListener(view -> logoutUser());

    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadMainList() {
        navmenurecycleView = findViewById(R.id.navigationRecycleview);
        drawerRingModelArrayList = new ArrayList<>();

        // Adding items to the list
        drawerRingModelArrayList.add(new DrawerRingModel("ENGAGEMENT RINGS", R.drawable.baseline_keyboard_arrow_down_24, false,
                Arrays.asList("Engagement Ring Style", "Shape By Diamond", "Feautured", "Engagement Ring Guides")));
        drawerRingModelArrayList.add(new DrawerRingModel("BENAVI® SILVER", R.drawable.baseline_keyboard_arrow_down_24, false
                , Arrays.asList("Jewelary", "Shop By Shape")));
        drawerRingModelArrayList.add(new DrawerRingModel("JEWELERY", R.drawable.baseline_keyboard_arrow_down_24, false,
                Arrays.asList("Jwelery", "Shope By Shape")));
        drawerRingModelArrayList.add(new DrawerRingModel("WEDDING RINGS", R.drawable.baseline_keyboard_arrow_down_24, false,
                Arrays.asList("Women", "Men", "By Metal")));
        drawerRingModelArrayList.add(new DrawerRingModel("DIAMONDS", R.drawable.baseline_keyboard_arrow_down_24, false,
                List.of("Diamond Guides")));
        drawerRingModelArrayList.add(new DrawerRingModel("GEMSTONES", R.drawable.baseline_keyboard_arrow_down_24, false,
                Arrays.asList("Gemstone Guides", "Present Gemstone Rings", "Ready To Shop")));
        drawerRingModelArrayList.add(new DrawerRingModel("ABOUT", R.drawable.baseline_keyboard_arrow_down_24, false,
                Arrays.asList("About Us", "")));

        adapter = new DrawerNavRingAdapter(this, drawerRingModelArrayList, position -> {
            // Toggle the selected item
            for (int i = 0; i < drawerRingModelArrayList.size(); i++) {
                DrawerRingModel item = drawerRingModelArrayList.get(i);
                if (i == position) {
                    item.setExpanded(!item.isExpanded()); // Toggle expanded state
                } else {
                    item.setExpanded(false); // Collapse others
                }
            }
            adapter.notifyDataSetChanged(); // Refresh the adapter
        });

        navmenurecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        navmenurecycleView.setAdapter(adapter);
    }

    private void logoutUser() {

        mAuth.signOut();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();


        startActivity(new Intent(MenuNavDetailsActivity.this, SignInActivity.class));
        finish();
    }

    @SuppressLint("SetTextI18n")
    private void fetchUserData(String userId) {
        db.collection("users").document(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String firstName = document.getString("firstName");
                            String lastName = document.getString("lastName");

                            // Set full name in the TextView
                            if (firstName != null && lastName != null) {
                                menuNavDetailsUserName.setText(firstName + " " + lastName);

                                // Extract initials
                                String initials = String.valueOf(firstName.charAt(0)).toUpperCase() +
                                        String.valueOf(lastName.charAt(0)).toUpperCase();

                                // Set initials in the uderDpImageMain TextView
                                uderDpImageMain.setText(initials);
                            } else {
                                menuNavDetailsUserName.setText("User Name");
                                uderDpImageMain.setText("UN"); // Default initials
                            }
                        } else {
                            Toast.makeText(MenuNavDetailsActivity.this, "No user data found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("MenuNavDetailsActivity", "Error fetching user data: ", task.getException());
                        Toast.makeText(MenuNavDetailsActivity.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}