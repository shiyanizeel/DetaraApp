package com.skad.myapplication.Models.JewelleryData;

public class JewelleryDataModel {
    String jewelleryName;
    int imagesName;

    public String getJewelleryName() {
        return jewelleryName;
    }

    public void setJewelleryName(String jewelleryName) {
        this.jewelleryName = jewelleryName;
    }

    public int getImagesName() {
        return imagesName;
    }

    public void setImagesName(int imagesName) {
        this.imagesName = imagesName;
    }

    public JewelleryDataModel(String jewelleryName, int imagesName) {
        this.jewelleryName = jewelleryName;
        this.imagesName = imagesName;
    }
}
