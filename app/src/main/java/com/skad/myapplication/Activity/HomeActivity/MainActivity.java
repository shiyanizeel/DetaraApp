package com.skad.myapplication.Activity.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.Models.ApiModel.ProductDetailsModel;
import com.skad.myapplication.R;

import com.skad.myapplication.fragments.AppointmentFragment;
import com.skad.myapplication.fragments.CallFragment;
import com.skad.myapplication.fragments.GiftFragment;
import com.skad.myapplication.fragments.HomeFragment;
import com.skad.myapplication.fragments.OfferFragment;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView menuBtn,favButton,cartButton;
    View customTopbarMain;
     SharedPreferencesHelper sharedPreferencesHelper;
     TextView favCountBadge,cartCountBadge;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        customTopbarMain = findViewById(R.id.customTopBarMain);
        favButton = findViewById(R.id.favouriteBtn);
        cartButton = findViewById(R.id.cartBtn);
        progressBar = findViewById(R.id.progressBarRingMain);
        //============================================ Setup Bottom Navigation      ===================================
        setBottomNavigationView(savedInstanceState);
        //============================================ Setup Top Menu Btn     ===================================
        setTopMenuBtn();

//================================== set favourit Btn =============
        // Initialize SharedPreferencesHelper to get the favorites list
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(this, "myFavs");
        favCountBadge = findViewById(R.id.favCountBadge);
        // Get the saved favorite items and update the UI accordingly
        updateFavoriteButton();
        favButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,FavouriteActivity.class)));

//       =================   set cart Btn ==============
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(this, "myCart");
        cartCountBadge = findViewById(R.id.cartCountBadge);
        updateCartButton();
        cartButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));


    }
    public void setBottomNavigationView(Bundle savedInstanceState) {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the listener for when an item is selected
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Reset icons to default state
            resetIcons();

            int id = item.getItemId();
            if (id == R.id.home) {
                selectedFragment = new HomeFragment();
                item.setIcon(R.drawable.homeicon);  // Change the selected icon
            } else if (id == R.id.gift) {
                selectedFragment = new GiftFragment();
                item.setIcon(R.drawable.gifticon);  // Change the selected icon
            } else if (id == R.id.offer) {
                selectedFragment = new OfferFragment();
                item.setIcon(R.drawable.offericon);  // Change the selected icon
            } else if (id == R.id.call) {
                item.setIcon(R.drawable.callicon);
                // Handle bottom sheet for the "call" item
                CallFragment bottomSheet = new CallFragment();
                bottomSheet.show(getSupportFragmentManager(), "callOptionsBottomSheet");
                return true; // Return true so that the item is marked as selected
            }else if (id == R.id.appointment) {
                selectedFragment = new AppointmentFragment();
                item.setIcon(R.drawable.appointmenticon);  // Change the selected icon
            }

            // Replace the fragment in the container
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true; // Indicate that the item was selected
        });

        // Ensure HomeFragment is loaded when the app starts
        if (savedInstanceState == null) {
            // Replace the fragment with HomeFragment on initial load
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();

            // Set the home item as selected initially
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }

    // Method to reset all icons to their default state
    private void resetIcons() {
        Menu menu = bottomNavigationView.getMenu();
        // Reset the icon for each item in the bottom navigation
        menu.findItem(R.id.home).setIcon(R.drawable.homebordericon);  // Reset to original icon
        menu.findItem(R.id.gift).setIcon(R.drawable.giftbordricon);  // Reset to original icon
        menu.findItem(R.id.offer).setIcon(R.drawable.offerbordericon);  // Reset to original icon
        menu.findItem(R.id.call).setIcon(R.drawable.callbordericon);  // Reset to original icon
        menu.findItem(R.id.appointment).setIcon(R.drawable.appointmentbordericon);  // Reset to original icon
    }


    public void setTopMenuBtn() {
        menuBtn = findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MenuNavDetailsActivity.class)));
    }


    private void updateFavoriteButton() {
        // Get the list of favorite products from SharedPreferences
        ArrayList<Product> favoriteProducts = (ArrayList<Product>) sharedPreferencesHelper.getUsers();

        // Get the count of favorite items
        int favoriteCount = favoriteProducts.size();

        // If there are no favorites, show the default "Favorites" text (or keep the icon)
        if (favoriteCount == 0) {
            favCountBadge.setVisibility(View.GONE); // Hide the badge
        } else {
            // If there is at least one favorite, show the count in the badge
            favCountBadge.setVisibility(View.VISIBLE); // Show the badge
            favCountBadge.setText(String.valueOf(favoriteCount));
        }
    }
    private void updateCartButton() {
        // Get the list of favorite products from SharedPreferences
        ArrayList<ProductDetailsModel> cartProducts = (ArrayList<ProductDetailsModel>) sharedPreferencesHelper.getCart();

        // Get the count of favorite items
        int cartCount = cartProducts.size();

        // If there are no favorites, show the default "Favorites" text (or keep the icon)
        if (cartCount == 0) {
            cartCountBadge.setVisibility(View.GONE); // Hide the badge
        } else {
            // If there is at least one favorite, show the count in the badge
            cartCountBadge.setVisibility(View.VISIBLE); // Show the badge
            cartCountBadge.setText(String.valueOf(cartCount));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Update the favorite button whenever we return to MainActivity (after going to FavouriteActivity)
        updateFavoriteButton();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}