package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FacilitiesList {
@SerializedName("sjc_facilities")
    ArrayList<Facilities>facilitiesArrayList;

    private String success;

    private String message;

    public ArrayList<Facilities>getFacilitiesArrayList(){
        return facilitiesArrayList;
    }
   public void setFacilitiesArrayList(ArrayList<Facilities>facilitiesArrayList){

   }
    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
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
