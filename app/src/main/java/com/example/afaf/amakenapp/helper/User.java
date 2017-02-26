package com.example.afaf.amakenapp.helper;

import java.util.Map;

/**
 * Created by User on 2/25/2017.
 */

public class User {

    private String userEmail, userName, userGende;
    private int countryId;
    private String countryName;
    private int cityId;
    private String cityName;
    private int profilePicId;
    private String profilePicUrl;
    private int role;

    public User(String userEmail, String userName, String userGende,
                //int countryId,
                String countryName,
                //int cityId,
                String cityName,
                //int profilePicId,
                String profilePicUrl, int role) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userGende = userGende;
       // this.countryId = countryId;
        this.countryName = countryName;
       // this.cityId = cityId;
        this.cityName = cityName;
       // this.profilePicId = profilePicId;
        this.profilePicUrl = profilePicUrl;
        this.role = role;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGende() {
        return userGende;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getProfilePicId() {
        return profilePicId;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public int getRole() {
        return role;
    }
}
