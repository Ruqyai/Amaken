package com.example.afaf.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterViewFlipper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.ExpandReviewDetailsListItem;
import com.example.afaf.amakenapp.helper.ReviewsCustomAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ExpandDetailsMapsActivityEvent extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Context context;

    private ImageView imageViewGallery;
    private ImageView imageViewLike;
    private ImageView imageViewSave;
    private ImageView imageViewReveiw;
    private TextView eventPhotosNumber,
                    eventLikesNumber,
                    eventBookmarksNumber,
                    eventReplysNumber,
                    eventName,
                    eventCategory,
                    eventRatingStat,
                    eventDescription,
                    ownerName,
                    ownerWebUrl,
                    ownerPhone,
                    ownerEmail,
                    locationCountry,
                    locationCity,
                    locationAdress, eventStartDate, eventEndDate, eventStartTime, eventEndTime, eventDays;


    private RatingBar placeRating;
    private ImageView imageViewHomeBusinesEventImage;
    private ImageButton filpNextEvent, flipPreviousEvent;

    ////
    private List<ExpandReviewDetailsListItem> listItems;
    private AdapterViewFlipper reviewsFlipper;
    private ReviewsCustomAdapter reviewsCustomAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details_maps_event);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_event);
        mapFragment.getMapAsync(this);


        ///////find views by id for textViews
        eventPhotosNumber = (TextView) findViewById(R.id.textNumberGalleryImageEvent);
        eventLikesNumber = (TextView) findViewById(R.id.textNumberLikesEvent);
        eventBookmarksNumber = (TextView) findViewById(R.id.textNumberSaveEvent);
        eventReplysNumber = (TextView) findViewById(R.id.textNumberReviewEvent);
        eventName = (TextView) findViewById(R.id.event_name);
        eventCategory = (TextView) findViewById(R.id.event_category);
        eventRatingStat = (TextView) findViewById(R.id.event_rating_stat);
        eventDescription = (TextView) findViewById(R.id.event_description);
        ownerName = (TextView) findViewById(R.id.event_businessOwner_name);
        ownerWebUrl = (TextView) findViewById(R.id.textWebevent);
        ownerPhone = (TextView) findViewById(R.id.textPhoneevent);
        ownerEmail = (TextView) findViewById(R.id.textEmailevent);
        locationCountry = (TextView) findViewById(R.id.textCountryDetailsevent);
        locationCity = (TextView) findViewById(R.id.textCityDetailsevent);
        locationAdress = (TextView) findViewById(R.id.textAddressDetailsevent);
        eventStartDate = (TextView) findViewById(R.id.event_startDate);
        eventEndDate = (TextView) findViewById(R.id.event_endDate);
        eventStartTime = (TextView) findViewById(R.id.event_startTime);
        eventEndTime = (TextView) findViewById(R.id.event_endTime);
        eventDays = (TextView) findViewById(R.id.event_days);

        ///////



        imageViewHomeBusinesEventImage=(ImageView) findViewById(R.id.Expand_event_photos);
        Glide.with(getApplicationContext()).load(R.drawable.store).into(imageViewHomeBusinesEventImage);


        imageViewGallery=(ImageView)findViewById(R.id.imageButtonGalleryEvent);
        imageViewGallery.setImageResource(R.drawable.ic_gallary);
        imageViewGallery.setOnClickListener(this);

        imageViewSave=(ImageView)findViewById(R.id.imageButtonSaveeEvent);
        imageViewSave.setImageResource(R.drawable.ic_bookmark_border);
        imageViewSave.setOnClickListener(this);

        imageViewLike=(ImageView)findViewById(R.id.imageButtonLikeEvent);
        imageViewLike.setImageResource(R.drawable.ic_like_border);
        imageViewLike.setOnClickListener(this);

        imageViewReveiw=(ImageView)findViewById(R.id.imageButtonReviewEvent);
        imageViewReveiw.setImageResource(R.drawable.ic_reply);
        imageViewReveiw.setOnClickListener(this);


        flipPreviousEvent = (ImageButton) findViewById(R.id.flipp_previous_event);
        filpNextEvent = (ImageButton) findViewById(R.id.flipp_next_event);

        filpNextEvent.setOnClickListener(this);
        flipPreviousEvent.setOnClickListener(this);


        //animations for the next and previous buttons
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(500);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        flipPreviousEvent.startAnimation(mAnimation);
        filpNextEvent.startAnimation(mAnimation);




        reviewsFlipper = (AdapterViewFlipper) findViewById(R.id.reviews_simple_flipper_events);
        listItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ExpandReviewDetailsListItem listItem = new ExpandReviewDetailsListItem(
                    "User Name " + i + " ",
                    " on TimeStamp ",
                    R.drawable.ic_person,
                    "good place, good place, good place, good place, good place, good place",
                    R.attr.ratingBarStyleSmall,
                    R.drawable.ic_thump_up,
                    "22",
                    R.drawable.ic_report_flag
            );

            listItems.add(listItem);

        }

        reviewsCustomAdapter = new ReviewsCustomAdapter(listItems, this);
        reviewsFlipper.setAdapter(reviewsCustomAdapter);
        //reviewsFlipper.setFlipInterval(2000);
       // reviewsFlipper.setAutoStart(true);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        UiSettings uiSettings= googleMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
    }

    @Override
    public void onClick(View v) {

        if (v == imageViewGallery){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            //// TODO: 3/17/2017 get images from database
            Toast.makeText(getApplicationContext(), "This is gallery clicked", Toast.LENGTH_LONG).show();

        }

        if (v == imageViewLike){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            Glide.with(getApplicationContext()).load(R.drawable.ic_like_fill).into(imageViewLike);
            //// TODO: 3/17/2017 aslo store like on database
            Toast.makeText(getApplicationContext(), "Added to your likes", Toast.LENGTH_LONG).show();


        }
        if (v == imageViewSave){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            Glide.with(getApplicationContext()).load(R.drawable.ic_bookmark_fill).into(imageViewSave);
            //// TODO: 3/17/2017 aslo store bookmark on database
            Toast.makeText(getApplicationContext(), "Added to your bookmarks", Toast.LENGTH_LONG).show();


        }
        if (v == imageViewReveiw){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            //// TODO: 3/17/2017 aslo store review on database
            startActivity(new Intent(this, AddReview.class));

        }

        if (v == filpNextEvent){
            filpNextEvent.clearAnimation();
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.left_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.right_out);
            reviewsFlipper.showNext();

        }
        if (v == flipPreviousEvent){
            flipPreviousEvent.clearAnimation();
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.right_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.left_out);
            reviewsFlipper.showPrevious();

        }

    }

    /////////////////////////

    ///////



}
