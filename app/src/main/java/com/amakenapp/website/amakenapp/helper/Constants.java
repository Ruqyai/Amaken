package com.amakenapp.website.amakenapp.helper;

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

    public static final int DIALOG_EXIT = 3;
    public static final int CODE_NORMAL_USER = 1244;
    public static final int CODE_BUSINESS_USER = 1245;

    public static final String URL_EVENT_INFO = ROOT_URL+"getEventById/";
    public static final String URL_EVENT_GALLERY = ROOT_URL+"getEventGallery/";
    public static final String URL_EVENT_USERS_REVIEWS = ROOT_URL+"getReviewsOnEvent/";
    public static final String URL_EVENT_STORE_LIKE = ROOT_URL+"storeEventLike";
    public static final String URL_EVENT_CHECK_LIKE = ROOT_URL+"checkEventLikeEsxits";

    public static final String URL_EVENT_STORE_BOOKMARK = ROOT_URL+"storeEventBookmark";
    public static final String URL_EVENT_CHECK_BOOKMARK = ROOT_URL+"checkEventBookmarkEsxits";
    public static final String URL_EVENT_BOOKMARK_NUMBERS = ROOT_URL+"getEventBookmarksNumbers/";
    public static final String URL_EVENT_LIKES_NUMBERS = ROOT_URL+"getEventLikesNumbers/";
    public static final String URL_EVENT_USERS_GALLERY = ROOT_URL+"getReviewsGalleryOnEvent/";














}