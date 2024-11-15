package com.skad.myapplication.utils.Api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {
    private static final String BASE_URL = "https://bad88f-3b.myshopify.com/admin/api/2022-10/";

    public static RetrofitInstance instance;
    public ApiHelper apiInterface;

    RetrofitInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiHelper.class);
    }
    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }
}

