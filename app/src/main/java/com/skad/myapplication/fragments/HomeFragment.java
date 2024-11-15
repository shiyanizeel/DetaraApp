package com.skad.myapplication.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Activity.HomeActivity.RingItemActivity;
import com.skad.myapplication.Models.HomeModel.HomePicModel;
import com.skad.myapplication.Models.HomeModel.JewelleryTypeModel;
import com.skad.myapplication.Models.HomeModel.ReviewModel;
import com.skad.myapplication.Models.HomeModel.ServiceModel;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.HomeActivity.HomePicAdapter;
import com.skad.myapplication.adapter.HomeActivity.JewelleryShapeAdapter;
import com.skad.myapplication.adapter.HomeActivity.JewelleryTypeAdapter;
import com.skad.myapplication.adapter.HomeActivity.ReviewAdapter;
import com.skad.myapplication.adapter.HomeActivity.ServiceAdapter;
import com.skad.myapplication.helper.Interfaces.ClickInterFace;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView jewelleryRV, jewelleryShapeRV;
    ArrayList<JewelleryTypeModel> jeweleryTypeModelArrayList = new ArrayList<>();
    ArrayList<JewelleryTypeModel> jeweleryTypeShapeModelArrayList = new ArrayList<>();
    ArrayList<ReviewModel> reviewModelArrayList = new ArrayList<>();
    ArrayList<HomePicModel> homePicModelArrayList = new ArrayList<>();
    ArrayList<ServiceModel> serviceModelArrayList = new ArrayList<>();
    ReviewAdapter reviewAdapter;
    HomePicAdapter homePicAdapter;
    ServiceAdapter serviceAdapter;
    ImageSwitcher imageSwitcher;
    ImageButton btPrevious, btNext;
    ImageSwitcher imageSwitcher1;
    RecyclerView reviewrecycleviewHome, homepicrecycleview, servicerecycleviewHome;
    int[] imageList = {R.drawable.pickertext1, R.drawable.pickertext2, R.drawable.pickertext3, R.drawable.pickertext4};
    int count = imageList.length;
    int currentIndex1 = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //============================================ Find the ids ===================================
        jewelleryRV = view.findViewById(R.id.jewelleryTypeRV);
        jewelleryShapeRV = view.findViewById(R.id.jewelleryShapeRV);

//  ==================== set Search Contain ===================


        //============================================ Jewellery Types Recycle view ===================================
        setJewelleryRecycleView();
        //============================================ Jewellery Types Shape Recycle view ===================================
        setJeweleryShape();

//        /=========  set gift card ======================
        // Find your ImageView in the layout
        ImageView giftCardHome = view.findViewById(R.id.giftCardHome);

// Set an OnClickListener to handle the click
        giftCardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GiftFragment giftFragment = new GiftFragment();

                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, giftFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //  =============================   letest jwellary set ========================


//        ==============================  materials diamond ==========================


//         ===============================   set offer ================================
        // Step 1: Initialize the ImageSwitcher and set the factory
        imageSwitcher = view.findViewById(R.id.imageSwitcherHome);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                return imageView;
            }
        });
        int[] images = {R.drawable.offer1, R.drawable.offer2};
        final int[] currentIndex = {0};

// Step 3: Set the first image
        imageSwitcher.setImageResource(images[currentIndex[0]]);

// Step 4: Start automatic image switching
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Update the current index and switch the image
                currentIndex[0] = (currentIndex[0] + 1) % images.length;
                imageSwitcher.setImageResource(images[currentIndex[0]]);
                handler.postDelayed(this, 2000); // Change image every 2 seconds
            }
        };
        handler.postDelayed(runnable, 2000); // Start with a delay of 2 seconds

// Step 5: Set onClickListener to switch to OfferFragment when an image is clicked
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of OfferFragment
                OfferFragment offerFragment = new OfferFragment();

                // Pass the selected image as an argument to the fragment
                Bundle bundle = new Bundle();
                bundle.putInt("selectedImage", images[currentIndex[0]]);
                offerFragment.setArguments(bundle);

                // Perform fragment transaction to replace the current fragment with OfferFragment
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, offerFragment);  // R.id.fragment_container should be the ID of your FrameLayout in the activity's layout
                transaction.addToBackStack(null);  // Add to backstack to allow back navigation
                transaction.commit();
            }
        });

//        ==================   customer Review ==============
        reviewrecycleviewHome = view.findViewById(R.id.reviewRecycleviewHome);
        reviewModelArrayList.add(new ReviewModel(R.drawable.review1, "hsdvasghcfsdyufghsdjhkfsgcfhsgvfhjsd \n gavdghasfcysucfasytcfasytdf"));
        reviewModelArrayList.add(new ReviewModel(R.drawable.review1, "hsdvasghcfsdyufghsdjhkfsgcfhsgvfhjsd \n gavdghasfcysucfasytcfasytdf"));
        reviewModelArrayList.add(new ReviewModel(R.drawable.review1, "hsdvasghcfsdyufghsdjhkfsgcfhsgvfhjsd \n gavdghasfcysucfasytcfasytdf"));
        reviewModelArrayList.add(new ReviewModel(R.drawable.review1, "hsdvasghcfsdyufghsdjhkfsgcfhsgvfhjsd \n gavdghasfcysucfasytcfasytdf"));
        reviewModelArrayList.add(new ReviewModel(R.drawable.review1, "hsdvasghcfsdyufghsdjhkfsgcfhsgvfhjsd \n gavdghasfcysucfasytcfasytdf"));
        reviewModelArrayList.add(new ReviewModel(R.drawable.review1, "hsdvasghcfsdyufghsdjhkfsgcfhsgvfhjsd \n gavdghasfcysucfasytcfasytdf"));
        reviewAdapter = new ReviewAdapter(getContext(), reviewModelArrayList);
        reviewrecycleviewHome.setAdapter(reviewAdapter);
        reviewrecycleviewHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


// ============================    homePic set =================================
        homepicrecycleview = view.findViewById(R.id.picHome);
        homePicModelArrayList.add(new HomePicModel(R.drawable.img1));
        homePicModelArrayList.add(new HomePicModel(R.drawable.img2));
        homePicModelArrayList.add(new HomePicModel(R.drawable.img3));
        homePicModelArrayList.add(new HomePicModel(R.drawable.img4));
        homePicModelArrayList.add(new HomePicModel(R.drawable.img5));
        homePicModelArrayList.add(new HomePicModel(R.drawable.img6));
        homePicAdapter = new HomePicAdapter(getContext(), homePicModelArrayList);
        homepicrecycleview.setAdapter(homePicAdapter);
        homepicrecycleview.setLayoutManager(new GridLayoutManager(getContext(), 3));


//           =========================== service set =======================
        servicerecycleviewHome = view.findViewById(R.id.serviceRecycleview);
        serviceModelArrayList.add(new ServiceModel(R.drawable.worldshipping, "World Wide\n" + "Shipping"));
        serviceModelArrayList.add(new ServiceModel(R.drawable.certifyjwellary, "100% Certified\n" + "Jewellery"));
        serviceModelArrayList.add(new ServiceModel(R.drawable.customization, "Coustomization"));
        serviceModelArrayList.add(new ServiceModel(R.drawable.trasparancy, "100% Transparency"));
        serviceModelArrayList.add(new ServiceModel(R.drawable.worlddesign, "A world of designs"));
        serviceModelArrayList.add(new ServiceModel(R.drawable.nocompromize, "No Compromise\n" + "On Ethics"));
        serviceAdapter = new ServiceAdapter(getContext(), serviceModelArrayList);
        servicerecycleviewHome.setAdapter(serviceAdapter);
        servicerecycleviewHome.setLayoutManager(new GridLayoutManager(getContext(), 3));


        //          ==========================    set picker text ===================
        btPrevious = view.findViewById(R.id.bt_previous);
        btNext = view.findViewById(R.id.bt_next);
        imageSwitcher1 = view.findViewById(R.id.image_switcher);
        imageSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView1 = new ImageView(getContext());
                imageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);


                imageView1.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                ));


                return imageView1;
            }
        });
        imageSwitcher1.setImageResource(imageList[0]);
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageSwitcher1.setInAnimation(getContext(), R.anim.rightbtn_anim);
                imageSwitcher1.setOutAnimation(getContext(), R.anim.leftbtn_anim);
                --currentIndex1;


                if (currentIndex1 < 0)
                    currentIndex1 = imageList.length - 1;

                imageSwitcher1.setImageResource(imageList[currentIndex1]);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageSwitcher1.setInAnimation(getContext(), R.anim.leftbtn_anim);
                imageSwitcher1.setOutAnimation(getContext(), R.anim.rightbtn_anim);
                currentIndex1++;
                if (currentIndex1 == count)
                    currentIndex1 = 0;
                imageSwitcher1.setImageResource(imageList[currentIndex1]);
            }
        });


        return view;
    }


    //    ============= other methods =============
    @SuppressLint("NotifyDataSetChanged")
    public void setJewelleryRecycleView() {
        jeweleryTypeModelArrayList.add(new JewelleryTypeModel(R.drawable.engajring, "ENGAGEMENT RINGS"));
        jeweleryTypeModelArrayList.add(new JewelleryTypeModel(R.drawable.diamond, "DIAMONDS"));
        jeweleryTypeModelArrayList.add(new JewelleryTypeModel(R.drawable.weddingring, "WEDDING RINGS"));
        jeweleryTypeModelArrayList.add(new JewelleryTypeModel(R.drawable.gemstones, "GEMSTONES"));
        jeweleryTypeModelArrayList.add(new JewelleryTypeModel(R.drawable.jewellery, "JEWELERY"));
        jeweleryTypeModelArrayList.add(new JewelleryTypeModel(R.drawable.benavisilver, "BENAVI® SILVER"));
        JewelleryTypeAdapter jewelleryTypeAdapter = new JewelleryTypeAdapter(getContext(), jeweleryTypeModelArrayList, new ClickInterFace() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), RingItemActivity.class);
                intent.putExtra("jewelleryName", jeweleryTypeModelArrayList.get(position).getJewelleryName());
//                intent.putExtra("showFilter",false);
                startActivity(intent);

            }
        });
        jewelleryRV.setAdapter(jewelleryTypeAdapter);
        jewelleryRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        jewelleryTypeAdapter.notifyDataSetChanged();

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setJeweleryShape() {
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.round, "Round"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.ovel, "Oval"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.emrald, "Emerald"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.cushion, "Cushion"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.pear, "Pear"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.radiant, "Radiant"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.princess, "Princess"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.marquis, "Marquise"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.asscher, "Asscher"));
        jeweleryTypeShapeModelArrayList.add(new JewelleryTypeModel(R.drawable.heart, "Heart"));
        JewelleryShapeAdapter jewelleryShapeAdapter = new JewelleryShapeAdapter(getContext(), jeweleryTypeShapeModelArrayList, new ClickInterFace() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), RingItemActivity.class);
                intent.putExtra("jewelleryName", jeweleryTypeShapeModelArrayList.get(position).getJewelleryName());
                intent.putExtra("showFilter", false);
                startActivity(intent);
            }
        });
        jewelleryShapeRV.setAdapter(jewelleryShapeAdapter);
        jewelleryShapeRV.setLayoutManager(new GridLayoutManager(getContext(), 5));
        jewelleryShapeAdapter.notifyDataSetChanged();
    }
}