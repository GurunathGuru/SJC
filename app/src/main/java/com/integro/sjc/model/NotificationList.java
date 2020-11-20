package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationList {

 @SerializedName("sjc_notification")
 private ArrayList<Notification>notificationArrayList;

    private int success;

    private String message;

    public ArrayList<Notification>getNotificationArrayList()

    {
        return notificationArrayList;
    }
     public NotificationList(ArrayList<Notification>notificationArrayList)
    {
        this.notificationArrayList=notificationArrayList;
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
