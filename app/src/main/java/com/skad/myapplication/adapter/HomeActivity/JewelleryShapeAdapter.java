package com.skad.myapplication.adapter.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Models.HomeModel.JewelleryTypeModel;
import com.skad.myapplication.R;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;

import java.util.ArrayList;

public class JewelleryShapeAdapter extends RecyclerView.Adapter<JewelleryShapeAdapter.ViewHolder> {
    Context context;
    ArrayList<JewelleryTypeModel> jewelleryTypeShapeModelArrayList;
    ClickInterFace clickInterFace;

    public JewelleryShapeAdapter(Context context, ArrayList<JewelleryTypeModel> jewelleryTypeShapeModelArrayList,ClickInterFace clickInterFace) {
        this.context = context;
        this.jewelleryTypeShapeModelArrayList = jewelleryTypeShapeModelArrayList;
        this.clickInterFace = clickInterFace;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        JewelleryTypeModel JewelleryTypeShapeModel = jewelleryTypeShapeModelArrayList.get(position);
        if(JewelleryTypeShapeModel!= null){
            if (JewelleryTypeShapeModel.getJewelleryShapeImg()!= 0){
                holder.homeImgrecycle2.setImageResource(JewelleryTypeShapeModel.getJewelleryShapeImg());
                holder.homeImgrecycle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickInterFace != null) {
                            clickInterFace.onClick(position);
                        }
                    }
                });
            }
        }
        assert JewelleryTypeShapeModel != null;
        holder.homeTextHead2.setText(JewelleryTypeShapeModel.getJewelleryName());
    }

    @Override
    public int getItemCount() {
        return jewelleryTypeShapeModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homeImgrecycle2;
        TextView homeTextHead2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeImgrecycle2 = itemView.findViewById(R.id.homeImgeRecycle2);
            homeTextHead2 = itemView.findViewById(R.id.homeEngajringHead2);
        }
    }
}
