package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkListItem;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.res.ColorStateList.valueOf;

public class ProfileCategories extends AppCompatActivity {

    private CardView
            cafeCard ,
            educationCard ,
            entertainmentCard ,
            hair_stylistsCard ,
            hospitalsCard ,
            hotelsCard ,
            mallsCard ,
            parksCard ,
            restaurantsCard,
            spaCard,
            sportCard,
            tourismCard;
    private Button  buttonEdit,buttonSave;


    SharedPrefManager sharedPrefManager;
    private static int userId;
    private CoordinatorLayout coordinatorLayoutLayout;
    private LinearLayout
            cafeCard1 ,
            educationCard1 ,
            entertainmentCard1 ,
            hair_stylistsCard1 ,
            hospitalsCard1 ,
            hotelsCard1 ,
            mallsCard1 ,
            parksCard1 ,
            restaurantsCard1,
            spaCard1,
            sportCard1,
            tourismCard1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_categories);

        coordinatorLayoutLayout= (CoordinatorLayout) findViewById(R.id.activity_profile_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilecategories_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();




        /*cafeCard = (CardView) findViewById(R.id.Card_cafe);
        educationCard = (CardView) findViewById(R.id.educationCard);
        entertainmentCard = (CardView) findViewById(R.id.entertainmentCard);
        hair_stylistsCard = (CardView) findViewById(R.id.hair_stylistsCard);
        hospitalsCard = (CardView) findViewById(R.id.hospitalsCard);
        hotelsCard = (CardView) findViewById(R.id.hotelsCard);
        mallsCard = (CardView) findViewById(R.id.mallsCard);
        parksCard = (CardView)findViewById(R.id.parksCard);
        restaurantsCard = (CardView) findViewById(R.id.restaurantsCard);
        spaCard = (CardView) findViewById(R.id.spaCard);
        sportCard = (CardView) findViewById(R.id.sportCard);
        tourismCard = (CardView)findViewById(R.id.tourismCard);*/

        cafeCard1 = (LinearLayout) findViewById(R.id.Card_cafe1);
        educationCard1 = (LinearLayout) findViewById(R.id.educationCard1);
        entertainmentCard1 = (LinearLayout) findViewById(R.id.entertainmentCard1);
        hair_stylistsCard1 = (LinearLayout) findViewById(R.id.hair_stylistsCard1);
        hospitalsCard1 = (LinearLayout) findViewById(R.id.hospitalsCard1);
        hotelsCard1 = (LinearLayout) findViewById(R.id.hotelsCard1);
        mallsCard1 = (LinearLayout) findViewById(R.id.mallsCard1);
        parksCard1 = (LinearLayout)findViewById(R.id.parksCard1);
        restaurantsCard1 = (LinearLayout) findViewById(R.id.restaurantsCard1);
        spaCard1 = (LinearLayout) findViewById(R.id.spaCard1);
        sportCard1 = (LinearLayout) findViewById(R.id.sportCard1);
        tourismCard1 = (LinearLayout)findViewById(R.id.tourismCard1);





        buttonSave=(Button) findViewById(R.id.ButtonSave);
        buttonSave.setVisibility(View.INVISIBLE);
        buttonEdit=(Button) findViewById(R.id.ButtonEdit);
        onClickEdit();
        onClickSave();
        getAllUserCaegories(userId);


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



    public void onClickEdit(){

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSave.setVisibility(View.VISIBLE);
                onClickImage1();

            }
        });
    }

    public void onClickSave(){

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSave.setVisibility(View.INVISIBLE);

            }
        });
    }

    ///////////////////

 /*   public void onClickImage(){

        cafeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(3, userId, cafeCard);

            }
        });
        educationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(5, userId, educationCard);

            }
        });
        entertainmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(8, userId, entertainmentCard);

            }
        });
        hair_stylistsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(2, userId, hair_stylistsCard);
            }
        });
        hospitalsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storeUserInterest(1, userId, hospitalsCard);
            }
        });

        hotelsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(11, userId, hotelsCard);
            }
        });
        mallsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(7, userId, mallsCard);

            }
        });
        parksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(12, userId, parksCard);
            }
        });
        restaurantsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(6, userId, restaurantsCard);

            }
        });
        spaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(9, userId, spaCard);

            }
        });
        sportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(10, userId, sportCard);

            }
        });
        tourismCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest(4, userId, tourismCard);

            }
        });

    }
*/
    public void onClickImage1(){

        cafeCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(3, userId, cafeCard1);

            }
        });
        educationCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(5, userId, educationCard1);

            }
        });
        entertainmentCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(8, userId, entertainmentCard1);

            }
        });
        hair_stylistsCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(2, userId, hair_stylistsCard1);
            }
        });
        hospitalsCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storeUserInterest1(1, userId, hospitalsCard1);
            }
        });

        hotelsCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(11, userId, hotelsCard1);
            }
        });
        mallsCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(7, userId, mallsCard1);

            }
        });
        parksCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(12, userId, parksCard1);
            }
        });
        restaurantsCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(6, userId, restaurantsCard1);

            }
        });
        spaCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(9, userId, spaCard1);

            }
        });
        sportCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(10, userId, sportCard1);

            }
        });
        tourismCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserInterest1(4, userId, tourismCard1);

            }
        });



    }


    public void getAllUserCaegories(int userId) {
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_USER_INTEREST_CATEGORIES + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("categories");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject userCategories = arr.getJSONObject(i);

                                    int interestcatoryid = userCategories.getInt("interest_id");
                                    switch (interestcatoryid) {
                                        case 1:
                                            hospitalsCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 2:
                                            hair_stylistsCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 3:
                                            cafeCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 4:
                                            tourismCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 5:
                                            educationCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 6:
                                            restaurantsCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 7:
                                            mallsCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 8:
                                            entertainmentCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 9:
                                            spaCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 10:
                                            sportCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));

                                            break;
                                        case 11:
                                           hotelsCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        case 12:
                                           parksCard1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                            break;
                                        default:
                                            break;
                                    }
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





    public void storeUserInterest1(int interestId, int userId, LinearLayout selectedCard) {
        final int interestID = interestId;
        final int userID = userId;
        final LinearLayout selectedcard = selectedCard;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_GET_ASSING_USER_INTEREST_CATEGORY ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                selectedcard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));
                                Snackbar snackbar = Snackbar.make(coordinatorLayoutLayout, obj.getString("message"), Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            } else {
                                selectedcard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.barlight));
                                Snackbar snackbar = Snackbar.make(coordinatorLayoutLayout, obj.getString("message"), Snackbar.LENGTH_SHORT);
                                snackbar.show();
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
                params.put("interest_id", interestID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }







}
