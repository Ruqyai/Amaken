package com.example.afaf.amakenapp.helper;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.afaf.amakenapp.R;

import java.util.List;


/**
 * Created by USER on 3/10/2017.
 */

public class ProfileReviewsAdapter extends RecyclerView.Adapter<ProfileReviewsAdapter.ViewHolder> {
    private List<ProfileReviewsListItem> listItems;
    private Context context;

    public ProfileReviewsAdapter(List<ProfileReviewsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ProfileReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_reviews_list_item, parent, false);
        return new ProfileReviewsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProfileReviewsAdapter.ViewHolder holder, int position) {

        ProfileReviewsListItem listItem = listItems.get(position);




        holder.yourReview.setText(listItem.getYourReviewText());
        holder.reviewTimeStamp.setText(listItem.getReviewTimeStamp());
        Glide.with(context).load(listItem.getProfile_pic()).into(holder.reviewProfilePic);
        holder.reviewText.setText(listItem.getReviewText());
        Glide.with(context).load(listItem.getLikesLogo()).into(holder.likeLogo);
        holder.reviewLikesStat.setText(listItem.getLikesStat());
        holder.reviewRating.setRating(listItem.getRatingbarLogo());




        holder.optionsMenuReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.optionsMenuReviews);
                //inflating menu from xml resource
                popup.inflate(R.menu.profile_reviews_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.placeOrEventOrReviewReviewView:
                                //handle menu1 click
                                break;
                            case R.id.review_edit:
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



        public TextView yourReview;
        public TextView reviewTimeStamp;

        public ImageView reviewProfilePic;
        public TextView reviewText;
        public ImageView likeLogo;
        public TextView reviewLikesStat;
        public RatingBar reviewRating;

        public TextView optionsMenuReviews;







        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);

            yourReview = (TextView) itemView.findViewById(R.id.textView10);
            reviewTimeStamp= (TextView) itemView.findViewById(R.id.review_timestamp);


            reviewProfilePic =(ImageView)itemView.findViewById(R.id.reviews_profile_pic);
            reviewText = (TextView) itemView.findViewById(R.id.text_review);
            likeLogo =(ImageView)itemView.findViewById(R.id.reviews_like_logo);
            reviewLikesStat= (TextView) itemView.findViewById(R.id.reviews_likes_stat);
            reviewRating =(RatingBar)itemView.findViewById(R.id.review_Rating_avg);



            optionsMenuReviews = (TextView) itemView.findViewById(R.id.reviews_menuOptions);


        }
    }



}
