package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GratitudeList {

    private String success;

    private String message;

    @SerializedName("sjc_gratitude")
    private ArrayList<Gratitude> gratitudeArrayList;

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

    public ArrayList<Gratitude> getGratitudeArrayList() {
        return gratitudeArrayList;
    }

    public void setGratitudeArrayList(ArrayList<Gratitude> gratitudeArrayList) {
        this.gratitudeArrayList = gratitudeArrayList;
    }
}
