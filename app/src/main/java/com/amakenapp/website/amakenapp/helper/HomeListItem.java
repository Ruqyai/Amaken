package com.amakenapp.website.amakenapp.helper;

/**
 * Created by User on 3/11/2017.
 */

public class HomeListItem {

    private int placeId;

    private String placeBusinessProfileImage;
    private String placeBusinessName;

    private String placePicture;
    private String placeName;
    private String placeCategory;

    private String placeDescription = "Description";
    private String placeDescriptionMultiLineText;
    private String placeExpand = "Expand >>";
    private String placeRatingStat;
    private Float ratingplace;

    public HomeListItem() {
    }

    public HomeListItem(int placeId, String placeBusinessProfileImage, String placeBusinessName, String placePicture, String placeName, String placeCategory, String placeDescription, String placeDescriptionMultiLineText, String placeExpand, String placeRatingStat, Float ratingplace) {
        this.placeId = placeId;
        this.placeBusinessProfileImage = placeBusinessProfileImage;
        this.placeBusinessName = placeBusinessName;
        this.placePicture = placePicture;
        this.placeName = placeName;
        this.placeCategory = placeCategory;
        this.placeDescription = placeDescription;
        this.placeDescriptionMultiLineText = placeDescriptionMultiLineText;
        this.placeExpand = placeExpand;
        this.placeRatingStat = placeRatingStat;
        this.ratingplace = ratingplace;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceBusinessProfileImage() {
        return placeBusinessProfileImage;
    }

    public void setPlaceBusinessProfileImage(String placeBusinessProfileImage) {
        this.placeBusinessProfileImage = placeBusinessProfileImage;
    }

    public String getPlaceBusinessName() {
        return placeBusinessName;
    }

    public void setPlaceBusinessName(String placeBusinessName) {
        this.placeBusinessName = placeBusinessName;
    }

    public String getPlacePicture() {
        return placePicture;
    }

    public void setPlacePicture(String placePicture) {
        this.placePicture = placePicture;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCategory() {
        return placeCategory;
    }

    public void setPlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getPlaceDescriptionMultiLineText() {
        return placeDescriptionMultiLineText;
    }

    public void setPlaceDescriptionMultiLineText(String placeDescriptionMultiLineText) {
        this.placeDescriptionMultiLineText = placeDescriptionMultiLineText;
    }

    public String getPlaceExpand() {
        return placeExpand;
    }

    public void setPlaceExpand(String placeExpand) {
        this.placeExpand = placeExpand;
    }

    public String getPlaceRatingStat() {
        return placeRatingStat;
    }

    public void setPlaceRatingStat(String placeRatingStat) {
        this.placeRatingStat = placeRatingStat;
    }

    public Float getRatingplace() {
        return ratingplace;
    }

    public void setRatingplace(Float ratingplace) {
        this.ratingplace = ratingplace;
    }
}
