package com.amakenapp.website.amakenapp.store;

import java.io.Serializable;

/**
 * Created by sondos on 05/03/2017.
 */

public class Photo implements Serializable{
   // private int id;
    private String userName, date;
    private String photo_description;
    private String photo_url;

    public Photo() {
    }

    public Photo(String photo_description, String photo_url) {
        //this.id = id;
        this.photo_description = photo_description;
        this.photo_url = photo_url;
    }

    //public int getId(){ return  id; }
    public String getPhoto_description(){ return photo_description;}
    public String getPhoto_url(){ return photo_url; }

   // public void setId(int id) {this.id = id;}

    public void setPhoto_description(String photo_description) {
        this.photo_description = photo_description;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
