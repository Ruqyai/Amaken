package com.example.afaf.amakenapp.helper;

/**
 * Created by Muha on 3/11/2017.
 */

public class ProfileReviewsListItem {

    private String yourReviewText;
    private String reviewTimeStamp;


    private int profile_pic;
    private String reviewText;
    private int ratingbarLogo;
    private int likesLogo;
    private String likesStat;

    public ProfileReviewsListItem(
                                  String yourReviewText,
                                  String reviewTimeStamp,
                                  int profile_pic,
                                  String reviewText,
                                  int ratingbarLogo,
                                  int likesLogo,
                                  String likesStat) {

        this.yourReviewText = yourReviewText;
        this.reviewTimeStamp = reviewTimeStamp;
        this.profile_pic = profile_pic;
        this.reviewText = reviewText;
        this.ratingbarLogo = ratingbarLogo;
        this.likesLogo = likesLogo;
        this.likesStat = likesStat;
    }



    public String getYourReviewText() {
        return yourReviewText;
    }

    public void setYourReviewText(String yourReviewText) {
        this.yourReviewText = yourReviewText;
    }

    public String getReviewTimeStamp() {
        return reviewTimeStamp;
    }

    public void setReviewTimeStamp(String reviewTimeStamp) {
        this.reviewTimeStamp = reviewTimeStamp;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRatingbarLogo() {
        return ratingbarLogo;
    }

    public void setRatingbarLogo(int ratingbarLogo) {
        this.ratingbarLogo = ratingbarLogo;
    }

    public int getLikesLogo() {
        return likesLogo;
    }

    public void setLikesLogo(int likesLogo) {
        this.likesLogo = likesLogo;
    }

    public String getLikesStat() {
        return likesStat;
    }

    public void setLikesStat(String likesStat) {
        this.likesStat = likesStat;
    }
}