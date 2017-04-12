package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.amakenapp.website.amakenapp.helper.ChildAnimationExample;
import com.amakenapp.website.amakenapp.helper.EventReviewSViewPager;
import com.amakenapp.website.amakenapp.helper.ExpandReviewDetailsListItem;
import com.amakenapp.website.amakenapp.helper.ReviewsCustomAdapter;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.ExpandReviewDetailsListItem;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ReviewsCustomAdapter;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.amakenapp.website.amakenapp.helper.ViewPagerAdapter;
import com.amakenapp.website.amakenapp.store.Photo;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;

public class ExpandDetailsMapsActivityEvent extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{

    private GoogleMap mMap;
    private LatLng addressPoint;
    Context context;
    SharedPrefManager sharedPrefManager;


    private static int eventID;
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

  /*  // view pager constants
    ViewPager viewPagerReviews;
    PagerAdapter reviewsViewPager;
    private ArrayList<ExpandReviewDetailsListItem> reviews;
    private static int currentpagereview = 0;
    private static int numpagesreview = 0;
    //////
    */

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


    private RatingBar eventRating;
    private ImageView imageViewHomeBusinesEventImage;
    private ImageButton filpNextEvent, flipPreviousEvent;
    private ImageButton filpNextEventimage, flipPreviousEventimage;


    ////
    private List<ExpandReviewDetailsListItem> listItems;
    private AdapterViewFlipper reviewsFlipper;
    private ReviewsCustomAdapter reviewsCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details_maps_event);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_event);
        mapFragment.getMapAsync(this);

        //get user id from shared preferences
        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();
        eventID = getIntent().getExtras().getInt("EVENT_ID");


        ////////////////////////////////////////////////////

        update2 = (ImageView) findViewById(R.id.update2);
        update2.setOnClickListener(this);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider_event);

        file_maps = new HashMap<String, String>();
        eventGalleryLoading(eventID);

        //////////////////////////////////////////////////////////////////
       /* //GALLERY view pager code
        imagesGallery = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.pager);
        galleryViewPager = new ViewPagerAdapter(this, imagesGallery);
        viewPager.setAdapter(galleryViewPager);*/

/*
        //review pager code
        reviews = new ArrayList<>();
        viewPagerReviews = (ViewPager) findViewById(R.id.event_reviewpager);
        reviewsViewPager = new EventReviewSViewPager(this, reviews);
        viewPagerReviews.setAdapter(reviewsViewPager);*/

        ///////find views by id for textViews
        eventPhotosNumber = (TextView) findViewById(R.id.textNumberGalleryImageEvent);
        eventLikesNumber = (TextView) findViewById(R.id.textNumberLikesEvent);
        eventBookmarksNumber = (TextView) findViewById(R.id.textNumberSaveEvent);
        eventReplysNumber = (TextView) findViewById(R.id.textNumberReviewEvent);
        eventName = (TextView) findViewById(R.id.event_name);
        eventCategory = (TextView) findViewById(R.id.event_category);
        eventRating = (RatingBar) findViewById(R.id.event_rating);
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


        imageViewGallery = (ImageView) findViewById(R.id.imageButtonGalleryEvent);
        imageViewGallery.setImageResource(R.drawable.ic_gallary);
        imageViewGallery.setOnClickListener(this);

        imageViewSave = (ImageView) findViewById(R.id.imageButtonSaveeEvent);
        imageViewSave.setImageResource(R.drawable.ic_bookmark_border);
        imageViewSave.setOnClickListener(this);

        imageViewLike = (ImageView) findViewById(R.id.imageButtonLikeEvent);
        imageViewLike.setImageResource(R.drawable.ic_like_border);
        imageViewLike.setOnClickListener(this);

        imageViewReveiw = (ImageView) findViewById(R.id.imageButtonReviewEvent);
        imageViewReveiw.setImageResource(R.drawable.ic_reply);
        imageViewReveiw.setOnClickListener(this);


        flipPreviousEvent = (ImageButton) findViewById(R.id.flipp_previous_event);
        filpNextEvent = (ImageButton) findViewById(R.id.flipp_next_event);

        filpNextEvent.setOnClickListener(this);
        flipPreviousEvent.setOnClickListener(this);


        /*flipPreviousEventimage = (ImageButton) findViewById(R.id.flipp_previous_image_event);
        filpNextEventimage = (ImageButton) findViewById(R.id.flipp_next_image_event);

        filpNextEventimage.setOnClickListener(this);
        flipPreviousEventimage.setOnClickListener(this);*/

/////////////animations for the next and previous buttons
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(500);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        flipPreviousEvent.startAnimation(mAnimation);
        filpNextEvent.startAnimation(mAnimation);
////////////////


        // reviews flipper
        eventInformationLoading(eventID, userId);
        reviewsFlipper = (AdapterViewFlipper) findViewById(R.id.reviews_simple_flipper_events);
        listItems = new ArrayList<>();
        reviewsFlipper.setFlipInterval(3000);
        reviewsFlipper.setAutoStart(true);

        eventReviews(eventID, userId);
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

        if (v == imageViewGallery) {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));

            if (reviewsPhotosNumber == 0 )
                Toast.makeText(getApplicationContext(), "No images uploaded from users!", Toast.LENGTH_LONG).show();
            else if(reviewsPhotosNumber > 0)
            {Toast.makeText(getApplicationContext(), "This is Users' Reviews Gallery", Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putInt("EventId", eventID);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            GalleryPager newFragment = GalleryPager.newInstance();
            newFragment.setArguments(bundle);
            newFragment.show(ft, "slideshow");}
        }

        if (v == imageViewLike) {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            eventStoreLike(eventID, userId);

        }
        if (v == imageViewSave) {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            eventStoreBookmark(eventID, userId);
        }
        if (v == imageViewReveiw) {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation));
            //// TODO: 3/17/2017 aslo store review on database
            startActivity(new Intent(this, AddReview.class));

        }

        if (v == filpNextEvent) {
            filpNextEvent.clearAnimation();
            reviewsFlipper.stopFlipping();
/*            int tab = viewPagerReviews.getCurrentItem();
            tab++;
            viewPagerReviews.setCurrentItem(tab); */
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.left_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.right_out);
            reviewsFlipper.showNext();

        }
        if (v == flipPreviousEvent) {
            flipPreviousEvent.clearAnimation();
            reviewsFlipper.stopFlipping();

           /* int tab = viewPagerReviews.getCurrentItem();
            if (tab > 0) {
                tab--;
                viewPagerReviews.setCurrentItem(tab);
            } else if (tab == 0) {
                viewPagerReviews.setCurrentItem(tab);
            }*/
            reviewsFlipper.setInAnimation(getApplicationContext(), R.animator.right_in);
            reviewsFlipper.setOutAnimation(getApplicationContext(), R.animator.left_out);
            reviewsFlipper.showPrevious();

        }


        if (v == filpNextEventimage) {
            int tab = viewPager.getCurrentItem();
            tab++;
            viewPager.setCurrentItem(tab);

        }
        if (v == flipPreviousEventimage) {
            int tab = viewPager.getCurrentItem();
            if (tab > 0) {
                tab--;
                viewPager.setCurrentItem(tab);
            } else if (tab == 0) {
                viewPager.setCurrentItem(tab);
            }

        }


        if (v == update2) {
            int click = 0;
            for (int i = 0; i > 10; i++) {
                click++;
                if (click == 1) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);
                }
                if (click == 1) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
                }
                if (click == 2) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
                }
                if (click == 3) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
                }
                if (click == 4) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
                }
                if (click == 5) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                }
                if (click == 6) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
                }
                if (click == 7) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);
                }
                if (click == 8) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                }
                if (click == 9) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
                }
                if (click == 10) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipPage);
                }
                if (click == 11) {
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
                }
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

    /*@Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, "The Name of This place is : " + slider.getBundle().get("extra"), Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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


    /////////////////////////


    public void eventInformationLoading(int eventId, int userId) {
        final int eventID = eventId;
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_EVENT_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                obj.getInt("event_id");
                                reviewsPhotosNumber = obj.getInt("event_users_imagesGallery_number");
                                String reviewsPhotosNumber2 = Integer.toString(reviewsPhotosNumber);
                                eventPhotosNumber.setText(reviewsPhotosNumber2);

                                int likesNum = obj.getInt("event_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                eventLikesNumber.setText(likesNum1);

                                if (obj.getString("like_message").equals(Constants.STRING_LIKE_EXSITS))
                                imageViewLike.setImageResource(R.drawable.ic_like_fill);


                                int bookmarksNum = obj.getInt("event_bookmarks_number");
                                String bookmarksNum1 = Integer.toString(bookmarksNum);
                                eventBookmarksNumber.setText(bookmarksNum1);

                                if (obj.getString("bookmark_message").equals(Constants.STRING_BOOKMARK_EXSITS))
                                imageViewSave.setImageResource(R.drawable.ic_bookmark_fill);

                                reviewsNumber = obj.getInt("event_reviews_number");
                                String reviewsNumber2 = Integer.toString(reviewsNumber);
                                eventReplysNumber.setText(reviewsNumber2);


                                eventName.setText(obj.getString("event_name"));
                                eventCategory.setText(obj.getString("event_category"));
                                Double rate = obj.getDouble("rate_avrg");
                                String rate2 = Double.toString(rate);
                                Float rate3 = Float.parseFloat(rate2);
                                eventRatingStat.setText(rate2);
                                eventRating.setRating(rate3);
                                eventDescription.setText(obj.getString("event_description"));
                                ownerName.setText(obj.getString("owner_name"));
                                ownerWebUrl.setText(obj.getString("owner_web"));
                                ownerPhone.setText(obj.getString("owner_phone"));
                                ownerEmail.setText(obj.getString("owner_email"));
                                eventStartDate.setText(obj.getString("strat_date"));
                                eventEndDate.setText(obj.getString("end_date"));
                                eventStartTime.setText(obj.getString("start_time"));
                                eventEndTime.setText(obj.getString("end_time"));
                                eventDays.setText(obj.getString("days"));
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
                params.put("event_id", eventID + "");
                params.put("user_id", userID + "");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }




    public void eventStoreLike(int eventId, int userId) {
        final int eventID = eventId;
        final int userID = userId;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_EVENT_STORE_LIKE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                imageViewLike.setImageResource(R.drawable.ic_like_fill);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                int likesNum = obj.getInt("event_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                eventLikesNumber.setText(likesNum1);
                            } else {
                                imageViewLike.setImageResource(R.drawable.ic_like_border);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                int likesNum = obj.getInt("event_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                eventLikesNumber.setText(likesNum1);
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
                params.put("event_id", eventID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }



    public void eventStoreBookmark(int eventId, int userId) {
        final int eventID = eventId;
        final int userID = userId;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_EVENT_STORE_BOOKMARK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                imageViewSave.setImageResource(R.drawable.ic_bookmark_fill);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                //eventBookmarksNumber(eventID);
                                int bookmarksNum = obj.getInt("event_bookmarks_number");
                                String bookmarksNum1 = Integer.toString(bookmarksNum);
                                eventBookmarksNumber.setText(bookmarksNum1);
                            } else {
                                imageViewSave.setImageResource(R.drawable.ic_bookmark_border);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                //eventBookmarksNumber(eventID);
                                int bookmarksNum = obj.getInt("event_bookmarks_number");
                                String bookmarksNum1 = Integer.toString(bookmarksNum);
                                eventBookmarksNumber.setText(bookmarksNum1);
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
                params.put("event_id", eventID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }



    public void eventReviews(int eventId, int userId) {
        final int eventID = eventId;
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_EVENT_USERS_REVIEWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            reviewsNumber = obj.getInt("reviews_number");
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("eventReviews");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject reviewDetails = arr.getJSONObject(i);

                                    Double rate = reviewDetails.getDouble("rate_value");
                                    String rate2 = Double.toString(rate);
                                    Float rate3 = Float.parseFloat(rate2);


                                    ExpandReviewDetailsListItem listItem = new ExpandReviewDetailsListItem();

                                    listItem.setReviewId(reviewDetails.getInt("id"));
                                    listItem.setEventId(reviewDetails.getInt("event_id"));
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
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                params.put("event_id", eventID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(send);
    }



    public void eventGalleryLoading(int eventId) {
        final int eventID = eventId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_EVENT_GALLERY + eventID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("event_gallery");
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
                                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
                                    mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                    mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                    mDemoSlider.setDuration(4000);
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

