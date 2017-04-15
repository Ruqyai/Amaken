package com.amakenapp.website.amakenapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SignUpUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editTextUsername, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private RadioGroup genderRadio;
    private ProgressDialog progressDialog;
    Spinner spinnerDialog, spinnerDialog2;

    private ArrayList<String> countries, cities;
    private ArrayList<Integer> countryIds, citiesIds;

    private  String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_up_user_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerDialog = (Spinner) findViewById(R.id.sign_up_user_countries_spinner_dialog);
        spinnerDialog2 = (Spinner) findViewById(R.id.sign_up_user_cities_spinner_dialog);


        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();

        spinnerDialog.setOnItemSelectedListener(this);



        editTextEmail = (EditText) findViewById(R.id.UserEmail);
        editTextUsername = (EditText) findViewById(R.id.UserName);
        editTextPassword = (EditText) findViewById(R.id.UserPassword);

        genderRadio = (RadioGroup) findViewById(R.id.gender);





        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.button_Register);
        buttonRegister.setOnClickListener(this);

        loadCountries();


    }




    @Override
    public void onClick(View v) {
        if (v == buttonRegister)
        {

            registerRegularUser();
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



    //THIS IS FOR LOADING THE CITIES OF A PARTICULAR COUNTRY
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(SignUpUser.this, android.R.layout.simple_spinner_dropdown_item, cities);
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


    //THIS IS FOR LOADING ALL COUNTRIES
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(SignUpUser.this, android.R.layout.simple_spinner_dropdown_item, countries);
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



    //THIS IS FOR USER REGISTRATION
    private void registerRegularUser() {
        final int user_type = Constants.CODE_NORMAL_USER;
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String gender = ((RadioButton)findViewById(genderRadio.getCheckedRadioButtonId())).getText().toString();
        final String web_url = "";
        final String phone_number = "";
        final int country_id = countryIds.get(spinnerDialog.getSelectedItemPosition());
        final int city_id = citiesIds.get(spinnerDialog2.getSelectedItemPosition());



        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
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
                                singIn();
                                Intent intent=new Intent(getApplicationContext(), ChooseInterest.class);
                                intent.putExtra("email",email);
                                startActivity(intent);



                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), "bel" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_type", user_type+"");
                params.put("email", email);
                params.put("password", password);
                params.put("name", username);
                params.put("gender", gender);
                params.put("web_url", web_url);
                params.put("phone_number", phone_number);
                params.put("country_id", country_id+"");
                params.put("city_id", city_id+"");

                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void singIn() {
        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                int userId = obj.getInt("id");
                                String userIdString = Integer.toString(userId);
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                userIdString,
                                                obj.getString("user_type"),
                                                obj.getString("user_email"),
                                                obj.getString("user_password"),
                                                obj.getString("user_name"),
                                                TextUtils.isEmpty(obj.getString("gender"))?"":obj.getString("gender"),
                                                TextUtils.isEmpty(obj.getString("web_url"))?"":obj.getString("web_url"),
                                                TextUtils.isEmpty(obj.getString("phone_number"))?"":obj.getString("phone_number"),
                                                obj.getInt("country_id"),
                                                obj.getString("country_name"),
                                                obj.getInt("city_id"),
                                                obj.getString("city_name"),
                                                TextUtils.isEmpty(obj.getString("profile_pic_id"))?"":obj.getString("profile_pic_id") ,
                                                TextUtils.isEmpty(obj.getString("profile_pic_url"))?"":obj.getString("profile_pic_url")
                                        );
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
                params.put("userEmail", email);
                params.put("password", password);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }




}
