package com.example.afaf.amakenapp.helper;

/**
 * Created by User on 2/12/2017.
 */

public class Constants {

    private static final String ROOT_URL = "http://amakenapp.website/placesapp/v1/";

    public static final String URL_LOGIN = ROOT_URL+"UserLogin";
    public static final String URL_COUNTRIES = ROOT_URL+"getAllCountries";
    public static final String URL_CITIES = ROOT_URL+"getCitiesByCountryId/";



    public static final String URL_SINGUP = ROOT_URL+ "createUser";
    public static final String URL_STOREINTERESTS = ROOT_URL+"assignInterestCategory";


    public static final int CODE_NORMAL_USER = 1244;
    public static final int CODE_BUSINESS_USER = 1245;
}