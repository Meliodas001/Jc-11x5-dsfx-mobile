package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

public class NoticeInfo implements Serializable {

    private String Id;
    private String Title;
    private String Details;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
