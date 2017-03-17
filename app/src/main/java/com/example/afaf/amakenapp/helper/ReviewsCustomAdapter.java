package com.example.afaf.amakenapp.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.afaf.amakenapp.R;

import java.util.List;

/**
 * Created by Muha on 3/16/2017.
 */

public class ReviewsCustomAdapter extends BaseAdapter{

     List<ExpandReviewDetailsListItem> listItems;
     Context context;
     LayoutInflater inflater;

    public ReviewsCustomAdapter(List<ExpandReviewDetailsListItem> listItems,
                                Context context) {
        this.listItems = listItems;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view  = inflater.inflate(R.layout.item_users_reviews_exapand_detailes, null);

        TextView reviewUserName;
        TextView reviewTimestamp;
        ImageView reviewUserProfilePic;
        TextView reviewText;
        RatingBar reviewRatingValue;
         ImageView reviewLikeImage;
         TextView reviewLikesNumber;
         ImageView reviewFalgImage;


            reviewUserName = (TextView) view.findViewById(R.id.textNameUserWroteReview);
            reviewTimestamp = (TextView) view.findViewById(R.id.all_reviews_timestamp);
            reviewUserProfilePic =(ImageView) view.findViewById(R.id.imageViewUserWrite);


            reviewText = (TextView) view.findViewById(R.id.textViewUserWhatIsWrite);
            reviewRatingValue = (RatingBar) view.findViewById(R.id.all_reviews_singleReview_rating);
            reviewLikeImage =(ImageView) view.findViewById(R.id.all_reviews_likeLogo);
            reviewLikesNumber = (TextView) view.findViewById(R.id.all_reviews_reviewLikeStat) ;

            reviewFalgImage =(ImageView) view.findViewById(R.id.all_reviewReportFlag);


        ExpandReviewDetailsListItem listItem = listItems.get(position);

        reviewUserName.setText(listItem.getReviewUserName());
        reviewTimestamp.setText(listItem.getReviewTimestamp());
        reviewUserProfilePic.setImageResource(listItem.getReviewUserProfilePic());
        reviewText.setText(listItem.getReviewText());
        reviewRatingValue.setRating(listItem.getReviewRatingValue());
        reviewLikeImage.setImageResource(listItem.getReviewLikeImage());
        reviewLikesNumber.setText(listItem.getReviewLikesNumber());
        reviewFalgImage.setImageResource(listItem.getReviewFlagImage());
       return view;
    }




}