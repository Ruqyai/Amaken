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

import com.amakenapp.website.amakenapp.helper.BusinessProfilePlaceOrEventAdapter;
import com.amakenapp.website.amakenapp.helper.BusinessProfilePlaceOrEventListItem;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkListItem;
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

public class ProfileBookmarks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProfileBookmarkListItem> listItems;
    private RelativeLayout loading;

    private LinearLayout  no_bookmarks;
    private TextView addbookmark;

    SharedPrefManager sharedPrefManager;
    private static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bookmarks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilebookmarks_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());
        userId = sharedPrefManager.getUserId();

        loading = (RelativeLayout) findViewById(R.id.linlaHeaderProgress_bookmarks);
        loading.setVisibility(View.VISIBLE);

        no_bookmarks = (LinearLayout) findViewById(R.id.no_bookmarks);
        addbookmark =(TextView) findViewById(R.id.add_bookmarks);
        addbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileBookmarks.this, NavDrw.class));
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBookmarks);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();


        getAllbookmarks(userId);



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

    public void getAllbookmarks(int userId) {
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_USER_BOOKMARKS + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("bookmarks");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject bookmarkDetails = arr.getJSONObject(i);

                                    ProfileBookmarkListItem listItem= new ProfileBookmarkListItem();
                                    listItem.setBoomarkId(bookmarkDetails.getInt("id"));


                                    String bookmarkType = bookmarkDetails.getString("bookmark_type");
                                    if(bookmarkType.equals("Place")) {
                                        listItem.setBookmarkType(bookmarkType);
                                        listItem.setBookmarkTimeStamp(bookmarkType+" Bookmarked"+" on  "+bookmarkDetails.getString("bookmark_timestamp")+"");
                                        listItem.setPlaceOrEventId(bookmarkDetails.getInt("place_id"));
                                        listItem.setPlaceOrEventPic(bookmarkDetails.getString("place_photo"));
                                        listItem.setPlaceOrEventName(bookmarkDetails.getString("place_name"));
                                        listItem.setPlaceOrEventCategory(bookmarkDetails.getString("place_category"));
                                    }
                                    else if(bookmarkType.equals("Event"))
                                    {
                                        listItem.setBookmarkType(bookmarkType);
                                        listItem.setBookmarkTimeStamp(bookmarkType+" Bookmarked"+" on  "+bookmarkDetails.getString("bookmark_timestamp")+"");
                                        listItem.setPlaceOrEventId(bookmarkDetails.getInt("event_id"));
                                        listItem.setPlaceOrEventPic(bookmarkDetails.getString("event_photo"));
                                        listItem.setPlaceOrEventName(bookmarkDetails.getString("event_name"));
                                        listItem.setPlaceOrEventCategory(bookmarkDetails.getString("event_category"));
                                    }

                                    listItems.add(listItem);
                                }
                                adapter = new ProfileBookmarkAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                                loading.setVisibility(View.GONE);
                            } else {
                                loading.setVisibility(View.GONE);
                                no_bookmarks.setVisibility(View.VISIBLE);
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
