package com.example.afaf.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class Country {

    private int id;
    private String country_name;

    public Country(int id, String country_name ){
        this.id=id;
        this.country_name=country_name ;
    }
    public int getId(){
        return id;
    }
    public String getCountry_name(){
        return country_name;
    }

}
