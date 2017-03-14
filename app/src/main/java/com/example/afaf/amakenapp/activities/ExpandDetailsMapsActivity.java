package com.example.afaf.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.HomeReviewDetailesAdapter;
import com.example.afaf.amakenapp.helper.HomeReviewDetailsListItem;
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
    public ImageView imageViewHomeBusinessPlaceImage;

    //
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<HomeReviewDetailsListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        imageViewHomeBusinessPlaceImage=(ImageView) findViewById(R.id.imageViewBusinessPlace);
        imageViewHomeBusinessPlaceImage.setImageResource(R.drawable.place_home_image);
        imageViewGallery=(ImageView)findViewById(R.id.imageButtonGalleryHome);
        imageViewGallery.setImageResource(R.drawable.gallery_place);
        imageViewSave=(ImageView)findViewById(R.id.imageButtonSaveHome);
        imageViewSave.setImageResource(R.drawable.save_home);
        imageViewLike=(ImageView)findViewById(R.id.imageButtonLikeHome);
        imageViewLike.setImageResource(R.drawable.like_home);
        imageViewReveiw=(ImageView)findViewById(R.id.imageButtonReviewHome);
        imageViewReveiw.setImageResource(R.drawable.review_home);

        /*
        allReviews=(TextView)findViewById(R.id.textViewSeeAllReviews);

        allReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpandDetailsMapsActivity.this, AllUsersReviewsActivity.class);
                startActivity(intent);
            }
        });

        //  ExpandReview ex = new ExpandReview();
        //  ex.onRecycle();

**/



        recyclerView = (RecyclerView)findViewById(R.id.RecyclerViewUnderMap);
       recyclerView.setHasFixedSize(false);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            HomeReviewDetailsListItem listItem = new  HomeReviewDetailsListItem(
                    R.drawable.profile_user_written,
                    "User Name  " + i+  " " ,
                    "User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place ",

                    R.drawable.ic_thumb_up_black_24dp,
                    R.drawable.ic_assistant_photo_black_24dp

            );

            listItems.add(listItem);

        }
        adapter = new HomeReviewDetailesAdapter(listItems,this);

        recyclerView.setAdapter(adapter);



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

    /////////////////////////

    ///////



}
