package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.amakenapp.website.amakenapp.R;

public class AddPlace extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addplace_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         /* Imagebutton for dialog for the place photo */
        // ImageButton to show the dialog
        ImageButton PlaceCamDialog = (ImageButton) findViewById(R.id.photoimageButton);


        // dialog window for one clicking the button

        // Capture button clicks
        PlaceCamDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                 DialogPlaceFragment dFragment = new DialogPlaceFragment();
                // Show DialogFragment
              dFragment.show(fm, "Dialog Fragment");
            }
        });

        /* Imagebutton for dialog for the location  */

        ImageButton locationCamDialog = (ImageButton) findViewById(R.id.locationimageButton);

        locationCamDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, NavDrw.class));

            return true;
        }
        return true;
    }
}

