package com.amakenapp.website.amakenapp.helper;

/**
 * Created by User on 3/12/2017.
 */

public class ExpandReviewDetailsListItem {

    private String reviewUserName;
    private String reviewTimestamp;
    private int reviewUserProfilePic;
    private String reviewText;
    private int reviewRatingValue;
    private int reviewLikeImage;
    private String reviewLikesNumber;
    private int reviewFlagImage;

    public ExpandReviewDetailsListItem(String reviewUserName,
                                       String reviewTimestamp,
                                       int reviewUserProfilePic,
                                       String reviewText,
                                       int reviewRatingValue,
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

    public int getReviewUserProfilePic() {
        return reviewUserProfilePic;
    }

    public void setReviewUserProfilePic(int reviewUserProfilePic) {
        this.reviewUserProfilePic = reviewUserProfilePic;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getReviewRatingValue() {
        return reviewRatingValue;
    }

    public void setReviewRatingValue(int reviewRatingValue) {
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