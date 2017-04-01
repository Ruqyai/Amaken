package com.amakenapp.website.amakenapp.helper;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.*;

import java.util.List;

/**
 * Created by User on 3/12/2017.
 */

public class ExpandReviewDetailesAdapter extends RecyclerView.Adapter<ExpandReviewDetailesAdapter.ViewHolder> {

    private List<ExpandReviewDetailsListItem> listItems;
    private Context context;

    public ExpandReviewDetailesAdapter(List<ExpandReviewDetailsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

    }

    /*
        public ExpandReviewDetailesAdapter(List<ExpandReviewDetailsListItem> listItems, Context context) {
            this.listItems = listItems;
            this.context = context;
        }
    **/
    @Override
    public ExpandReviewDetailesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users_reviews_exapand_detailes, parent, false);
        return new ExpandReviewDetailesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExpandReviewDetailesAdapter.ViewHolder holder, int position) {
        ExpandReviewDetailsListItem listItem = listItems.get(position);

        holder.reviewUserName.setText(listItem.getReviewUserName());
        holder.reviewTimestamp.setText(listItem.getReviewTimestamp());
        holder.reviewUserProfilePic.setImageResource(listItem.getReviewUserProfilePic());
        holder.reviewText.setText(listItem.getReviewText());
        holder.reviewRatingValue.setRating(listItem.getReviewRatingValue());
        holder.reviewLikeImage.setImageResource(listItem.getReviewLikeImage());
        holder.reviewLikesNumber.setText(listItem.getReviewLikesNumber());
        holder.reviewFalgImage.setImageResource(listItem.getReviewFlagImage());


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView reviewUserName;
        public TextView reviewTimestamp;
        public ImageView reviewUserProfilePic;
        public TextView reviewText;
        public RatingBar reviewRatingValue;
        public ImageView reviewLikeImage;
        public TextView reviewLikesNumber;
        public ImageView reviewFalgImage;

        public ViewHolder(View itemView) {
            super(itemView);

            reviewUserName = (TextView) itemView.findViewById(R.id.textNameUserWroteReview);
            reviewTimestamp = (TextView) itemView.findViewById(R.id.all_reviews_timestamp);
            reviewUserProfilePic =(ImageView) itemView.findViewById(R.id.imageViewUserWrite);


            reviewText = (TextView) itemView.findViewById(R.id.textViewUserWhatIsWrite);
            reviewRatingValue = (RatingBar) itemView.findViewById(R.id.all_reviews_singleReview_rating);
            reviewLikeImage =(ImageView) itemView.findViewById(R.id.all_reviews_likeLogo);
            reviewLikesNumber = (TextView) itemView.findViewById(R.id.all_reviews_reviewLikeStat) ;

            reviewFalgImage =(ImageView) itemView.findViewById(R.id.all_reviewReportFlag);


        }

    }
}
