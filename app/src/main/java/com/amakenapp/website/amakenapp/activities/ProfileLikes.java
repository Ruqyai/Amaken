package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkListItem;
import com.amakenapp.website.amakenapp.helper.ProfileLikeAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileLikesListItem;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileLikes extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProfileLikesListItem> listItems;

    private RelativeLayout loading;
    private LinearLayout  no_likes;
    private TextView addlike;

    SharedPrefManager sharedPrefManager;
    private static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_likes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilelikes_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());
        userId = sharedPrefManager.getUserId();

        loading = (RelativeLayout) findViewById(R.id.linlaHeaderProgress_likes);
        loading.setVisibility(View.VISIBLE);

        no_likes = (LinearLayout) findViewById(R.id.no_likes);
        addlike =(TextView) findViewById(R.id.add_like);
        addlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileLikes.this, NavDrw.class));
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewLikes);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();







        getAllLikes(userId);

    }


    //// TODO: 3/9/2017  get previous fragment (business profile activity) activity not NavDrw
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return true;
    }


    public void getAllLikes(int userId) {
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_USER_LIKES + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("likes");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject likeDetails = arr.getJSONObject(i);

                                    ProfileLikesListItem listItem= new ProfileLikesListItem();
                                    listItem.setLike_id(likeDetails.getInt("id"));
                                    String likeType = likeDetails.getString("like_type");
                                    listItem.setLike_type(likeType);

                                    if (likeType.equalsIgnoreCase("Place"))
                                    {
                                        listItem.setLike_timestamp("Place liked on "+likeDetails.getString("time_stamp"));
                                        listItem.setPlaceorevent_id(likeDetails.getInt("place_id"));
                                        listItem.setPlaceOrEventPic(likeDetails.getString("place_photo"));
                                        listItem.setPlaceOrEventName(likeDetails.getString("place_name"));
                                        listItem.setPlaceOrEventCategory(likeDetails.getString("place_category"));
                                    }
                                    else if (likeType.equalsIgnoreCase("Event"))
                                    {
                                        listItem.setLike_timestamp("Event liked on "+likeDetails.getString("time_stamp"));
                                        listItem.setPlaceorevent_id(likeDetails.getInt("event_id"));
                                        listItem.setPlaceOrEventPic(likeDetails.getString("event_photo"));
                                        listItem.setPlaceOrEventName(likeDetails.getString("event_name"));
                                        listItem.setPlaceOrEventCategory(likeDetails.getString("event_category"));
                                    }
                                    else if (likeType.equalsIgnoreCase("Review"))
                                    {
                                        listItem.setLike_timestamp("Review liked on "+likeDetails.getString("time_stamp"));
                                        String reviewType = likeDetails.getString("review_type");
                                        listItem.setReviewType(reviewType);
                                        listItem.setReviewUserPic(likeDetails.getString("user_photo"));
                                        listItem.setReviewUserName(likeDetails.getString("user_name"));
                                        listItem.setReviewText(likeDetails.getString("review_text"));

                                        Double rate = likeDetails.getDouble("rate_value");
                                        String rate2 = Double.toString(rate);
                                        Float rate3 = Float.parseFloat(rate2);
                                        listItem.setReviewRating(rate3);

                                        if (reviewType.equalsIgnoreCase("Place"))
                                        {
                                            listItem.setEventOrPlaceId(likeDetails.getInt("place_id"));
                                        }
                                        else if(reviewType.equalsIgnoreCase("Event"))
                                        {
                                            listItem.setEventOrPlaceId(likeDetails.getInt("event_id"));
                                        }

                                    }


                                    listItems.add(listItem);
                                }
                                adapter = new ProfileLikeAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);

                                loading.setVisibility(View.GONE);
                            } else {
                                loading.setVisibility(View.GONE);
                                no_likes.setVisibility(View.VISIBLE);
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
