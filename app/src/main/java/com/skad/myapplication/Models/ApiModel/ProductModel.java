package com.skad.myapplication.Models.ApiModel;

import com.skad.myapplication.Models.ApiModel.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private List<Product> products = new ArrayList<>(); // Initialize the list

    public List<Product> getProducts() {
        return products;
    }
}

