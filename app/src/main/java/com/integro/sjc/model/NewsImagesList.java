package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsImagesList {

    private String success;

    @SerializedName("newsimages")
    private ArrayList<NewsImages> newsImagesArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<NewsImages> getNewsImagesArrayList() {
        return newsImagesArrayList;
    }

    public void setNewsImagesArrayList(ArrayList<NewsImages> newsImagesArrayList) {
        this.newsImagesArrayList = newsImagesArrayList;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }
}
