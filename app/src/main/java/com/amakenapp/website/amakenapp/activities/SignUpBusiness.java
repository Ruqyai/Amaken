package com.amakenapp.website.amakenapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.Map;
import java.util.regex.Pattern;

import static android.util.Patterns.GOOD_IRI_CHAR;
import static android.util.Patterns.TOP_LEVEL_DOMAIN_STR_FOR_WEB_URL;


public class SignUpBusiness extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private EditText editEmail, editPassword, editPersonName,editwebsiteUrl,editPhoneNumber;
    private Button signUpBusiness;
    Spinner spinnerDialog, spinnerDialog2;
    private ProgressDialog progressDialog;
    private ArrayList<String> countries, cities;
    private ArrayList<Integer> countryIds, citiesIds;
    private  String userEmail;
    private String password;

    TextInputLayout emailLayout, nameLayout, passLayout, webLayout, phoneLayout;



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

        progressDialog = new ProgressDialog(this);
        editEmail = (EditText) findViewById(R.id.businessEmail);
        emailLayout= (TextInputLayout) findViewById(R.id.business_Email);

        editPassword = (EditText) findViewById(R.id.businessPassword);
        passLayout =(TextInputLayout) findViewById(R.id.business_Password);


        editPersonName = (EditText) findViewById(R.id.businessName);
        nameLayout= (TextInputLayout) findViewById(R.id.business_name);


        editwebsiteUrl = (EditText) findViewById(R.id.businessWebUrl);
        webLayout =(TextInputLayout) findViewById(R.id.business_WebUrl);

        editPhoneNumber = (EditText) findViewById(R.id.businessPhoneNumber);
        phoneLayout =(TextInputLayout) findViewById(R.id.business_PhoneNumber);


        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emailLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editEmail.getText().toString().isEmpty())
                {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("Please Enter Your Email...!");
                }
                else
                    emailLayout.setErrorEnabled(false);
            }
        });



        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editPassword.getText().toString().isEmpty())
                {
                    passLayout.setErrorEnabled(true);
                    passLayout.setError("Please Enter a Password...!");
                }
                else
                    passLayout.setErrorEnabled(false);
            }
        });



        editPersonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editPersonName.getText().toString().isEmpty())
                {
                    nameLayout.setErrorEnabled(true);
                    nameLayout.setError("Please Enter Your Name...!");
                }
                else
                    nameLayout.setErrorEnabled(false);
            }
        });



        editwebsiteUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                webLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {}});



        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phoneLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {}
        });




        loadCountries();


    }


    @Override
    public void onClick(View v) {
        if (v == signUpBusiness) {

            String useremail = editEmail.getText().toString().trim();
            String userpass = editPassword.getText().toString().trim();
            String username= editPersonName.getText().toString().trim();
            String userWeb = editwebsiteUrl.getText().toString().trim();
            String userPhone = editPhoneNumber.getText().toString().trim();


            if(useremail.isEmpty())
            {
                emailLayout.requestFocus();
                emailLayout.setError("Please Enter Your Email...!");
            }
            else if(!isValidEmail(useremail))
            {
                emailLayout.requestFocus();
                emailLayout.setError("Hmm...This dose not look like an Email address!");

            }
            else if (userpass.length()<6)
            {
                passLayout.requestFocus();
                passLayout.setError("Password must be at least 6 characters...!");
            }
            else if(username.isEmpty())
            {
                nameLayout.requestFocus();
                nameLayout.setError("Please Enter Your Name...!");
            }
            else if (!userWeb.isEmpty() && !isValidWeb(userWeb)) {
                webLayout.requestFocus();
                webLayout.setError("Hmm...This dose not look like a valid Web address!");

            }
            else if (!userPhone.isEmpty() && !isValidPhone(userPhone)) {
                phoneLayout.requestFocus();
                phoneLayout.setError("Hmm...This dose not look like a valid Phone Number!");

            }else
                singUp();

        }

    }


    public final static boolean isValidEmail(CharSequence target) {
        final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        return EMAIL_ADDRESS_PATTERN.matcher(target).matches();
    }

    public final static boolean isValidWeb(CharSequence target) {
        final Pattern WEB_URL = Pattern.compile(
                "((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
                        + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
                        + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
                        + "((?:(?:[" + GOOD_IRI_CHAR + "][" + GOOD_IRI_CHAR + "\\-]{0,64}\\.)+"   // named host
                        + TOP_LEVEL_DOMAIN_STR_FOR_WEB_URL
                        + "|(?:(?:25[0-5]|2[0-4]" // or ip address
                        + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]"
                        + "|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]"
                        + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                        + "|[1-9][0-9]|[0-9])))"
                        + "(?:\\:\\d{1,5})?)" // plus option port number
                        + "(\\/(?:(?:[" + GOOD_IRI_CHAR + "\\;\\/\\?\\:\\@\\&\\=\\#\\~"  // plus option query params
                        + "\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?"
                        + "(?:\\b|$)"); // and finally, a word boundary or end of
        // input.  This is to stop foo.sure from

        return WEB_URL.matcher(target).matches();
    }



    public final static boolean isValidPhone(CharSequence target) {
         final Pattern PHONE = Pattern.compile(                                  // sdd = space, dot, or dash
                              "(\\+[0-9]+[\\- \\.]*)?"                    // +<digits><sdd>*
                               + "(\\([0-9]+\\)[\\- \\.]*)?"               // (<digits>)<sdd>*
                               + "([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])");

        return PHONE.matcher(target).matches();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), SignUpChooser.class));
            overridePendingTransition(0,0);

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

        userEmail = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        final String personName = editPersonName.getText().toString().trim();
        final String  gender = "";
        final String WebsiteUrl = editwebsiteUrl.getText().toString().trim();
        final String phoneNumber = editPhoneNumber.getText().toString().trim();
        final int countryID = countryIds.get(spinnerDialog.getSelectedItemPosition());
        final int cityID = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

        progressDialog.setMessage("Registering user...");
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
                                singIn();
                                Intent intent=new Intent(getApplicationContext(), ChooseInterest.class);
                                intent.putExtra("email",userEmail);
                                startActivity(intent);

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
                                                obj.getString("profile_pic_id"),
                                                obj.getString("profile_pic_url"),
                                                obj.getString("profile_pic_timeStamp")
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
                params.put("userEmail", userEmail);
                params.put("password", password);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }

}