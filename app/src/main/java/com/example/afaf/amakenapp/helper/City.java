package com.example.afaf.amakenapp.helper;

/**
 * Created by sondos on 05/03/2017.
 */

public class City {

        private int id;
        private String city_name;
        private int country_id;

        public City(int id,String city_name, int country_id){
            this.id=id;
            this.city_name=city_name;
            this.country_id=country_id;

        }

        public int getId(){
            return id;
        }

        public String getCity_name(){
            return city_name;
        }

        public int getCountry_id(){
            return country_id;
        }
    }


