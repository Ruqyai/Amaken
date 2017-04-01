package com.amakenapp.website.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class Event {

    private int id;
    private String event_name;
    private String event_description;
    private int rate_avrg;
    //need to check if it is foreign
    private int owner_id;
    //need to check if it is foreign
    private int event_category_id;
    //need to check if it is foreign
    private int event_date_id;
    //need to check if it is foreign
    private int photo_id;
    //need to check if it is foreign
    private int location_id;

    private int event_country_id;
    private int event_city_id;


    public  Event(int id, String event_name, String event_description, int rate_avrg,
                  int owner_id, int event_category_id, int event_date_id, int photo_id,
                  int location_id, int event_country_id, int event_city_id){
        this.id= id;
        this.event_name= event_name;
        this.event_description= event_description;
        this.rate_avrg= rate_avrg;
        this.owner_id= owner_id;
        this.event_category_id= event_category_id;
        this.event_date_id=event_date_id;
        this.photo_id= photo_id;
        this.location_id= location_id;
        this.event_country_id= event_country_id;
        this.event_city_id= event_city_id;

    }

    public int getId(){
        return id;
    }

    public String getEvent_name(){
        return event_name;
    }
    public String event_description(){
        return  event_description;
    }
    public int getRate_avrg(){ return  rate_avrg;}
    public int getOwner_id(){ return  owner_id;}
    public int getEvent_category_id(){ return event_category_id;}
    public int getEvent_date_id(){ return  event_date_id;}
    public int getPhoto_id(){return  photo_id; }
    public int getLocation_id(){ return location_id;}
    public int getEvent_country_id(){return  event_country_id;}
    public int getEvent_city_id(){ return  event_city_id; }

    /**
     * Created by sondos on 05/03/2017.
     */

    public static class Event_Category {
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
}

