package com.skad.myapplication.Activity.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.Models.ApiModel.ProductDetailsModel;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.CartAdapters.CartAdapter;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity  {
    SharedPreferencesHelper sharedPreferencesHelper;
    RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    TextView textviewsubitem;
    List<Product> Cartlist = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);













        // Initialize Views
        cartRecyclerView = findViewById(R.id.cartRecyclerview);

        // Initialize SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(this, "myCart");

        // Load saved cart items from SharedPreferences
        Cartlist.addAll(sharedPreferencesHelper.getUsers());

        // Retrieve the passed ProductDetailsModel object if it exists
//        Product selectedProduct = (Product) getIntent().getSerializableExtra("product");

        // Add the selected product to the cart list (if it's not null) and save to SharedPreferences
//        if (selectedProduct != null) {
//            Cartlist.add(selectedProduct);
//            sharedPreferencesHelper.addProductToCart(selectedProduct); // Save updated list
//        }

        // Set up the RecyclerView with CartAdapter
        // Assuming `sharedPreferencesHelper` is already instantiated in this activity
        cartAdapter = new CartAdapter(Cartlist, position -> {
            // Remove item from cartList and update SharedPreferences
            Log.e("newSize", "onClick: " + position);

            Product removedProduct = Cartlist.get(position);
            Log.e("newSize", "cartId: " + removedProduct.getId());
            Cartlist.remove(position);
            Log.e("newSize", "after remove: " + Cartlist.size());
            sharedPreferencesHelper.removeProduct(removedProduct);
            cartAdapter.notifyItemRemoved(position);
        }, sharedPreferencesHelper, new CartAdapter.ProductCountListener() {
            @Override
            public void onProductCountChanged(int newCount) {
                textviewsubitem.setText("(Item): " + newCount);
            }
        }); // Pass the SharedPreferencesHelper instance here

        cartRecyclerView.setAdapter(cartAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        textviewsubitem = findViewById(R.id.textviewsubitem);
// Get the product count from the Intent
        int productCount = getIntent().getIntExtra("product_count", 0);  // Default value is 0

// Update the TextView with the product count
        textviewsubitem.setText("(Item): " + productCount);




    }

    @SuppressLint("NotifyDataSetChanged")
    protected void onResume() {
        super.onResume();
        // Reload the cart list from SharedPreferences
        Cartlist.clear();
        Cartlist.addAll(sharedPreferencesHelper.getUsers());

        // Notify the adapter of data changes
        if (cartAdapter != null) {
            cartAdapter.notifyDataSetChanged();
        }
    }


}