package com.skad.myapplication.adapter.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.helper.Interfaces.ClickInterFace;

import java.util.List;

public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {

    private final List<String> subItemList;
    private final Context context;
    ClickInterFace clickInterFace;

    public SubItemAdapter(List<String> subItemList, Context context, ClickInterFace clickInterFace) {
        this.subItemList = subItemList;
        this.context = context;
        this.clickInterFace =clickInterFace;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.subItemTextView.setText(subItemList.get(position));
        holder.subItemTextView.setOnClickListener(new View.OnClickListener() {
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
        return subItemList.size();
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView subItemTextView;

        public SubItemViewHolder(@NonNull View itemView) {
            super(itemView);
            subItemTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}

