package com.skad.myapplication.Models.HomeModel;

public class JewelleryTypeModel {
    int jewelleryShapeImg;
    String jewelleryName;

    public int getJewelleryShapeImg() {
        return jewelleryShapeImg;
    }

    public void setJewelleryShapeImg(int jewelleryShapeImg) {
        this.jewelleryShapeImg = jewelleryShapeImg;
    }

    public String getJewelleryName() {
        return jewelleryName;
    }

    public void setJewelleryName(String jewelleryName) {
        this.jewelleryName = jewelleryName;
    }

    public JewelleryTypeModel(int jewelleryShapeImg, String jewelleryName) {
        this.jewelleryShapeImg = jewelleryShapeImg;
        this.jewelleryName = jewelleryName;
    }
}
