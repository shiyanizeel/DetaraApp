package com.skad.myapplication.Activity.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.Models.ApiModel.ProductModel;
import com.skad.myapplication.Models.HomeModel.JewelleryTypeModel;
import com.skad.myapplication.R;
import com.skad.myapplication.adapter.HomeActivity.JewelleryTypeAdapter;
import com.skad.myapplication.adapter.ProductListAdapter.RingStyleAdapter;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;
import com.skad.myapplication.utils.Api.ApiHelper;
import com.skad.myapplication.utils.Api.CollectionCallback;
import com.skad.myapplication.utils.Api.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RingItemActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    RecyclerView ringItemTypeRV,ringItemRV;
    ArrayList<JewelleryTypeModel> jewelleryDataModelArrayList = new ArrayList<>();
    ArrayList<String> productCollectionId = new ArrayList<>();

    List<Product> productArrayList = new ArrayList<>();
    RingStyleAdapter ringStyleAdapter;
    TextView productCount;
    LinearLayout ringItemfilterItem;
    ProgressBar progressBar;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RingItemActivity.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ring_item);

        ringItemTypeRV = findViewById(R.id.ringItemTypeRV);
        ringItemRV = findViewById(R.id.ringItemRV);
        productCount = findViewById(R.id.ringItemproductcount);
        ringItemfilterItem = findViewById(R.id.ringItemfilterItem);
        ringItemfilterItem = findViewById(R.id.ringItemfilterItem);
        String name = getIntent().getStringExtra("jewelleryName");
        boolean showFilter = getIntent().getBooleanExtra("showFilter",true);
//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        assert name != null;
        getJewelleryDataModelArrayList(name);
        //TODO :- hide or display the filter view
        if(!showFilter) {
            ringItemTypeRV.setVisibility(View.INVISIBLE);
            ringItemfilterItem.setVisibility(View.INVISIBLE);
        }

        JewelleryTypeAdapter jewelleryTypeAdapter = new JewelleryTypeAdapter(getApplicationContext(), jewelleryDataModelArrayList, position -> getCollectionInfo(productCollectionId.get(position)));
        ringItemTypeRV.setAdapter(jewelleryTypeAdapter);
        ringItemTypeRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));





        ringStyleAdapter = new RingStyleAdapter(getApplicationContext(), productArrayList, false, position -> {


        });
        ringItemRV.setAdapter(ringStyleAdapter);
        ringItemRV.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        ringStyleAdapter.notifyDataSetChanged();
        getCollectionInfo(productCollectionId.get(0));
    }
    public void getJewelleryDataModelArrayList(String jewelleryName) {
        jewelleryDataModelArrayList.clear();
        // TODO :- convert this into switch cases
        if(jewelleryName.equalsIgnoreCase(String.valueOf(R.string.ER_engagement_ring_style))) {
            //jewelleryType

            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.solitraire,"Solitaire"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.accent,"Accent"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.three_stone,"Three \n Stone"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.hidden_halo,"Hidden \n Halo"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.yellow_gold,"Yellow \n Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.yellow_gold,"White \n Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.nature_inspired,"Natured \n Inspired"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.halo,"Halo"));

            //Product type
            productCollectionId.add("433116774650");
            productCollectionId.add("433117004026");
            productCollectionId.add("433117036794");
            productCollectionId.add("433055236346");
            productCollectionId.add("433117102330");
            productCollectionId.add("433117135098");
            productCollectionId.add("433117233402");
            productCollectionId.add("433055039738");

        } else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.ER_shape_By_Diamond))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.round,"Round"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.ovel,"Ovel"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.emrald,"Emerald"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.cushion,"Cushion"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.pear,"Pear"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.radiant,"Radiant"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.princess,"Princess"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.marquis,"Marquise"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.asscher,"Asscher"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.heart,"Heart"));

            productCollectionId.add("434117476602");
            productCollectionId.add("434117902586");
            productCollectionId.add("434118557946");
            productCollectionId.add("434118492410");
            productCollectionId.add("434118361338");
            productCollectionId.add("434118295802");
            productCollectionId.add("434118525178");
            productCollectionId.add("434118656250");
            productCollectionId.add("434119082234");
            productCollectionId.add("434118918394");

        }
        else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.Benavi_jewellery))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Earings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Rings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"neckleces"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Bracelats"));

            productCollectionId.add("432847716602");
            productCollectionId.add("432852271354");
            productCollectionId.add("432851910906");
            productCollectionId.add("434530320634");

        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.Benavi_shape_By_Shope))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Diamond Stud Earrings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Tennis Bracelets"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Fashion Rings"));

            productCollectionId.add("434527895802");
            productCollectionId.add("434530320634");
            productCollectionId.add("432852271354");

        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.Jewellery))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Earings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Pandant"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Bracelates"));

            productCollectionId.add("433589944570");
            productCollectionId.add("433590468858");
            productCollectionId.add("433590239482");


        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.Jewellery_shope_By_Shape))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JewelerydiamondStudy"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JeweleryTennisBraslats"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JeweleryFashionRings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JeweleryStakingRings"));

            productCollectionId.add("433774100730");
            productCollectionId.add("432852664570");
            productCollectionId.add("432852271354");
            productCollectionId.add("434846925050");

        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.WR_by_Women))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Women Wedding Rings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Women Diamond Rings"));

            productCollectionId.add("433023615226");
            productCollectionId.add("433023746298");

        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.WR_by_Man))) {
            productCollectionId.add("433024041210");

        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.WR_by_Metal))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Platinum"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Yellow Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"White Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Rose Gold"));

            productCollectionId.add("433024237818");
            productCollectionId.add("433117102330");
            productCollectionId.add("433117135098");
            productCollectionId.add("433025515770");

        }
        else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.Gemstone_ready_To_Shope))) {

            productCollectionId.add("433054482682");

        }else if (jewelleryName.equalsIgnoreCase(String.valueOf(R.string.Gemstone_present))) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"MoissaniteRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"SapphireRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"AquamarineRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"MorganiteRing"));

            productCollectionId.add("433023123706");
            productCollectionId.add("433023123706");
            productCollectionId.add("433023123706");
            productCollectionId.add("433023123706");

        }
        else if(jewelleryName.equalsIgnoreCase("Round")) {
            productCollectionId.add("434117476602");
        }else if(jewelleryName.equalsIgnoreCase("Ovel")) {
            productCollectionId.add("434117902586");
        }else if(jewelleryName.equalsIgnoreCase("Emerald")) {
            productCollectionId.add("434118557946");

        }else if(jewelleryName.equalsIgnoreCase("Cushion")) {
            productCollectionId.add("434118492410");

        }else if(jewelleryName.equalsIgnoreCase("Pear")) {
            productCollectionId.add("434118361338");

        }else if(jewelleryName.equalsIgnoreCase("Radiant")) {
            productCollectionId.add("434118295802");

        }else if(jewelleryName.equalsIgnoreCase("Princess")) {
            productCollectionId.add("434118525178");

        }else if(jewelleryName.equalsIgnoreCase("Marquise")) {
            productCollectionId.add("434118656250");

        }else if(jewelleryName.equalsIgnoreCase("Asscher")) {
            productCollectionId.add("434119082234");

        }else if(jewelleryName.equalsIgnoreCase("Heart")) {
            productCollectionId.add("434118918394");
        }

        else if(jewelleryName.equalsIgnoreCase("Aniversary Gift")) {
            productCollectionId.add("433018503418");
            System.out.println("anivarsary"+  productCollectionId.add("433018503418"));

        }else if(jewelleryName.equalsIgnoreCase("Bridal Party Gift")) {
            productCollectionId.add("433023385850");
        }else if(jewelleryName.equalsIgnoreCase("Birthday Gift")) {
            productCollectionId.add("433023844602");
        }else if(jewelleryName.equalsIgnoreCase("Velentine Gift")) {
            productCollectionId.add("433025450234");
        }else if(jewelleryName.equalsIgnoreCase("Graduation Gift")) {
            productCollectionId.add("433025548538");
        }
       else if(jewelleryName.equalsIgnoreCase("ENGAGEMENT RINGS") ){
            //jewelleryType

            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.solitraire,"Solitaire"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.accent,"Accent"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.three_stone,"Three \n Stone"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.hidden_halo,"Hidden \n Halo"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.yellow_gold,"Yellow \n Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.yellow_gold,"White \n Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.nature_inspired,"Natured \n Inspired"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.halo,"Halo"));

            //Product type
            productCollectionId.add("433116774650");
            productCollectionId.add("433117004026");
            productCollectionId.add("433117036794");
            productCollectionId.add("433055236346");
            productCollectionId.add("433117102330");
            productCollectionId.add("433117135098");
            productCollectionId.add("433117233402");
            productCollectionId.add("433055039738");

        }else if (jewelleryName.equalsIgnoreCase("DIAMONDS")) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.round,"Round"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.ovel,"Ovel"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.emrald,"Emerald"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.cushion,"Cushion"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.pear,"Pear"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.radiant,"Radiant"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.princess,"Princess"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.marquis,"Marquise"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.asscher,"Asscher"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.heart,"Heart"));

            productCollectionId.add("434117476602");
            productCollectionId.add("434117902586");
            productCollectionId.add("434118557946");
            productCollectionId.add("434118492410");
            productCollectionId.add("434118361338");
            productCollectionId.add("434118295802");
            productCollectionId.add("434118525178");
            productCollectionId.add("434118656250");
            productCollectionId.add("434119082234");
            productCollectionId.add("434118918394");

        }
        else if (jewelleryName.equalsIgnoreCase("WEDDING RINGS")) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Women Wedding Rings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Women Diamond Rings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Men's Diamond Rings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Platinum"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Yellow Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"White Gold"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Rose Gold"));

            productCollectionId.add("433023615226");
            productCollectionId.add("433023746298");
            productCollectionId.add("433024041210");
            productCollectionId.add("433024237818");
            productCollectionId.add("433117102330");
            productCollectionId.add("433117135098");
            productCollectionId.add("433025515770");


        }
        else if (jewelleryName.equalsIgnoreCase("GEMSTONES")) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"MoissaniteRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"SapphireRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"AquamarineRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"MorganiteRing"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Shope By Shape"));

            productCollectionId.add("433023123706");
            productCollectionId.add("433023123706");
            productCollectionId.add("433023123706");
            productCollectionId.add("433023123706");
            productCollectionId.add("433054482682");

        }
        else if (jewelleryName.equalsIgnoreCase("JEWELERY")) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Earings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Pandant"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Bracelates"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JewelerydiamondStudy"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JeweleryTennisBraslats"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JeweleryFashionRings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"JeweleryStakingRings"));


            productCollectionId.add("433589944570");
            productCollectionId.add("433590468858");
            productCollectionId.add("433590239482");
            productCollectionId.add("433774100730");
            productCollectionId.add("432852664570");
            productCollectionId.add("432852271354");
            productCollectionId.add("434846925050");

        }
        else if (jewelleryName.equalsIgnoreCase("BENAVI® SILVER")) {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Earings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Rings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"neckleces"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Bracelats"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Diamond Stud Earrings"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Tennis Bracelets"));
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.diamondring,"Fashion Rings"));

            productCollectionId.add("432847716602");
            productCollectionId.add("432852271354");
            productCollectionId.add("432851910906");
            productCollectionId.add("434530320634");
            productCollectionId.add("434527895802");
            productCollectionId.add("434530320634");
            productCollectionId.add("432852271354");
        }




        else {
            jewelleryDataModelArrayList.add(new JewelleryTypeModel(R.drawable.emrald,"abc"));

        }

    }



    public static void getCollectionInfo(Context context, String collectionId, CollectionCallback callback) {
        RingItemActivity.context = context;
        ApiHelper apiService = RetrofitInstance.getInstance().apiInterface;
        Call<ProductModel> call = apiService.getProductDetails("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", collectionId);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ProductModel> call, @NonNull Response<ProductModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("gettingRes", "onResponse: " + response.body().getProducts().size());
//                    collectionData = response.body();
                    callback.onSuccess(response.body());
                } else {
                    Log.e("Response Body", "onResponse: Body is null or not successful");
                    callback.onError("Response body is null or not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductModel> call, @NonNull Throwable t) {
                Log.e("onFailure", "onFailure: " + t.getLocalizedMessage());
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    private void getCollectionInfo(String collectionId) {
        productArrayList.clear();
        progressBar = findViewById(R.id.progressBarRingItem);
        progressBar.setVisibility(View.VISIBLE);
        getCollectionInfo(RingItemActivity.this, collectionId, new CollectionCallback() {

            public void onSuccess(ProductModel collectionData) {
                List<Product> allData = SharedPreferencesHelper.getInstance(getApplicationContext(),"myFavs").markFavorites(collectionData.getProducts());

                productArrayList.addAll(allData);
                progressBar.setVisibility(View.INVISIBLE);
                ringStyleAdapter.notifyDataSetChanged();
                productCount.setText(""+allData.size());

//                productCount.setText(allData.size());
            }

            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})



            @Override
            public void onError(String errorMessage) {

            }
        });
    }

}
//
//
//    }
//


