package com.amakenapp.website.amakenapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.android.volley.AuthFailureError;
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


public class AddPlace extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FragmentManager fm = getSupportFragmentManager();
    Spinner spinnerDialog, spinnerDialog1, spinnerDialog2;
    private EditText editPlaceName, editDesPlace;
    private Button buttonDone;
    private ProgressDialog progressDialog;
    private ArrayList<String> countries, cities, categories;
    private ArrayList<Integer> countryIds, citiesIds, categoriesIds;

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
            @Override
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


        progressDialog = new ProgressDialog(this);

        editPlaceName = (EditText) findViewById(R.id.EnterPlaceName);
        editDesPlace = (EditText) findViewById(R.id.DescriptionthePlace);

        spinnerDialog = (Spinner) findViewById(R.id.add_place_categories_spinner_dialog);
        spinnerDialog1 = (Spinner) findViewById(R.id.add_place_countries_spinner_dialog);
        spinnerDialog2 = (Spinner) findViewById(R.id.add_place_cities_spinner_dialog);

        categories = new ArrayList<>();
        categoriesIds = new ArrayList<>();

        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();

        spinnerDialog1.setOnItemSelectedListener(this);

        buttonDone = (Button) findViewById(R.id.add_place_buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonDone) {
                    doneAddPlace();

                }
            }
        });

        loadCountries();


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), NavDrw.class));

            return true;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int countryId = countryIds.get(position);
        loadCities(countryId);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // THIS IS FOR loading categories
    private void loadCategories(int countryId) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_PLACECATEGORIES + countryId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("categories");

                    for (int i = 0; i < arr.length(); i++) {
                        categories.add(arr.getJSONObject(i).getString("category_type"));

                        categoriesIds.add(arr.getJSONObject(i).getInt("id"));
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(AddPlace.this, android.R.layout.simple_spinner_dropdown_item, categories);
                    spinnerDialog.setAdapter(adapter);
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



    // THIS IS FOR loading cities for a particular country
    private void loadCities(int countryId) {
        cities.clear();
        citiesIds.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_CITIES + countryId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("cities");

                    for (int i = 0; i < arr.length(); i++) {
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
    private void loadCountries() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_COUNTRIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("countries");

                    for (int i = 0; i < arr.length(); i++) {
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

// this is for user sign up

    public void doneAddPlace() {
        final int userType = Constants.CODE_BUSINESS_USER;

        final String placeName = editPlaceName.getText().toString().trim();
        final String DescripPlace = editDesPlace.getText().toString().trim();
        final int categoryID = categoriesIds.get(spinnerDialog.getSelectedItemPosition());
        final int countryID = countryIds.get(spinnerDialog1.getSelectedItemPosition());
        final int cityID = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

        progressDialog.setMessage("Added place...");
        progressDialog.show();

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_SINGUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                finish();
                                // TODO: 3/5/2017  need fixing causing crash
                                startActivity(new Intent(getApplicationContext(), ChooseInterest.class));

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressDialog.dismiss();

                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("place_name", placeName + "");
                params.put("place_description", DescripPlace);
//                params.put("password", password);
//                params.put("name", personName);
//                params.put("gender", gender);
//                params.put("web_url", WebsiteUrl);
//                params.put("phone_number", phoneNumber);
                params.put("country_id", countryID + "");
                params.put("city_id", cityID + "");

                return params;


            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }


}

