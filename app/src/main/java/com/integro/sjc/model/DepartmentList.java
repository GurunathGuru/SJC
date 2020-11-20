package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DepartmentList {
    @SerializedName("sjc_dept")
    private ArrayList<Department>departmentArrayList;
    private int success;
    private String message;

    public ArrayList<Department>getDepartmentArrayList()
    {
        return departmentArrayList;
    }
    public DepartmentList(ArrayList<Department>departmentArrayList)
    {
        this.departmentArrayList=departmentArrayList;
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
