package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnnouncementsList {
    @SerializedName("sjc_announcements")
    ArrayList<Announcements>announcementsArrayList;

    private String success;

    private String message;

    public ArrayList<Announcements>getAnnouncementsArrayList(){
        return announcementsArrayList;
    }
    public void setAnnouncementsArrayList(ArrayList<Announcements>announcementsArrayList){
        this.announcementsArrayList=announcementsArrayList;
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
