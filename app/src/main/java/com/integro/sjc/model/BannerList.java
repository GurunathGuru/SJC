package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerList {

    @SerializedName("sjc_banner")
    private ArrayList<Banner> bannerArrayList;

    private String success;

    private String message;

    public ArrayList<Banner> getBannerArrayList() {
        return bannerArrayList;
    }

    public void setBannerArrayList(ArrayList<Banner> bannerArrayList) {
        this.bannerArrayList = bannerArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
