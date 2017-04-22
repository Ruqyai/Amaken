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

public class ProfileBookmarkAdapter extends RecyclerView.Adapter<ProfileBookmarkAdapter.ViewHolder> {
    private List<ProfileBookmarkListItem> listItems;
    private Context context;
    private AlertDialog.Builder alertDialog;


    public ProfileBookmarkAdapter(List<ProfileBookmarkListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ProfileBookmarkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_bookmarks_list_item, parent, false);
        return new ProfileBookmarkAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProfileBookmarkAdapter.ViewHolder holder, final int position) {

        ProfileBookmarkListItem listItem = listItems.get(position);

        final int bookmarkId = listItem.getBoomarkId();
        final int bookmark_place_or_event_id= listItem.getPlaceOrEventId();
        final String bookmarkType = listItem.getBookmarkType();


        Glide.with(context).load(listItem.getPlaceOrEventPic()).into(holder.PlaceOrEventPicture);
        //holder.PlaceOrEventPicture.setImageResource(listItem.getBusinessProfilePlaceOrEventPic());

        holder.PlaceOrEventName.setText(listItem.getPlaceOrEventName());
        holder.PlaceOrEventCategory.setText(listItem.getPlaceOrEventCategory());
        holder.BookmarkTimeStamp.setText(listItem.getBookmarkTimeStamp());





        holder.bookmarkLogo.setImageResource(listItem.getBookmarkLogo());
        holder.optionsMenuBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                final Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popup = new PopupMenu(wrapper, holder.optionsMenuBookmarks);
                //inflating menu from xml resource
                popup.inflate(R.menu.profile_bookmarks_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.placeOrEventView:
                                //handle menu1 click
                                if (bookmarkType.equalsIgnoreCase(Constants.STRING_TYPE_PLACE))
                                {
                                    Intent myIntent = new Intent(context, ExpandDetailsMapsActivity.class);
                                    myIntent.putExtra("PLACE_ID", bookmark_place_or_event_id);
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(myIntent);
                                }
                                else if (bookmarkType.equalsIgnoreCase(Constants.STRING_TYPE_EVENT))
                                {
                                    Intent myIntent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                                    myIntent.putExtra("EVENT_ID", bookmark_place_or_event_id);
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(myIntent);
                                }
                                break;
                            case R.id.bookmarkRemove:
                                //handle menu2 click

                                    alertDialog = new AlertDialog.Builder(context);
                                    alertDialog.setTitle("Delete");
                                    TextView myMsg = new TextView(context);
                                    myMsg.setText(" \n\n Are You Sure You Want to Remove This from Your Bookmarks?");
                                    myMsg.setPadding(5, 5, 5, 5);
                                    myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                                    alertDialog.setIcon(R.drawable.ic_delete_alert);
                                    alertDialog.setView(myMsg);

                                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int which) {
                                            deletebookmark(bookmarkId);
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

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView bookmarkId;
        public TextView bookmarktype;
        public TextView PlaceOrEventId;



        public ImageView PlaceOrEventPicture;
        public TextView PlaceOrEventName;
        public TextView PlaceOrEventCategory;
        public ImageView bookmarkLogo;
        public TextView optionsMenuBookmarks;
        public TextView BookmarkTimeStamp;







        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);
            bookmarkId = (TextView) itemView.findViewById(R.id.bookmarks_id);
            bookmarktype = (TextView) itemView.findViewById(R.id.bookmarks_type);
            PlaceOrEventId = (TextView) itemView.findViewById(R.id.bookmarks_place_or_event_id);

            PlaceOrEventPicture = (ImageView) itemView.findViewById(R.id.bookmarks_placeOrEventPicture);
            PlaceOrEventName = (TextView) itemView.findViewById(R.id.bookmarks_placeorEventName);
            PlaceOrEventCategory = (TextView) itemView.findViewById(R.id.bookmarks_placeorEventCategory);
            bookmarkLogo =(ImageView)itemView.findViewById(R.id.bookmarks_bookmarkLogo);
            BookmarkTimeStamp = (TextView) itemView.findViewById(R.id.bookmarks_timestamp);



            optionsMenuBookmarks = (TextView) itemView.findViewById(R.id.bookmarks_menuOptions);


        }
    }


    public void swap(int position){

        listItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItems.size());
        notifyDataSetChanged();

    }


    public void deletebookmark(int bookmarkId) {
        final int bookmarkID = bookmarkId;

        StringRequest send = new StringRequest(Request.Method.DELETE,
                Constants.URL_DELETE_USER_BOOKMARK + bookmarkID,
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
