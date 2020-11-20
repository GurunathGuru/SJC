package com.integro.sjc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OurCoursesList {
    @SerializedName("sjc_course")
    private ArrayList<OurCourses> ourCoursesArrayList;


    private int success;

    private String message;

    public ArrayList<OurCourses>getOurCoursesArrayList(){
        return ourCoursesArrayList;
    }

    public OurCoursesList(ArrayList<OurCourses>ourCoursesArrayList)
    {
        this.ourCoursesArrayList=ourCoursesArrayList;
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
