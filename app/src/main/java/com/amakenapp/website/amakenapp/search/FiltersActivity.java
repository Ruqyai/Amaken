package com.amakenapp.website.amakenapp.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FiltersActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    Spinner spinnerCounty,spinnerCity, spinnerCategories;
    private ArrayList<String> countries, cities, categories;
    private ArrayList<Integer> countryIds, citiesIds,categoriesIds;
    private Map<String,Integer> ciIds;
    private RadioGroup filterRadio;
    private Button done,ignore;
    RadioGroup radioGroup;
    RadioGroup radioGroupType;
    TextView category;
   private static int user_type,filer_type;
    private static int counID, cit_ID_city,cateID ;
    private int choosedFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        radioGroup = (RadioGroup) findViewById(R.id.radioFilter);
        radioGroupType = (RadioGroup) findViewById(R.id.user_type_group);
        radioGroupType.setVisibility(View.INVISIBLE);
        category=(TextView) findViewById(R.id.cate);
        ciIds=new HashMap<>();
        /////
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new  RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    switch (checkedId) {
                        case R.id.placeFilter:
                            category.setText("Category");
                            spinnerCategories.setVisibility(View.VISIBLE);
                            radioGroupType.setVisibility(View.INVISIBLE);
                            choosedFilter=1;

                            break;

                        case R.id.eventFilter:
                            category.setText("Category");
                            spinnerCategories.setVisibility(View.VISIBLE);
                            radioGroupType.setVisibility(View.INVISIBLE);
                            choosedFilter=2;
                            break;

                        case R.id.userFilter:
                            category.setText("User Type");
                            spinnerCategories.setVisibility(View.INVISIBLE);
                            radioGroupType.setVisibility(View.VISIBLE);
                            done.setEnabled(false);
                            done.setBackgroundResource(R.color.barlight);
                            choosedFilter=3;
                            break;
                    }
                }
            }

        });

        radioGroupType = (RadioGroup) findViewById(R.id.user_type_group);
        radioGroupType.clearCheck();
        radioGroupType.setOnCheckedChangeListener(new  RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {


                    switch (checkedId) {
                        case R.id.owner:
                            user_type=1245;
                            done.setEnabled(true);
                            done.setBackgroundResource(R.color.bar);
                            break;
                        case R.id.user:
                            user_type=1244;
                            done.setEnabled(true);
                            done.setBackgroundResource(R.color.bar);

                            break;
                    }
                }
            }

        });

        spinnerCounty= (Spinner) findViewById(R.id.spinnerCountry);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        spinnerCategories=  (Spinner) findViewById(R.id.spinnerCategory);
        done=(Button) findViewById(R.id.filterButton);
        ignore=(Button) findViewById(R.id.ignoreFilterButton);

        countries = new ArrayList<>();
        countryIds = new ArrayList<>();
        categories = new ArrayList<>();
        categoriesIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();
        ciIds=new HashMap<>();
        filterRadio = (RadioGroup) findViewById(R.id.radioFilter);

            spinnerCounty.setOnItemSelectedListener(this);
        spinnerCategories.setOnItemSelectedListener(this);
        done.setOnClickListener(this);
        ignore.setOnClickListener(this);

        loadCountries();
        loadCategory();

        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.findViewById(R.id.spinnerCountry)!=null){
            counID = countryIds.get(position);
            loadCities(counID);

       }
        if(parent.findViewById(R.id.spinnerCity)!=null){
            cit_ID_city = citiesIds.get(position) ;
        }
        if(parent.findViewById(R.id.spinnerCategory)!=null){
            cateID = categoriesIds.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void loadCities(int countryId){
        cities.clear();
        citiesIds.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_CITIES+countryId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("cities");

                    for(int i = 0; i<arr.length(); i++){
                        cities.add(arr.getJSONObject(i).getString("city_name"));

                        citiesIds.add(arr.getJSONObject(i).getInt("id"));
                        ciIds.put(arr.getJSONObject(i).getString("city_name"),arr.getJSONObject(i).getInt("id"));
                    }

                    ArrayAdapter adapter = new ArrayAdapter<>(FiltersActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);
                    spinnerCity.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private void loadCountries(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_COUNTRIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("countries");

                    for(int i = 0; i<arr.length(); i++){
                        countries.add(arr.getJSONObject(i).getString("country_name"));

                        countryIds.add(arr.getJSONObject(i).getInt("id"));
                    }

                    ArrayAdapter adapter = new ArrayAdapter<>(FiltersActivity.this, android.R.layout.simple_spinner_dropdown_item, countries);
                    spinnerCounty.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void loadCategory(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_PLACECATEGORIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("categories");

                    for(int i = 0; i<arr.length(); i++){
                        categories.add(arr.getJSONObject(i).getString("category_type"));

                        categoriesIds.add(arr.getJSONObject(i).getInt("id"));
                    }

                    ArrayAdapter adapter = new ArrayAdapter<>(FiltersActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);
                    spinnerCategories.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==done){
        selectedFilter();

      }
        if(v==ignore){
            finish();
        }
    }
    private void selectedFilter() {

        if(choosedFilter==1){
            filterPlace(counID, cit_ID_city,cateID);
        }
       else if(choosedFilter==2){
            filterEvent(counID, cit_ID_city,cateID);
        }
       else if(choosedFilter==3){
            filterUser(counID, cit_ID_city,user_type);
        }
        else{
            Toast.makeText(this,"Choose ether place, event,or user",Toast.LENGTH_LONG).show();
        }
    }

    public void filterPlace(int cou,  int cit,int cate){

     final int  couID= countryIds.get(spinnerCounty.getSelectedItemPosition());
        final int  ciID=citiesIds.get(spinnerCity.getSelectedItemPosition());
        final int   catID=categoriesIds.get(spinnerCategories.getSelectedItemPosition());
        Intent intent=new Intent(getApplicationContext(), SearchResult.class);
        intent.putExtra("counID_Place",couID);
        intent.putExtra("citID_Place",ciID);
        intent.putExtra("cateID_Place",catID);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
     //   Toast.makeText(getApplicationContext(),"  " +couID+"  "+ciID+"  "+catID, Toast.LENGTH_LONG).show();

    }

    public void filterEvent(int cou, int cit,int cate){
        final int  couID= countryIds.get(spinnerCounty.getSelectedItemPosition());
        final int  ciID=citiesIds.get(spinnerCity.getSelectedItemPosition());
        final int   catID=categoriesIds.get(spinnerCategories.getSelectedItemPosition());
        Intent intent=new Intent(getApplicationContext(), SearchResult.class);
        intent.putExtra("counID_Event",couID);
        intent.putExtra("citID_Event",ciID);
        intent.putExtra("cateID_Event",catID);
     //   Toast.makeText(getApplicationContext(),"  " +couID+"  "+ciID+"  "+catID, Toast.LENGTH_LONG).show();
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    public void filterUser(int cou, int cit,int type){
        final int  couID=  countryIds.get(spinnerCounty.getSelectedItemPosition());
        final int  ciID=citiesIds.get(spinnerCity.getSelectedItemPosition());
        final int userType=type;
        Intent intent=new Intent(getApplicationContext(), SearchResult.class);
        intent.putExtra("counID_User",couID);
        intent.putExtra("citID_User",ciID);
        intent.putExtra("user_type_User",userType);
     //   Toast.makeText(getApplicationContext(),"  " +couID+"  "+ciID+"  "+userType, Toast.LENGTH_LONG).show();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
