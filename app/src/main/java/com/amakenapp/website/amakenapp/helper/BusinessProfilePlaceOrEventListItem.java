package com.amakenapp.website.amakenapp.helper;

/**
 * Created by Muha on 3/10/2017.
 */




public class BusinessProfilePlaceOrEventListItem {

    private int BusinessProfilePlaceOrEventPic;
    private String BusinessProfilePlaceOrEventName;
    private String BusinessProfilePlaceOrEventCategory;
    private int BusinessProfilePlaceOrEventLikeLogo;
    private int BusinessProfilePlaceOrEventBookmarkLogo;
    private int BusinessProfilePlaceOrEventRatingbarLogo;
    private String statBookmark, statLikes,statRatings;

    public BusinessProfilePlaceOrEventListItem(int BusinessProfilePlaceOrEventPic,
                                               String BusinessProfilePlaceOrEventName,
                                               String placeDescUserLikes,
                                               int BusinessProfilePlaceOrEventLikeLogo,
                                               int bookmarkImageUser,
                                               int ratingbarBusiness,
                                               String statBookmark,
                                               String statLikes,
                                               String statRatings) {

        this.BusinessProfilePlaceOrEventPic = BusinessProfilePlaceOrEventPic;
        this.BusinessProfilePlaceOrEventName = BusinessProfilePlaceOrEventName;
        this.BusinessProfilePlaceOrEventCategory = placeDescUserLikes;
        this.BusinessProfilePlaceOrEventLikeLogo = BusinessProfilePlaceOrEventLikeLogo;
        this.BusinessProfilePlaceOrEventBookmarkLogo = bookmarkImageUser;
        this.BusinessProfilePlaceOrEventRatingbarLogo = ratingbarBusiness;
        this.statBookmark = statBookmark;
        this.statLikes = statLikes;
        this.statRatings = statRatings;
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

    public int getBusinessProfilePlaceOrEventPic() {
        return BusinessProfilePlaceOrEventPic;
    }

    public void setBusinessProfilePlaceOrEventPic(int businessProfilePlaceOrEventPic) {
        this.BusinessProfilePlaceOrEventPic = businessProfilePlaceOrEventPic;
    }

    public String getBusinessProfilePlaceOrEventName() {
        return BusinessProfilePlaceOrEventName;
    }

    public void setBusinessProfilePlaceOrEventName(String businessProfilePlaceOrEventName) {
        this.BusinessProfilePlaceOrEventName = businessProfilePlaceOrEventName;
    }

    public String getBusinessProfilePlaceOrEventCategory() {
        return BusinessProfilePlaceOrEventCategory;
    }

    public void setBusinessProfilePlaceOrEventCategory(String businessProfilePlaceOrEventCategory) {
        this.BusinessProfilePlaceOrEventCategory = businessProfilePlaceOrEventCategory;
    }

    public int getBusinessProfilePlaceOrEventLikeLogo() {
        return BusinessProfilePlaceOrEventLikeLogo;
    }

    public void setBusinessProfilePlaceOrEventLikeLogo(int businessProfilePlaceOrEventLikeLogo) {
        this.BusinessProfilePlaceOrEventLikeLogo = businessProfilePlaceOrEventLikeLogo;
    }

    public int getBusinessProfilePlaceOrEventBookmarkLogo() {
        return BusinessProfilePlaceOrEventBookmarkLogo;
    }

    public void setBusinessProfilePlaceOrEventBookmarkLogo(int businessProfilePlaceOrEventBookmarkLogo) {
        this.BusinessProfilePlaceOrEventBookmarkLogo = businessProfilePlaceOrEventBookmarkLogo;
    }

    public int getBusinessProfilePlaceOrEventRatingbarLogo() {
        return BusinessProfilePlaceOrEventRatingbarLogo;
    }

    public void setBusinessProfilePlaceOrEventRatingbarLogo(int businessProfilePlaceOrEventRatingbarLogo) {
        this.BusinessProfilePlaceOrEventRatingbarLogo = businessProfilePlaceOrEventRatingbarLogo;
    }
}