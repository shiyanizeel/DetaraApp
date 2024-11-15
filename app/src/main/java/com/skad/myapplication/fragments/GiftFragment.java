package com.skad.myapplication.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Activity.HomeActivity.RingItemActivity;
import com.skad.myapplication.Models.HomeModel.JewelleryTypeModel;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.GiftAdapter.GiftTypeAdapter;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;

import java.util.ArrayList;

public class GiftFragment extends Fragment {
    RecyclerView giftTypeItemRv;
    ArrayList<JewelleryTypeModel> giftTyeItemArraylist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
giftTypeItemRv = view.findViewById(R.id.giftTypeRv);

setGiftItem();
        return view;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setGiftItem() {
        // Make sure giftTyeItemArraylist is properly initialized
        if (giftTyeItemArraylist == null) {
            giftTyeItemArraylist = new ArrayList<>();
        }

        // Populate the list
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.aniversarydp, "Aniversary Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.bridalpartygiftdp, "Bridal Party Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.birthdaygiftdp, "Birthday Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.velentinegift, "Velentine Gift"));
        giftTyeItemArraylist.add(new JewelleryTypeModel(R.drawable.graduationgiftdp, "Graduation Gift"));

        // Check if the list is empty before setting the adapter
        GiftTypeAdapter giftTypeAdapter = new GiftTypeAdapter(getContext(), giftTyeItemArraylist, new ClickInterFace() {
            @Override
            public void onClick(int position) {
                // Ensure position is valid (within bounds of the list)
                if (position >= 0 && position < giftTyeItemArraylist.size()) {
                    Intent intent = new Intent(getContext(), RingItemActivity.class);
                    intent.putExtra("jewelleryName", giftTyeItemArraylist.get(position).getJewelleryName());
                    intent.putExtra("showFilter", false);
                    startActivity(intent);
                }
            }
        });
        giftTypeItemRv.setAdapter(giftTypeAdapter);
        giftTypeItemRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        giftTypeAdapter.notifyDataSetChanged();
    }

}
