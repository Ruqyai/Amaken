package com.amakenapp.website.amakenapp.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.amakenapp.website.amakenapp.R;

/**
 * Created by USER on 4/20/2017.
 */

public class HelpOverview01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_overview01);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar011);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            overridePendingTransition(0,0);

            return true;
        }
        return true;
    }
}
