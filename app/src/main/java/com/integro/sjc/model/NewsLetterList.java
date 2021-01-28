package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsLetterList {

    private String success;

    @SerializedName("sjc_newsletter")
    private ArrayList<NewsLetter> newsLetterArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<NewsLetter> getNewsLetterArrayList() {
        return newsLetterArrayList;
    }

    public void setNewsLetterArrayList(ArrayList<NewsLetter> newsLetterArrayList) {
        this.newsLetterArrayList = newsLetterArrayList;
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
