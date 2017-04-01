package com.amakenapp.website.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class Place_Category {
    private int id;
    private String category;

    public Place_Category(int id, String category){this.id=id; this.category=category; }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
