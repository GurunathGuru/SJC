package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrincipalMessageList {

    @SerializedName("sjc_principalmsg")
    ArrayList<PrincipalMessage> principalMessageArrayList;

    private String success;

    private String message;

    public ArrayList<PrincipalMessage> getPrincipalMessageArrayList() {
        return principalMessageArrayList;
    }

    public void setPrincipalMessageArrayList(ArrayList<PrincipalMessage> principalMessageArrayList) {
        this.principalMessageArrayList = principalMessageArrayList;
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
