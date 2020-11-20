package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryImagesList {
    private String success;

    @SerializedName("galleryimages")
    private ArrayList<GalleryImages> galleryImagesArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<GalleryImages> getGalleryImagesArrayList() {
        return galleryImagesArrayList;
    }

    public void setGalleryImagesArrayList(ArrayList<GalleryImages> galleryImagesArrayList) {
        this.galleryImagesArrayList = galleryImagesArrayList;
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
