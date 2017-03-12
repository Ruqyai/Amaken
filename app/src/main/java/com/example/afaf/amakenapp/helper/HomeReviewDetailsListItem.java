package com.example.afaf.amakenapp.helper;

/**
 * Created by User on 3/12/2017.
 */

public class HomeReviewDetailsListItem {

    private String elandNameUserWriteReview;
    private String expandTheReview;
    private int elandUserWriteReviewProfileImage;
    private int elandIconLikeImage;
    private int elandIconFlagImage;

    public HomeReviewDetailsListItem(
            int elandUserWriteReviewProfileImage,
            String elandNameUserWriteReview,
            String expandTheReview,
            int elandIconLikeImage,
            int elandIconFlagImage) {
        this.elandNameUserWriteReview = elandNameUserWriteReview;
        this.expandTheReview = expandTheReview;
        this.elandUserWriteReviewProfileImage = elandUserWriteReviewProfileImage;
        this.elandIconLikeImage = elandIconLikeImage;
        this.elandIconFlagImage = elandIconFlagImage;

    }

    public String getElandNameUserWriteReview() {
        return elandNameUserWriteReview;
    }

    public String getExpandTheReview() {
        return expandTheReview;
    }

    public int getElandUserWriteReviewProfileImage() {
        return elandUserWriteReviewProfileImage;
    }

    public int getElandIconLikeImage() {
        return elandIconLikeImage;
    }

    public int getElandIconFlagImage() {
        return elandIconFlagImage;
    }

    public void setElandNameUserWriteReview(String elandNameUserWriteReview) {
        this.elandNameUserWriteReview = elandNameUserWriteReview;
    }

    public void setExpandTheReview(String expandTheReview) {
        this.expandTheReview = expandTheReview;
    }

    public void setElandUserWriteReviewProfileImage(int elandUserWriteReviewProfileImage) {
        this.elandUserWriteReviewProfileImage = elandUserWriteReviewProfileImage;
    }

    public void setElandIconLikeImage(int elandIconLikeImage) {
        this.elandIconLikeImage = elandIconLikeImage;
    }

    public void setElandIconFlagImage(int elandIconFlagImage) {
        this.elandIconFlagImage = elandIconFlagImage;
    }
}