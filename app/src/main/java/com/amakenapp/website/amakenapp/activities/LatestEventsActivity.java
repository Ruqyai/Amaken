package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.EventsAdapter;
import com.amakenapp.website.amakenapp.helper.EventsListItem;
import com.amakenapp.website.amakenapp.helper.ExpandReviewDetailsListItem;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ReviewsCustomAdapter;
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


/**
 * Created by Muha on 3/8/2017.
 */

public class LatestEventsActivity extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<EventsListItem> listItems;
    Context context;

    SharedPrefManager sharedPrefManager;
    private static int userId, userType;
    private LinearLayout loading_event, no_events;
    private TextView addevent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.activity_events, container, false);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity().getApplicationContext());
        userId = sharedPrefManager.getUserId();
        userType = sharedPrefManager.getUserType();

        loading_event = (LinearLayout) myView.findViewById(R.id.linlaHeaderProgress_event);
        loading_event.setVisibility(View.VISIBLE);

        no_events = (LinearLayout) myView.findViewById(R.id.no_events_event);
        addevent =(TextView) myView.findViewById(R.id.add_event_events);
        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddEvent.class));
            }
        });
        recyclerView = (RecyclerView) myView.findViewById(R.id.events_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        getAllLatestevents(userId);


        return myView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Latest Events");
    }



    public void getAllLatestevents(int userId) {
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_ALL_EVENTS + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("event");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject eventDetails = arr.getJSONObject(i);


                                    EventsListItem listItem = new EventsListItem();
                                    listItem.setEventId(eventDetails.getInt("event_id"));
                                    listItem.setEventavaliabilty(eventDetails.getString("avaliable_message"));
                                    listItem.setEventBusinessName(eventDetails.getString("owner_name"));
                                    listItem.setEventBusinessProfileImage(eventDetails.getString("owner_profile_pic"));
                                    listItem.setEventPicture(eventDetails.getString("event_pic"));
                                    listItem.setEventName(eventDetails.getString("event_name"));
                                    listItem.setEventCategory1("("+eventDetails.getString("event_category")+")");
                                    listItem.setEventDescriptionMultiLineText(eventDetails.getString("event_description"));

                                    Double rate = eventDetails.getDouble("rate_avrg");
                                    String rate2 = Double.toString(rate);
                                    Float rate3 = Float.parseFloat(rate2);
                                    listItem.setRatingEvent(rate3);
                                    listItem.setEventRatingStat(rate2);

                                    listItems.add(listItem);
                                }
                                adapter = new EventsAdapter (listItems, getActivity());
                                recyclerView.setAdapter(adapter);
                                loading_event.setVisibility(View.GONE);


                            } else {
                                loading_event.setVisibility(View.GONE);
                                no_events.setVisibility(View.VISIBLE);
                                if(userType==Constants.CODE_NORMAL_USER)
                                    addevent.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getActivity(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        }) ;
        MySingleton.getInstance(getActivity()).addToRequestQueue(send);
    }



}
