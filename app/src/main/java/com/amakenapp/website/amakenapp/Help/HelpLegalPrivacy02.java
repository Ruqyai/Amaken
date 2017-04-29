package com.amakenapp.website.amakenapp.Help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.amakenapp.website.amakenapp.R;

public class HelpLegalPrivacy02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_legal_privacy02);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar023);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), HelpExpandableListItem.class));

            return true;
        }
        return true;
    }
}
