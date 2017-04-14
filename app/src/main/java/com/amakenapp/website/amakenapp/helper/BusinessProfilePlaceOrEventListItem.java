package com.amakenapp.website.amakenapp.helper;

import com.amakenapp.website.amakenapp.R;

/**
 * Created by Muha on 3/10/2017.
 */




public class BusinessProfilePlaceOrEventListItem {


    private int PlaceOrEventId;
    private String type;
    private String PlaceOrEventPic;
    private String PlaceOrEventName;
    private String PlaceOrEventCategory;
    private int PlaceOrEventLikeLogo;
    private int PlaceOrEventBookmarkLogo;
    private Float PlaceOrEventRatingbar;
    private String statBookmark, statLikes,statRatings;

    public BusinessProfilePlaceOrEventListItem() {
    }

    public BusinessProfilePlaceOrEventListItem(String placeOrEventPic, String placeOrEventName, String placeOrEventCategory, int placeOrEventLikeLogo, int placeOrEventBookmarkLogo, Float placeOrEventRatingbar, String statBookmark, String statLikes, String statRatings) {
        PlaceOrEventPic = placeOrEventPic;
        PlaceOrEventName = placeOrEventName;
        PlaceOrEventCategory = placeOrEventCategory;
        PlaceOrEventLikeLogo = placeOrEventLikeLogo;
        PlaceOrEventBookmarkLogo = placeOrEventBookmarkLogo;
        PlaceOrEventRatingbar = placeOrEventRatingbar;
        this.statBookmark = statBookmark;
        this.statLikes = statLikes;
        this.statRatings = statRatings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlaceOrEventId() {
        return PlaceOrEventId;
    }

    public void setPlaceOrEventId(int placeOrEventId) {
        PlaceOrEventId = placeOrEventId;
    }

    public String getPlaceOrEventPic() {
        return PlaceOrEventPic;
    }

    public void setPlaceOrEventPic(String placeOrEventPic) {
        PlaceOrEventPic = placeOrEventPic;
    }

    public String getPlaceOrEventName() {
        return PlaceOrEventName;
    }

    public void setPlaceOrEventName(String placeOrEventName) {
        PlaceOrEventName = placeOrEventName;
    }

    public String getPlaceOrEventCategory() {
        return PlaceOrEventCategory;
    }

    public void setPlaceOrEventCategory(String placeOrEventCategory) {
        PlaceOrEventCategory = placeOrEventCategory;
    }

    public int getPlaceOrEventLikeLogo() {
        return PlaceOrEventLikeLogo;
    }

    public void setPlaceOrEventLikeLogo(int placeOrEventLikeLogo) {
        PlaceOrEventLikeLogo = placeOrEventLikeLogo;
    }

    public int getPlaceOrEventBookmarkLogo() {
        return PlaceOrEventBookmarkLogo;
    }

    public void setPlaceOrEventBookmarkLogo(int placeOrEventBookmarkLogo) {
        PlaceOrEventBookmarkLogo = placeOrEventBookmarkLogo;
    }

    public Float getPlaceOrEventRatingbar() {
        return PlaceOrEventRatingbar;
    }

    public void setPlaceOrEventRatingbar(Float placeOrEventRatingbar) {
        PlaceOrEventRatingbar = placeOrEventRatingbar;
    }

    public String getStatBookmark() {
        return statBookmark;
    }

    public void setStatBookmark(String statBookmark) {
        this.statBookmark = statBookmark;
    }

    public String getStatLikes() {
        return statLikes;
    }

    public void setStatLikes(String statLikes) {
        this.statLikes = statLikes;
    }

    public String getStatRatings() {
        return statRatings;
    }

    public void setStatRatings(String statRatings) {
        this.statRatings = statRatings;
    }
}