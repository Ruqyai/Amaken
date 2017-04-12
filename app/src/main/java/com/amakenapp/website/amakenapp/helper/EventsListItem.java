package com.amakenapp.website.amakenapp.helper;

/**
 * Created by User on 3/11/2017.
 */

public class EventsListItem {

    private int eventId;
    private String eventavaliabilty;
    private String eventBusinessProfileImage;
    private String eventBusinessName;
    private int event_availableOrBusyLogo;

    private String eventPicture;
    private String eventName;
    private String eventCategory1;

    private String eventDescription = "Description";
    private String eventDescriptionMultiLineText;
    private String eventExpand = "Expand >>";
    private String eventRatingStat;
    private Float ratingEvent;
    private String ratingStat;

    public EventsListItem(int eventId, String eventBusinessProfileImage, String eventBusinessName, int event_availableOrBusyLogo, String eventPicture, String eventName, String eventCategory, String eventDescription, String eventDescriptionMultiLineText, String eventExpand, String eventRatingStat, Float ratingEvent, String ratingStat) {
        this.eventId = eventId;
        this.eventBusinessProfileImage = eventBusinessProfileImage;
        this.eventBusinessName = eventBusinessName;
        this.event_availableOrBusyLogo = event_availableOrBusyLogo;
        this.eventPicture = eventPicture;
        this.eventName = eventName;
        this.eventCategory1 = eventCategory;
        this.eventDescription = eventDescription;
        this.eventDescriptionMultiLineText = eventDescriptionMultiLineText;
        this.eventExpand = eventExpand;
        this.eventRatingStat = eventRatingStat;
        this.ratingEvent = ratingEvent;
        this.ratingStat = ratingStat;
    }

    public void setEventCategory1(String eventCategory1) {
        this.eventCategory1 = eventCategory1;
    }

    public String getEventavaliabilty() {
        return eventavaliabilty;
    }

    public void setEventavaliabilty(String eventavaliabilty) {
        this.eventavaliabilty = eventavaliabilty;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventBusinessProfileImage() {
        return eventBusinessProfileImage;
    }

    public void setEventBusinessProfileImage(String eventBusinessProfileImage) {
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

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory1() {
        return eventCategory1;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory1 = eventCategory;
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

    public Float getRatingEvent() {
        return ratingEvent;
    }

    public void setRatingEvent(Float ratingEvent) {
        this.ratingEvent = ratingEvent;
    }

    public String getRatingStat() {
        return ratingStat;
    }

    public void setRatingStat(String ratingStat) {
        this.ratingStat = ratingStat;
    }

    public EventsListItem() {
    }
}
