package com.example.afaf.amakenapp.helper;

/**
 * Created by Muha on 3/11/2017.
 */

public class ProfileBookmarkListItem {


        private int PlaceOrEventPic;
        private String PlaceOrEventName;
        private String PlaceOrEventCategory;
        private int bookmarkLogo;
        private String bookmarkTimeStamp;


    public ProfileBookmarkListItem(int PlaceOrEventPic,
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

    public String getBookmarkTimeStamp() {
        return bookmarkTimeStamp;
    }

    public void setBookmarkTimeStamp(String bookmarkTimeStamp) {
        this.bookmarkTimeStamp = bookmarkTimeStamp;
    }

    public int getPlaceOrEventPic() {
        return PlaceOrEventPic;
    }

    public void setPlaceOrEventPic(int placeOrEventPic) {
        this.PlaceOrEventPic = placeOrEventPic;
    }

    public String getPlaceOrEventName() {
        return PlaceOrEventName;
    }

    public void setPlaceOrEventName(String placeOrEventName) {
        this.PlaceOrEventName = placeOrEventName;
    }

    public String getPlaceOrEventCategory() {
        return PlaceOrEventCategory;
    }

    public void setPlaceOrEventCategory(String placeOrEventCategory) {
        this.PlaceOrEventCategory = placeOrEventCategory;
    }

    public int getBookmarkLogo() {
        return bookmarkLogo;
    }

    public void setBookmarkLogo(int bookmarkLogo) {
        this.bookmarkLogo = bookmarkLogo;
    }
}
