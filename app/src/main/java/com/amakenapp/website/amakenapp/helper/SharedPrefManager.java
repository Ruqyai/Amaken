package com.amakenapp.website.amakenapp.helper;

/**
 * Created by User on 2/12/2017.
 */


import android.content.Context;
import android.content.SharedPreferences;

import com.amakenapp.website.amakenapp.store.User;

import java.net.URI;


public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";


    private static final String TAG_TOKEN = "tagtoken";

    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_TYPE = "usertype";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_PASSWORD = "userpassword";
    private static  String KEY_USER_NAME = "username";
    private static  String KEY_USER_GENDER = "usergender";
    private static  String KEY_USER_WEB_URL = "userweburl";
    private static  String KEY_USER_PHONE_NUMBER = "userphonenumber";
    private static  String KEY_USER_COUNTRY_ID = "usercountry_id";
    private static  String KEY_USER_COUNTRY_NAME = "usercountry_name";
    private static  String KEY_USER_CITY_ID = "usercity_id";
    private static  String KEY_USER_CITY_NAME = "usercity_name";
    private static  String KEY_USER_PROFILE_PIC_ID = "userprofile_pic_id";
    private static  String KEY_USER_PROFILE_PIC_URL = "profile_pic_url";
    private static  String KEY_USER_PROFILE_PIC_URL_TIME_STAMP = "profile_pic_url_timestamp";








    private SharedPrefManager(Context context) {
        mCtx = context;

    }



    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(
            String id,
            String user_type,
            String user_email,
            String user_password,
            String user_name,
            String user_gender,
            String web_url,
            String phone_number,
            int country_id,
            String country_name,
            int city_id,
            String city_name,
            String profile_pic_id,
            String profile_pic_url,
            String pic_timestamp) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_ID, id);
        editor.putString(KEY_USER_TYPE, user_type);
        editor.putString(KEY_USER_EMAIL, user_email);
        editor.putString(KEY_USER_PASSWORD, user_password);
        editor.putString(KEY_USER_NAME, user_name);
        editor.putString(KEY_USER_GENDER, user_gender);
        editor.putString(KEY_USER_WEB_URL, web_url);
        editor.putString(KEY_USER_PHONE_NUMBER, phone_number);
        editor.putInt(KEY_USER_COUNTRY_ID, country_id);
        editor.putString(KEY_USER_COUNTRY_NAME, country_name);
        editor.putInt(KEY_USER_CITY_ID, city_id);
        editor.putString(KEY_USER_CITY_NAME, city_name);
        editor.putString(KEY_USER_PROFILE_PIC_ID, profile_pic_id);
        editor.putString(KEY_USER_PROFILE_PIC_URL, profile_pic_url);
        editor.putString(KEY_USER_PROFILE_PIC_URL_TIME_STAMP, pic_timestamp);


        editor.commit();

        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_NAME, null) != null) {
            return true;
        }
        return false;
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        return true;
    }

    public int getUserId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String x= sharedPreferences.getString(KEY_USER_ID, null);
        int id=Integer.parseInt(x);
        return  id;
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME, null);
    }
    public String getUserWeb() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_WEB_URL, null);
    }
    public String getUserPhone() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PHONE_NUMBER, null);
    }
    public String getKeyUserProfilePicUrl() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PROFILE_PIC_URL, null);

    }
    public String getKeyUserProfilePicUrlTimeStamp() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PROFILE_PIC_URL_TIME_STAMP, null);
    }

    public String getKeyUserCountryName() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_COUNTRY_NAME, null);
    }
    public String getKeyUserCityName() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_CITY_NAME, null);
    }

    public int getUserCountryId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String x= sharedPreferences.getString(KEY_USER_COUNTRY_ID, null);
        return    Integer.parseInt(x);

    }

    public int getUserCityId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String x= sharedPreferences.getString(KEY_USER_CITY_ID, null);
        return    Integer.parseInt(x);

    }
    public int getUserType() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String x= sharedPreferences.getString(KEY_USER_TYPE, null);
        return    Integer.parseInt(x);

    }

    public String getUserEmail() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public int getUserProfilePicId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String x= sharedPreferences.getString(KEY_USER_PROFILE_PIC_ID, null);
        int id=Integer.parseInt(x);
        return  id;
    }

    public static void setKeyUserProfilePicUrl(String keyUserProfilePicUrl) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_PROFILE_PIC_URL, keyUserProfilePicUrl);
        editor.commit();
    }

    public static void setKeyUserProfilePicId(String keyUserProfilePicId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_PROFILE_PIC_ID, keyUserProfilePicId);
        editor.commit();
    }

     public static void setKeyUserCountryId(int keyUserCountryId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_COUNTRY_ID, keyUserCountryId);
        editor.commit();
    }

    public static void setKeyUserCountryName(String keyUserCountryName) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_COUNTRY_NAME, keyUserCountryName);
        editor.commit();
    }

    public static void setKeyUserCityId(int keyUserCityId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_CITY_ID, keyUserCityId);
        editor.commit();
    }

    public static void setKeyUserCityName(String keyUserCityName) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_CITY_NAME, keyUserCityName);
        editor.commit();
    }

    public static void setKeyUserProfilePicUrlTimeStamp(String keyUserProfilePicUrlTimeStamp) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_PROFILE_PIC_URL_TIME_STAMP, keyUserProfilePicUrlTimeStamp);
        editor.commit();
    }


    public static void setKeyUserName(String keyUserProfilePicUrl) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, keyUserProfilePicUrl);
        editor.commit();
    }
    public static void setKeyUserWebUrl(String keyUserProfilePicUrl) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_WEB_URL, keyUserProfilePicUrl);
        editor.commit();
    }
    public static void setKeyUserPhoneNumber(String keyUserProfilePicUrl) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_PHONE_NUMBER, keyUserProfilePicUrl);
        editor.commit();
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        User user = new User(
                Integer.parseInt(sharedPreferences.getString("user_id", "null")),
                Integer.parseInt(sharedPreferences.getString("user_type", "null")),
                sharedPreferences.getString("user_email", "null"),
                sharedPreferences.getString("user_password", "null"),
                sharedPreferences.getString("user_name", "null"),
                sharedPreferences.getString("user_gender", "null"),
                sharedPreferences.getString("user_web_url", "null"),
                sharedPreferences.getString("user_phone_number", "null"),
                Integer.parseInt(sharedPreferences.getString("user_country_id", "null")),
                sharedPreferences.getString("user_country_name", "null"),
                Integer.parseInt(sharedPreferences.getString("user_city_id", "null")),
                sharedPreferences.getString("user_city_name", "null"),
                Integer.parseInt(sharedPreferences.getString("profile_pic_id","null")),
                sharedPreferences.getString("profile_pic_url", "null"),
                sharedPreferences.getString("profile_pic_url_timestamp", "null")
        );

        return user;

    }

    //this method will save the device token to shared preferences
    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }
}

