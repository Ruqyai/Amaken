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

    public static final String STRING_TYPE_EVENT = "EVENT";
    public static final String STRING_TYPE_PLACE = "PLACE";
    public static final String STRING_USER_PROFILE_PIC = "no profile photo";
    public static final String STRING_BOOKMARK_EXSITS= "Bookmark exist";
    public static final String STRING_LIKE_EXSITS= "Like exist";
    public static final String STRING_REVIEW_LIKE_EXSITS= "review like exsits";
    public static final String STRING_EVENT_STILL_OPEN= "available";






    public static final String URL_GET_ALL_EVENTS = ROOT_URL+"getAllEvents/";
    public static final String URL_EVENT_INFO = ROOT_URL+"getEventById";
    public static final String URL_EVENT_GALLERY = ROOT_URL+"getEventGallery/";
    public static final String URL_EVENT_USERS_REVIEWS = ROOT_URL+"getReviewsOnEvent";
    public static final String URL_EVENT_STORE_LIKE = ROOT_URL+"storeEventLike";
    public static final String URL_EVENT_STORE_BOOKMARK = ROOT_URL+"storeEventBookmark";
    public static final String URL_EVENT_USERS_GALLERY = ROOT_URL+"getReviewsGalleryOnEvent/";


    public static final String URL_GET_ALL_PLACES = ROOT_URL+"getAllPlaces/";
    public static final String URL_PLACE_INFO = ROOT_URL+"getPlaceById";
    public static final String URL_PLACE_GALLERY = ROOT_URL+"getPlaceGallery/";
    public static final String URL_PLACE_USERS_REVIEWS = ROOT_URL+"getReviewsOnPlace";
    public static final String URL_PLACE_STORE_LIKE = ROOT_URL+"storePlaceLike";
    public static final String URL_PLACE_STORE_BOOKMARK = ROOT_URL+"storePlaceBookmark";
    public static final String URL_PLACE_USERS_GALLERY = ROOT_URL+"getReviewsGalleryOnPlace/";



    public static final String URL_REVIEW_STORE_LIKE = ROOT_URL+"storeReviewLike";
    public static final String URL_REVIEW_LIKES_NUMBERS = ROOT_URL+"getReviewLikesNumbers/";
    public static final String URL_REVIEW_CHECK_LIKE = ROOT_URL+"checkReviewLikeEsxits";
    public static final String URL_REVIEW_REPORT = ROOT_URL+"reportReview";



    public static final String URL_GET_USER_PLACES = ROOT_URL+"getUserPlaces/";
    public static final String URL_GET_USER_EVENTS = ROOT_URL+"getUserEvents/";













}