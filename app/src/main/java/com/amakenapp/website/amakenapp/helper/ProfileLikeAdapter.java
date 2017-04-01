package com.amakenapp.website.amakenapp.helper;


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
import com.amakenapp.website.amakenapp.R;

import java.util.List;


/**
 * Created by USER on 3/10/2017.
 */

public class ProfileLikeAdapter extends RecyclerView.Adapter<ProfileLikeAdapter.ViewHolder> {
    private List<ProfileLikesListItem> listItems;
    private Context context;

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
    public void onBindViewHolder(final ProfileLikeAdapter.ViewHolder holder, int position) {

        ProfileLikesListItem listItem = listItems.get(position);

        Glide.with(context).load(listItem.getPlaceOrEventPic()).into(holder.PlaceOrEventPicture);
        //holder.PlaceOrEventPicture.setImageResource(listItem.getBusinessProfilePlaceOrEventPic());

        holder.PlaceOrEventName.setText(listItem.getPlaceOrEventName());
        holder.PlaceOrEventCategory.setText(listItem.getPlaceOrEventCategory());
        holder.LikeTimeStamp.setText(listItem.getLikeTimeStamp());



        Glide.with(context).load(listItem.getLikeLogo()).into(holder.likeLogo);


        holder.optionsMenuLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.optionsMenuLikes);
                //inflating menu from xml resource
                popup.inflate(R.menu.profile_likes_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.placeOrEventOrReviewLikeView:
                                //handle menu1 click
                                break;
                            case R.id.likeUnlike:
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
        public ImageView likeLogo;
        public TextView optionsMenuLikes;
        public TextView LikeTimeStamp;







        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);
            PlaceOrEventPicture = (ImageView) itemView.findViewById(R.id.likes_placeOrEventPicture);
            PlaceOrEventName = (TextView) itemView.findViewById(R.id.likes_placeorEventName);
            PlaceOrEventCategory = (TextView) itemView.findViewById(R.id.likes_placeorEventCategory);
            likeLogo =(ImageView)itemView.findViewById(R.id.likes_likesLogo);
            LikeTimeStamp = (TextView) itemView.findViewById(R.id.likes_timestamp);



            optionsMenuLikes = (TextView) itemView.findViewById(R.id.likes_menuOptions);


        }
    }



}
