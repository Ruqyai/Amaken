package com.example.afaf.amakenapp.helper;

/**
 * Created by sondos on 05/03/2017.
 */

public class User_likes {
    private int id;
    private Place place;
    private Event event;
    private int review_id;
    private User user;
    private String like_type;
    private long time_stamp;

    public User_likes(int id, Place place, Event event, int review_id,
                            User user, String like_type, long time_stamp){
        this.event=event;
        this.id=id;
        this.place=place;
        this.review_id=review_id;
        this.user=user;
        this.like_type=like_type;
        this.time_stamp=time_stamp;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public int getReview_id() {
        return review_id;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public Place getPlace() {
        return place;
    }

    public String getLike_type() {
        return like_type;
    }

}
