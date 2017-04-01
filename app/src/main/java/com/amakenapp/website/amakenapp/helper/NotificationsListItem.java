package com.amakenapp.website.amakenapp.helper;

/**
 * Created by Muha on 3/11/2017.
 */

public class NotificationsListItem {

    private String notificationTimeStamp;


    private int notification_user_profile_pic;
    private String notification_message;
    private String notification_id_hidden;

    public NotificationsListItem(String notificationTimeStamp,
                                 int notification_user_profile_pic,
                                 String notification_message,
                                 String notification_id_hidden) {
        this.notificationTimeStamp = notificationTimeStamp;
        this.notification_user_profile_pic = notification_user_profile_pic;
        this.notification_message = notification_message;
        this.notification_id_hidden = notification_id_hidden;
    }

    public String getNotificationTimeStamp() {
        return notificationTimeStamp;
    }

    public void setNotificationTimeStamp(String notificationTimeStamp) {
        this.notificationTimeStamp = notificationTimeStamp;
    }

    public int getNotification_user_profile_pic() {
        return notification_user_profile_pic;
    }

    public void setNotification_user_profile_pic(int notification_user_profile_pic) {
        this.notification_user_profile_pic = notification_user_profile_pic;
    }

    public String getNotification_message() {
        return notification_message;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }

    public String getNotification_id_hidden() {
        return notification_id_hidden;
    }

    public void setNotification_id_hidden(String notification_id_hidden) {
        this.notification_id_hidden = notification_id_hidden;
    }
}