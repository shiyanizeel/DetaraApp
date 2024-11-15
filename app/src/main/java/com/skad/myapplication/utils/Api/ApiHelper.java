package com.skad.myapplication.utils.Api;



import com.skad.myapplication.Models.ApiModel.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("collections/{id}/products.json")
    Call<ProductModel> getProductDetails(
            @Header("X-Shopify-Access-Token") String accessToken,
            @Path("id") String collectionId
    );
}
