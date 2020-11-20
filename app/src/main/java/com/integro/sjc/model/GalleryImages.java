package com.integro.sjc.model;

import java.io.Serializable;

public class GalleryImages implements Serializable {

    private String image;

    private String g_id;

    private String updated_at;

    private String id;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getG_id ()
    {
        return g_id;
    }

    public void setG_id (String g_id)
    {
        this.g_id = g_id;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }
}
