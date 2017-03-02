package com.example.afaf.amakenapp.helper;

import java.util.Map;

/**
 * Created by User on 2/25/2017.
 */

public class User {
    private int id;
    private int user_type;
    private String user_email, user_password, user_name;
    private int gender;
    private String web_url, phone_number;
    private int country_id;
    private String country_name;
    private int city_id;
    private String city_name;
    private int profile_pic_id;
    private String profile_pic_url;


    public User(int id,
                int user_type,
                String user_email,
                String user_password,
                String user_name,
                int gender,
                String web_url,
                String phone_number,
                int country_id,
                String country_name,
                int city_id,
                String city_name,
                int profile_pic_id,
                String profile_pic_url) {
        this.id = id;
        this.user_type = user_type;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_name = user_name;
        this.gender = gender;
        this.web_url = web_url;
        this.phone_number = phone_number;
        this.country_id = country_id;
        this.country_name = country_name;
        this.city_id = city_id;
        this.city_name = city_name;
        this.profile_pic_id = profile_pic_id;
        this.profile_pic_url = profile_pic_url;
    }

    public int getId() {return id;}

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
