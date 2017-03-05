package com.example.afaf.amakenapp.helper;

/**
 * Created by sondos on 05/03/2017.
 */

public class Event_Category {
    private int id;
    private  String category_type;

    public Event_Category(int id, String category_type){
        this.id=id;
        this.category_type=category_type;
    }
    public int getId(){
        return id;
    }
    public String getCategory_type(){
        return category_type;
    }

}
