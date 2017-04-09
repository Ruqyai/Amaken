package com.amakenapp.website.amakenapp.activities;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.amakenapp.website.amakenapp.helper.*;

//// TODO: 3/5/2017  asking about this import
import com.amakenapp.website.amakenapp.R;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseInterest extends AppCompatActivity implements View.OnClickListener{

    private GridView gridView;
    private View btnGo;


    private ArrayList<String> selectedStrings;
    private ArrayList<Integer> selectedIds = new ArrayList<>();
    Map<Integer,String>interest;


    private static final String[] numbers = new String[]{
            "Cafes",
            "Education",
            "Entertainment",
            "Hair stylists",
            "Hospitals",
            "Hotels",
            "Malls",
            "Parks",
            "Restaurants",
            "Spa",
            "Sport",
            "tourism"};

    private static final int[] numb = new int[]{
            R.drawable.cafes,
            R.drawable.education,
            R.drawable.entertainment,
            R.drawable.hair_stylists,
            R.drawable.hospitals,
            R.drawable.hotels,
            R.drawable.malls,
            R.drawable.parks,
            R.drawable.restaurants,
            R.drawable.spa,
            R.drawable.sport,
            R.drawable.tourism

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_interest);

        gridView = (GridView) findViewById(R.id.grid);
        btnGo = findViewById(R.id.button);

        selectedStrings = new ArrayList<>();
        interest=new HashMap<>();

        interest.put(1,"Hospitals");
        interest.put(2,"Hair stylists");
        interest.put(3,"Cafes");
        interest.put(4,"Hospitals");
        interest.put(5,"tourism");
        interest.put(6,"Education");
        interest.put(7,"Entertainment");
        interest.put(8,"Malls");
        interest.put(9,"Spa");
        interest.put(10,"Sport");
        interest.put(11,"Hotels");
        interest.put(12,"Parks");


        final GridViewAdapter adapter = new GridViewAdapter(numbers,numb, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int selectedIndex = adapter.selectedPositions.indexOf(position);

                if (selectedIndex > -1) {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((GridItemView) v).display(false);
                    selectedIds.remove(selectedIds.indexOf(position+1));
                } else {
                    adapter.selectedPositions.add(position);
                    ((GridItemView) v).display(true);
                    selectedIds.add(position+1);

                }

            }
        });

        //set listener for Button event
        btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGo) {
            storingInterests(selectedIds);
        }
    }

    public void storingInterests(final List<Integer> interests) {

        //getting user ID , //// TODO: 3/7/2017 should be from previous ctivity
        final int userID = 10;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_STOREINTERESTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                finish();
                                startActivity(new Intent(getApplicationContext(), NavDrw.class));

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
                params.put("user_id", userID+"");

                for(int i = 0; i<interests.size(); i++) {
                    params.put("interest_id["+i+"]", String.valueOf(interests.get(i)));
                }
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }
}