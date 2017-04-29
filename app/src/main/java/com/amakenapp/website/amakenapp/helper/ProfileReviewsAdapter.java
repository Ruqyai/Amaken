package com.amakenapp.website.amakenapp.helper;


import android.content.Context;
import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.activities.AddReview;
import com.amakenapp.website.amakenapp.activities.EditReviewActivity;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.siyamed.shapeimageview.RoundedImageView;

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
        final String reviewType = listItem.getReviewType();
        final int reviewId = listItem.getReviewId();
        final int place_or_event_id = listItem.getEventOrPlaceId();

        holder.yourReview.setText(listItem.getYourReviewText());
        holder.likeLogo.setImageResource(listItem.getReviewLikeImage());

        holder.reviewTimeStamp.setText(listItem.getReviewTimestamp());
        Glide.with(context).load(listItem.getPlaceoreventPic())
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .skipMemoryCache( true )
                .into(holder.placeorEventPic);
        holder.placeOrEventName.setText(listItem.getPlaceoreventName());
        holder.placeOrEventCategory.setText(listItem.getPlaceoreventCategory());

        holder.reviewText.setText(listItem.getReviewText());
        holder.reviewLikesStat.setText(listItem.getReviewLikesNumber());
        holder.reviewRating.setRating(listItem.getReviewRatingValue());


        holder.optionsMenuReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                final Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popup = new PopupMenu(wrapper, holder.optionsMenuReviews);
                //inflating menu from xml resource
                popup.inflate(R.menu.profile_reviews_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.placeOrEventOrReviewReviewView:
                                //handle menu1 click
                                if (reviewType.equalsIgnoreCase(Constants.STRING_TYPE_PLACE))
                                {
                                    Intent myIntent = new Intent(context, ExpandDetailsMapsActivity.class);
                                    myIntent.putExtra("PLACE_ID", place_or_event_id);
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(myIntent);
                                }
                                else if (reviewType.equalsIgnoreCase(Constants.STRING_TYPE_EVENT))
                                {
                                    Intent myIntent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                                    myIntent.putExtra("EVENT_ID", place_or_event_id);
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(myIntent);
                                }
                                break;
                            case R.id.review_edit:
                                //handle menu2 click
                                Intent myIntent = new Intent(context, EditReviewActivity.class);
                                myIntent.putExtra("REVIEW_ID", reviewId);
                                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(myIntent);
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

        public ImageView placeorEventPic;
        public TextView placeOrEventName;
        public TextView placeOrEventCategory;


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


            placeorEventPic =(ImageView) itemView.findViewById(R.id.reviews_profile_pic);
            placeOrEventName= (TextView) itemView.findViewById(R.id.review_place_or_event_name);
            placeOrEventCategory= (TextView) itemView.findViewById(R.id.review_place_or_event_category);

            reviewText = (TextView) itemView.findViewById(R.id.text_review);
            likeLogo =(ImageView)itemView.findViewById(R.id.reviews_like_logo);
            reviewLikesStat= (TextView) itemView.findViewById(R.id.reviews_likes_stat);
            reviewRating =(RatingBar)itemView.findViewById(R.id.review_Rating_avg);



            optionsMenuReviews = (TextView) itemView.findViewById(R.id.reviews_menuOptions);


        }
    }



}
