package com.skad.myapplication.Models.HomeModel;

public class ServiceModel {
    int serviceImg;
    String serviceHead;

    public int getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(int serviceImg) {
        this.serviceImg = serviceImg;
    }

    public String getServiceHead() {
        return serviceHead;
    }

    public void setServiceHead(String serviceHead) {
        this.serviceHead = serviceHead;
    }

    public ServiceModel(int serviceImg, String serviceHead) {
        this.serviceImg = serviceImg;
        this.serviceHead = serviceHead;
    }
}
