package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.BusinessProfilePlaceOrEventAdapter;
import com.example.afaf.amakenapp.helper.BusinessProfilePlaceOrEventListItem;
import com.example.afaf.amakenapp.helper.ProfileBookmarkAdapter;
import com.example.afaf.amakenapp.helper.ProfileBookmarkListItem;

import java.util.ArrayList;
import java.util.List;

public class ProfileBookmarks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProfileBookmarkListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bookmarks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilebookmarks_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBookmarks);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();


        for (int i = 0; i <= 10; i++) {
            ProfileBookmarkListItem listItem = new ProfileBookmarkListItem(
                    R.drawable.target,
                    "Place Name " + i ,
                    "Place Category",
                    R.drawable.bookmark

            );

            listItems.add(listItem);

        }


        adapter = new ProfileBookmarkAdapter(listItems, this);

        recyclerView.setAdapter(adapter);

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
