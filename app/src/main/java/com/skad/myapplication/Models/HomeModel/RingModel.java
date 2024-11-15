package com.skad.myapplication.Models.HomeModel;

  public class RingModel {
     String ringName;
     int imageResId;
    String ringPrice;
    int favouriteIcon,cartIcon;

    public RingModel(String ringName, int imageResId, String ringPrice, int favouriteIcon, int cartIcon) {
        this.ringName = ringName;
        this.imageResId = imageResId;
        this.ringPrice = ringPrice;
        this.favouriteIcon = favouriteIcon;
        this.cartIcon = cartIcon;
    }

    public int getFavouriteIcon() {
        return favouriteIcon;
    }

    public void setFavouriteIcon(int favouriteIcon) {
        this.favouriteIcon = favouriteIcon;
    }

    public int getCartIcon() {
        return cartIcon;
    }

    public void setCartIcon(int cartIcon) {
        this.cartIcon = cartIcon;
    }

    public String getRingName() {
        return ringName;
    }

    public void setRingName(String ringName) {
        this.ringName = ringName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getRingPrice() {
        return ringPrice;
    }

    public void setRingPrice(String ringPrice) {
        this.ringPrice = ringPrice;
    }

}
