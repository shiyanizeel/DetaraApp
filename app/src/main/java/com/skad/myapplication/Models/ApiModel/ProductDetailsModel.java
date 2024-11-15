package com.skad.myapplication.Models.ApiModel;

import java.io.Serializable;

public class ProductDetailsModel implements Serializable {
    long cartId;
    private String imageUrl;
    private String producttitle;
    private String price;
    private String color;
    private String size;
    private String caret;
    private String metal;
    private String weight;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ProductDetailsModel(long cartId, String imageUrl, String producttitle, String price, String color, String size, String caret, String metal) {
       this.cartId = cartId;
        this.imageUrl = imageUrl;
        this.producttitle = producttitle;
        this.price = price;
        this.color = color;
        this.size = size;
        this.caret = caret;
        this.metal = metal;

    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public ProductDetailsModel() {
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCaret() {
        return caret;
    }

    public void setCaret(String caret) {
        this.caret = caret;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }
}