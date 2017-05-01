package com.amakenapp.website.amakenapp.helper;

/**
 * Created by Muha on 3/11/2017.
 */

public class NotificationsListItem {

    private String notificationTimeStamp;
    private String notification_message;


    private String notification_user_profile_pic;
    private int generatorId;


    private int notification_id;
    private int targetUserId;
    private String notiType;
    private int eventId;
    private int placeId;
    private int reviewId;
    private int reportId;
    private int likeId;
    private int bookmarkId;

    public NotificationsListItem() {
    }


    public String getNotificationTimeStamp() {
        return notificationTimeStamp;
    }

    public void setNotificationTimeStamp(String notificationTimeStamp) {
        this.notificationTimeStamp = notificationTimeStamp;
    }

    public String getNotification_message() {
        return notification_message;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }

    public String getNotification_user_profile_pic() {
        return notification_user_profile_pic;
    }

    public void setNotification_user_profile_pic(String notification_user_profile_pic) {
        this.notification_user_profile_pic = notification_user_profile_pic;
    }

    public int getGeneratorId() {
        return generatorId;
    }

    public void setGeneratorId(int generatorId) {
        this.generatorId = generatorId;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getNotiType() {
        return notiType;
    }

    public void setNotiType(String notiType) {
        this.notiType = notiType;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReoprtId() {
        return reportId;
    }

    public void setReoprtId(int reoprtId) {
        this.reportId = reoprtId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}