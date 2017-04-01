package com.amakenapp.website.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class User_Interest_Category {
    private int id;
    private User user;
    private int intrest_id;

    public User_Interest_Category(int id, User user, int intrest_id){
        this.id=id;
        this.user=user;
        this.intrest_id=intrest_id;
    }

    public int getId() {
        return id;
    }

    public int getIntrest_id() {
        return intrest_id;
    }

    public User getUser() {
        return user;
    }
}
