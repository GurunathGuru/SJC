package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AboutSjcList {
    @SerializedName("sjc_about")
    private ArrayList<AboutSjc> aboutSjcArrayList;

    private int success;
    private String message;

    public ArrayList<AboutSjc>getAboutSjcArrayList(){
        return aboutSjcArrayList;
    }

    public AboutSjcList(ArrayList<AboutSjc>aboutSjcArrayList)
    {
        this.aboutSjcArrayList=aboutSjcArrayList;
    }

    public int getSuccess ()
    {
        return success;
    }

    public void setSuccess (int success)
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
