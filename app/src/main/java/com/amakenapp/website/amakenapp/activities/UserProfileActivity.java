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
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Muha on 3/8/2017.
 */

public class UserProfileActivity extends Fragment implements View.OnClickListener{
    private CardView
            userBookmarksCard,
            userLikesCard,
            userReviewsCard,
            userCategoriesCard;
    private CircleImageView userProfilePic;
    private TextView changeProfilePicTxt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_user_profile, container, false);


        userBookmarksCard = (CardView) myView.findViewById(R.id.user_profile_bookmarksCard);
        userLikesCard = (CardView) myView.findViewById(R.id.user_profile_likesCard);
        userReviewsCard = (CardView) myView.findViewById(R.id.user_profile_reviewsCard);
        userCategoriesCard = (CardView) myView.findViewById(R.id.user_profile_categoriesCard);
        userProfilePic = (CircleImageView) myView.findViewById(R.id.user_profile_pic);
        changeProfilePicTxt = (TextView) myView.findViewById(R.id.userProfile_changeProfilePicTxt);

        SharedPrefManager sharedPrefManager=SharedPrefManager.getInstance(getContext());
        String x=sharedPrefManager.getUsername();
        String y=sharedPrefManager.getKeyUserProfilePicUrl();
        changeProfilePicTxt.setText(x);
        Picasso.with(getApplicationContext()).load(y).into(userProfilePic);


        userBookmarksCard.setOnClickListener(this);
        userLikesCard.setOnClickListener(this);
        userReviewsCard.setOnClickListener(this);
        userCategoriesCard.setOnClickListener(this);
        userProfilePic.setOnClickListener(this);
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

        if (v == userProfilePic){
            //// TODO: 3/12/2017  dialog with 2 buttons one for camera and the other for gallery
            Toast.makeText(getActivity(), "This is profile pic  clicked", Toast.LENGTH_LONG).show();
        }
        if (v == changeProfilePicTxt){
            //// TODO: 3/12/2017  same as profile pic clicked action
            Toast.makeText(getActivity(), "This is change profile pic text clicked", Toast.LENGTH_LONG).show();
        }

        if (v == userBookmarksCard){
            startActivity(new Intent(getActivity(), ProfileBookmarks.class));
        }
        if (v == userLikesCard){
            startActivity(new Intent(getActivity(), ProfileLikes.class));
        }
        if (v == userReviewsCard){
            startActivity(new Intent(getActivity(), ProfileReviews.class));
        }

        if (v == userCategoriesCard){
            startActivity(new Intent(getActivity(), ProfileCategories.class));
        }
    }

}
