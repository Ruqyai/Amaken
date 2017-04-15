package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

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

public class AddPlace extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();
    Spinner spinnerDialog, spinnerDialog1, spinnerDialog2;
    private ArrayList<String> countries, cities, categories ;
    private ArrayList<Integer> countryIds, citiesIds, categoriesIds ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addplace_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         /* Imagebutton for dialog for the place photo */
        // ImageButton to show the dialog
        ImageButton PlaceCamDialog = (ImageButton) findViewById(R.id.photoimageButton);


        // dialog window for one clicking the button

        // Capture button clicks
        PlaceCamDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                 DialogPlaceFragment dFragment = new DialogPlaceFragment();
                // Show DialogFragment
              dFragment.show(fm, "Dialog Fragment");
            }
        });

        /* Imagebutton for dialog for the location  */

        ImageButton locationCamDialog = (ImageButton) findViewById(R.id.locationimageButton);

        locationCamDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        spinnerDialog  = (Spinner) findViewById(R.id.add_place_categories_spinner_dialog);
        spinnerDialog1 = (Spinner) findViewById(R.id.add_place_countries_spinner_dialog);
        spinnerDialog2 = (Spinner) findViewById(R.id.add_place_cities_spinner_dialog);

        categories = new ArrayList<>();
        categoriesIds = new ArrayList<>();

        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();


    }


    // THIS IS FOR loading cities for a particular country
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
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(AddPlace.this, android.R.layout.simple_spinner_dropdown_item, cities);
                    spinnerDialog2.setAdapter(adapter);
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


    // this is for loading all countries
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(AddPlace.this, android.R.layout.simple_spinner_dropdown_item, countries);
                    spinnerDialog1.setAdapter(adapter);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, NavDrw.class));

            return true;
        }
        return true;
    }
}

