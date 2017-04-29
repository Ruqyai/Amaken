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
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ProfileLikeAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileLikesListItem;
import com.amakenapp.website.amakenapp.helper.ProfileReviewsAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileReviewsListItem;
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

public class ProfileReviews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProfileReviewsListItem> listItems;

    private LinearLayout loading, no_reviwes;
    private TextView addrevies;

    SharedPrefManager sharedPrefManager;
    private static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_reviews);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilereviews_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());
        userId = sharedPrefManager.getUserId();

        loading = (LinearLayout) findViewById(R.id.linlaHeaderProgress_reviews);
        loading.setVisibility(View.VISIBLE);

        no_reviwes = (LinearLayout) findViewById(R.id.no_reviews_yet);
        addrevies =(TextView) findViewById(R.id.add_review);
        addrevies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileReviews.this, NavDrw.class));
            }
        });


        ///////////////

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReviews);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();



        getAllLikes(userId);

        ///////////
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
                Constants.URL_GET_USER_REVIEWS + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("userReviews");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject reviewDetails = arr.getJSONObject(i);

                                    ProfileReviewsListItem listItem= new ProfileReviewsListItem();

                                    listItem.setReviewId(reviewDetails.getInt("id"));
                                    listItem.setReviewTimestamp(reviewDetails.getString("review_timeStamp"));
                                    listItem.setReviewText(reviewDetails.getString("review_text"));

                                    int likesNum = reviewDetails.getInt("review_likes_number");
                                    String likesNum1 = Integer.toString(likesNum);
                                    listItem.setReviewLikesNumber(likesNum1);


                                    Double rate = reviewDetails.getDouble("rate_value");
                                    String rate2 = Double.toString(rate);
                                    Float rate3 = Float.parseFloat(rate2);
                                    listItem.setReviewRatingValue(rate3);


                                    String reviewType = reviewDetails.getString("review_type");
                                    listItem.setReviewType(reviewType);


                                    if (reviewType.equalsIgnoreCase("Place"))
                                    {
                                        listItem.setEventOrPlaceId(reviewDetails.getInt("place_id"));
                                        listItem.setPlaceoreventPic(reviewDetails.getString("place_photo"));
                                        listItem.setPlaceoreventName(reviewDetails.getString("place_name"));
                                        listItem.setPlaceoreventCategory(reviewDetails.getString("place_category"));
                                    }
                                    else if (reviewType.equalsIgnoreCase("Event"))
                                    {
                                        listItem.setEventOrPlaceId(reviewDetails.getInt("event_id"));
                                        listItem.setPlaceoreventName(reviewDetails.getString("event_name"));
                                        listItem.setPlaceoreventCategory(reviewDetails.getString("event_category"));
                                        listItem.setPlaceoreventPic(reviewDetails.getString("event_photo"));
                                    }


                                    listItems.add(listItem);
                                }
                                adapter = new ProfileReviewsAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);

                                loading.setVisibility(View.GONE);
                            } else {
                                loading.setVisibility(View.GONE);
                                no_reviwes.setVisibility(View.VISIBLE);
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
