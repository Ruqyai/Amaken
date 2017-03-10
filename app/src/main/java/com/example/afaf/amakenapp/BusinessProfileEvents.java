package com.example.afaf.amakenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.afaf.amakenapp.activities.NavDrw;

public class BusinessProfileEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.businessProfileEvents_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
