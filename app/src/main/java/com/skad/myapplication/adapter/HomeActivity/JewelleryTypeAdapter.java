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

public class  JewelleryTypeAdapter extends RecyclerView.Adapter<JewelleryTypeAdapter.ViewHolder> {

    Context context;
    ArrayList<JewelleryTypeModel> jewelleryTypeModelArrayList;
    ClickInterFace clickInterFace;

    public JewelleryTypeAdapter(Context context, ArrayList<JewelleryTypeModel> jewelleryTypeModelArrayList,ClickInterFace clickInterFace) {
        this.context = context;
        this.jewelleryTypeModelArrayList = jewelleryTypeModelArrayList;
        this.clickInterFace = clickInterFace;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        JewelleryTypeModel jewelleryTypeModel = jewelleryTypeModelArrayList.get(position);
        if(jewelleryTypeModel!= null){
            if (jewelleryTypeModel.getJewelleryShapeImg()!= 0){
                holder.homeImgrecycle1.setImageResource(jewelleryTypeModel.getJewelleryShapeImg());
            }
        }
        assert jewelleryTypeModel != null;
        holder.homeTextHead1.setText(jewelleryTypeModel.getJewelleryName());
        holder.homeImgrecycle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickInterFace != null) {
                    clickInterFace.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jewelleryTypeModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homeImgrecycle1;
        TextView homeTextHead1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeImgrecycle1 = itemView.findViewById(R.id.homeImgerecycle1);
            homeTextHead1 = itemView.findViewById(R.id.hometextHeadRecycle1);
        }
    }
}
