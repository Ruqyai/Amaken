package com.amakenapp.website.amakenapp.helper;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by USER on 3/10/2017.
 */

public class ProfileLikeAdapter extends RecyclerView.Adapter<ProfileLikeAdapter.ViewHolder> {
    private List<ProfileLikesListItem> listItems;
    private Context context;
    private AlertDialog.Builder alertDialog;


    public ProfileLikeAdapter(List<ProfileLikesListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ProfileLikeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_likes_list_item, parent, false);
        return new ProfileLikeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProfileLikeAdapter.ViewHolder holder, final int position) {

        ProfileLikesListItem listItem = listItems.get(position);

        final int like_id = listItem.getLike_id();
        final String like_type = listItem.getLike_type();


        if (like_type.equalsIgnoreCase(Constants.STRING_TYPE_PLACE) || like_type.equalsIgnoreCase(Constants.STRING_TYPE_EVENT) )
        {
            final int like_place_or_event_id = listItem.getPlaceorevent_id();
            holder.reviewlikelayout.setVisibility(View.GONE);
            holder.placeoreventlikelayout.setVisibility(View.VISIBLE);
            holder.likeLogo.setImageResource(listItem.getLikeLogo());
            holder.LikeTimeStamp.setText(listItem.getLike_timestamp());
            Glide.with(context).load(listItem.getPlaceOrEventPic()).into(holder.PlaceOrEventPicture);
            holder.PlaceOrEventName.setText(listItem.getPlaceOrEventName());
            holder.PlaceOrEventCategory.setText(listItem.getPlaceOrEventCategory());
            holder.optionsMenuLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //creating a popup menu
                    final Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                    PopupMenu popup = new PopupMenu(wrapper, holder.optionsMenuLikes);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.profile_likes_options_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.placeOrEventOrReviewLikeView:
                                    //handle menu1 click
                                    if (like_type.equalsIgnoreCase(Constants.STRING_TYPE_PLACE)) {
                                        Intent myIntent = new Intent(context, ExpandDetailsMapsActivity.class);
                                        myIntent.putExtra("PLACE_ID", like_place_or_event_id);
                                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(myIntent);
                                    }
                                    else if (like_type.equalsIgnoreCase(Constants.STRING_TYPE_EVENT))
                                    {
                                        Intent myIntent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                                        myIntent.putExtra("EVENT_ID", like_place_or_event_id);
                                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(myIntent);
                                    }

                                    break;
                                case R.id.likeUnlike:
                                    //handle menu2 click
                                    alertDialog = new AlertDialog.Builder(context);
                                    alertDialog.setTitle("Delete");
                                    TextView myMsg = new TextView(context);
                                    myMsg.setText(" \n\n Are You Sure You Want to Remove This from Your Likes?");
                                    myMsg.setPadding(5, 5, 5, 5);
                                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                                    alertDialog.setIcon(R.drawable.ic_delete_alert);
                                    alertDialog.setView(myMsg);

                                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int which) {
                                            deleteLike(like_id);
                                            swap(position);

                                        }
                                    });

                                    alertDialog.setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });

                                    final AlertDialog dialog = alertDialog.create();
                                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                                    //if(isFinishing())
                                    dialog.show();

                                    break;
                            }
                            return false;
                        }
                    });
                    //displaying the popup
                    popup.show();

                }
            });

        }
        else if (like_type.equalsIgnoreCase(Constants.STRING_TYPE_Review))
        {
            final int placeOrEventIdReview = listItem.getEventOrPlaceId();
            final String reviewType = listItem.getReviewType();
            holder.placeoreventlikelayout.setVisibility(View.GONE);
            holder.reviewlikelayout.setVisibility(View.VISIBLE);
            holder.Likelogoreviews.setImageResource(listItem.getLikeLogo());
            holder.LikeTimeStampreviews.setText(listItem.getLike_timestamp());
            Glide.with(context).load(listItem.getReviewUserPic()).into(holder.userPic);
            holder.username.setText(listItem.getReviewUserName());
            holder.reviewtext.setText(listItem.getReviewText());
            holder.reviewrating.setRating(listItem.getReviewRating());
            holder.optionsMenuLikesrevies.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //creating a popup menu
                    final Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                    PopupMenu popup = new PopupMenu(wrapper, holder.optionsMenuLikes);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.profile_likes_options_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.placeOrEventOrReviewLikeView:
                                    //handle menu1 click
                                    if (reviewType.equalsIgnoreCase(Constants.STRING_TYPE_PLACE)) {
                                        Intent myIntent = new Intent(context, ExpandDetailsMapsActivity.class);
                                        myIntent.putExtra("PLACE_ID", placeOrEventIdReview);
                                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(myIntent);
                                    }
                                    else if (reviewType.equalsIgnoreCase(Constants.STRING_TYPE_EVENT))
                                    {
                                        Intent myIntent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                                        myIntent.putExtra("EVENT_ID", placeOrEventIdReview);
                                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(myIntent);
                                    }

                                    break;
                                case R.id.likeUnlike:
                                    //handle menu2 click
                                    alertDialog = new AlertDialog.Builder(context);
                                    alertDialog.setTitle("Delete");
                                    TextView myMsg = new TextView(context);
                                    myMsg.setText(" \n\n Are You Sure You Want to Remove This from Your Likes?");
                                    myMsg.setPadding(5, 5, 5, 5);
                                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                                    alertDialog.setIcon(R.drawable.ic_delete_alert);
                                    alertDialog.setView(myMsg);

                                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int which) {
                                            deleteLike(like_id);
                                            swap(position);

                                        }
                                    });

                                    alertDialog.setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });

                                    final AlertDialog dialog = alertDialog.create();
                                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                                    //if(isFinishing())
                                    dialog.show();

                                    break;
                            }
                            return false;
                        }
                    });
                    //displaying the popup
                    popup.show();

                }
            });
        }












    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public RelativeLayout placeoreventlikelayout;
        public ImageView likeLogo;
        public TextView LikeTimeStamp;
        public TextView optionsMenuLikes;
        public ImageView PlaceOrEventPicture;
        public TextView PlaceOrEventName;
        public TextView PlaceOrEventCategory;


        public RelativeLayout reviewlikelayout;
        public ImageView Likelogoreviews;
        public TextView LikeTimeStampreviews;
        public TextView optionsMenuLikesrevies;
        public ImageView userPic;
        public TextView username;
        public TextView reviewtext;
        public RatingBar reviewrating;











        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);

            //place Or Event layout
            placeoreventlikelayout = (RelativeLayout) itemView.findViewById(R.id.like_place_or_event_layout);
            likeLogo =(ImageView)itemView.findViewById(R.id.likes_likesLogo);
            LikeTimeStamp = (TextView) itemView.findViewById(R.id.likes_timestamp);
            optionsMenuLikes = (TextView) itemView.findViewById(R.id.likes_menuOptions);
            PlaceOrEventPicture = (ImageView) itemView.findViewById(R.id.likes_placeOrEventPicture);
            PlaceOrEventName = (TextView) itemView.findViewById(R.id.likes_placeorEventName);
            PlaceOrEventCategory = (TextView) itemView.findViewById(R.id.likes_placeorEventCategory);



            //review layout
            reviewlikelayout = (RelativeLayout) itemView.findViewById(R.id.review_like_layout);
            Likelogoreviews =(ImageView)itemView.findViewById(R.id.likes_likesLogo_review);
            LikeTimeStampreviews = (TextView) itemView.findViewById(R.id.likes_timestamp_review);
            optionsMenuLikesrevies = (TextView) itemView.findViewById(R.id.likes__reeview_menuOptions);
            userPic = (ImageView) itemView.findViewById(R.id.imageViewUserWrite);
            username = (TextView) itemView.findViewById(R.id.textNameUserWroteReview);
            reviewtext = (TextView) itemView.findViewById(R.id.textViewUserWhatIsWrite);
            reviewrating = (RatingBar)itemView.findViewById(R.id.all_reviews_singleReview_rating);





        }
    }




    public void swap(int position){

        listItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItems.size());
        notifyDataSetChanged();

    }


    public void deleteLike(int likeId) {
        final int likeID = likeId;

        StringRequest send = new StringRequest(Request.Method.DELETE,
                Constants.URL_DELETE_USER_LIKE + likeID,
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
                Toast.makeText(
                        context, error.getMessage(), Toast.LENGTH_LONG).show();
            }}) {};
        MySingleton.getInstance(context).addToRequestQueue(send);

    }

}
