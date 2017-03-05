package com.example.afaf.amakenapp.helper;

/**
 * Created by sondos on 05/03/2017.
 */

public class User_Reviews {
    private int id;
    private String review_text;
    private int rate_value;
    private Place place;
    private Event event;
    private User user;
    private int review_pic_id;
    private  String review_type;


    public User_Reviews( int id, String review_text, int rate_value,
    Place place, Event event, User user, int review_pic_id , String review_type){
        this.id=id;
        this.review_text=review_text;
        this.review_text=review_text;
        this.rate_value=rate_value;
        this.place=place;
        this.event=event;
        this.user=user;
        this.review_pic_id= review_pic_id;
        this.review_type=review_type;
    }

    public int getId() {
        return id;
    }
    public String getReview_text() {
        return review_text;
    }
    public int getRate_value() {
        return rate_value;
    }
    public Place getPlace() {
        return place;
    }
    public Event getEvent() {
        return event;
    }
    public User getUser() {
        return user;
    }
    public int getReview_pic_id() {
        return review_pic_id;
    }
    public String getReview_type() {
        return review_type;
    }









}
