package com.amakenapp.website.amakenapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.chat.ChatActivity;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.amakenapp.website.amakenapp.store.User;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

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
    private AlertDialog.Builder alertDialog;
    private static final int PICK_IMAGE = 100;
    private static final int PICK_FROM_FILE = 200;
    Uri imageUriProfile;
    ImageView imageViewProfile;





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
        SharedPrefManager sharedPrefManager=SharedPrefManager.getInstance(getContext());
        String x=sharedPrefManager.getUsername();
        String y=sharedPrefManager.getKeyUserProfilePicUrl();
        changeProfilePicTxt.setText(x);
        Picasso.with(getApplicationContext()).load(y).into(businessProfilePic);




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
            alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Change Profile Picture");
            final View view1 = getActivity().getLayoutInflater().inflate(R.layout.change_profile_pic_dialog, null);
            alertDialog.setView(view1);

            final TextView choosefromGallery = (TextView) view1.findViewById(R.id.chooseFromGallery);
            final TextView choosefromCamera = (TextView) view1.findViewById(R.id.chooseFromCamers);

            imageViewProfile = (ImageView) view1.findViewById(R.id.preview_of_profile_pic);

            choosefromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGallery();
                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CameraPicture();
                }
            });

            alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which) {
                    //deletebookmark(bookmarkId);
                    //swap(position);

                }
            });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            final AlertDialog dialog = alertDialog.create();
            dialog.show();
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





    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public void CameraPicture() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_FROM_FILE);
    }


    /**
     * switching cases for the images collected from gallery or the camera
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            super.onActivityResult(requestCode, resultCode, data);
            /*startActivity(new Intent(getActivity().getApplicationContext(), NavDrw.class));
            Fragment fragment5 = new BusinessProfileActivity();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_nav_drw, fragment5);
            ft.addToBackStack(null);
            ft.commit();*/

            imageUriProfile = data.getData();
            imageViewProfile.setVisibility(View.VISIBLE);
            imageViewProfile.setImageURI(imageUriProfile);
        }

        if (resultCode == RESULT_OK && requestCode == PICK_FROM_FILE) {
            super.onActivityResult(requestCode, resultCode, data);
            /*startActivity(new Intent(getActivity().getApplicationContext(), NavDrw.class));
            Fragment fragment5 = new BusinessProfileActivity();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_nav_drw, fragment5);
            ft.commit();*/
            Bitmap imageUri = (Bitmap) data.getExtras().get("data");
            imageViewProfile.setVisibility(View.VISIBLE);
            imageViewProfile.setImageBitmap(imageUri);

        }


    }




    public void uploadPhoto(Bitmap image) {
        final Bitmap image1 = image;


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_UPLOAD_PHOTO ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                obj.getInt("id");
                                obj.getString("photo_url");
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("image", image1+"");
                return params;
            }
        };

        MySingleton.getInstance(getActivity()).addToRequestQueue(send);

    }
}
