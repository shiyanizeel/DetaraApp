package com.skad.myapplication.utils.Api;


import com.skad.myapplication.Models.ApiModel.ProductModel;

public interface CollectionCallback {
    void onSuccess(ProductModel collectionData);
    void onError(String errorMessage);
}
