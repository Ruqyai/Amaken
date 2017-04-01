package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Muha on 3/8/2017.
 */

public class BusinessProfileActivity extends Fragment implements View.OnClickListener{
    private CardView
            businessPlacesCard ,
            businessEventsCard ,
            businessBookmarksCard,
            businessLikesCard,
            businessReviewsCard,
            businessCategoriesCard;
    private CircleImageView businessProfilePic;
    private TextView changeProfilePicTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_business_profile, container, false);

        businessPlacesCard = (CardView) myView.findViewById(R.id.business_profile_placeCard);
        businessEventsCard = (CardView) myView.findViewById(R.id.business_profile_eventsCard);
        businessBookmarksCard = (CardView) myView.findViewById(R.id.business_profile_bookmarksCard);
        businessLikesCard = (CardView) myView.findViewById(R.id.business_profile_likesCard);
        businessReviewsCard = (CardView) myView.findViewById(R.id.business_profile_reviewsCard);
        businessCategoriesCard = (CardView) myView.findViewById(R.id.business_profile_categoriesCard);
        businessProfilePic = (CircleImageView) myView.findViewById(R.id.change_business_profile_pic);
        changeProfilePicTxt = (TextView) myView.findViewById(R.id.businessProfile_changeProfilePicTxt);



        businessPlacesCard.setOnClickListener(this);
        businessEventsCard.setOnClickListener(this);
        businessBookmarksCard.setOnClickListener(this);
        businessLikesCard.setOnClickListener(this);
        businessReviewsCard.setOnClickListener(this);
        businessCategoriesCard.setOnClickListener(this);
        businessProfilePic.setOnClickListener(this);
        changeProfilePicTxt.setOnClickListener(this);

        return myView;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Profile");
    }

    @Override
    public void onClick(View v) {

        if (v == businessProfilePic){
            //// TODO: 3/12/2017  dialog with 2 buttons one for camera and the other for gallery
            Toast.makeText(getActivity(), "This is profile pic  clicked", Toast.LENGTH_LONG).show();
        }

        if (v == changeProfilePicTxt){
            //// TODO: 3/12/2017  same as profile pic clicked action
            Toast.makeText(getActivity(), "This is change profile pic text clicked", Toast.LENGTH_LONG).show();
        }
        if (v == businessPlacesCard){
            startActivity(new Intent(getActivity(), BusinessProfilePlaces.class));
        }
        if (v == businessEventsCard){
            startActivity(new Intent(getActivity(), BusinessProfileEvents.class));
        }
        if (v == businessBookmarksCard){
            startActivity(new Intent(getActivity(), ProfileBookmarks.class));
        }
        if (v == businessLikesCard){
            startActivity(new Intent(getActivity(), ProfileLikes.class));
        }
        if (v == businessReviewsCard){
            startActivity(new Intent(getActivity(), ProfileReviews.class));
        }

        if (v == businessCategoriesCard){
            startActivity(new Intent(getActivity(), ProfileCategories.class));
        }
    }

}
