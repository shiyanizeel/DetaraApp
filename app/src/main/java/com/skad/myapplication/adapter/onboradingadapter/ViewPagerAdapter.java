package com.skad.myapplication.adapter.onboradingadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.skad.myapplication.R;


public class ViewPagerAdapter extends PagerAdapter {

    private final LayoutInflater layoutInflater;
    private final Integer[] images = {R.drawable.viewpage1, R.drawable.viewpage2, R.drawable.viewpage3};
    private final String[] headings = {"Timeless Diamonds\n" +
            "Eternal Luxury", "Up to 50% off on your \n" +
            "First Order", "Free Shipping on\n" +
            "orders over 799 USD"};
    private final String[] subheadings = {"Experience the Luxury of Shopping. Explore our\n" +
            "Handpicked Selection of Exquisite Diamond Jewelry.", "Luxurious Diamonds, Exceptional Value:\n" +
            "Up to 50% Off Your First Purchase", "Spend $799 or more and enjoy free shipping worldwide.\n" +
            "Happy Shopping.!"};

    public ViewPagerAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.viewpage_item, container, false);
        ImageView imageView = view.findViewById(R.id.viewpageImg);
        TextView headingTextView = view.findViewById(R.id.viewpageHead);
        TextView subheadingTextView = view.findViewById(R.id.viewPageSubText);

        imageView.setImageResource(images[position]);
        headingTextView.setText(headings[position]);
        subheadingTextView.setText(subheadings[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}