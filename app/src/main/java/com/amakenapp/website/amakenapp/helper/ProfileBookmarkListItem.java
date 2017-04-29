package com.amakenapp.website.amakenapp.helper;

import com.amakenapp.website.amakenapp.R;

/**
 * Created by Muha on 3/11/2017.
 */

public class ProfileBookmarkListItem {


        private int boomarkId;
        private int placeOrEventId;
        private String bookmarkType;
        private String PlaceOrEventPic;
        private String PlaceOrEventName;
        private String PlaceOrEventCategory;
        private int bookmarkLogo = R.drawable.bookmark2;
        private String bookmarkTimeStamp;

    public ProfileBookmarkListItem() {
    }

    public ProfileBookmarkListItem(String PlaceOrEventPic,
                                   String PlaceOrEventName,
                                   String PlaceOrEventCategory,
                                   int bookmarkLogo,
                                   String bookmarkTimeStamp) {
        this.PlaceOrEventPic = PlaceOrEventPic;
        this.PlaceOrEventName = PlaceOrEventName;
        this.PlaceOrEventCategory = PlaceOrEventCategory;
        this.bookmarkLogo = bookmarkLogo;
        this.bookmarkTimeStamp = bookmarkTimeStamp;
    }

    public int getBoomarkId() {
        return boomarkId;
    }

    public void setBoomarkId(int boomarkId) {
        this.boomarkId = boomarkId;
    }

    public int getPlaceOrEventId() {
        return placeOrEventId;
    }

    public void setPlaceOrEventId(int placeOrEventId) {
        this.placeOrEventId = placeOrEventId;
    }

    public String getBookmarkType() {
        return bookmarkType;
    }

    public void setBookmarkType(String bookmarkType) {
        this.bookmarkType = bookmarkType;
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

    public int getBookmarkLogo() {
        return bookmarkLogo;
    }

    public void setBookmarkLogo(int bookmarkLogo) {
        this.bookmarkLogo = bookmarkLogo;
    }

    public String getBookmarkTimeStamp() {
        return bookmarkTimeStamp;
    }

    public void setBookmarkTimeStamp(String bookmarkTimeStamp) {
        this.bookmarkTimeStamp = bookmarkTimeStamp;
    }
}
