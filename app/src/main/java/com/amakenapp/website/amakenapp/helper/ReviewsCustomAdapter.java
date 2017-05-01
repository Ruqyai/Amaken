package com.amakenapp.website.amakenapp.helper;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.amakenapp.website.amakenapp.activities.UserDetailsActivity;
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



public class ReviewsCustomAdapter extends BaseAdapter {

    List<ExpandReviewDetailsListItem> listItems;
    Context context ;
    LayoutInflater inflater;

    SharedPrefManager sharedPrefManager;
    private static int userId, insertedLikeId, reviewId1, reportId, userWhoWroteThereviewId, placeOwnerId, eventOwnerId;
    private String userName, placeName, eventName, reviewType;

    private TextView reviewId;
   // private TextView reviewType;
    private int placeId;
    private int eventId;



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
        userName = sharedPrefManager.getUsername();


        reviewId = (TextView) view.findViewById(R.id.review_id);
        reviewlikeExsit = (TextView) view.findViewById(R.id.review_like_exsits);

        //reviewType = (TextView) view.findViewById(R.id.review_type);




        reviewUserName = (TextView) view.findViewById(R.id.textNameUserWroteReview);
        reviewTimestamp = (TextView) view.findViewById(R.id.all_reviews_timestamp);
        reviewUserProfilePic =(ImageView) view.findViewById(R.id.imageViewUserWrite);


        reviewText = (TextView) view.findViewById(R.id.textViewUserWhatIsWrite);
        reviewRatingValue = (RatingBar) view.findViewById(R.id.all_reviews_singleReview_rating);
        reviewLikeImage =(ImageView) view.findViewById(R.id.all_reviews_likeLogo);
        reviewLikesNumber = (TextView) view.findViewById(R.id.all_reviews_reviewLikeStat) ;
        reviewFalgImage =(ImageView) view.findViewById(R.id.all_reviewReportFlag);


        ExpandReviewDetailsListItem listItem = listItems.get(position);

        reviewId1= listItem.getReviewId();
        userWhoWroteThereviewId= listItem.getUserWhoWroteReviewId();
        placeOwnerId = listItem.getPlaceOwnerId();
        eventOwnerId = listItem.getEventOwnerId();

        reviewType = listItem.getReviewType();
        placeName = listItem.getPlaceName();
        eventName = listItem.getEventName();

        //String reviewId2 = Integer.toString(reviewId1);
        //reviewId.setText(reviewId2);

        //reviewType.setText(listItem.getReviewType());

         eventId= listItem.getEventId();
        //String eventId2 = Integer.toString(eventId1);
        //eventId.setText(eventId2);

         placeId= listItem.getPlaceId();
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

        reviewUserProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("USER_ID", userWhoWroteThereviewId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

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


    private void showToast(String meg){
        final String message = meg;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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
                                insertedLikeId = obj.getInt("inserted_like_id");
                                reviewLikeImage.setImageResource(R.drawable.ic_thump_up_fill);
                                //reviewLikesNumber(reviewID);
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();


                                if (reviewType.equals(Constants.STRING_Review_Type_place))
                                    addNotification(userWhoWroteThereviewId, userID, userName+" liked your review on "+placeName, "like review on place", placeId, 0, reviewId1, 0, insertedLikeId, 0 );
                                else if(reviewType.equals(Constants.STRING_Review_Type_event))
                                    addNotification(userWhoWroteThereviewId, userID, userName+" liked your review on "+eventName, "like review on event", 0, eventId, reviewId1, 0, insertedLikeId, 0 );



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
                                reportId = obj.getInt("inserted_report_id");
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                                if (reviewType.equals(Constants.STRING_Review_Type_place))
                                    addNotification(placeOwnerId, userID, userName+" reported a review on your place "+placeName, "report review", 0, 0, reviewId1, reportId, 0, 0 );
                                else if(reviewType.equals(Constants.STRING_Review_Type_event))
                                    addNotification(eventOwnerId, userID, userName+" reported a review on your event "+eventName, "report review", 0, 0, reviewId1, reportId, 0, 0 );

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


    public void addNotification(int targeId, int generatorId, String notificationmessage, String notiType, int placeid, int eventid, int reviewid, int reprtedreviewid, int likeid , int bookmarkid  ) {
        final int targetUserID= targeId;
        final int generatorID= generatorId;
        final String notificationMessage = notificationmessage;
        final String type = notiType;
        final int placeID2 = placeid;
        final int eventID2 = eventid;
        final int reviewID2 = reviewid;
        final int reported_reviewID2 = reprtedreviewid;
        final int likeID2 = likeid;
        final int bookmark2 = bookmarkid;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_ADD_NOTIFICATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //showToast(obj.getInt("inserted_notification_id")+"");
                                //showToast(obj.getString("message"));

                            } else {
                                //showToast(obj.getString("message"));

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
                params.put("target_user_id", targetUserID+"");
                params.put("generator_user_id", generatorID+"");
                params.put("notification_message", notificationMessage);
                params.put("type", type);
                params.put("place_id", placeID2+"");
                params.put("event_id", eventID2+"");
                params.put("review_id", reviewID2+"");
                params.put("reported_review_id", reported_reviewID2+"");
                params.put("like_id", likeID2+"");
                params.put("bookmark_id", bookmark2+"");





                return params;
            }

        };

        MySingleton.getInstance(context).addToRequestQueue(send);

    }

}
