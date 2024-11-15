package com.skad.myapplication.adapter.HomeActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Models.HomeModel.HomePicModel;
import com.skad.myapplication.R;

import java.util.ArrayList;

public class HomePicAdapter extends RecyclerView.Adapter<HomePicAdapter.ViewHolder> {
    Context context;
    ArrayList<HomePicModel> homePicModelArrayList;

    public HomePicAdapter(Context context, ArrayList<HomePicModel> homePicModelArrayList) {
        this.context = context;
        this.homePicModelArrayList = homePicModelArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.homepic_item,parent,false);
      return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  HomePicModel homePicModel = homePicModelArrayList.get(position);
  holder.homePicImg.setImageResource(homePicModel.getHomepicImg());
    }

    @Override
    public int getItemCount() {
        return homePicModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homePicImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homePicImg = itemView.findViewById(R.id.homepicImg);

        }
    }
}
