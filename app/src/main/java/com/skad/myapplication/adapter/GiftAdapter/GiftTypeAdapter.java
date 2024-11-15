package com.skad.myapplication.adapter.GiftAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

;

import com.skad.myapplication.Models.HomeModel.JewelleryTypeModel;
import com.skad.myapplication.R;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;

import java.util.ArrayList;

public class GiftTypeAdapter extends RecyclerView.Adapter<GiftTypeAdapter.ViewHolder> {
    Context context;
    ArrayList<JewelleryTypeModel> giftTypeModelArraylist;
    ClickInterFace clickInterFace;

    public GiftTypeAdapter(Context context, ArrayList<JewelleryTypeModel> giftTypeModelArraylist, ClickInterFace clickInterFace) {
        this.context = context;
        this.giftTypeModelArraylist = giftTypeModelArraylist;
        this.clickInterFace = clickInterFace;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        JewelleryTypeModel JewelleryTypeShapeModel = giftTypeModelArraylist.get(position);
        if(JewelleryTypeShapeModel!= null){
            if (JewelleryTypeShapeModel.getJewelleryShapeImg()!= 0){
                holder.giftTypeImg.setImageResource(JewelleryTypeShapeModel.getJewelleryShapeImg());
                holder.giftTypeImg.setOnClickListener(new View.OnClickListener() {
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
        holder.giftItemTypeName.setText(JewelleryTypeShapeModel.getJewelleryName());
    }

    @Override
    public int getItemCount() {
        return giftTypeModelArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView giftTypeImg;
        TextView giftItemTypeName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            giftTypeImg = itemView.findViewById(R.id.giftItemTypeImg);
            giftItemTypeName = itemView.findViewById(R.id.giftItemTypeName);
        }
    }
}
