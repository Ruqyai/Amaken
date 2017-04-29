package com.amakenapp.website.amakenapp.helper;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by Muha on 3/16/2017.
 */

public class ReviewsCustomAdapter extends BaseAdapter {

    List<ExpandReviewDetailsListItem> listItems;
    Context context ;
    LayoutInflater inflater;

    SharedPrefManager sharedPrefManager;
    private static int userId;

    private TextView reviewId;
    private TextView reviewType;
    private TextView placeId;
    private TextView eventId;



    private TextView reviewUserName;
    private TextView reviewlikeExsit;

    private TextView reviewTimestamp;
    private ImageView reviewUserProfilePic;
    private TextView reviewText;
    private RatingBar reviewRatingValue;
    private ImageView reviewLikeImage;
    private TextView reviewLikesNumber;
    private ImageView reviewFalgImage;


    public ReviewsCustomAdapter(List<ExpandReviewDetailsListItem> listItems,
                                Context context) {
        this.listItems = listItems;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view  = inflater.inflate(R.layout.item_users_reviews_exapand_detailes, null);


        sharedPrefManager = SharedPrefManager.getInstance(context);
        userId = sharedPrefManager.getUserId();

        reviewId = (TextView) view.findViewById(R.id.review_id);
        reviewId = (TextView) view.findViewById(R.id.review_like_exsits);

        reviewType = (TextView) view.findViewById(R.id.review_type);
        eventId = (TextView) view.findViewById(R.id.event_id);
        placeId  = (TextView) view.findViewById(R.id.place_id);




        reviewUserName = (TextView) view.findViewById(R.id.textNameUserWroteReview);
        reviewTimestamp = (TextView) view.findViewById(R.id.all_reviews_timestamp);
        reviewUserProfilePic =(ImageView) view.findViewById(R.id.imageViewUserWrite);


        reviewText = (TextView) view.findViewById(R.id.textViewUserWhatIsWrite);
        reviewRatingValue = (RatingBar) view.findViewById(R.id.all_reviews_singleReview_rating);
        reviewLikeImage =(ImageView) view.findViewById(R.id.all_reviews_likeLogo);
        reviewLikesNumber = (TextView) view.findViewById(R.id.all_reviews_reviewLikeStat) ;
        reviewFalgImage =(ImageView) view.findViewById(R.id.all_reviewReportFlag);


        ExpandReviewDetailsListItem listItem = listItems.get(position);

        final int reviewId1= listItem.getReviewId();
        //String reviewId2 = Integer.toString(reviewId1);
        //reviewId.setText(reviewId2);

        //reviewType.setText(listItem.getReviewType());

        //int eventId1= listItem.getEventId();
        //String eventId2 = Integer.toString(eventId1);
        //eventId.setText(eventId2);

        //int placeId1= listItem.getPlaceId();
        //String placeId2 = Integer.toString(placeId1);
        //placeId.setText(placeId2);


        reviewUserName.setText(listItem.getReviewUserName());
        reviewTimestamp.setText(listItem.getReviewTimestamp());
        reviewText.setText(listItem.getReviewText());
        reviewRatingValue.setRating(listItem.getReviewRatingValue());
        reviewLikesNumber.setText(listItem.getReviewLikesNumber());


        String userProfilePic = listItem.getReviewUserProfilePic();
        if (userProfilePic.equals(Constants.STRING_USER_PROFILE_PIC))
           {
            reviewUserProfilePic.setImageResource(R.drawable.ic_person);
           }
        else
          {
            Glide.with(context).load(listItem.getReviewUserProfilePic())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(reviewUserProfilePic);
          }

        String reviewlikeexsits = listItem.getReview_like_exsits();
        if (reviewlikeexsits.equals(Constants.STRING_REVIEW_LIKE_EXSITS))
            reviewLikeImage.setImageResource(R.drawable.ic_thump_up_fill);
        else reviewLikeImage.setImageResource(R.drawable.ic_thump_up);


        Glide.with(context).load(listItem.getReviewFlagImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .skipMemoryCache( true )
                .into(reviewFalgImage);



        reviewLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewStoreLike(reviewId1, userId);
                //Toast.makeText(context, "like clicked " + position, Toast.LENGTH_LONG).show();
            }
        });


        reviewFalgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getRootView().getContext());
                alertDialog.setTitle("REPORT");
                final View view1 = inflater.inflate(R.layout.report_dialog, null);

                final AppCompatEditText input = (AppCompatEditText) view1.findViewById(R.id.report_reasonEdit);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
                view1.setLayoutParams(lp);
                alertDialog.setView(view1);
                alertDialog.setIcon(R.drawable.ic_report_flag);

                alertDialog.setPositiveButton("Send", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        String reason = input.getText().toString().trim();
                        reviewStoreReport(reviewId1, userId, reason);
                    }
                });



                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                final AlertDialog dialog = alertDialog.create();
                dialog.show();
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

                input.setError("Enter Report Reason!");
                input.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        if (input.getText().toString().length()<= 0) {
                            input.setError("Enter Report Reason!");
                            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                        } else {
                            input.setError(null);
                            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

                        }
                    }
                    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                    public void onTextChanged(CharSequence s, int start, int before, int count){
                        input.setError(null);
                    }
                });

            } });


        return view;
    }


    public void reviewStoreLike(int reviewId, int userId) {
        final int reviewID = reviewId;
        final int userID = userId;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_REVIEW_STORE_LIKE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                reviewLikeImage.setImageResource(R.drawable.ic_thump_up_fill);
                                //reviewLikesNumber(reviewID);
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                                int likesNum = obj.getInt("review_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                reviewLikesNumber.setText(likesNum1);

                            } else {
                                reviewLikeImage.setImageResource(R.drawable.ic_thump_up);
                                //reviewLikesNumber(reviewID);
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                                int likesNum = obj.getInt("review_likes_number");
                                String likesNum1 = Integer.toString(likesNum);
                                reviewLikesNumber.setText(likesNum1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        context,
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("review_id", reviewID + "");
                params.put("user_id", userID + "");
                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(send);

    }


    public void reviewStoreReport(int reviewId, int userId, final String reportReason) {
        final int reviewID = reviewId;
        final int userID = userId;
        final String reportreason1 = reportReason;



        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_REVIEW_REPORT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("review_id", reviewID + "");
                params.put("reporter_id", userID + "");
                params.put("report_reason", reportreason1);

                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(send);

    }


    }
