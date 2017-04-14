package com.amakenapp.website.amakenapp.activities;

/**
 * Created by Muha on 4/3/2017.
 */
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.store.Photo;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.ViewPagerAdapter;
import com.amakenapp.website.amakenapp.store.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Handler;


public class GalleryPager extends DialogFragment{
    private String TAG = GalleryPager.class.getSimpleName();


    ViewPager viewPager;
    PagerAdapter galleryViewPager;
    private ArrayList<Photo> imagesGallery;
    private TextView lblCount, lblUser, lblDate, lblDescription, seeDetails;
    private LinearLayout infoLayout;
    private Animation slideUp, slideDown;
    final int position = 0;

    private int eventId;
    private int placeId;


    static GalleryPager newInstance() {
        GalleryPager f = new GalleryPager();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gallery_pager, container, false);

        //view pager code
        imagesGallery = new ArrayList<>();
        viewPager = (ViewPager) v.findViewById(R.id.gallery_viewpager);
        galleryViewPager = new ViewPagerAdapter(getActivity(), imagesGallery);
        viewPager.setAdapter(galleryViewPager);

        lblCount = (TextView) v.findViewById(R.id.lbl_count);
        lblUser = (TextView) v.findViewById(R.id.user_name);
        lblDate = (TextView) v.findViewById(R.id.date);
        seeDetails = (TextView) v.findViewById(R.id.see_details);
        lblDescription = (TextView) v.findViewById(R.id.description);
        infoLayout = (LinearLayout) v.findViewById(R.id.infoVisible);

        seeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
                slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
                displayMetaInfo(position);
                infoLayout.startAnimation(slideUp);
            }
        });


        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        String galleryType = getArguments().getString("GALLERY_TYPE");

        if(galleryType.equals("PLACE"))

        {
            placeId = getArguments().getInt("PlaceId");
            usersGalleryLoadingPlace(placeId);
        }
        else if(galleryType.equals("EVENT"))
        {
            eventId = getArguments().getInt("EventId");
            usersGalleryLoading(eventId);
        }


        return v;
    }




       ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            seeDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
                     slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
                    displayMetaInfo(position);
                    infoLayout.startAnimation(slideUp);
                }
            });

        }


        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int position) {
        }
    };


    public void displayMetaInfo(final int position) {
        lblCount.setText((position + 1) + " of " + imagesGallery.size());
        lblCount.setVisibility(View.VISIBLE);
        infoLayout.setVisibility(View.VISIBLE);
        Photo image = imagesGallery.get(position);
        lblUser.setText( image.getUserName());
        lblDate.setText( image.getDate());
        lblDescription.setText(image.getPhoto_description());


        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                long finish = millisUntilFinished / 1000;
            }

            public void onFinish() {
                displaynothing(position);

            }

        }.start();
    }

    public void displaynothing(int position) {
        lblCount.setVisibility(View.INVISIBLE);
        infoLayout.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    public void usersGalleryLoading(int eventIdArg) {
        eventId = eventIdArg;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_EVENT_USERS_GALLERY+ eventId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        imagesGallery.clear();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr =  obj.getJSONArray("reviews_gallery");
                                for(int i = 0; i<arr.length(); i++){
                                    JSONObject url = arr.getJSONObject(i);
                                            Photo image =  new Photo();
                                            image.setUserName(url.getString("user_name"));
                                            image.setDate(url.getString("review_timeStamp"));
                                            image.setPhoto_description(url.getString("image_description"));
                                            image.setPhoto_url(url.getString("image_url"));
                                            imagesGallery.add(image);
                                }
                            } else {
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        galleryViewPager.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(
                        getActivity(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {

        };

        MySingleton.getInstance(getActivity()).addToRequestQueue(send);

    }





    public void usersGalleryLoadingPlace(int placeIdArg) {
        placeId = placeIdArg;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_PLACE_USERS_GALLERY+ placeId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        imagesGallery.clear();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr =  obj.getJSONArray("reviews_gallery");
                                for(int i = 0; i<arr.length(); i++){
                                    JSONObject url = arr.getJSONObject(i);
                                    Photo image =  new Photo();
                                    image.setUserName(url.getString("user_name"));
                                    image.setDate(url.getString("review_timeStamp"));
                                    image.setPhoto_description(url.getString("image_description"));
                                    image.setPhoto_url(url.getString("image_url"));
                                    imagesGallery.add(image);
                                }
                            } else {
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        galleryViewPager.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(
                        getActivity(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {

        };

        MySingleton.getInstance(getActivity()).addToRequestQueue(send);

    }
}
