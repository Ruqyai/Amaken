package com.example.afaf.amakenapp.helper;

/**
 * Created by sondos on 05/03/2017.
 */

public class Location {
    private int id;
    private String address;
    private long latitude;
    private long longitude;


    public Location( int id, String address, long latitude, long longitude){
        this.id=id;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public int getId(){ return  id; }
    public String getAddress(){ return  address; }
    public long getLatitude(){ return  latitude; }
    public long getLongitude(){return  longitude;}



}
