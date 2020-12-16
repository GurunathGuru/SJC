package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryList {

    private String success;

    @SerializedName("sjc_gallery")
    private ArrayList<Gallery> galleryArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<Gallery> getGalleryArrayList() {
        return galleryArrayList;
    }

    public void setGalleryArrayList(ArrayList<Gallery> galleryArrayList) {
        this.galleryArrayList = galleryArrayList;
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
