package com.amakenapp.website.amakenapp.helper;

/**
 * Created by User on 3/11/2017.
 */

public class EventsListItem {

    private int eventBusinessProfileImage;
    private String eventBusinessName;
    private int event_availableOrBusyLogo;

    private int eventPicture;
    private String eventName;
    private String eventCategory;
    private String eventDescription;
    private String eventDescriptionMultiLineText;
    private String eventExpand;
    private String eventRatingStat;
    private int ratingEvent;

    public EventsListItem(int eventBusinessProfileImage,
                          String eventBusinessName,
                          int event_availableOrBusyLogo,
                          int eventPicture, String eventName,
                          String eventCategory,
                          String eventDescription,
                          String eventDescriptionMultiLineText,
                          String eventExpand,
                          String eventRatingStat,
                          int ratingEvent)
    {
        this.eventBusinessProfileImage = eventBusinessProfileImage;
        this.eventBusinessName = eventBusinessName;
        this.event_availableOrBusyLogo = event_availableOrBusyLogo;
        this.eventPicture = eventPicture;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.eventDescription = eventDescription;
        this.eventDescriptionMultiLineText = eventDescriptionMultiLineText;
        this.eventExpand = eventExpand;
        this.eventRatingStat = eventRatingStat;
        this.ratingEvent = ratingEvent;
    }

    public int getEventBusinessProfileImage() {
        return eventBusinessProfileImage;
    }

    public void setEventBusinessProfileImage(int eventBusinessProfileImage) {
        this.eventBusinessProfileImage = eventBusinessProfileImage;
    }

    public String getEventBusinessName() {
        return eventBusinessName;
    }

    public void setEventBusinessName(String eventBusinessName) {
        this.eventBusinessName = eventBusinessName;
    }

    public int getEvent_availableOrBusyLogo() {
        return event_availableOrBusyLogo;
    }

    public void setEvent_availableOrBusyLogo(int event_availableOrBusyLogo) {
        this.event_availableOrBusyLogo = event_availableOrBusyLogo;
    }

    public int getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(int eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDescriptionMultiLineText() {
        return eventDescriptionMultiLineText;
    }

    public void setEventDescriptionMultiLineText(String eventDescriptionMultiLineText) {
        this.eventDescriptionMultiLineText = eventDescriptionMultiLineText;
    }

    public String getEventExpand() {
        return eventExpand;
    }

    public void setEventExpand(String eventExpand) {
        this.eventExpand = eventExpand;
    }

    public String getEventRatingStat() {
        return eventRatingStat;
    }

    public void setEventRatingStat(String eventRatingStat) {
        this.eventRatingStat = eventRatingStat;
    }

    public int getRatingEvent() {
        return ratingEvent;
    }

    public void setRatingEvent(int ratingEvent) {
        this.ratingEvent = ratingEvent;
    }
}
