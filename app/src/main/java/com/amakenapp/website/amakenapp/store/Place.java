package com.amakenapp.website.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class Place {
    private int id;
    private String place_name;
    private String place_description;
    private double rate_avg;
    private int owner_id;
    private int location_id;
    private int photo_id;
    private int place_category_id;
    private int place_country_id;
    private int place_city_id;

    public Place (int id, String place_name, String place_description, double rate_avg, int owner_id,
                  int location_id, int photo_id, int place_category_id, int place_city_id){
        this.id=id;
        this.place_name=place_name;
        this.place_description=place_description;
        this.rate_avg=rate_avg;
        this.location_id=location_id;
        this.photo_id=photo_id;
        this.place_country_id=place_category_id;
        this.place_city_id= place_city_id;

    }

    public int getId(){
        return id;
    }
    public String getPlace_name(){ return place_name; }
    public String getPlace_description(){ return place_description; }
    public double getRate_avg(){ return rate_avg; }
    public int getOwner_id(){ return  owner_id; }
    public int getLocation_id(){ return  location_id; }
    public int getPhoto_id(){ return  photo_id; }
    public int getPlace_category_id(){return  place_category_id; }
    public int getPlace_country_id(){return  place_country_id; }
    public int getPlace_city_id(){return  place_city_id; }
}
