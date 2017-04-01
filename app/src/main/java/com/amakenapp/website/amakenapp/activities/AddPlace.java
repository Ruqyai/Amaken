package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;

public class AddPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addplace_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


           /* Imagebutton for dialog for the place photo */
        // ImageButton to show the dailog
        ImageButton PlaceCamDialog = (ImageButton) findViewById(R.id.photoimageButton);


        // dialog window for one clicking the button
        PlaceCamDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder placeBuilder = new AlertDialog.Builder(AddPlace.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_photo, null);
                final EditText mDescription = (EditText) mView.findViewById(R.id.description);
                Button fromGallery = (Button) mView.findViewById(R.id.buttonFromGall);
                Button TakePhoto = (Button) mView.findViewById(R.id.buttonTakephoto);
                Button ClearPhoto = (Button) mView.findViewById(R.id.buttonClearphoto);
                Button mDone = (Button) mView.findViewById(R.id.buttonDone);

                mDone.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(AddPlace.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                    }

                });

                placeBuilder.setView(mView);
                AlertDialog dialog = placeBuilder.create();
                dialog.show();

            }
        });
        /* Imagebutton for dialog for the location  */

        ImageButton locationCamDialog = (ImageButton) findViewById(R.id.locationimageButton);

        locationCamDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder locationBuilder = new AlertDialog.Builder(AddPlace.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_location, null);
                final EditText LocDescription = (EditText) mView.findViewById(R.id.description);
                Button StreetName = (Button) mView.findViewById(R.id.button1);
                Button coordination = (Button) mView.findViewById(R.id.button2);
                Button mDone = (Button) mView.findViewById(R.id.button4);

                mDone.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(AddPlace.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                    }

                });

                locationBuilder.setView(mView);
                AlertDialog dialog = locationBuilder.create();
                dialog.show();

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
