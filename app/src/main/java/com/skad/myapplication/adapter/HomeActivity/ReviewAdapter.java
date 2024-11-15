package com.skad.myapplication.adapter.HomeActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skad.myapplication.Models.HomeModel.ReviewModel;
import com.skad.myapplication.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    Context context;
    ArrayList<ReviewModel> reviewModelArrayList;

    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList) {
        this.context = context;
        this.reviewModelArrayList = reviewModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
       return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  ReviewModel reviewModel = reviewModelArrayList.get(position);
  holder.reviewImg.setImageResource(reviewModel.getReviewImg());
  holder.reviewText.setText(reviewModel.getReviewText());
    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView reviewImg;
        TextView reviewText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewImg = itemView.findViewById(R.id.reviewImgHome);
            reviewText = itemView.findViewById(R.id.reviewText);
        }
    }
}
