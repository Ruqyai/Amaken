package com.amakenapp.website.amakenapp.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.store.Photo;
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

/**
 * Created by Muha on 4/8/2017.
 */

public class EventReviewSViewPager extends PagerAdapter {
    LayoutInflater inflater;
    Context context;
    private List<ExpandReviewDetailsListItem> reviews;


    TextView reviewUserName;
    TextView reviewTimestamp;
    ImageView reviewUserProfilePic;
    TextView reviewText;
    RatingBar reviewRatingValue;
    ImageView reviewLikeImage;
    TextView reviewLikesNumber;
    ImageView reviewFalgImage;

    public EventReviewSViewPager (){

    }

    public EventReviewSViewPager(Context context, List<ExpandReviewDetailsListItem> reviews ) {
        this.reviews = reviews;
        this.context = context;

    }


    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position ) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_users_reviews_exapand_detailes, container, false);
        ExpandReviewDetailsListItem reviews2 = reviews.get(position);


        reviewUserName = (TextView) view.findViewById(R.id.textNameUserWroteReview);
        reviewTimestamp = (TextView) view.findViewById(R.id.all_reviews_timestamp);
        reviewUserProfilePic = (ImageView) view.findViewById(R.id.imageViewUserWrite);


        reviewText = (TextView) view.findViewById(R.id.textViewUserWhatIsWrite);
        reviewRatingValue = (RatingBar) view.findViewById(R.id.all_reviews_singleReview_rating);
        reviewLikeImage = (ImageView) view.findViewById(R.id.all_reviews_likeLogo);
        reviewLikesNumber = (TextView) view.findViewById(R.id.all_reviews_reviewLikeStat);

        reviewFalgImage = (ImageView) view.findViewById(R.id.all_reviewReportFlag);



        reviewUserName.setText(reviews2.getReviewUserName());
        reviewTimestamp.setText(reviews2.getReviewTimestamp());
        reviewText.setText(reviews2.getReviewText());
        reviewRatingValue.setRating(reviews2.getReviewRatingValue());
        reviewLikesNumber.setText(reviews2.getReviewLikesNumber());

        Glide.with(context).load(reviews2.getReviewUserProfilePic())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(reviewUserProfilePic);

        reviewCheckLike(14, 9);

        reviewFalgImage.setImageResource(reviews2.getReviewFlagImage());


        reviewLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewStoreLike(14, 9);
                //Toast.makeText(context, "like clicked " + position, Toast.LENGTH_LONG).show();
            }
        });


        reviewFalgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
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
                        reviewStoreReport(2, 9, reason);
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






        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager)container).removeView((RelativeLayout)object);

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
                                reviewLikesNumber(reviewID);
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                reviewLikeImage.setImageResource(R.drawable.ic_thump_up);
                                reviewLikesNumber(reviewID);
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


    public void reviewLikesNumber(int reviewId) {
        final int reviewID = reviewId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_REVIEW_LIKES_NUMBERS + reviewID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                int likesNum = obj.getInt("event_likes_number");
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
        };
        MySingleton.getInstance(context).addToRequestQueue(send);
    }


    public void reviewCheckLike(int reviewId, int userId) {
        final int reviewID = reviewId;
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_REVIEW_CHECK_LIKE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                reviewLikeImage.setImageResource(R.drawable.ic_thump_up_fill);
                            } else {
                                reviewLikeImage.setImageResource(R.drawable.ic_thump_up);
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
