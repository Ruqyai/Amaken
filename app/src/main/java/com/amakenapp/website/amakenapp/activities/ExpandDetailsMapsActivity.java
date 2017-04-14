package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.amakenapp.website.amakenapp.helper.ChildAnimationExample;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.amakenapp.website.amakenapp.helper.TransformerAdapter;
import com.amakenapp.website.amakenapp.store.Photo;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandDetailsMapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{

    private GoogleMap mMap;
    Context context;

    private LatLng addressPoint;
    SharedPrefManager sharedPrefManager;


    private static int placeID = 9;
    private static int userId;

    // view pager constants
    ViewPager viewPager;
    PagerAdapter galleryViewPager;
    private ArrayList<Photo> imagesGallery;
    private static int currentpage = 0;
    private static int numpages = 0;
    //////


    // slide show
    private SliderLayout mDemoSlider;
    private ImageView update2;
    private HashMap<String, String> file_maps;
    private List<String> imagesDescriptions, imagesURLs;
    private TextSliderView textSliderView;


    // view flipper arrays
    private static int reviewsNumber;
    private static int reviewsPhotosNumber;




    private ImageView imageViewGallery;
    private ImageView imageViewLike;
    private ImageView imageViewSave;
    private ImageView imageViewReveiw;
    private TextView noReviews, addReview, placePhotosNumber,
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

        //get user id from shared preferences
        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();
        placeID = getIntent().getExtras().getInt("PLACE_ID");

       ////////////////////////////////////////////////////

        //update=(ImageView) findViewById(R.id.update) ;
        //update.setOnClickListener(this);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        file_maps = new HashMap<String, String>();
        placeGalleryLoading(placeID);




        //////////////////////////////////////////////////////////////////
        ///////find views by id for textViews
        noReviews = (TextView) findViewById(R.id.no_place_reviews);
        addReview = (TextView) findViewById(R.id.add_one_place);

        placePhotosNumber = (TextView) findViewById(R.id.textNumberGalleryImage);
        placeLikesNumber = (TextView) findViewById(R.id.textNumberLikes);
        placeBookmarksNumber = (TextView) findViewById(R.id.textNumberSave);
        placeReplysNumber = (TextView) findViewById(R.id.textNumberReview);
        placeName = (TextView) findViewById(R.id.place_name);
        placeCategory = (TextView) findViewById(R.id.place_category);
        placeRating = (RatingBar) findViewById(R.id.place_rating);
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
        addReview.setOnClickListener(this);



        flipPrevious = (ImageButton) findViewById(R.id.flipp_previous);
        filpNext = (ImageButton) findViewById(R.id.flipp_next);

        filpNext.setOnClickListener(this);
        flipPrevious.setOnClickListener(this);


        //animations for the next and previous buttons
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(500);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        flipPrevious.startAnimation(mAnimation);
        filpNext.startAnimation(mAnimation);



        placeInformationLoading(placeID, userId);
        reviewsFlipper = (AdapterViewFlipper) findViewById(R.id.reviews_simple_flipper);
        listItems = new ArrayList<>();

        reviewsFlipper.setFlipInterval(3000);
        reviewsFlipper.setAutoStart(true);

        placeReviews(placeID, userId);


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
        addressPoint = new LatLng(23.497085, 44.870015);
        mMap.addMarker(new MarkerOptions().position(addressPoint).title("Marker in Saudi Arabia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(addressPoint));
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
    }

    @Override
    public void onClick(View v) {

        if (v == imageViewGallery){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));

            if (reviewsPhotosNumber == 0 )
                Toast.makeText(getApplicationContext(), "No images uploaded from users!", Toast.LENGTH_LONG).show();
            else if(reviewsPhotosNumber > 0)
            {Toast.makeText(getApplicationContext(), "This is Users' Reviews Gallery", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putInt("PlaceId", placeID);
                bundle.putString("GALLERY_TYPE", "PLACE");

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                GalleryPager newFragment = GalleryPager.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");}
        }

        if (v == imageViewLike){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            placeStoreLike(placeID, userId);
        }
        if (v == imageViewSave){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            placeStoreBookmark(placeID, userId);
        }
        if (v == imageViewReveiw  || v == addReview){
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            //// TODO: 3/17/2017 aslo store review on database
            startActivity(new Intent(this, AddReview.class));

        }

        if (v == filpNext){
            filpNext.clearAnimation();
            reviewsFlipper.stopFlipping();
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.left_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.right_out);
            reviewsFlipper.showNext();

        }
        if (v == flipPrevious){
            flipPrevious.clearAnimation();
            reviewsFlipper.stopFlipping();
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


    public void placeInformationLoading(int placeId, int userId) {
        final int placeID = placeId;
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_PLACE_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                obj.getInt("place_id");
                                reviewsPhotosNumber = obj.getInt("place_users_imagesGallery_number");
                                String reviewsPhotosNumber2 = Integer.toString(reviewsPhotosNumber);
                                placePhotosNumber.setText(reviewsPhotosNumber2);

                                int likesNum = obj.getInt("place_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                placeLikesNumber.setText(likesNum1);

                                if (obj.getString("like_message").equals(Constants.STRING_LIKE_EXSITS))
                                    imageViewLike.setImageResource(R.drawable.ic_like_fill);


                                int bookmarksNum = obj.getInt("place_bookmarks_number");
                                String bookmarksNum1 = Integer.toString(bookmarksNum);
                                placeBookmarksNumber.setText(bookmarksNum1);

                                if (obj.getString("bookmark_message").equals(Constants.STRING_BOOKMARK_EXSITS))
                                    imageViewSave.setImageResource(R.drawable.ic_bookmark_fill);

                                reviewsNumber = obj.getInt("place_reviews_number");
                                String reviewsNumber2 = Integer.toString(reviewsNumber);
                                placeReplysNumber.setText(reviewsNumber2);


                                placeName.setText(obj.getString("place_name"));
                                placeCategory.setText(obj.getString("place_category"));
                                Double rate = obj.getDouble("rate_avrg");
                                String rate2 = Double.toString(rate);
                                Float rate3 = Float.parseFloat(rate2);
                                placeRatingStat.setText(rate2);
                                placeRating.setRating(rate3);
                                placeDescription.setText(obj.getString("place_description"));
                                ownerName.setText(obj.getString("owner_name"));
                                ownerWebUrl.setText(obj.getString("owner_web"));
                                ownerPhone.setText(obj.getString("owner_phone"));
                                ownerEmail.setText(obj.getString("owner_email"));
                                locationAdress.setText(obj.getString("address"));

                                Double lat = obj.getDouble("latitude");
                                Double lang = obj.getDouble("longitude");
                                mMap.clear();
                                addressPoint = new LatLng(lat, lang);
                                mMap.addMarker(new MarkerOptions().position(addressPoint).title(obj.getString("address")));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(addressPoint));
                                locationCountry.setText(obj.getString("country"));
                                locationCity.setText(obj.getString("city"));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("place_id", placeID + "");
                params.put("user_id", userID + "");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }




    public void placeStoreLike(int placeId, int userId) {
        final int placeID = placeId;
        final int userID = userId;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_PLACE_STORE_LIKE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                imageViewLike.setImageResource(R.drawable.ic_like_fill);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                int likesNum = obj.getInt("place_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                placeLikesNumber.setText(likesNum1);
                            } else {
                                imageViewLike.setImageResource(R.drawable.ic_like_border);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                int likesNum = obj.getInt("place_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                placeLikesNumber.setText(likesNum1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("place_id", placeID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }



    public void placeStoreBookmark(int placeId, int userId) {
        final int placeID = placeId;
        final int userID = userId;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_PLACE_STORE_BOOKMARK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                imageViewSave.setImageResource(R.drawable.ic_bookmark_fill);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                int bookmarksNum = obj.getInt("place_bookmarks_number");
                                String bookmarksNum1 = Integer.toString(bookmarksNum);
                                placeBookmarksNumber.setText(bookmarksNum1);
                            } else {
                                imageViewSave.setImageResource(R.drawable.ic_bookmark_border);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                int bookmarksNum = obj.getInt("place_bookmarks_number");
                                String bookmarksNum1 = Integer.toString(bookmarksNum);
                                placeBookmarksNumber.setText(bookmarksNum1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("place_id", placeID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }



    public void placeReviews(int placeId, int userId) {
        final int placeID = placeId;
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_PLACE_USERS_REVIEWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            reviewsNumber = obj.getInt("reviews_number");
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("placeReviews");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject reviewDetails = arr.getJSONObject(i);

                                    Double rate = reviewDetails.getDouble("rate_value");
                                    String rate2 = Double.toString(rate);
                                    Float rate3 = Float.parseFloat(rate2);


                                    ExpandReviewDetailsListItem listItem = new ExpandReviewDetailsListItem();

                                    listItem.setReviewId(reviewDetails.getInt("id"));
                                    listItem.setPlaceId(reviewDetails.getInt("place_id"));
                                    listItem.setReviewType(reviewDetails.getString("review_type"));
                                    listItem.setReviewUserName(reviewDetails.getString("user_name"));
                                    listItem.setReviewTimestamp(reviewDetails.getString("review_timeStamp"));
                                    listItem.setReviewUserProfilePic(reviewDetails.getString("user_photo"));
                                    listItem.setReviewText(reviewDetails.getString("review_text"));
                                    listItem.setReviewRatingValue(rate3);
                                    listItem.setReviewLikesNumber(reviewDetails.getString("review_likes_number"));
                                    listItem.setReview_like_exsits(reviewDetails.getString("review_like_message"));

                                    listItems.add(listItem);

                                }

                                reviewsCustomAdapter = new ReviewsCustomAdapter(listItems, getBaseContext());
                                reviewsFlipper.setAdapter(reviewsCustomAdapter);

                            } else {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                noReviews.setVisibility(View.VISIBLE);
                                addReview.setVisibility(View.VISIBLE);
                                flipPrevious.clearAnimation();
                                filpNext.clearAnimation();
                                filpNext.setVisibility(View.GONE);
                                flipPrevious.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("place_id", placeID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(send);
    }



    public void placeGalleryLoading(int placeId) {
        final int placeID = placeId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_PLACE_GALLERY + placeID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("place_gallery");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject url = arr.getJSONObject(i);
                                    file_maps.put(url.getString("image_description"), url.getString("image_url"));
                                }
                                for (String name : file_maps.keySet()) {
                                    textSliderView = new TextSliderView(getApplication());
                                    // initialize a SliderLayout
                                    textSliderView.description(name)
                                            .image(file_maps.get(name))
                                            .setScaleType(BaseSliderView.ScaleType.Fit)
                                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                                @Override
                                                public void onSliderClick(BaseSliderView slider) {

                                                }
                                            });

                                    //add your extra information
                                    textSliderView.bundle(new Bundle());
                                    textSliderView.getBundle().putString("extra", name);
                                    mDemoSlider.addSlider(textSliderView);

                                }
                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                mDemoSlider.setDuration(5000);
                                mDemoSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                    }

                                    @Override
                                    public void onPageSelected(int position) {
                                        Log.d("Slider Demo", "Page Changed: " + position);


                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int state) {

                                    }
                                });

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        }) {

        };
        MySingleton.getInstance(this).addToRequestQueue(send);
    }


}
