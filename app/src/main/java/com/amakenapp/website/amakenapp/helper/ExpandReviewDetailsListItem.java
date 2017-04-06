package com.amakenapp.website.amakenapp.helper;

import android.widget.RatingBar;

import com.amakenapp.website.amakenapp.R;

/**
 * Created by User on 3/12/2017.
 */

public class ExpandReviewDetailsListItem {

    private String reviewUserName;
    private String reviewTimestamp;
    private String reviewUserProfilePic;
    private String reviewText;
    private Float reviewRatingValue;
    private int reviewLikeImage = R.drawable.ic_thump_up;
    private String reviewLikesNumber;
    private int reviewFlagImage = R.drawable.ic_report_flag;


    public ExpandReviewDetailsListItem() {
    }

    public ExpandReviewDetailsListItem(String reviewUserName,
                                       String reviewTimestamp,
                                       String reviewUserProfilePic,
                                       String reviewText,
                                       Float reviewRatingValue,
                                       int reviewLikeImage,
                                       String reviewLikesNumber,
                                       int reviewFlagImage) {
        this.reviewUserName = reviewUserName;
        this.reviewTimestamp = reviewTimestamp;
        this.reviewUserProfilePic = reviewUserProfilePic;
        this.reviewText = reviewText;
        this.reviewRatingValue = reviewRatingValue;
        this.reviewLikeImage = reviewLikeImage;
        this.reviewLikesNumber = reviewLikesNumber;
        this.reviewFlagImage = reviewFlagImage;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewTimestamp() {
        return reviewTimestamp;
    }

    public void setReviewTimestamp(String reviewTimestamp) {
        this.reviewTimestamp = reviewTimestamp;
    }

    public String getReviewUserProfilePic() {
        return reviewUserProfilePic;
    }

    public void setReviewUserProfilePic(String reviewUserProfilePic) {
        this.reviewUserProfilePic = reviewUserProfilePic;
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

    public int getReviewFlagImage() {
        return reviewFlagImage;
    }

    public void setReviewFlagImage(int reviewFlagImage) {
        this.reviewFlagImage = reviewFlagImage;
    }
}