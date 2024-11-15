package com.skad.myapplication.helper.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.Models.ApiModel.ProductDetailsModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPreferencesHelper {

    private static  String KEY_USERS ="";

    private static volatile SharedPreferencesHelper instance; // Singleton instance
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    // Private constructor to prevent direct instantiation
    private SharedPreferencesHelper(Context context,String sharedName) {
        sharedPreferences = context.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Public method to provide access to the singleton instance
    public static SharedPreferencesHelper getInstance(Context context,String sharedName) {
        KEY_USERS = sharedName;
        if (instance == null) {
            synchronized (SharedPreferencesHelper.class) {
                if (instance == null) {
                    instance = new SharedPreferencesHelper(context.getApplicationContext(),sharedName);
                }
            }
        }
        return instance;
    }

    // Method to get the count of saved products
    public int getCountProduct() {
        List<Product> users = getUsers();
        return users.size();
    }

    // Method to save a list of products
    public void saveUsers(List<Product> users) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(users);
        editor.putString(KEY_USERS, json);
        editor.apply();
    }

    // Method to retrieve the list of products
    public List<Product> getUsers() {
        String json = sharedPreferences.getString(KEY_USERS, null);
        if (json == null) {
            return new ArrayList<>(); // Return an empty list if no users are stored
        }
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Method to add a single product to the list
    public void addUser(Product user) {
        List<Product> users = getUsers(); // Retrieve the existing list
        users.add(user); // Add the new product
        saveUsers(users); // Save the updated list
    }

    // Method to remove a product from the list
    public void removeProduct(Product product) {
        List<Product> products = getUsers();
        ArrayList<Product> tempProduct = new ArrayList<>();
        products.remove(product);
        for(int i = 0;i<products.size();i++) {
            if(products.get(i).getId() != product.getId()) {
                tempProduct.add(products.get(i));
            }
        }
        saveUsers(tempProduct);
    }
    public List<Product> markFavorites(List<Product> apiProducts) {
        // Retrieve the list of products stored in SharedPreferences
        List<Product> storedProducts = getUsers();

        // Create a Set of stored product IDs for fast lookup
        Set<Long> storedProductIds = new HashSet<>();
        for (Product product : storedProducts) {
            storedProductIds.add(product.getId()); // Assuming getId() returns a unique identifier
        }

        // Iterate over the API products and mark as favorite if present in stored products
        for (Product apiProduct : apiProducts) {
            if (storedProductIds.contains(apiProduct.getId())) {
                apiProduct.setFav(true); // Assuming setFav(boolean value) sets the favorite status
            }
        }

        // Return the modified list
        return apiProducts;
    }
    // Save a product to the cart (appending to the list)
    public void addProductToCart(ProductDetailsModel product) {
        List<ProductDetailsModel> cartList = getCart(); // Get current cart list
        cartList.add(product);  // Add the new product
        saveCart(cartList); // Save the updated list
    }

    // Retrieve the list of cart products
    public List<ProductDetailsModel> getCart() {
        String json = sharedPreferences.getString("cart_items", "[]");  // Default is an empty list
        Type type = new TypeToken<List<ProductDetailsModel>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    // Save the cart list (convert list to JSON string)
    public void saveCart(List<ProductDetailsModel> cartList) {
        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cart_items", json);
        editor.apply();
    }

    // Remove a specific product from the cart
    // Remove a specific product from the cart
    public void removeProductFromCart(ProductDetailsModel productDetailsModel) {
        // Retrieve the current cart list
        List<ProductDetailsModel> cartList = getCart();
        List<ProductDetailsModel> updatedCartList = new ArrayList<>();

        // Filter out the item to be removed by matching cartId
        for (ProductDetailsModel product : cartList) {
            if (product.getCartId() != productDetailsModel.getCartId()) {
                updatedCartList.add(product);
            }
        }
        Log.e("newSize", "removeProductFromCart: "+updatedCartList.size() );
        // Save the updated list to SharedPreferences
        saveCart(updatedCartList);
    }




}




