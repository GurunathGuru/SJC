package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryAlbumList {

    private String success;

    @SerializedName("sjc_gallery")
    private ArrayList<GalleryAlbum> galleryAlbumArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<GalleryAlbum> getGalleryAlbumArrayList() {
        return galleryAlbumArrayList;
    }

    public void setGalleryAlbumArrayList(ArrayList<GalleryAlbum> galleryAlbumArrayList) {
        this.galleryAlbumArrayList = galleryAlbumArrayList;
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
