package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DistinguishAlumniList {

    private String success;

    @SerializedName("sjc_alumni")
    private ArrayList<DistinguishAlumni> distinguishAlumniArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<DistinguishAlumni> getDistinguishAlumniArrayList() {
        return distinguishAlumniArrayList;
    }

    public void setDistinguishAlumniArrayList(ArrayList<DistinguishAlumni> distinguishAlumniArrayList) {
        this.distinguishAlumniArrayList = distinguishAlumniArrayList;
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
