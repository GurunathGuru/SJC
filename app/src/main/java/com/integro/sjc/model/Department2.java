package com.integro.sjc.model;

import java.io.Serializable;

public class Department2 implements Serializable {

    private String id;

    private String d_id;

    private String image;

    private String title;

    private String description;

    private String updated_at;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getD_id ()
    {
        return d_id;
    }

    public void setD_id (String d_id)
    {
        this.d_id = d_id;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }
}
