package com.example.afaf.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.example.afaf.amakenapp.R;
import com.google.android.gms.maps.SupportMapFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class ExpandDetailsMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context context;

    public ImageView imageViewGallery;
    public ImageView imageViewLike;
    public ImageView imageViewSave;
    public ImageView imageViewReveiw;
    public TextView allReviews;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        imageViewGallery=(ImageView)findViewById(R.id.imageButtonGalleryHome);
        imageViewGallery.setImageResource(R.drawable.gallery_place);
        imageViewSave=(ImageView)findViewById(R.id.imageButtonSaveHome);
        imageViewSave.setImageResource(R.drawable.save_home);
        imageViewLike=(ImageView)findViewById(R.id.imageButtonLikeHome);
        imageViewLike.setImageResource(R.drawable.like_home);
        imageViewReveiw=(ImageView)findViewById(R.id.imageButtonReviewHome);
        imageViewReveiw.setImageResource(R.drawable.review_home);
        allReviews=(TextView)findViewById(R.id.textViewSeeAllReviews);

        //   allReviews.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         Intent myIntent = new Intent(v.getContext(),
        //                AllUsersReviewsActivity.class);
        //        context.startActivity(myIntent);
        //      }
        //   });

        allReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpandDetailsMapsActivity.this, AllUsersReviewsActivity.class);
                startActivity(intent);
            }
        });

        //  ExpandReview ex = new ExpandReview();
        //  ex.onRecycle();

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
    }



}
