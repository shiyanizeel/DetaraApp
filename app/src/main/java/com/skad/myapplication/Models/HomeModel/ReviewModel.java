package com.skad.myapplication.Models.HomeModel;

public class ReviewModel {
    int reviewImg;
    String reviewText;

    public int getReviewImg() {
        return reviewImg;
    }

    public void setReviewImg(int reviewImg) {
        this.reviewImg = reviewImg;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public ReviewModel(int reviewImg, String reviewText) {
        this.reviewImg = reviewImg;
        this.reviewText = reviewText;
    }
}
