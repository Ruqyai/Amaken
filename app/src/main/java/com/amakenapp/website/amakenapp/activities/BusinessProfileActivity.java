package com.amakenapp.website.amakenapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Muha on 3/8/2017.
 */

public class BusinessProfileActivity extends Fragment implements View.OnClickListener {
    private CardView
            businessPlacesCard,
            businessEventsCard,
            businessBookmarksCard,
            businessLikesCard,
            businessReviewsCard,
            businessCategoriesCard;


    //final int CAMERA_REQUEST = 12333;
    final int GALLERY_REQUEST = 2200;


    public static final int CAMERA_REQUEST = 1888;
    public static final int TAKE_GALLERY_CODE = 1;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    private Bitmap bitmap;
    String photoPath;


    private CircleImageView businessProfilePic;
    private TextView changeProfilePicTxt;
    private AlertDialog.Builder alertDialog;
    private AlertDialog dialog;
    Context context ;

    private static final int PICK_IMAGE = 100;
    private static final int PICK_FROM_FILE = 200;
    Uri imageUriProfile;
    ImageView imageViewProfile;


    private String changedUserName, changedWebUrl, changedPhoneNumber;
    private int changedCountryId, changedCityId;
    private static AppCompatEditText input, input2, input3;
    Spinner spinnerDialog, spinnerDialog1, spinnerDialog2;
    private ArrayList<String> countries, cities;
    private ArrayList<Integer> countryIds, citiesIds;
    private static AlertDialog dialogChangeUserDetail;

    SharedPrefManager sharedPrefManager;
    private static int userId;
    String username,userWeb, userPhone, userCountyName, userCityName,userProfilePicUrl, userProfilePicIdTimeStamp;
    private  static  int  userProfilePicId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_business_profile, container, false);
        context = getApplicationContext();


        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        businessPlacesCard = (CardView) myView.findViewById(R.id.business_profile_placeCard);
        businessEventsCard = (CardView) myView.findViewById(R.id.business_profile_eventsCard);
        businessBookmarksCard = (CardView) myView.findViewById(R.id.business_profile_bookmarksCard);
        businessLikesCard = (CardView) myView.findViewById(R.id.business_profile_likesCard);
        businessReviewsCard = (CardView) myView.findViewById(R.id.business_profile_reviewsCard);
        businessCategoriesCard = (CardView) myView.findViewById(R.id.business_profile_categoriesCard);
        businessProfilePic = (CircleImageView) myView.findViewById(R.id.change_business_profile_pic);
        changeProfilePicTxt = (TextView) myView.findViewById(R.id.businessProfile_changeProfilePicTxt);

        sharedPrefManager = SharedPrefManager.getInstance(getContext());
        userId = sharedPrefManager.getUserId();
        username = sharedPrefManager.getUsername();
        userWeb = sharedPrefManager.getUserWeb();
        userPhone = sharedPrefManager.getUserPhone();
        userCountyName = sharedPrefManager.getKeyUserCountryName();
        userCityName = sharedPrefManager.getKeyUserCityName();

        userProfilePicUrl = sharedPrefManager.getKeyUserProfilePicUrl();
        userProfilePicId = sharedPrefManager.getUserProfilePicId();
        userProfilePicIdTimeStamp = sharedPrefManager.getKeyUserProfilePicUrlTimeStamp();

        changeProfilePicTxt.setText(username);

        if(userProfilePicId==0)
            businessProfilePic.setImageResource(R.drawable.business1);
        else
             Glide.with(getApplicationContext()).load(userProfilePicUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                //.signature(new StringSignature(String.valueOf(userProfilePicIdTimeStamp)))
                .into(businessProfilePic);


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

        if (v == businessProfilePic) {


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
                    callGallery();                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {callCamera();}});

            alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //uploadPhoto();
                    if(userProfilePicId == 0)
                    {
                     uploadPhoto();
                    }
                    else
                    {
                     updatePhoto();
                    }

                }
            });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            dialog = alertDialog.create();
            dialog.show();
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        }




        if (v == changeProfilePicTxt) {

            alertDialog = new AlertDialog.Builder(v.getRootView().getContext());
            alertDialog.setTitle("Change Your Details");
            final View view1 = getActivity().getLayoutInflater().inflate(R.layout.change_username_dialog, null);
            input = (AppCompatEditText) view1.findViewById(R.id.report_reasonEdit);
            input2 = (AppCompatEditText) view1.findViewById(R.id.edit_web);
            input3 = (AppCompatEditText) view1.findViewById(R.id.editphone);
            final TextView countryName = (TextView) view1.findViewById(R.id.viewCountry);
            final TextView cityName = (TextView) view1.findViewById(R.id.viewCity);

            input.setText(username);
            input2.setText(userWeb);
            input3.setText(userPhone);
            countryName.setText("("+userCountyName+")");
            cityName.setText("("+userCityName+")");


            spinnerDialog = (Spinner) view1.findViewById(R.id.place_category);
            spinnerDialog1 = (Spinner) view1.findViewById(R.id.sppiner_country);
            spinnerDialog2 = (Spinner) view1.findViewById(R.id.spiner_city);


            countries = new ArrayList<>();
            countryIds = new ArrayList<>();

            cities = new ArrayList<>();
            citiesIds = new ArrayList<>();

            spinnerDialog1.setOnItemSelectedListener(new OnSpinnerItemClicked());
            loadCountries();

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
            view1.setLayoutParams(lp);
            alertDialog.setView(view1);
            alertDialog.setPositiveButton("Send", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which) {
                     updateUserDetails();
                }
            });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            dialogChangeUserDetail = alertDialog.create();
            dialogChangeUserDetail.show();
            input.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (input.getText().toString().length()<= 0) {
                        input.setError("Enter a Name!");
                        ((AlertDialog) dialogChangeUserDetail).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else {
                        input.setError(null);
                        ((AlertDialog) dialogChangeUserDetail).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

                    }
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                public void onTextChanged(CharSequence s, int start, int before, int count){
                    input.setError(null);
                }
            });




        }


        if (v == businessPlacesCard) {
            startActivity(new Intent(getActivity(), BusinessProfilePlaces.class));
        }
        if (v == businessEventsCard) {
            startActivity(new Intent(getActivity(), BusinessProfileEvents.class));
        }
        if (v == businessBookmarksCard) {
            startActivity(new Intent(getActivity(), ProfileBookmarks.class));
        }
        if (v == businessLikesCard) {
            startActivity(new Intent(getActivity(), ProfileLikes.class));
        }
        if (v == businessReviewsCard) {
            startActivity(new Intent(getActivity(), ProfileReviews.class));
        }

        if (v == businessCategoriesCard) {
            startActivity(new Intent(getActivity(), ProfileCategories.class));

        }
    }


    public class OnSpinnerItemClicked implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int countryId = countryIds.get(position);
            loadCities(countryId);
        }

        @Override
        public void onNothingSelected(AdapterView parent) {
            ((AlertDialog) dialogChangeUserDetail).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            Toast.makeText(getApplicationContext(), "No Country is selected", Toast.LENGTH_LONG).show();

        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void callCamera() {
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    private void callGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, TAKE_GALLERY_CODE);
    }


/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                photoPath = cameraPhoto.getPhotoPath();
                try {
                    bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    imageViewProfile.setVisibility(View.VISIBLE);
                    imageViewProfile.setImageBitmap(bitmap);
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true); //imageView is your ImageView
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong while loading photos", Toast.LENGTH_LONG).show();
                }
            }
        }//end if resultCode

        if (requestCode == GALLERY_REQUEST) {
            galleryPhoto.setPhotoUri(data.getData());
            photoPath = galleryPhoto.getPath();
            try {
                bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                imageViewProfile.setVisibility(View.VISIBLE);
                imageViewProfile.setImageBitmap(bitmap);
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong while choosing photos", Toast.LENGTH_LONG).show();
            }
        }
    }//end if resultCode
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageViewProfile.setVisibility(View.VISIBLE);
            imageViewProfile.setImageBitmap(bitmap);
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
            File finalFile = new File(getRealPathFromURI(tempUri));
            photoPath = finalFile.toString();
        } else if (requestCode == TAKE_GALLERY_CODE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                photoPath = cursor.getString(columnIndex);
                cursor.close();
                File tempFile = new File(photoPath);
                bitmap = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
                imageViewProfile.setVisibility(View.VISIBLE);
                imageViewProfile.setImageBitmap(bitmap);
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        }
    }


    public void uploadPhoto() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Uploading...", "Please wait...", false, false);

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_UPLOAD_PHOTO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                String picIdString = Integer.toString(obj.getInt("inserted_photo_id"));
                                sharedPrefManager.setKeyUserProfilePicId(picIdString);
                                sharedPrefManager.setKeyUserProfilePicUrl(obj.getString("inserted_photo_url")) ;
                                sharedPrefManager.setKeyUserProfilePicUrlTimeStamp(obj.getString("inserted_photo_url_timeStamp"));

                                getActivity().finish();
                                getActivity().overridePendingTransition(0, 0);
                                startActivity(new Intent(getActivity(), NavDrw.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                getActivity().overridePendingTransition(0, 0);Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
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
                String image = getStringImage(bitmap);
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId+"");
                params.put("photo_url", image);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);

    }




    public void updatePhoto() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Updating...", "Please wait...", false, false);

        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_UPDATE_PHOTO + userProfilePicId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                sharedPrefManager.setKeyUserProfilePicUrl(obj.getString("inserted_photo_url")) ;
                                sharedPrefManager.setKeyUserProfilePicUrlTimeStamp(obj.getString("inserted_photo_url_timeStamp")); ;

                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                getActivity().finish();
                                getActivity().overridePendingTransition(0, 0);
                                startActivity(new Intent(getActivity(), NavDrw.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                getActivity().overridePendingTransition(0, 0);
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
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
                String image = getStringImage(bitmap);
                Map<String, String> params = new HashMap<>();
                params.put("photo", image);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);

    }




    public void updateUserDetails() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Uploading...", "Please wait...", false, false);

        final String userNAME = input.getText().toString().trim();
        final String userWEB = input2.getText().toString().trim();
        final String userPHONE = input3.getText().toString().trim();
        final int usercountruid = countryIds.get(spinnerDialog1.getSelectedItemPosition());
        final int usercityid = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_Update_User_Details ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                sharedPrefManager.setKeyUserName(obj.getString("user_name"));
                                sharedPrefManager.setKeyUserWebUrl(obj.getString("web_url"));
                                sharedPrefManager.setKeyUserPhoneNumber(obj.getString("phone_number"));
                                sharedPrefManager.setKeyUserCountryId(obj.getInt("country_id"));
                                sharedPrefManager.setKeyUserCountryName(obj.getString("country_name"));
                                sharedPrefManager.setKeyUserCityId(obj.getInt("city_id"));
                                sharedPrefManager.setKeyUserCityName(obj.getString("city_name"));
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                getActivity().finish();
                                getActivity().overridePendingTransition(0, 0);
                                startActivity(new Intent(getActivity(), NavDrw.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                getActivity().overridePendingTransition(0, 0);
                                /*Fragment fragment = new BusinessProfileActivity();
                                FragmentTransaction ft = ((NavDrw) getActivity()).getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_nav_drw, fragment);
                                ft.addToBackStack(null);
                                ft.commit();*/

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
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
                params.put("user_id", userId+"");
                params.put("name", userNAME);
                params.put("web_url", userWEB);
                params.put("phone_number", userPHONE);
                params.put("country_id", usercountruid+"");
                params.put("city_id", usercityid+"");

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);

    }


    private void loadCountries() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_COUNTRIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("countries");

                    for (int i = 0; i < arr.length(); i++) {
                        countries.add(arr.getJSONObject(i).getString("country_name"));

                        countryIds.add(arr.getJSONObject(i).getInt("id"));
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countries);
                    spinnerDialog1.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    // THIS IS FOR loading cities for a particular country
    private void loadCities(int countryId) {
        cities.clear();
        citiesIds.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_CITIES + countryId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("cities");

                    for (int i = 0; i < arr.length(); i++) {
                        cities.add(arr.getJSONObject(i).getString("city_name"));

                        citiesIds.add(arr.getJSONObject(i).getInt("id"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cities);
                    spinnerDialog2.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}


