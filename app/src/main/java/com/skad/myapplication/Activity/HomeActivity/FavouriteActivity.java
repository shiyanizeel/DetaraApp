package com.skad.myapplication.Activity.HomeActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.ProductListAdapter.RingStyleAdapter;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    SharedPreferencesHelper sharedPreferencesHelper;
    ArrayList<Product> productArrayList = new ArrayList<>();
    RingStyleAdapter ringStyleAdapter;
    RecyclerView favoriteRingsRecyclerView;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favourite);
        sharedPreferencesHelper =  SharedPreferencesHelper.getInstance(this,"myFav");

        favoriteRingsRecyclerView = findViewById(R.id.favoriteRingsRecyclerView);

        productArrayList = (ArrayList<Product>) sharedPreferencesHelper.getUsers();
progressBar = findViewById(R.id.progressBarFavourite);
        ringStyleAdapter = new RingStyleAdapter(getApplicationContext(), productArrayList,true, position -> progressBar.setVisibility(View.INVISIBLE));
        favoriteRingsRecyclerView.setAdapter(ringStyleAdapter);
        favoriteRingsRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));


    }

}