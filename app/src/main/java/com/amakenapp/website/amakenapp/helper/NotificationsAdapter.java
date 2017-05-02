package com.amakenapp.website.amakenapp.helper;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
import com.amakenapp.website.amakenapp.activities.UserDetailsActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by USER on 3/10/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<NotificationsListItem> listItems;
    private Context context;

    private int notiId, generatorId, targetId, placeId, eventId, reviewId, reportId, likeid, bookmarkId;
    String notiTime, notiMessage, notiUserProfile, notiType;

    public NotificationsAdapter(List<NotificationsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
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

        notiId=listItem.getNotification_id();
        notiType = listItem.getNotiType();
        notiMessage = listItem.getNotification_message();
        notiTime = listItem.getNotificationTimeStamp();

        generatorId = listItem.getGeneratorId();
        targetId = listItem.getTargetUserId();
        placeId=listItem.getPlaceId();
        eventId=listItem.getEventId();
        reviewId=listItem.getReviewId();
        reportId=listItem.getReoprtId();
        likeid=listItem.getLikeId();
        bookmarkId=listItem.getBookmarkId();

        notiUserProfile = listItem.getNotification_user_profile_pic();






        holder.notificationTimeStamp.setText(listItem.getNotificationTimeStamp());

        if (notiUserProfile.equals("no profile pic"))
        holder.notification_user_ProfilePic.setImageResource(R.drawable.ic_person);
        else
        Glide.with(context).load(listItem.getNotification_user_profile_pic()).diskCacheStrategy( DiskCacheStrategy.ALL ).skipMemoryCache( true ).into(holder.notification_user_ProfilePic);

        holder.notificationMessage.setText(listItem.getNotification_message());

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
            public void onClick(View v) {
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
                                showToast("action view");


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

}
