package com.skad.myapplication.adapter.ProductListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.skad.myapplication.Activity.ProductDetails.ProductDetailsActivity;
import com.skad.myapplication.Models.ApiModel.Image;
import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.R;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;


import java.util.List;

public class RingStyleAdapter extends RecyclerView.Adapter<RingStyleAdapter.ViewHolder> {
    Context context;
//    ArrayList<RingModel> ringModelArrayList;
    List<Product> productList;
    ClickInterFace clickInterFace;
    SharedPreferencesHelper sharedPreferencesHelper ;
    Boolean forFav;

    public RingStyleAdapter(Context context, List<Product> productList,Boolean forFav, ClickInterFace clickInterFace) {
        this.context = context;
        this.productList = productList;
        this.clickInterFace = clickInterFace;
        this.sharedPreferencesHelper  =  SharedPreferencesHelper.getInstance(context,"myFav");
        this.forFav = forFav;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        RingModel ringModel = ringModelArrayList.get(position);
        Product ringProduct = productList.get(position);
        if (holder.ringStyleImgview != null) {
            // set the fav image
            if(ringProduct.getFav()) {
                holder.favouriteIconBtn.setImageResource(R.drawable.baseline_favorite_24);
            }else {
                holder.favouriteIconBtn.setImageResource(R.drawable.baseline_favorite_border_24);
            }
            // Get the image object
            Image productImage = ringProduct.getImage(); // Assuming this returns an Image object
            String imageUrl = (productImage != null) ? productImage.getSrc() : null; // Extract the URL

            System.out.println("productImg: " + imageUrl);

            if (imageUrl != null && !imageUrl.isEmpty()) {
                // Load the image using Glide or Picasso
                Glide.with(context)
                        .load(imageUrl)
                        .into(holder.ringStyleImgview);
            } else {
                // Fallback to a default image resource if the URL is invalid
                holder.ringStyleImgview.setImageResource(R.drawable.detarahead); // Use your default image resource
            }
        }



        Log.e("TAG", "onBindViewHolder: "+ringProduct.getTitle() );
        holder.ringStyleName.setText(ringProduct.getTitle());




        holder.favouriteIconBtn.setOnClickListener(v -> {
            if (ringProduct.getFav()) {
                // Remove from favorites
                ringProduct.setFav(false);

                sharedPreferencesHelper.removeProduct(ringProduct);
                Log.e("favCount", "onBindViewHolder: remove from fav list " + sharedPreferencesHelper.getCountProduct());
                holder.favouriteIconBtn.setImageResource(R.drawable.baseline_favorite_border_24);
            } else {
                // Add to favorites
                ringProduct.setFav(true);
                sharedPreferencesHelper.addUser(ringProduct);
                Log.e("favCount", "onBindViewHolder: added to fav list " + sharedPreferencesHelper.getCountProduct());
                holder.favouriteIconBtn.setImageResource(R.drawable.baseline_favorite_24);
            }

            if(forFav) {
                productList.clear();
                productList.addAll(sharedPreferencesHelper.getUsers());
                notifyDataSetChanged();
            }
        });



        holder.ringStyleItemCardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailsActivity.class);

            // Ensure click interface is handled if required
            if (clickInterFace != null) {
                clickInterFace.onClick(position);
//                String price = ringProduct.getPrice(); // Ensure `getPrice` method is available if needed
                // Pass the data to ProductDetailsActivity
                intent.putExtra("productDetails",ringProduct);
//                intent.putExtra("ringPrice", price); // Uncomment if needed
                // Add new task flag if necessary
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            // Start ProductDetailsActivity
            context.startActivity(intent);
        });

    }



    @Override
    public int getItemCount() {
        Log.e("getItemCount", "getItemCount: "+productList.size());

        return productList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favouriteIconBtn,ringStyleImgview;
        TextView ringStyleName,ringStylePrice;
        CardView ringStyleItemCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favouriteIconBtn = itemView.findViewById(R.id.favouriteIconBtn);
            ringStyleImgview = itemView.findViewById(R.id.ringStyleImageView);
            ringStyleName = itemView.findViewById(R.id.ringStyleTextView);
            ringStylePrice = itemView.findViewById(R.id.ringStyleprice);
//            cartIconBtn  = itemView.findViewById(R.id.cartIconBtn);
            ringStyleItemCardView = itemView.findViewById(R.id.ringStyleItemCardView);
        }
    }


}
