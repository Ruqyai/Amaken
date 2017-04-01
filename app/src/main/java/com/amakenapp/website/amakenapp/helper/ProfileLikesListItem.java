package com.amakenapp.website.amakenapp.helper;

/**
 * Created by Muha on 3/11/2017.
 */

public class ProfileLikesListItem {


    private int PlaceOrEventPic;
    private String PlaceOrEventName;
    private String PlaceOrEventCategory;
    private int likeLogo;
    private String likeTimeStamp;

    public ProfileLikesListItem(int placeOrEventPic, String placeOrEventName, String placeOrEventCategory, int likeLogo, String likeTimeStamp) {
        PlaceOrEventPic = placeOrEventPic;
        PlaceOrEventName = placeOrEventName;
        PlaceOrEventCategory = placeOrEventCategory;
        this.likeLogo = likeLogo;
        this.likeTimeStamp = likeTimeStamp;
    }

    public int getPlaceOrEventPic() {
        return PlaceOrEventPic;
    }

    public void setPlaceOrEventPic(int placeOrEventPic) {
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

    public int getLikeLogo() {
        return likeLogo;
    }

    public void setLikeLogo(int likeLogo) {
        this.likeLogo = likeLogo;
    }

    public String getLikeTimeStamp() {
        return likeTimeStamp;
    }

    public void setLikeTimeStamp(String likeTimeStamp) {
        this.likeTimeStamp = likeTimeStamp;
    }
}