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


import com.skad.myapplication.Models.HomeModel.ServiceModel;
import com.skad.myapplication.R;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    Context context;
    ArrayList<ServiceModel> serviceModelArrayList;

    public ServiceAdapter(Context context, ArrayList<ServiceModel> serviceModelArrayList) {
        this.context = context;
        this.serviceModelArrayList = serviceModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item,parent,false);
      return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     ServiceModel serviceModel = serviceModelArrayList.get(position);
     holder.serviceImg.setImageResource(serviceModel.getServiceImg());
     holder.serviceHead.setText(serviceModel.getServiceHead());
    }

    @Override
    public int getItemCount() {
        return serviceModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImg;
        TextView serviceHead;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImg = itemView.findViewById(R.id.serviceImge);
            serviceHead = itemView.findViewById(R.id.serviceText);
        }
    }
}
