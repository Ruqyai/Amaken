package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
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
import java.util.List;
import java.util.Map;

public class ComplateActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private ArrayList<String> countries, cities;
    private ArrayList<Integer> countryIds, citiesIds;
    private ArrayList<Integer> storeInter;
    Spinner spinnerDialog, spinnerDialog2;
    private Button buttonSave;
    private CheckBox
            cafeBox,
            educationBox,
            entertainmentBox,
            hair_stylistsBox,
            hospitalsBox,
            hotelsBox,
            mallsBox,
            parksBox,
            restaurantsBox,
            spaBox,
            sportBox,
            tourismBox;
    private String email, password, username, picture;
    LinearLayout linearLayout;
    TextView chooseInterest, nameOfUser;
    SharedPrefManager sharedPrefManager;
    //  private static int userId;
    private int usID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complate);
        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());

        email = getIntent().getExtras().getString("email");
        password = getIntent().getExtras().getString("password");
        username = getIntent().getExtras().getString("name");
        picture = getIntent().getExtras().getString("pic");

        buttonSave = (Button) findViewById(R.id.ButtonSave);

        hospitalsBox = (CheckBox) findViewById(R.id.checkBoxyHospitals);
        hair_stylistsBox = (CheckBox) findViewById(R.id.checkBoxHairstylists);
        cafeBox = (CheckBox) findViewById(R.id.checkBoxCafes);
        tourismBox = (CheckBox) findViewById(R.id.checkBoxtourism);
        educationBox = (CheckBox) findViewById(R.id.checkBoxEducation);
        restaurantsBox = (CheckBox) findViewById(R.id.checkBoxRestaurants);
        mallsBox = (CheckBox) findViewById(R.id.checkBoxyMalls);
        entertainmentBox = (CheckBox) findViewById(R.id.checkBoxEntertainment);
        spaBox = (CheckBox) findViewById(R.id.checkBoxSpa);
        sportBox = (CheckBox) findViewById(R.id.checkBoxSport);
        hotelsBox = (CheckBox) findViewById(R.id.checkBoxHotels);
        parksBox = (CheckBox) findViewById(R.id.checkBoxParks);


        linearLayout = (LinearLayout) findViewById(R.id.layCate);
        spinnerDialog = (Spinner) findViewById(R.id.sign_up_user_countries_spinner_dialog);
        spinnerDialog2 = (Spinner) findViewById(R.id.sign_up_user_cities_spinner_dialog);

        chooseInterest = (TextView) findViewById(R.id.textcChooseInteresting);
        chooseInterest.setOnClickListener(this);
        nameOfUser = (TextView) findViewById(R.id.textUserWeclome);
        nameOfUser.setText(username);


        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();
        storeInter = new ArrayList<>();

        spinnerDialog.setOnItemSelectedListener(this);
        buttonSave.setOnClickListener(this);
        loadCountries();
    }

    public void checkBoxInterest(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBoxyHospitals:
                if (checked) {
                    storeInter.add(1);
                } else {
                    storeInter.remove(1);
                }
                //    break;
            case R.id.checkBoxHairstylists:
                if (checked) {
                    storeInter.add(2);
                } else {
                    storeInter.remove(2);
                }
                //   break;
            case R.id.checkBoxCafes:
                if (checked) {
                    storeInter.add(3);
                } else {
                    storeInter.remove(3);
                }
                //    break;
            case R.id.checkBoxtourism:
                if (checked) {
                    storeInter.add(4);
                } else {
                    storeInter.remove(4);
                }
                //  break;
            case R.id.checkBoxEducation:
                if (checked) {
                    storeInter.add(5);
                } else {
                    storeInter.remove(5);
                }
                //   break;
            case R.id.checkBoxRestaurants:
                if (checked) {
                    storeInter.add(6);
                } else {
                    storeInter.remove(6);
                }
                //   break;
            case R.id.checkBoxyMalls:
                if (checked) {
                    storeInter.add(7);
                } else {
                    storeInter.remove(7);
                }

                //    break;
            case R.id.checkBoxEntertainment:
                if (checked) {
                    storeInter.add(8);
                } else {
                    storeInter.remove(8);
                }

                //    break;
            case R.id.checkBoxSpa:
                if (checked) {
                    storeInter.add(9);
                } else {
                    storeInter.remove(9);
                }
                //   break;
            case R.id.checkBoxSport:
                if (checked) {
                    storeInter.add(10);
                } else {
                    storeInter.remove(10);
                }
                //    break;
            case R.id.checkBoxHotels:
                if (checked) {
                    storeInter.add(11);
                } else {
                    storeInter.remove(11);
                }
                //    break;
            case R.id.checkBoxParks:
                if (checked) {
                    storeInter.add(12);
                } else {
                    storeInter.remove(12);
                }
                //    break;

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int countryId = countryIds.get(position);
        loadCities(countryId);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(ComplateActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(ComplateActivity.this, android.R.layout.simple_spinner_dropdown_item, countries);
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
    private void registerRegularUser() {
        final int user_type = Constants.CODE_NORMAL_USER;
        final String gender = "";
        final String web_url = "";
        final String phone_number = "";
        final int country_id = countryIds.get(spinnerDialog.getSelectedItemPosition());
        final int city_id = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SINGUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {

                                singIn();

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

                        Toast.makeText(getApplicationContext(), "bel" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_type", user_type + "");
                params.put("email", email);
                params.put("password", password);
                params.put("name", username);
                params.put("gender", gender);
                params.put("web_url", web_url);
                params.put("phone_number", phone_number);
                params.put("country_id", country_id + "");
                params.put("city_id", city_id + "");

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
                                                TextUtils.isEmpty(obj.getString("gender")) ? "" : obj.getString("gender"),
                                                TextUtils.isEmpty(obj.getString("web_url")) ? "" : obj.getString("web_url"),
                                                TextUtils.isEmpty(obj.getString("phone_number")) ? "" : obj.getString("phone_number"),
                                                obj.getInt("country_id"),
                                                obj.getString("country_name"),
                                                obj.getInt("city_id"),
                                                obj.getString("city_name"),
                                                "8888",
                                                picture,
                                                TextUtils.isEmpty(obj.getString("profile_pic_timeStamp")) ? "" : obj.getString("profile_pic_timeStamp")
                                        );
                            } else {
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

    @Override
    public void onClick(View v) {

        if (v == buttonSave) {
            storingInterests(storeInter);
            startActivity(new Intent(getApplicationContext(), NavDrw.class));
        }
        if (v == chooseInterest) {
            buttonSave.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            registerRegularUser();

        }
    }

    public void storingInterests(final List<Integer> interests) {
        final int userId = sharedPrefManager.getUserId();
        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_STOREINTERESTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                finish();
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId + "");
                for (int i = 0; i < interests.size(); i++) {
                    params.put("interest_id[" + i + "]", String.valueOf(interests.get(i)));
                }
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(send);
    }

    public int getUserIdByEmail() {
        StringRequest send = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                usID = obj.getInt("user_email");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email", email);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(send);
        return usID;
    }

}
