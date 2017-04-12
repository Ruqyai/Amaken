package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.amakenapp.website.amakenapp.helper.ChildAnimationExample;
import com.amakenapp.website.amakenapp.helper.TransformerAdapter;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.ExpandReviewDetailsListItem;
import com.amakenapp.website.amakenapp.helper.ReviewsCustomAdapter;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.maps.SupportMapFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandDetailsMapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private GoogleMap mMap;
    Context context;
    private SliderLayout mDemoSlider;

    private ImageView imageViewGallery;
    private ImageView imageViewLike;
    private ImageView imageViewSave;
    private ImageView imageViewReveiw;
    private TextView placePhotosNumber,
                    placeLikesNumber,
                    placeBookmarksNumber,
                    placeReplysNumber,
                    placeName,
                    placeCategory,
                    placeRatingStat,
                    placeDescription,
                    ownerName,
                    ownerWebUrl,
                    ownerPhone,
                    ownerEmail,
                    locationCountry,
                    locationCity,
                    locationAdress;

    private RatingBar placeRating;
    private ImageView imageViewHomeBusinessPlaceImage,update;
    private ImageButton filpNext, flipPrevious;

    ////
    private List<ExpandReviewDetailsListItem> listItems;
    private AdapterViewFlipper reviewsFlipper;
    private ReviewsCustomAdapter reviewsCustomAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       ////////////////////////////////////////////////////
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> file_maps = new HashMap<String, String>();
        file_maps.put("The name1 ","https://corporate.target.com/_media/TargetCorp/Press/Corporate%20Fact%20Sheet/press-corporate-hero.jpg?width=745&height=370&ext=.jpg");
        file_maps.put("The name2 ","https://t1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/1rrV/image/46KpZZtiwlY5xELBcux5rTqChVY.jpg");
        file_maps.put("The name3 ","http://brandchannel.com/wp-content/uploads/2013/03/TargetCanada-560.jpg");
        file_maps.put("The name4 ", "https://t1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/1rrV/image/46KpZZtiwlY5xELBcux5rTqChVY.jpg");

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);



        //////////////////////////////////////////////////////////////////
        ///////find views by id for textViews
        placePhotosNumber = (TextView) findViewById(R.id.textNumberGalleryImage);
        placeLikesNumber = (TextView) findViewById(R.id.textNumberLikes);
        placeBookmarksNumber = (TextView) findViewById(R.id.textNumberSave);
        placeReplysNumber = (TextView) findViewById(R.id.textNumberReview);
        placeName = (TextView) findViewById(R.id.place_name);
        placeCategory = (TextView) findViewById(R.id.place_category);
        placeRatingStat = (TextView) findViewById(R.id.place_rating_stat);
        placeDescription = (TextView) findViewById(R.id.place_description);
        ownerName = (TextView) findViewById(R.id.place_businessOwner_name);
        ownerWebUrl = (TextView) findViewById(R.id.textWeb);
        ownerPhone = (TextView) findViewById(R.id.textPhone);
        ownerEmail = (TextView) findViewById(R.id.textEmail);
        locationCountry = (TextView) findViewById(R.id.textCountryDetails);
        locationCity = (TextView) findViewById(R.id.textCityDetails);
        locationAdress = (TextView) findViewById(R.id.textAddressDetails);
        ///////



      //  imageViewHomeBusinessPlaceImage=(ImageView) findViewById(R.id.Expand_place_photos);
//        Glide.with(getApplicationContext()).load(R.drawable.store).into(imageViewHomeBusinessPlaceImage);


        imageViewGallery=(ImageView)findViewById(R.id.imageButtonGalleryHome);
        imageViewGallery.setImageResource(R.drawable.ic_gallary);
        imageViewGallery.setOnClickListener(this);

        imageViewSave=(ImageView)findViewById(R.id.imageButtonSaveHome);
        imageViewSave.setImageResource(R.drawable.ic_bookmark_border);
        imageViewSave.setOnClickListener(this);

        imageViewLike=(ImageView)findViewById(R.id.imageButtonLikeHome);
        imageViewLike.setImageResource(R.drawable.ic_like_border);
        imageViewLike.setOnClickListener(this);

        imageViewReveiw=(ImageView)findViewById(R.id.imageButtonReviewHome);
        imageViewReveiw.setImageResource(R.drawable.ic_reply);
        imageViewReveiw.setOnClickListener(this);


        flipPrevious = (ImageButton) findViewById(R.id.flipp_previous);
        filpNext = (ImageButton) findViewById(R.id.flipp_next);

        filpNext.setOnClickListener(this);
        flipPrevious.setOnClickListener(this);

        update=(ImageView) findViewById(R.id.update) ;
        update.setOnClickListener(this);

        //animations for the next and previous buttons
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(500);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        flipPrevious.startAnimation(mAnimation);
        filpNext.startAnimation(mAnimation);




        reviewsFlipper = (AdapterViewFlipper) findViewById(R.id.reviews_simple_flipper);
        listItems = new ArrayList<>();

        /*for (int i = 0; i < 10; i++) {
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

        }*/

        reviewsCustomAdapter = new ReviewsCustomAdapter(listItems, this);
        reviewsFlipper.setAdapter(reviewsCustomAdapter);
        reviewsFlipper.setFlipInterval(2000);
       reviewsFlipper.setAutoStart(true);



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

        if (v == filpNext){
            filpNext.clearAnimation();
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.left_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.right_out);
            reviewsFlipper.showNext();

        }
        if (v == flipPrevious){
            flipPrevious.clearAnimation();
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.right_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.left_out);
            reviewsFlipper.showPrevious();

        }
        if (v == update){
           int click=0;
            for(int i=0; i>10;i++){
            click++;
           if(click ==1){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);}
                if(click ==1){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);}
                if(click ==2){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);}
                if(click ==3){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateUp);}
                if(click ==4){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);}
                if(click ==5){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);}
                if(click ==6){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);}
                if(click ==7){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);}
                if(click ==8){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);}
                if(click ==9){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);}
                if(click ==10){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipPage);}
                if(click ==11){mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);}
            }
        }

    }

    /////////////////////////

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
       Toast.makeText(this,"The Name of This place is : "+ slider.getBundle().get("extra") ,Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_custom_indicator:
                mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
                break;
            case R.id.action_custom_child_animation:
                mDemoSlider.setCustomAnimation(new ChildAnimationExample());
                break;
            case R.id.action_restore_default:
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

}
