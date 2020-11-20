package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlacementList {
    private String success;

    @SerializedName("sjc_placement")
    private ArrayList<Placement> placementArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<Placement> getPlacementArrayList() {
        return placementArrayList;
    }

    public void setPlacementArrayList(ArrayList<Placement> placementArrayList) {
        this.placementArrayList = placementArrayList;
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
