package com.amakenapp.website.amakenapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.ProfileLikeAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileLikesListItem;

import java.util.ArrayList;
import java.util.List;

public class ProfileLikes extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProfileLikesListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_likes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilelikes_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewLikes);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();


        for (int i = 0; i <= 10; i++) {
            ProfileLikesListItem listItem = new ProfileLikesListItem(
                    R.drawable.target,
                    "Place or Event Name " + i ,
                    "Place or Event Category ",
                    R.drawable.redheart,
                    "This **** Liked on TimeStamp "

            );

            listItems.add(listItem);

        }


        adapter = new ProfileLikeAdapter(listItems, this);

        recyclerView.setAdapter(adapter);

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
}
