package com.amakenapp.website.amakenapp.search;

public class SearchList {

     private  int searchId;
     private  String searchName;
     private  String serchType;
     private  String searchDescription;
     private  String searchRate;
     private  String searchOwner_id;
     private  String searchCategory_id;
     private  String searchLocation_id;
     private  String searchCountry_id;
     private  String searchCity_id;
     private  String searchData;
    private String searchUser_type;

    public SearchList() {
    }

    public SearchList(int searchId, String searchName, String serchType, String searchDescription, String searchRate, String searchOwner_id, String searchCategory_id, String searchLocation_id, String searchCountry_id, String searchCity_id, String searchData, String searchUser_type) {
        this.searchId = searchId;
        this.searchName = searchName;
        this.serchType = serchType;
        this.searchDescription = searchDescription;
        this.searchRate = searchRate;
        this.searchOwner_id = searchOwner_id;
        this.searchCategory_id = searchCategory_id;
        this.searchLocation_id = searchLocation_id;
        this.searchCountry_id = searchCountry_id;
        this.searchCity_id = searchCity_id;
        this.searchData = searchData;
        this.searchUser_type = searchUser_type;
    }

    public int getSearchId() {
        return searchId;
    }

    public String getSearchName() {
        return searchName;
    }

    public String getSerchType() {
        return serchType;
    }

    public String getSearchDescription() {
        return searchDescription;
    }

    public String getSearchRate() {
        return searchRate;
    }

    public String getSearchOwner_id() {
        return searchOwner_id;
    }

    public String getSearchCategory_id() {
        return searchCategory_id;
    }

    public String getSearchLocation_id() {
        return searchLocation_id;
    }

    public String getSearchCountry_id() {
        return searchCountry_id;
    }

    public String getSearchCity_id() {
        return searchCity_id;
    }

    public String getSearchData() {
        return searchData;
    }

    public String getSearchUser_type() {
        return searchUser_type;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public void setSerchType(String serchType) {

        this.serchType = serchType;
    }

    public void setSearchDescription(String searchDescription) {
        this.searchDescription = searchDescription;
    }

    public void setSearchRate(String searchRate) {
        this.searchRate = searchRate;
    }

    public void setSearchOwner_id(String searchOwner_id) {
        this.searchOwner_id = searchOwner_id;
    }

    public void setSearchCategory_id(String searchCategory_id) {
        this.searchCategory_id = searchCategory_id;
    }

    public void setSearchLocation_id(String searchLocation_id) {
        this.searchLocation_id = searchLocation_id;
    }

    public void setSearchCountry_id(String searchCountry_id) {
        this.searchCountry_id = searchCountry_id;
    }

    public void setSearchCity_id(String searchCity_id) {
        this.searchCity_id = searchCity_id;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }

    public void setSearchUser_type(String searchUser_type) {
        this.searchUser_type = searchUser_type;
    }



    }
