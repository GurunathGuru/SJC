package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsList {

    @SerializedName("sjc_events")
    private ArrayList<Events> eventsArrayList;

    private String success;
    private String message;

    public ArrayList<Events> getEventsArrayList() {
        return eventsArrayList;
    }

    public void setEventsArrayList(ArrayList<Events> eventsArrayList) {
        this.eventsArrayList = eventsArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
