package com.skad.myapplication.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skad.myapplication.R;


public class CallFragment extends BottomSheetDialogFragment {
    TextView call,  watsapp;

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);


        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Appcolor));


        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        // Setup call option
        call = view.findViewById(R.id.call_option);
        call.setBackgroundColor(Color.TRANSPARENT);
        view.findViewById(R.id.call_option).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "7802022583"));
            startActivity(intent);
        });


        // Setup WhatsApp chat option
        watsapp = view.findViewById(R.id.whatsapp_option);
        watsapp.setBackgroundColor(Color.TRANSPARENT);
        view.findViewById(R.id.whatsapp_option).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/7802022583")); // Replace with your phone number
            startActivity(intent);
        });

        return view;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialog; // Use the custom theme
    }
}
