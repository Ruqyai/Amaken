package com.amakenapp.website.amakenapp.helper;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
import com.amakenapp.website.amakenapp.activities.UserDetailsActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by USER on 3/10/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<NotificationsListItem> listItems;
    private Context context;
    LayoutInflater inflater;


    private int notiId, generatorId, targetId, placeId, eventId, reviewId, reportId, likeid, bookmarkId;
    String notiTime, notiMessage, notiUserProfile, notiType;


    //review and report info
    String reviewtime, reviewuser, reviewtext, reviewpic, reviewlikes, reporttime, reporttext, reportpic, reportusername;
    Float reviewrate;
    RatingBar  rate;

    private ImageView userreview, userreport;
    private TextView reviewusername, timereview, textreview, likesreview, reportusername2, timereport, textreport;

    public NotificationsAdapter(List<NotificationsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notifications_list_item, parent, false);
        return new NotificationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.ViewHolder holder, final int position) {

        NotificationsListItem listItem = listItems.get(position);

        final int notiId=listItem.getNotification_id();
        final String notiType = listItem.getNotiType();
        final String notiMessage = listItem.getNotification_message();
        final String notiTime = listItem.getNotificationTimeStamp();
        reporttime = notiTime;

        final int generatorId = listItem.getGeneratorId();
        final int  targetId = listItem.getTargetUserId();
        final int placeId=listItem.getPlaceId();
        final int eventId=listItem.getEventId();
        final int  reviewId=listItem.getReviewId();
        final int reportId=listItem.getReoprtId();
        final int likeid=listItem.getLikeId();
        final int bookmarkId=listItem.getBookmarkId();

        final String notiUserProfile = listItem.getNotification_user_profile_pic();






        holder.notificationTimeStamp.setText(notiTime);

        if (notiUserProfile.equals("no profile pic"))
        holder.notification_user_ProfilePic.setImageResource(R.drawable.ic_person);
        else
        Glide.with(context).load(notiUserProfile).diskCacheStrategy( DiskCacheStrategy.NONE ).skipMemoryCache( true ).into(holder.notification_user_ProfilePic);

        holder.notificationMessage.setText(notiMessage);

        holder.notification_user_ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("USER_ID", generatorId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //creating a popup menu
                final Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popup = new PopupMenu(wrapper, holder.menu);
                //inflating menu from xml resource
                popup.inflate(R.menu.noti_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.noti_view:
                                //handle menu1 click

                                if (notiType.equals("place add") || notiType.equals("place review") || notiType.equals("place like") || notiType.equals("place bookmark") || notiType.equals("like review on place"))
                                {
                                    Intent intent = new Intent(context, ExpandDetailsMapsActivity.class);
                                    intent.putExtra("PLACE_ID", placeId);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                                else  if(notiType.equals("event add") || notiType.equals("event review") || notiType.equals("event like") || notiType.equals("event bookmark") || notiType.equals("like review on event"))
                                {
                                    Intent intent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                                    intent.putExtra("EVENT_ID", eventId);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                                else if (notiType.equals("report review"))
                                {
                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getRootView().getContext());
                                    alertDialog.setTitle("Take and Action");
                                    final View view1 = inflater.inflate(R.layout.take_action_on_review, null);
                                    userreview = (ImageView) view1.findViewById(R.id.imageViewUserWrite1);
                                     reviewusername = (TextView) view1.findViewById(R.id.textNameUserWroteReview1);
                                     timereview = (TextView) view1.findViewById(R.id.all_reviews_timestamp1);
                                     textreview = (TextView) view1.findViewById(R.id.textViewUserWhatIsWrite1);
                                    likesreview = (TextView) view1.findViewById(R.id.all_reviews_reviewLikeStat);
                                     rate = (RatingBar) view1.findViewById(R.id.all_reviews_singleReview_rating1);


                                     userreport = (ImageView) view1.findViewById(R.id.imageViewUserWrite);
                                     reportusername2 = (TextView) view1.findViewById(R.id.textNameUserWroteReview);
                                     timereport = (TextView) view1.findViewById(R.id.all_reviews_timestamp);
                                     textreport = (TextView) view1.findViewById(R.id.textViewUserWhatIsWrite);

                                    getReviewAndReportDetails(reviewId, reportId);

                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
                                    view1.setLayoutParams(lp);
                                    alertDialog.setView(view1);

                                    alertDialog.setPositiveButton("Delete Review", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int which) {
                                            deleteReview(reviewId);
                                            swap(position);

                                        }
                                    });



                                    alertDialog.setNegativeButton("Ignore Report",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });

                                    final AlertDialog dialog = alertDialog.create();
                                    dialog.show();


                                }


                                    break;
                            case R.id.noti_delete:
                                //handle menu2 click

                                deleteNotification(notiId);
                                swap(position);


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

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{



        public TextView notificationTimeStamp;
        public ImageView notification_user_ProfilePic;
        public TextView notificationMessage;
        public TextView notification_id_hidden;
        public TextView menu;

        public CardView notificationCard;







        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);

            notificationTimeStamp = (TextView) itemView.findViewById(R.id.notifications_timestamp);
            notification_user_ProfilePic =(ImageView)itemView.findViewById(R.id.notifications_user_profile_pic);
            notificationMessage = (TextView) itemView.findViewById(R.id.notifications_message);
            menu = (TextView) itemView.findViewById(R.id.menu);



            //May not be needed
            //notification_id_hidden= (TextView) itemView.findViewById(R.id.notification_id_hidden);


            // to activate the click on the card
            //notificationCard = (CardView) itemView.findViewById(R.id.notification_card_id);



        }
    }

    private void showToast(String meg){
        final String message = meg;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public void swap(int position){

        listItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItems.size());
        notifyDataSetChanged();

    }

    public void deleteNotification(int notificationID) {
        final int notiID= notificationID;
        StringRequest send = new StringRequest(Request.Method.DELETE,
                Constants.URL_DELETE_NOTIFICATION+ notiID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show();
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

        };

        MySingleton.getInstance(context).addToRequestQueue(send);

    }



    public void getReviewAndReportDetails(int reviewId, int  reportId) {
        final int revie_id= reviewId;
        final int report_id = reportId;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_Report_Info ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                reviewuser = obj.getString("review_user_name");
                                reviewtime = obj.getString("review_timeStamp");
                                reviewtext = obj.getString("review_review_text");
                                reviewpic = obj.getString("review_user_photo");

                                int likesNum = obj.getInt("review_likes_number");
                                reviewlikes = Integer.toString(likesNum);

                                Double rate2 = obj.getDouble("review_rate_value");
                                String rate3 = Double.toString(rate2);
                                reviewrate = Float.parseFloat(rate3);

                                reportusername = obj.getString("report_user_name");
                                reportpic = obj.getString("report_user_photo");
                                reporttext = obj.getString("report_review_text");


                                Glide.with(context).load(reviewpic).diskCacheStrategy( DiskCacheStrategy.NONE ).skipMemoryCache( true ).into(userreview);
                                reviewusername.setText(reviewuser);
                                timereview.setText(reviewtime);
                                textreview.setText(reviewtext);
                                likesreview.setText(reviewlikes);
                                rate.setRating(reviewrate);


                                Glide.with(context).load(reportpic).diskCacheStrategy( DiskCacheStrategy.NONE ).skipMemoryCache( true ).into(userreport);
                                timereport.setText(reporttime);
                                reportusername2.setText(reportusername);
                                textreport.setText(reporttext);
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show();
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
                params.put("review_id", revie_id+"");
                params.put("report_id", report_id+"");
                return params;
            }

        };

        MySingleton.getInstance(context).addToRequestQueue(send);

    }


    public void deleteReview(int reviewID3) {
        final int reviewID2= reviewID3;
        StringRequest send = new StringRequest(Request.Method.DELETE,
                Constants.URL_DELETE_REVIEW+ reviewID2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show();
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

        };

        MySingleton.getInstance(context).addToRequestQueue(send);

    }
}
