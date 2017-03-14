package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.ProfileReviewsAdapter;
import com.example.afaf.amakenapp.helper.ProfileReviewsListItem;

import java.util.ArrayList;
import java.util.List;

public class ProfileReviews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProfileReviewsListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_reviews);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilereviews_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ///////////////

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReviews);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();


        for (int i = 0; i <= 10; i++) {
            ProfileReviewsListItem listItem = new ProfileReviewsListItem(
                    "Your Review",
                    "on TimeStamp",
                    R.drawable.profile,
                    "Review Text, Review Text, Review Text, Review Text, Review Text, Review Text, Review Text, Review Text, ",
                    R.attr.ratingBarStyleSmall,
                    R.drawable.ic_like_icon,
                    "13 "

            );

            listItems.add(listItem);

        }


        adapter = new ProfileReviewsAdapter(listItems, this);

        recyclerView.setAdapter(adapter);

        ///////////
    }


    //// TODO: 3/9/2017  get previous fragment (business profile activity) activity not NavDrw
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, NavDrw.class));

            return true;
        }
        return true;
    }

}
