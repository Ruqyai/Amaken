package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.R;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
//import com.juxshare.org.KotlinAddressEditText;
//import com.juxshare.org.JavaAddressEditText;

public class DiscoverActivity extends AppCompatActivity {

    private TextView disName, disDetails, disWeb, disPhone, disRate;
    private ImageView mImageView;
    Intent intent;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    final String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        disName = (TextView) findViewById(R.id.place_name);
        disDetails = (TextView) findViewById(R.id.place_Address);
        disWeb = (TextView) findViewById(R.id.textWeb);
        disPhone = (TextView) findViewById(R.id.textPhone);
        disRate = (TextView) findViewById(R.id.place_rating_stat);
        mImageView=(ImageView)findViewById(R.id.imageMap);
        ratingbar = (RatingBar) findViewById(R.id.place_rating);

       // ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        //    @Override
        //    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        //    }

       // });

    }

    @Override
    protected void onStart() {
        super.onStart();
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            intent = builder.build(DiscoverActivity.this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }


    }

    //Important override onActivity results to get user selected results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(this, data);
                String selectedPlace = String.format("" + place.getName());
                String selectedAdrees = String.format("" + place.getAddress());
                String selectedRate = String.format("" + place.getRating());
                String selectedWeb = String.format("" + place.getWebsiteUri());
                String selectedPhen = String.format("" + place.getPhoneNumber());
                disName.setText(selectedPlace);
                disDetails.setText(selectedAdrees);
                disRate.setText(selectedRate);
                disPhone.setText(selectedPhen);
                disWeb.setText(selectedWeb);
                ratingbar.setRating(place.getRating());

            }

        }

    }

}


