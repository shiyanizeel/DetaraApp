package com.skad.myapplication.adapter.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Activity.HomeActivity.RingItemActivity;
import com.skad.myapplication.Activity.MenuNavDEtailsStatic.About.AboutUsActivity;
import com.skad.myapplication.Activity.MenuNavDEtailsStatic.DiamondGuide.DiamondGuideActivity;
import com.skad.myapplication.Activity.MenuNavDEtailsStatic.EngagementRings.EngagementRingGuideActivity;
import com.skad.myapplication.Activity.MenuNavDEtailsStatic.EngagementRings.EngagementfeaturedActivity;
import com.skad.myapplication.Activity.MenuNavDEtailsStatic.Gemstone.GemstoneGuideActivity;
import com.skad.myapplication.Models.HomeModel.DrawerRingModel;
import com.skad.myapplication.R;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;

import java.util.ArrayList;

public class DrawerNavRingAdapter extends RecyclerView.Adapter<DrawerNavRingAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<DrawerRingModel> drawerRingModelArrayList;
     final ClickInterFace clickInterFace;

    public DrawerNavRingAdapter(Context context, ArrayList<DrawerRingModel> drawerRingModelArrayList, ClickInterFace clickInterFace) {
        this.context = context;
        this.drawerRingModelArrayList = drawerRingModelArrayList;
        this.clickInterFace = clickInterFace;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navrecycle_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DrawerRingModel drawerRingModel = drawerRingModelArrayList.get(position);
        holder.drawerRingText.setText(drawerRingModel.getDrawerRingList());
        holder.drawerRingarrowForword.setImageResource(drawerRingModel.getDrawerRingArrowForword());


        if (drawerRingModel.isExpanded()) {
            holder.drawerRingarrowForword.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            holder.subRecyclerView.setVisibility(View.VISIBLE);


            SubItemAdapter subItemAdapter = new SubItemAdapter(drawerRingModel.getSubItems(), context, new ClickInterFace() {
                @Override
                public void onClick(int position) {
                    Intent ringStyleintent = new Intent(context, RingItemActivity.class);
                    Intent intent;
                    switch (drawerRingModel.getDrawerRingList()) {
                        case "ENGAGEMENT RINGS":
                            switch (position) {
                                case 0:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.ER_engagement_ring_style));
                                    context.startActivity(ringStyleintent);
                                    break;
                                    case 1:
                                        ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.ER_shape_By_Diamond));
                                        context.startActivity(ringStyleintent);
                                        break;
                                case 2:
                                    intent = new Intent(context, EngagementfeaturedActivity.class);
                                    context.startActivity(intent);
                                    break;
                                case 3:

                                    intent = new Intent(context, EngagementRingGuideActivity.class);
                                    context.startActivity(intent);
                                    break;
                                default:
                                    Toast.makeText(context, "No action defined for this item", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;

                        case "BENAVI® SILVER":
                            switch (position) {
                                case 0:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.Benavi_jewellery));
                                    context.startActivity(ringStyleintent);
                                    break;
                                case 1:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.Benavi_shape_By_Shope));
                                    context.startActivity(ringStyleintent);
                                    break;
                                default:
                                    Toast.makeText(context, "No action defined for this item", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case "JEWELERY" :
                            switch (position){
                                case  0 :
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.Jewellery));
                                    context.startActivity(ringStyleintent);
                                    break;
                                case 1 :
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.Jewellery_shope_By_Shape));
                                    context.startActivity(ringStyleintent);
                                    break;
                            }
                            break;

                        case "WEDDING RINGS" :
                            switch (position){
                                case 0:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.WR_by_Women));
                                    context.startActivity(ringStyleintent);
                                    break;
                                    case 1:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.WR_by_Man));
                                    context.startActivity(ringStyleintent);
                                    break;
                                    case 2:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.WR_by_Metal));
                                    context.startActivity(ringStyleintent);
                                    break;
                            }
                            break;
                        case "DIAMONDS":
                            if (position == 0) {
                                context.startActivity(new Intent(context, DiamondGuideActivity.class));
                            }
                            break;

                        case "GEMSTONES":
                            switch (position){
                                case 0 :
                                    context.startActivity(new Intent(context, GemstoneGuideActivity.class));
                                    break;
                                case  1:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.Gemstone_present));
                                    context.startActivity(ringStyleintent);
                                    break;
                                case 2:
                                    ringStyleintent.putExtra("jewelleryName",String.valueOf(R.string.Gemstone_ready_To_Shope));
                                    context.startActivity(ringStyleintent);
                                    break;
                            }
                            break;



                        case "ABOUT":
                            if (position == 0) {
                                context.startActivity(new Intent(context, AboutUsActivity.class));
                            }
                            break;

                        default:
                            Toast.makeText(context, "No action defined for this item", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });


            holder.subRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            holder.subRecyclerView.setAdapter(subItemAdapter);
        } else {
            holder.drawerRingarrowForword.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            holder.subRecyclerView.setVisibility(View.GONE);
        }


        holder.navMenuContain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerRingModel.setExpanded(!drawerRingModel.isExpanded());
                notifyItemChanged(position); // Notify adapter about change
            }
        });


        holder.drawerRingarrowForword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerRingModel.setExpanded(!drawerRingModel.isExpanded());
                notifyItemChanged(position); // Notify adapter about change
            }
        });
    }

    @Override
    public int getItemCount() {
        return drawerRingModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drawerRingText;
        ImageView drawerRingarrowForword;
        RelativeLayout navMenuContain;
        RecyclerView subRecyclerView; // Sub-list RecyclerView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drawerRingText = itemView.findViewById(R.id.drawerRingText);
            drawerRingarrowForword = itemView.findViewById(R.id.navArrowForword);
            navMenuContain = itemView.findViewById(R.id.navMenuContain);
            subRecyclerView = itemView.findViewById(R.id.subRecyclerView); // Initialize subRecyclerView
        }
    }
}
