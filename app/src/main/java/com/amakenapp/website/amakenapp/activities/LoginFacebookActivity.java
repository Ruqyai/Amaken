package com.amakenapp.website.amakenapp.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amakenapp.website.amakenapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginFacebookActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    Uri picture;
    private String UName;
    private String UEmail;
    private Uri UImage;
    private String UId;
    private String UPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_facebook);


        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                graphRequest(loginResult.getAccessToken());




            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Some errors accrue", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void graphRequest(AccessToken token) {

        GraphRequest request = GraphRequest.newMeRequest(token,
                new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {

                            Profile profile = Profile.getCurrentProfile();
                            UName = profile.getFirstName();
                            profile.describeContents();
                            UPassword = profile.getId();
                            UEmail = object.get("email").toString();

                            Toast.makeText(getApplicationContext(), "welcome  " + profile.getFirstName(), Toast.LENGTH_LONG).show();
                            singIn();

                            try {

                            } catch (Exception e) {
                                e.printStackTrace();

                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle b = new Bundle();
        b.putString("fields", "id,email,first_name,last_name,picture.type(large)");
        request.setParameters(b);
        request.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /////////////////////
    Uri defaultUri;

    public Uri getUImage() {
        if (picture != null) {
            return picture;
        } else {
            defaultUri = Uri.parse("https://img.clipartfest.com/76db68dca190430f68ae64dece275ad8_profile-clip-art-profile-picture-clipart_300-279.png");
            return defaultUri;
        }
    }

    public Uri getPicture() {
        return picture;
    }//// TODO: 4/30/2017

    ///////////////////////////////////////////////////////////////////////////////////////////
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
                                                getUImage().toString(),
                                                TextUtils.isEmpty(obj.getString("profile_pic_timeStamp")) ? "" : obj.getString("profile_pic_timeStamp")
                                        );
                                startActivity(new Intent(getApplicationContext(), NavDrw.class));
                            } else {

                                Intent intent = new Intent(getApplicationContext(), ComplateActivity.class);
                                intent.putExtra("email", UEmail);
                                intent.putExtra("password", UPassword);
                                intent.putExtra("name", UName);
                                intent.putExtra("pic", getUImage().toString());
                                startActivity(intent);
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
                params.put("userEmail", UEmail);
                params.put("password", UPassword);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }
}