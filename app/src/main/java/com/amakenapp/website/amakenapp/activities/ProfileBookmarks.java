package com.amakenapp.website.amakenapp.activities;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;

import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.HomeAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkListItem;
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
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBookmarks);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        listItems = new ArrayList<>();
        //getBookMarkDetails(userId);//
    }

   /* // TODO: 4/17/2017 I put in comment to avoid conflict after one of our team tell me she worked also in this activity in the same time
   /*    //// TODO: 4/17/2017  there are to comment related this (constants + profileBookmarkListItem)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return true;
    }
    public void getBookMarkDetails(int userId){
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_USER_BOOKMARK + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("bookmarks");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject userBookMark = arr.getJSONObject(i);
                                    ProfileBookmarkListItem listItem =new ProfileBookmarkListItem();
                                    String type=userBookMark.getString("bookmark_type");
                                    if(type.equals("Event"))
                                    {
                                        int id= userBookMark.getInt("id");
                                        int event_id= userBookMark.getInt("event_id");
                                    listItem.setPlaceOrEventPic(userBookMark.getString("event_photo"));
                                    listItem.setPlaceOrEventName(userBookMark.getString("event_name"));
                                    listItem.setPlaceOrEventCategory(userBookMark.getString("event_category"));
                                    listItem.setBookmarkLogo(R.drawable.bookmark);
                                    listItem.setBookmarkTimeStamp(userBookMark.getString("bookmark_timestamp"));
                                    }

                                    else
                                    {int id= userBookMark.getInt("id");
                                        int place_id= userBookMark.getInt("place_id");
                                        listItem.setPlaceOrEventPic(userBookMark.getString("place_photo"));
                                        listItem.setPlaceOrEventName(userBookMark.getString("place_name"));
                                        listItem.setPlaceOrEventCategory(userBookMark.getString("place_category"));
                                        listItem.setBookmarkLogo(R.drawable.bookmark);
                                        listItem.setBookmarkTimeStamp(userBookMark.getString("bookmark_timestamp"));

                                    }

                                    listItems.add(listItem);
                                }
                                adapter = new ProfileBookmarkAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);

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
*/
    }


