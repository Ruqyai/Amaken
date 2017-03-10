package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.afaf.amakenapp.BusinessProfileEvents;
import com.example.afaf.amakenapp.R;

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



        businessPlacesCard.setOnClickListener(this);
        businessEventsCard.setOnClickListener(this);
        businessBookmarksCard.setOnClickListener(this);
        businessLikesCard.setOnClickListener(this);
        businessReviewsCard.setOnClickListener(this);
        businessCategoriesCard.setOnClickListener(this);
        businessProfilePic.setOnClickListener(this);

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
            Toast.makeText(getActivity(), "This is profile pic  card clicked", Toast.LENGTH_LONG).show();
        }
        if (v == businessPlacesCard){
            startActivity(new Intent(getActivity(), BusinessProfilePlaces.class));
        }
        if (v == businessEventsCard){
           startActivity(new Intent(getActivity(), BusinessProfileEvents.class));
        }
        if (v == businessBookmarksCard){
            Toast.makeText(getActivity(), "This is bookmarks card clicked", Toast.LENGTH_LONG).show();
            //startActivity(new Intent(getActivity(), ProfileBookmarks.class));
        }
        if (v == businessLikesCard){
            Toast.makeText(getActivity(), "This is likes card clicked", Toast.LENGTH_LONG).show();
            //startActivity(new Intent(getActivity(), ProfileLikes.class));
        }
        if (v == businessReviewsCard){
            Toast.makeText(getActivity(), "This is reviews card clicked", Toast.LENGTH_LONG).show();
            // startActivity(new Intent(getActivity(), ProfileReviews.class));
        }

        if (v == businessCategoriesCard){
            Toast.makeText(getActivity(), "This is categories card clicked", Toast.LENGTH_LONG).show();

            //startActivity(new Intent(getActivity(), ProfileCategories.class));
        }
    }

}
