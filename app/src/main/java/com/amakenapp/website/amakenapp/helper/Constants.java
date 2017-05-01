package com.amakenapp.website.amakenapp.helper;

/**
 * Created by User on 2/12/2017.
 */

public class Constants {


    private static final String ROOT_URL = "http://amakenapp.website/placesapp/v1/";

    public static final String URL_SEARCH = ROOT_URL+"getSearchTerm/";
    public static final String URL_SEARCH_PLACE = ROOT_URL+"getPlaceFilter/";
    public static final String URL_SEARCH_EVENT = ROOT_URL+"getEventFilter/";
    public static final String URL_SEARCH_USER = ROOT_URL+"getUserFilter/";


    public static final String URL_GET_USER_BY_ID = ROOT_URL +"getUserById/";

    public static final String URL_LOGIN = ROOT_URL+"UserLogin";
    public static final String URL_COUNTRIES = ROOT_URL+"getAllCountries";
    public static final String URL_CITIES = ROOT_URL+"getCitiesByCountryId/";
    public static final String URL_PLACECATEGORIES = ROOT_URL+"getAllPlaceCategories";

   // public static final String URL_GET_USER_ID = ROOT_URL+"getUserId";

    public static final String URL_GET_USER_ID = ROOT_URL+"getUserId";



    public static final String URL_SINGUP = ROOT_URL+ "createUser";
    public static final String URL_STOREINTERESTS = ROOT_URL+"assignInterestCategory";

    public static final int DIALOG_EXIT = 3;
    public static final int CODE_NORMAL_USER = 1244;
    public static final int CODE_BUSINESS_USER = 1245;

    public static final String GO_TO_CAMERA = "camera";


    public static final String STRING_TYPE_EVENT = "EVENT";
    public static final String STRING_TYPE_PLACE = "PLACE";
    public static final String STRING_TYPE_Review = "Review";

    public static final String STRING_USER_PROFILE_PIC = "no profile photo";
    public static final String STRING_BOOKMARK_EXSITS= "Bookmark exist";
    public static final String STRING_Review_EXSITS = "Review exist";
    public static final String STRING_LIKE_EXSITS= "Like exist";
    public static final String STRING_REVIEW_LIKE_EXSITS= "review like exsits";
    public static final String STRING_EVENT_STILL_OPEN= "available";


//expand event information and operations
    public static final String URL_GET_ALL_EVENTS = ROOT_URL+"getAllEvents/";
    public static final String URL_EVENT_INFO = ROOT_URL+"getEventById";
    public static final String URL_EVENT_GALLERY = ROOT_URL+"getEventGallery/";
    public static final String URL_EVENT_USERS_REVIEWS = ROOT_URL+"getReviewsOnEvent";
    public static final String URL_EVENT_STORE_LIKE = ROOT_URL+"storeEventLike";
    public static final String URL_EVENT_STORE_BOOKMARK = ROOT_URL+"storeEventBookmark";
    public static final String URL_EVENT_USERS_GALLERY = ROOT_URL+"getReviewsGalleryOnEvent/";

//expand place operations and information
    public static final String URL_GET_ALL_PLACES = ROOT_URL+"getAllPlaces/";
    public static final String URL_PLACE_INFO = ROOT_URL+"getPlaceById";
    public static final String URL_PLACE_GALLERY = ROOT_URL+"getPlaceGallery/";
    public static final String URL_PLACE_USERS_REVIEWS = ROOT_URL+"getReviewsOnPlace";
    public static final String URL_PLACE_STORE_LIKE = ROOT_URL+"storePlaceLike";
    public static final String URL_PLACE_STORE_BOOKMARK = ROOT_URL+"storePlaceBookmark";
    public static final String URL_PLACE_USERS_GALLERY = ROOT_URL+"getReviewsGalleryOnPlace/";


//reviews urls
    public static final String URL_REVIEW_STORE_LIKE = ROOT_URL+"storeReviewLike";
    public static final String URL_REVIEW_LIKES_NUMBERS = ROOT_URL+"getReviewLikesNumbers/";
    public static final String URL_REVIEW_CHECK_LIKE = ROOT_URL+"checkReviewLikeEsxits";
    public static final String URL_REVIEW_REPORT = ROOT_URL+"reportReview";


//edit place details

    public static final String URL_GET_USER_PLACES = ROOT_URL+"getUserPlaces/";
    public static final String URL_EDIT_PLACE_IMAGE = ROOT_URL+"updatePlace/";
    public static final String URL_EDIT_PLACE_DETAILS = ROOT_URL+"updatePlace/";
    public static final String URL_EDIT_PLACE_LOCATION= ROOT_URL+"updateLocation/";
    public static final String URL_DELETE_USER_PLACE = ROOT_URL+"deletePlaceById/";
    public static final String URL_UPLOAD_PHOTO_Place = ROOT_URL+"insertNewPhotoUsingVolleyPlace";


//edit event details
    public static final String URL_CATEGORIES = ROOT_URL+"getAllPlaceCategories";
    public static final String URL_Event_CATEGORIES = ROOT_URL+"getAllEventCategories";
    public static final String URL_EDIT_EVENT_DETAILS = ROOT_URL+"updateEvent/";
    public static final String URL_EDIT_EVENT_DATE= ROOT_URL+"updateDate/";
    public static final String URL_UPLOAD_PHOTO_Event = ROOT_URL+"insertNewPhotoUsingVolleyEvent";
    public static final String URL_UPDATE_PHOTO_Event = ROOT_URL+"updatePhotoUsingVolleyBusinessGallery/";


    public static final String URL_ADD_EVENT= ROOT_URL+"createNewEvent";
    public static final String URL_ADD_EVENT_DATE= ROOT_URL+"createNewDate";
    public static final String URL_ADD_EVENT_LOCATION= ROOT_URL+"insertNewLocation";

    public static final String URL_ADD_PLACE= ROOT_URL+"createNewPlace";





//profile operations urls

    public static final String URL_GET_USER_EVENTS = ROOT_URL+"getUserEvents/";
    public static final String URL_DELETE_USER_Event = ROOT_URL+"deleteEventById/";

    public static final String URL_GET_USER_BOOKMARKS = ROOT_URL+"getUserBookmarks/";
    public static final String URL_DELETE_USER_BOOKMARK = ROOT_URL+"deleteBookmarkById/";

    public static final String URL_GET_USER_LIKES = ROOT_URL+"getUserLikes/";
    public static final String URL_DELETE_USER_LIKE = ROOT_URL+"deleteLikeById/";


    public static final String URL_GET_USER_REVIEWS = ROOT_URL+"getReviewsOfUser/";

    public static final String URL_GET_USER_INTEREST_CATEGORIES = ROOT_URL+"getAllInterestCategories/";
    public static final String URL_GET_ASSING_USER_INTEREST_CATEGORY = ROOT_URL+"assignInterest";


//profile pic insert urls
    public static final String URL_Update_User_Details= ROOT_URL+"updateUser";
    public static final String URL_UPLOAD_PHOTO = ROOT_URL+"insertNewPhotoUsingVolley";
    public static final String URL_UPDATE_PHOTO = ROOT_URL+"updatePhotoUsingVolley/";



//review operations
    public static final String URL_UPLOAD_PHOTO_REVIEW_PLACE= ROOT_URL+"insertNewPhotoUsingVolleyReviewOnPlace";
    public static final String URL_UPLOAD_PHOTO_REVIEW_EVENT= ROOT_URL+"insertNewPhotoUsingVolleyReviewOnEvent";
    public static final String URL_INSERT_REVIEW_ON_PLACE= ROOT_URL+"writePlaceReview";
    public static final String URL_INSERT_REVIEW_ON_EVENT= ROOT_URL+"writeEventReview";

    public static final String URL_EDIT_REVIEW= ROOT_URL+"updateReviewById/";
    public static final String URL_GET_REVIEW_BY_ID= ROOT_URL+"getReviewById/";
    public static final String URL_GET_REVIEW_Gallery= ROOT_URL+"getReviewGallery/";

















    public static final String PHOTO_UPLOAD_URL = "http://amakenapp.website/ImageUpload/zainah.php";
    public static final String IMAGES_URL = "http://amakenapp.website/ImageUpload/getImages.php";

    public static final String PHOTO_UPLOAD_URL_volley = "http://amakenapp.website/ImageUpload/uploadVolley.php";















}