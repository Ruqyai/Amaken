package com.example.afaf.amakenapp.helper;

/**
 * Created by User on 3/11/2017.
 */

public class HomeListItem {
    private String nameHomeBusiness;
    private String nameHomeCategory;
    private String homeNumberOfRate;
    private String homeDescription;
    private String homeDescriptionMultiLine;
    private String expandHome;
    private String homeNameTitPlaceHome;
    private int homeBusinessProfileImage;
    private int homeBusinessPlaceImage;
    private int ratingHome;

    public HomeListItem(
            int homeBusinessProfileImage,
            String nameHomeBusiness,
            int homeBusinessPlaceImage,
            String homeNameTitPlaceHome,
            String nameHomeCategory,
            String homeDescription,
            String homeDescriptionMultiLine,
            String expandHome,
            int ratingHome,
            String homeNumberOfRate
    ) {
        this.nameHomeBusiness = nameHomeBusiness;
        this.nameHomeCategory = nameHomeCategory;
        this.homeNumberOfRate = homeNumberOfRate;
        this.homeDescription = homeDescription;
        this.homeDescriptionMultiLine = homeDescriptionMultiLine;
        this.expandHome = expandHome;
        this.homeNameTitPlaceHome = homeNameTitPlaceHome;
        this.homeBusinessProfileImage = homeBusinessProfileImage;
        this.homeBusinessPlaceImage = homeBusinessPlaceImage;
        this.ratingHome = ratingHome;
    }

    public String getNameHomeBusiness() {
        return nameHomeBusiness;
    }

    public String getNameHomeCategory() {
        return nameHomeCategory;
    }

    public String getHomeNumberOfRate() {
        return homeNumberOfRate;
    }

    public String getHomeDescription() {
        return homeDescription;
    }

    public String getHomeDescriptionMultiLine() {
        return homeDescriptionMultiLine;
    }

    public String getExpandHome() {
        return expandHome;
    }

    public String getHomeNameTitPlaceHome() {
        return homeNameTitPlaceHome;
    }

    public int getHomeBusinessProfileImage() {
        return homeBusinessProfileImage;
    }

    public int getHomeBusinessPlaceImage() {
        return homeBusinessPlaceImage;
    }

    public int getRatingHome() {
        return ratingHome;
    }

    public void setNameHomeBusiness(String nameHomeBusiness) {
        this.nameHomeBusiness = nameHomeBusiness;
    }

    public void setNameHomeCategory(String nameHomeCategory) {
        this.nameHomeCategory = nameHomeCategory;
    }

    public void setHomeNumberOfRate(String homeNumberOfRate) {
        this.homeNumberOfRate = homeNumberOfRate;
    }

    public void setHomeDescription(String homeDescription) {
        this.homeDescription = homeDescription;
    }

    public void setHomeDescriptionMultiLine(String homeDescriptionMultiLine) {
        this.homeDescriptionMultiLine = homeDescriptionMultiLine;
    }

    public void setExpandHome(String expandHome) {
        this.expandHome = expandHome;
    }

    public void setHomeNameTitPlaceHome(String homeNameTitPlaceHome) {
        this.homeNameTitPlaceHome = homeNameTitPlaceHome;
    }

    public void setHomeBusinessProfileImage(int homeBusinessProfileImage) {
        this.homeBusinessProfileImage = homeBusinessProfileImage;
    }

    public void setHomeBusinessPlaceImage(int homeBusinessPlaceImage) {
        this.homeBusinessPlaceImage = homeBusinessPlaceImage;
    }

    public void setRatingHome(int ratingHome) {
        this.ratingHome = ratingHome;
    }
}
