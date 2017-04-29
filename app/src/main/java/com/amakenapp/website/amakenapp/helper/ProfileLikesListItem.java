package com.amakenapp.website.amakenapp.helper;

import com.amakenapp.website.amakenapp.R;

/**
 * Created by Muha on 3/11/2017.
 */

public class ProfileLikesListItem {


    private int like_id;
    private String like_type;
    private int likeLogo = R.drawable.heart_box_outline;
    private String like_timestamp;

    private int placeorevent_id;
    private String placeOrEventPic;
    private String placeOrEventName;
    private String placeOrEventCategory;


    private String reviewUserPic;
    private String reviewUserName;
    private String reviewText;
    private float reviewRating;
    private String reviewType;
    private int eventOrPlaceId;

    public ProfileLikesListItem() {
    }

    public ProfileLikesListItem(int like_id, String like_type, int likeLogo, String like_timestamp, int placeorevent_id, String placeOrEventPic, String placeOrEventName, String placeOrEventCategory, String reviewUserPic, String reviewUserName, String reviewText, float reviewRating, String reviewType, int eventOrPlaceId) {
        this.like_id = like_id;
        this.like_type = like_type;
        this.likeLogo = likeLogo;
        this.like_timestamp = like_timestamp;
        this.placeorevent_id = placeorevent_id;
        this.placeOrEventPic = placeOrEventPic;
        this.placeOrEventName = placeOrEventName;
        this.placeOrEventCategory = placeOrEventCategory;
        this.reviewUserPic = reviewUserPic;
        this.reviewUserName = reviewUserName;
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
        this.reviewType = reviewType;
        this.eventOrPlaceId = eventOrPlaceId;
    }

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }

    public String getLike_type() {
        return like_type;
    }

    public void setLike_type(String like_type) {
        this.like_type = like_type;
    }

    public int getLikeLogo() {
        return likeLogo;
    }

    public void setLikeLogo(int likeLogo) {
        this.likeLogo = likeLogo;
    }

    public String getLike_timestamp() {
        return like_timestamp;
    }

    public void setLike_timestamp(String like_timestamp) {
        this.like_timestamp = like_timestamp;
    }

    public int getPlaceorevent_id() {
        return placeorevent_id;
    }

    public void setPlaceorevent_id(int placeorevent_id) {
        this.placeorevent_id = placeorevent_id;
    }

    public String getPlaceOrEventPic() {
        return placeOrEventPic;
    }

    public void setPlaceOrEventPic(String placeOrEventPic) {
        this.placeOrEventPic = placeOrEventPic;
    }

    public String getPlaceOrEventName() {
        return placeOrEventName;
    }

    public void setPlaceOrEventName(String placeOrEventName) {
        this.placeOrEventName = placeOrEventName;
    }

    public String getPlaceOrEventCategory() {
        return placeOrEventCategory;
    }

    public void setPlaceOrEventCategory(String placeOrEventCategory) {
        this.placeOrEventCategory = placeOrEventCategory;
    }

    public String getReviewUserPic() {
        return reviewUserPic;
    }

    public void setReviewUserPic(String reviewUserPic) {
        this.reviewUserPic = reviewUserPic;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(float reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public int getEventOrPlaceId() {
        return eventOrPlaceId;
    }

    public void setEventOrPlaceId(int eventOrPlaceId) {
        this.eventOrPlaceId = eventOrPlaceId;
    }
}