package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Department2List {

    @SerializedName("department1")
    private ArrayList<Department2> department2ArrayList;

    private String success;

    private String message;

    public ArrayList<Department2> getDepartment2ArrayList() {
        return department2ArrayList;
    }

    public void setDepartment2ArrayList(ArrayList<Department2> department2ArrayList) {
        this.department2ArrayList = department2ArrayList;
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
