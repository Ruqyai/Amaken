package com.amakenapp.website.amakenapp.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UserDetailsActivity extends AppCompatActivity {
    private TextView name,name2,type,wep,phone,email,country,city,gender;
    private CircleImageView ProfilePic;
    private static int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        userId = getIntent().getExtras().getInt("USER_ID");
        // Toast.makeText(this, " "+userId+" "+userId, Toast.LENGTH_SHORT).show();
        name=(TextView)findViewById(R.id.user_details_name);
        name2=(TextView)findViewById(R.id.name);
        type=(TextView)findViewById(R.id.user_type_d);
        wep=(TextView)findViewById(R.id.textWeb_d);
        phone=(TextView)findViewById(R.id.textPhone_d);
        email=(TextView)findViewById(R.id.textEmail_d);
        country=(TextView)findViewById(R.id.textCountryDetails_d);
        city=(TextView)findViewById(R.id.textCityDetails_d);
        gender=(TextView)findViewById(R.id.user_gender_d);
        ProfilePic=(CircleImageView)findViewById(R.id.user_pic_d);

        getUserDetails(userId);

    }



    public void getUserDetails(int usId) {
        final int userID = usId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_USER_BY_ID + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("user");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject userDetails = arr.getJSONObject(i);
                                    name.setText(userDetails.getString("user_name"));
                                    name2.setText(userDetails.getString("user_name"));
                                    if(userDetails.getInt("user_type")==1244)
                                    {type.setText("User");
                                        email.setText(" hidden");
                                        wep.setText(" hidden");
                                        phone.setText(" hidden");
                                        gender.setText(userDetails.getString("gender"));
                                    }

                                    else{type.setText("Business Owner");
                                        email.setText(userDetails.getString("user_email"));
                                        wep.setText(userDetails.getString("web_url"));
                                        phone.setText(userDetails.getString("phone_number"));
                                        gender.setText("no information");}

                                    country.setText(userDetails.getString("country_name"));
                                    city.setText(userDetails.getString("city_name"));
                                    String url_pic=userDetails.getString("profile_pic_url");
                                    Glide.with(UserDetailsActivity.this).load(url_pic)
                                            .thumbnail(0.5f)
                                            .crossFade()
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(ProfilePic);

                                    // Picasso.with(getApplicationContext()).load(url_pic).into(ProfilePic);


                                }

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) ;
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);
    }

}
