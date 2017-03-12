package com.example.afaf.amakenapp.helper;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.afaf.amakenapp.R;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.List;


/**
 * Created by USER on 3/10/2017.
 */

public class ProfileBookmarkAdapter extends RecyclerView.Adapter<ProfileBookmarkAdapter.ViewHolder> {
    private List<ProfileBookmarkListItem> listItems;
    private Context context;

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
    public void onBindViewHolder(final ProfileBookmarkAdapter.ViewHolder holder, int position) {

        ProfileBookmarkListItem listItem = listItems.get(position);

        Glide.with(context).load(listItem.getPlaceOrEventPic()).into(holder.PlaceOrEventPicture);
        //holder.PlaceOrEventPicture.setImageResource(listItem.getBusinessProfilePlaceOrEventPic());

        holder.PlaceOrEventName.setText(listItem.getPlaceOrEventName());
        holder.PlaceOrEventCategory.setText(listItem.getPlaceOrEventCategory());
        holder.BookmarkTimeStamp.setText(listItem.getBookmarkTimeStamp());



        Glide.with(context).load(listItem.getBookmarkLogo()).into(holder.bookmarkLogo);


        holder.optionsMenuBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.optionsMenuBookmarks);
                //inflating menu from xml resource
                popup.inflate(R.menu.profile_bookmarks_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.placeOrEventView:
                                //handle menu1 click
                                break;
                            case R.id.bookmarkRemove:
                                //handle menu2 click
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

        public ImageView PlaceOrEventPicture;
        public TextView PlaceOrEventName;
        public TextView PlaceOrEventCategory;
        public ImageView bookmarkLogo;
        public TextView optionsMenuBookmarks;
        public TextView BookmarkTimeStamp;







        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);
            PlaceOrEventPicture = (ImageView) itemView.findViewById(R.id.placeOrEventPictureBookmarks);
            PlaceOrEventName = (TextView) itemView.findViewById(R.id.placeorEventNameBookmarks);
            PlaceOrEventCategory = (TextView) itemView.findViewById(R.id.placeorEventCategoryBookmarks);
            bookmarkLogo =(ImageView)itemView.findViewById(R.id.bookmarkLogoBookmarks);
            BookmarkTimeStamp = (TextView) itemView.findViewById(R.id.timeStampBookmarks);



            optionsMenuBookmarks = (TextView) itemView.findViewById(R.id.menuOptionsBookmarks);


        }
    }



}
