package com.amakenapp.website.amakenapp.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.EventsAdapter;
import com.amakenapp.website.amakenapp.helper.EventsListItem;
import com.amakenapp.website.amakenapp.helper.HomeAdapter;
import com.amakenapp.website.amakenapp.helper.HomeListItem;
import com.amakenapp.website.amakenapp.helper.MySingleton;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeActivity extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<HomeListItem> listItems;
    Context context;


    SharedPrefManager sharedPrefManager;
    private static int userId;

    public HomeActivity() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home Page");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.activity_home, container, false);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity().getApplicationContext());
        userId = sharedPrefManager.getUserId();

        recyclerView = (RecyclerView) myView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        getAllPlaces(userId);

        return myView;

    }


    public void getAllPlaces(int userId) {
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_ALL_PLACES + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("place");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject placeDetails = arr.getJSONObject(i);
                                    HomeListItem listItem = new HomeListItem();
                                    listItem.setPlaceId(placeDetails.getInt("place_id"));
                                    listItem.setPlaceBusinessName(placeDetails.getString("owner_name"));
                                    listItem.setPlaceBusinessProfileImage(placeDetails.getString("owner_profile_pic"));
                                    listItem.setPlacePicture(placeDetails.getString("place_pic"));
                                    listItem.setPlaceName(placeDetails.getString("place_name"));
                                    listItem.setPlaceCategory(placeDetails.getString("place_category"));
                                    listItem.setPlaceDescriptionMultiLineText(placeDetails.getString("place_description"));


                                    Double rate = placeDetails.getDouble("rate_avrg");
                                    String rate2 = Double.toString(rate);
                                    Float rate3 = Float.parseFloat(rate2);
                                    listItem.setRatingplace(rate3);
                                    listItem.setPlaceRatingStat(rate2);

                                    listItems.add(listItem);
                                }
                                adapter = new HomeAdapter(listItems, getActivity());
                                recyclerView.setAdapter(adapter);

                            } else {
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) ;
        MySingleton.getInstance(getActivity()).addToRequestQueue(send);
    }



}
