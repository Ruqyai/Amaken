package com.example.afaf.amakenapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afaf.amakenapp.helper.Constants;
import com.example.afaf.amakenapp.helper.MySingleton;
import com.example.afaf.amakenapp.R;

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
    private RadioButton selectedGenderRadioButton;
    private ProgressDialog progressDialog;
    Spinner spinnerDialog, spinnerDialog2;
    private TextView test;

    private ArrayList<String> countries, cities;
    private ArrayList<Integer> countryIds, citiesIds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_up_user_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerDialog = (Spinner) findViewById(R.id.spinner_dialog);
        spinnerDialog2 = (Spinner) findViewById(R.id.spinner_dialog2);


        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();

        spinnerDialog.setOnItemSelectedListener(this);
        //spinnerDialog2.setOnItemSelectedListener(this);



        editTextEmail = (EditText) findViewById(R.id.UserEmail);
        editTextUsername = (EditText) findViewById(R.id.UserName);
        editTextPassword = (EditText) findViewById(R.id.UserPassword);

        genderRadio = (RadioGroup) findViewById(R.id.gender);





        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.button_Register);
        buttonRegister.setOnClickListener(this);

        loadCountries();


    }


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




    private void registerRegularUser() {
        final int user_type = Constants.CODE_NORMAL_USER;
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String gender = ((RadioButton)findViewById(genderRadio.getCheckedRadioButtonId())).getText().toString();
        final String web_url = "";
        final String phone_number = "";
        final int country_id = 1;
        final int city_id = 2;



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

                                startActivity(new Intent(getApplicationContext(), com.example.afaf.amakenapp.activities.NavDrw.class));
                                finish();
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

}
