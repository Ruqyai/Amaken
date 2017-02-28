package com.example.afaf.amakenapp.helper;

import java.util.Map;

/**
 * Created by User on 2/25/2017.
 */

public class User {

    private String user_email, user_name, user_password, web_url, phone_number;
    private int gender;
    private int country_id;
    private String country_name;
    private int city_id;
    private String city_name;
    private int profile_pic_id;
    private String profile_pic_url;
    private int user_type;

    public User(String user_email, String user_name, String user_gender,
                //int country_id,
                String country_name,
                //int city_id,
                String city_name,
                //int profile_pic_id,
                String profile_pic_url, int user_type) {
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_password = user_password;
       // this.country_id = country_id;
        this.country_name = country_name;
       // this.city_id = city_id;
        this.city_name = city_name;
       // this.profile_pic_id = profile_pic_id;
        this.profile_pic_url = profile_pic_url;
        this.user_type = user_type;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getWeb_url() {
        return web_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public int getGender() {
        return gender;
    }

    public int getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public int getProfile_pic_id() {
        return profile_pic_id;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public int getUser_type() {
        return user_type;
    }
}
