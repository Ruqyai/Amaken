package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.Constants;
import com.example.afaf.amakenapp.helper.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class SignUpBusiness extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private EditText editEmail, editPassword, editPersonName,editwebsiteUrl,editPhoneNumber;
    private Button signUpBusiness;
    Spinner spinnerDialog, spinnerDialog2;
    private ArrayList<String> countries, cities;
    private ArrayList<Integer> countryIds, citiesIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_business);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_up_business_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        signUpBusiness = (Button) findViewById(R.id.SignUpBusiness);
        signUpBusiness.setOnClickListener(this);

        spinnerDialog = (Spinner) findViewById(R.id.sign_up_business_countries_spinner_dialog);
        spinnerDialog2 = (Spinner) findViewById(R.id.sign_up_business_cities_spinner_dialog);

        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();

        spinnerDialog.setOnItemSelectedListener(this);
        //spinnerDialog2.setOnItemSelectedListener(this);


        editEmail = (EditText) findViewById(R.id.businessEmail);
        editPassword = (EditText) findViewById(R.id.businessPassword);
        editPersonName = (EditText) findViewById(R.id.businessName);
        editwebsiteUrl = (EditText) findViewById(R.id.businessWebUrl);
        editPhoneNumber = (EditText) findViewById(R.id.businessPhoneNumber);

        loadCountries();
    }


    @Override
    public void onClick(View v) {
        if (v == signUpBusiness) {
            singUp();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), SignUpChooser.class));

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


    // THIS IS FOR loading cities for a particular country
    private void loadCities(int countryId){
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(SignUpBusiness.this, android.R.layout.simple_spinner_dropdown_item, cities);
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(SignUpBusiness.this, android.R.layout.simple_spinner_dropdown_item, countries);
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




// this is for user sign up

    public void singUp() {
        final int userType = Constants.CODE_BUSINESS_USER;

        final String userEmail = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        final String personName = editPersonName.getText().toString().trim();
        final String  gender = "";
        final String WebsiteUrl = editwebsiteUrl.getText().toString().trim();
        final String phoneNumber = editPhoneNumber.getText().toString().trim();
        final int countryID = 1;
        final int cityID = 2;

//        progressDialog.show();

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_SINGUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                //finish();
                                   // TODO: 3/5/2017  need fixing causing crash
                                //startActivity(new Intent(getApplicationContext(), ChooseInterest.class));

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
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_type", userType+"" );
                params.put("email", userEmail);
                params.put("password", password);
                params.put("name", personName);
                params.put("gender", gender);
                params.put("web_url", WebsiteUrl);
                params.put("phone_number", phoneNumber);
                params.put("country_id", countryID+"" );
                params.put("city_id", cityID+"" );

                return params;


            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }

}