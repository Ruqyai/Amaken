package com.example.afaf.amakenapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener{
    /*
    * =====param send in php file
    * 'userEmail','password'
    * ======param response in php file
    * $response['error'] = false;
        $response['id'] = $business_user['id'];
        $response['business_email'] = $business_user['business_email'];
        $response['business_password'] = $business_user['business_password'];
        $response['business_name'] = $business_user['business_name'];
        $response['business_web_url'] = $business_user['business_web_url'];
        $response['business_phone_number'] = $business_user['phone_number'];
        $response['business_country_id'] = $business_user['business_country_id'];
        $response['country_name'] = $db->getCountryByID($business_user['business_country_id']);
        $response['business_city_id'] = $business_user['business_city_id'];
        $response['city_name'] = $db->getCityByID($business_user['business_city_id']);
        $response['profile_pic_id'] = $business_user['profile_pic_id'];
        $response['profile_pic_url'] = $db->getPhotoURLByID($business_user['profile_pic_id']);
    * */
    private EditText editEmail, editPassword;
    private ImageButton login_Back;
    private Button login_SignIn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login_Back = (ImageButton) findViewById(R.id.login_Back);
        login_SignIn = (Button) findViewById(R.id.login_SingIn);

        editEmail = (EditText) findViewById(R.id.login_Email);
        editPassword = (EditText) findViewById(R.id.login_Password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        login_Back.setOnClickListener(this);
        login_SignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == login_Back) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        if (v == login_SignIn) {
            singIn();
            finish();
        }

    }

    // add by Ruqi

    //==================================
    public void singIn(){
        final String userEmail = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();

//        progressDialog.show();
        StringRequest send = new  StringRequest(Request.Method.POST,
                                                Constants.URL_LOGIN,
                                                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressDialog.dismiss();

                //if the request is successfull
                // you will get the whole server response as string in this response object
                //now convert the response to json
                try {
                    JSONObject obj = new JSONObject(response);
                    if (!obj.getBoolean("error")) {
                       SharedPrefManager.getInstance(getApplicationContext())
                               .userLogin(
                                       obj.getInt("id"),
                                       obj.getString("user_email"),
                                       obj.getString("user_password"),
                                       obj.getString("user_name"),
                                       obj.getString("user_gender"),
                                       obj.getInt("country_id"),
                                       obj.getString("country_name"),
                                       obj.getInt("city_id"),
                                       obj.getString("city_name"),
                                       obj.getInt("profile_pic_id"),
                                       obj.getString("profile_pic_url")


                                );
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                          finish();
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // return super.getParams();

                Map<String, String> params = new HashMap<>();
                // put all the parameters for the request in map object with key value pairs

                params.put("userEmail", userEmail); //get the value of userEmail from edittExt
                params.put("password",password); //get it from edittext

                //finally return the parameters
                return params;
            }
        };

        //now we will add it to the request queue
        MySingleton.getInstance(this).addToRequestQueue(send);

    }
    //===============================================================



}



