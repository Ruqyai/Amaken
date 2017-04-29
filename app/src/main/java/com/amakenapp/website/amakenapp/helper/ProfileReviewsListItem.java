package com.amakenapp.website.amakenapp.helper;

import com.amakenapp.website.amakenapp.R;

/**
 * Created by Muha on 3/11/2017.
 */

public class ProfileReviewsListItem {

    private int reviewId;
    private String reviewType;
    private String yourReviewText = "Your Review";
    private String reviewTimestamp;


    private int eventOrPlaceId;
    private String placeoreventPic;
    private String placeoreventName;
    private String placeoreventCategory;

    private String reviewText;
    private Float reviewRatingValue;
    private int reviewLikeImage = R.drawable.ic_thump_up;
    private String reviewLikesNumber;


    public ProfileReviewsListItem() {
    }

    public ProfileReviewsListItem(int reviewId, String reviewType, String reviewTimestamp, int eventOrPlaceId, String placeoreventPic, String placeoreventName, String placeoreventCategory, String reviewText, Float reviewRatingValue, int reviewLikeImage, String reviewLikesNumber) {
        this.reviewId = reviewId;
        this.reviewType = reviewType;
        this.reviewTimestamp = reviewTimestamp;
        this.eventOrPlaceId = eventOrPlaceId;
        this.placeoreventPic = placeoreventPic;
        this.placeoreventName = placeoreventName;
        this.placeoreventCategory = placeoreventCategory;
        this.reviewText = reviewText;
        this.reviewRatingValue = reviewRatingValue;
        this.reviewLikeImage = reviewLikeImage;
        this.reviewLikesNumber = reviewLikesNumber;
    }

    public String getYourReviewText() {
        return yourReviewText;
    }

    public void setYourReviewText(String yourReviewText) {
        this.yourReviewText = yourReviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getReviewTimestamp() {
        return reviewTimestamp;
    }

    public void setReviewTimestamp(String reviewTimestamp) {
        this.reviewTimestamp = reviewTimestamp;
    }

    public int getEventOrPlaceId() {
        return eventOrPlaceId;
    }

    public void setEventOrPlaceId(int eventOrPlaceId) {
        this.eventOrPlaceId = eventOrPlaceId;
    }

    public String getPlaceoreventPic() {
        return placeoreventPic;
    }

    public void setPlaceoreventPic(String placeoreventPic) {
        this.placeoreventPic = placeoreventPic;
    }

    public String getPlaceoreventName() {
        return placeoreventName;
    }

    public void setPlaceoreventName(String placeoreventName) {
        this.placeoreventName = placeoreventName;
    }

    public String getPlaceoreventCategory() {
        return placeoreventCategory;
    }

    public void setPlaceoreventCategory(String placeoreventCategory) {
        this.placeoreventCategory = placeoreventCategory;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Float getReviewRatingValue() {
        return reviewRatingValue;
    }

    public void setReviewRatingValue(Float reviewRatingValue) {
        this.reviewRatingValue = reviewRatingValue;
    }

    public int getReviewLikeImage() {
        return reviewLikeImage;
    }

    public void setReviewLikeImage(int reviewLikeImage) {
        this.reviewLikeImage = reviewLikeImage;
    }

    public String getReviewLikesNumber() {
        return reviewLikesNumber;
    }

    public void setReviewLikesNumber(String reviewLikesNumber) {
        this.reviewLikesNumber = reviewLikesNumber;
    }
}