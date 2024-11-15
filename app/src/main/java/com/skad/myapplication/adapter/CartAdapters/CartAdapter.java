package com.skad.myapplication.adapter.CartAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.credentials.playservices.HiddenActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.skad.myapplication.Activity.HomeActivity.CartActivity;
import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.Models.ApiModel.ProductDetailsModel;
import com.skad.myapplication.R;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<Product> cartList;
    SharedPreferencesHelper sharedPreferencesHelper ;
    ClickInterFace clickInterFace;
Context context;
    int count = 1;
     ProductCountListener productCountListener;

    public CartAdapter(List<Product> cartList, ClickInterFace clickInterFace,
                       SharedPreferencesHelper sharedPreferencesHelper,
                       ProductCountListener productCountListener) {
        this.cartList = cartList;
        this.clickInterFace = clickInterFace;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.productCountListener = productCountListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdetails_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Product product = cartList.get(position);

        // Load the image
        Glide.with(holder.itemView.getContext())
                .load(product.getImage().getSrc())
                .into(holder.itemImage);

        String selectedColor = product.getSelectedColor();
        String selectedCaret = product.getSelectedCaret();
        String selectedSize = product.getSelectedSize();
        String selectedMetal = product.getSelectedMetal();
        String selectedWeight = product.getSelectedWeight();
        holder.color.setVisibility(View.INVISIBLE);
        holder.caret.setVisibility(View.INVISIBLE);
        holder.metal.setVisibility(View.INVISIBLE);
        holder.weight.setVisibility(View.INVISIBLE);
        holder.size.setVisibility(View.INVISIBLE);
        if (selectedColor != null) {
            holder.color.setVisibility(View.VISIBLE);
            holder.color.setText(selectedColor);
        } if(selectedCaret != null) {
            Log.e("Ceret", "onBindViewHolder: "+selectedCaret );
            holder.caret.setVisibility(View.VISIBLE);
            holder.caret.setText(selectedCaret);
        }  if(selectedSize != null) {
            holder.size.setVisibility(View.VISIBLE);
            holder.size.setText(selectedSize);
        }  if(selectedMetal != null) {
            holder.metal.setVisibility(View.VISIBLE);
            holder.metal.setText(selectedMetal);
        }  if(selectedWeight != null) {
            holder.weight.setVisibility(View.VISIBLE);
            holder.weight.setText(selectedWeight);
        }

        holder.title.setText(product.getTitle());

        holder.caret.setText(product.getSelectedCaret());
        holder.color.setText(product.getSelectedColor());
        holder.price.setText("₹" + product.getPrice());




// Assuming there's a price field in ProductDetailsModel

//        holder.weight.setText("Weight: " + product.getWeight());
        holder.itemDetailRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickInterFace != null) {

                    // Use the click interface to pass the position
                    clickInterFace.onClick(position);

                    // Optionally remove the item from the cart or list immediately
                    // Remove item from the list (cart or product list)
//                    cartList.remove(cartList.get(position));
                    // Update the adapter after removing the item
//                    notifyItemRemoved(position);

                    // Save the updated list to SharedPreferences (or wherever you store it)
//                    sharedPreferencesHelper.saveCart(cartList);  // Assuming saveUsers is for saving updated list

                }
            }
        });

        holder.productadd.setOnClickListener(v -> {
            count++;
            holder.productCountText.setText(String.valueOf(count));
            if (productCountListener != null) {
                productCountListener.onProductCountChanged(count);
            }
        });

        holder.productminimize.setOnClickListener(v -> {
            if (count > 0) {
                count--;
                holder.productCountText.setText(String.valueOf(count));
                if (productCountListener != null) {
                    productCountListener.onProductCountChanged(count);
                }
            }
        });




    }
    public interface ProductCountListener {
        void onProductCountChanged(int newCount);
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage,productminimize,productadd;
        TextView title, price, color, size, caret, metal,itemDetailRemove,productCountText,weight;
        LinearLayout jeweleryDetailsCartContain;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemDetailsImg);
            title = itemView.findViewById(R.id.itemDetailsName);
            price = itemView.findViewById(R.id.itemDetailsPrice);
            color = itemView.findViewById(R.id.itemDetailsColor);
            size = itemView.findViewById(R.id.itemDetailsSize);
            caret = itemView.findViewById(R.id.itemDetailsCaret);
            metal = itemView.findViewById(R.id.itemDetailsMetal);
            weight = itemView.findViewById(R.id.itemDetailsWeight);
            productminimize = itemView.findViewById(R.id.productminimize);
            productadd = itemView.findViewById(R.id.productadd);
            itemDetailRemove = itemView.findViewById(R.id.itemDetailsRemove);
            productCountText = itemView.findViewById(R.id.productCountText);
            jeweleryDetailsCartContain = itemView.findViewById(R.id.jeweleryDetailsCartContain);
//            metal = itemView.findViewById(R.id.item); // Ensure you have this TextView in the layout

            // Debugging: check for null references
            if (title == null || price == null || color == null || size == null || caret == null || metal == null) {
                Log.e("CartAdapter", "One or more views are null in CartViewHolder");
            }
        }
    }

}
