package com.skad.myapplication.Activity.otherActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Activity.HomeActivity.RingItemActivity;
import com.skad.myapplication.Models.HomeModel.JewelleryTypeModel;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.GiftAdapter.GiftTypeAdapter;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity {
    RecyclerView collectionTypeRv ;
    ArrayList<JewelleryTypeModel> giftTyeItemArraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_collection);

    }
    @SuppressLint("NotifyDataSetChanged")
    public void setGiftItem() {
        // Make sure giftTyeItemArraylist is properly initialized
        if (giftTyeItemArraylist == null) {
            giftTyeItemArraylist = new ArrayList<>();
        }

        // Populate the list
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.aniversarydp, "Engagement Ring Style"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.bridalpartygiftdp, "Bridal Party Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.birthdaygiftdp, "Birthday Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.velentinegift, "Velentine Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.graduationgiftdp, "Graduation Gift"));

        // Check if the list is empty before setting the adapter
        GiftTypeAdapter giftTypeAdapter = new GiftTypeAdapter(getApplicationContext(), giftTyeItemArraylist, position -> {
            // Ensure position is valid (within bounds of the list)
            if (position >= 0 && position < giftTyeItemArraylist.size()) {
                Intent intent = new Intent(getApplicationContext(), RingItemActivity.class);
                intent.putExtra("jewelleryName", giftTyeItemArraylist.get(position).getJewelleryName());
                intent.putExtra("showFilter", false);
                startActivity(intent);
            }
        });
        collectionTypeRv.setAdapter(giftTypeAdapter);
        collectionTypeRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        giftTypeAdapter.notifyDataSetChanged();
    }

}