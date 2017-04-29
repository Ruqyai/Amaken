package com.amakenapp.website.amakenapp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MyCommand;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.amakenapp.website.amakenapp.R.id.ratingBar;

public class EditReviewActivity extends FragmentActivity implements View.OnClickListener{

    private GoogleMap mMap;
    private Marker mMarker;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    /*private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    SupportMapFragment mapFrag;
    Location mLastLocation;*/
    String des;

    private double currentLatitude;
    private double currentLongitude;
    Context context;

    private LatLng addressPoint;


    private static int REVIEWID;



    private TextView imagedescription1, imagedescription2, imagedescription3, imagedescription4, imagedescription5;
    private TextView changeplacename, chnageplacedescription, changeplacecategory, changeplacecountry, changeplacecit, changeplaceaddress, changeplacelocation, changedate;




    Spinner spinnerDialog, spinnerDialog1, spinnerDialog2;
    private ArrayList<String> countries, cities, categories;
    private ArrayList<Integer> countryIds, citiesIds, categoriesIds;

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5 ;
    private ImageButton filpNext, flipPrevious;
    private Button changeImageButton1,
                   changeImageButton2,
                   changeImageButton3,
                    changeImageButton4,
                    changeImageButton5,
                    changeReviewDetails,
                    changeLocationDetails,
                    changeeventdate;
    private LinearLayout loading;


    private Boolean changeButton1,
            changeButton2,
            changeButton3,
            changeButton4,
            changeButton5;

    private ViewFlipper viewFlipper;
    private static int  imageID1, imageID2, imageID3, imageID4, imageID5;
    private static String imagedescriptionText1, imagedescriptionText2, imagedescriptionText3, imagedescriptionText4, imagedescriptionText5;
    private static String imageviewString1, imageviewString2, imageviewString3, imageviewString4, imageviewString5, placeName1, placeDescription1;


    private View  parentLayout, view1, view2, view3, view4, view5;

    private AlertDialog.Builder alertDialog;
    FragmentManager fm = getSupportFragmentManager();
    private static Calendar myCalendar;
    private AlertDialog dialog;
    //final int CAMERA_REQUEST = 12333;
    final int GALLERY_REQUEST = 2200;




    public static final int CAMERA_REQUEST = 1888;
    public static final int TAKE_GALLERY_CODE = 1;

    private CircleImageView userProfilePic;
    private EditText reviewText;
    private RatingBar ratingbar1;
    SharedPrefManager sharedPrefManager;
    private ImageButton add_photo;
    private ImageView imageViewProfile ;
    private Button cancel, post;
    private  int userProfilePicId, userType, insertedReviewID, imagesNum =0;



    private static int eventID, placeID;
    private static int userId;
    private ProgressDialog progressDialog;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    private Bitmap bitmap;
    String photoPath, review_type;

    LinearLayout container;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);
        context = this.getApplicationContext();
        parentLayout = findViewById(R.id.root_view);
        loading = (LinearLayout) findViewById(R.id.linlaHeaderProgress_EDITEVENT);
        loading.setVisibility(View.VISIBLE);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        //get user id from shared preferences
        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();
        REVIEWID = getIntent().getExtras().getInt("REVIEW_ID");

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Change Review Photo");

        ////////////////////////////////////////////////////


        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper_edit);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view1 = (View) inflater.inflate(R.layout.child_one, null);
        view2 = (View) inflater.inflate(R.layout.child_tow, null);
        view3 = (View) inflater.inflate(R.layout.child_tree, null);
        view4 = (View) inflater.inflate(R.layout.child_four, null);
        view5 = (View) inflater.inflate(R.layout.child_five, null);



        imageView1 = (ImageView) view1.findViewById(R.id.image1);
        imageView2 = (ImageView) view2.findViewById(R.id.image2);
        imageView3 = (ImageView) view3.findViewById(R.id.image3);
        imageView4 = (ImageView) view4.findViewById(R.id.image4);
        imageView5 = (ImageView) view5.findViewById(R.id.image5);


        imagedescription1 = (TextView) view1.findViewById(R.id.text1);
        imagedescription2 = (TextView) view2.findViewById(R.id.text2);
        imagedescription3 = (TextView) view3.findViewById(R.id.text3);
        imagedescription4 = (TextView) view4.findViewById(R.id.text4);
        imagedescription5 = (TextView) view5.findViewById(R.id.text5);

        changeImageButton1 = (Button) view1.findViewById(R.id.change1);
        changeImageButton2 = (Button) view2.findViewById(R.id.change2);
        changeImageButton3 = (Button) view3.findViewById(R.id.change3);
        changeImageButton4 = (Button) view4.findViewById(R.id.change4);
        changeImageButton5 = (Button) view5.findViewById(R.id.change5);



        changeImageButton1.setOnClickListener(this);
        changeImageButton2.setOnClickListener(this);
        changeImageButton3.setOnClickListener(this);
        changeImageButton4.setOnClickListener(this);
        changeImageButton5.setOnClickListener(this);


        changeReviewDetails = (Button) findViewById(R.id.change_REVIEW_DETAILS);
        progressDialog = new ProgressDialog(this);
        changeReviewDetails.setOnClickListener(this);
        ratingbar1 = (RatingBar) findViewById(ratingBar);
        reviewText = (EditText) findViewById(R.id.editText2) ;
        reviewText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (reviewText.getText().toString().length()<= 0) {
                    reviewText.setError("Required field, Please Enter Review Text!");
                    changeReviewDetails.setEnabled(false);
                } else {
                    reviewText.setError(null);
                    changeReviewDetails.setEnabled(true);

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                reviewText.setError(null);
            }
        });



        ///////

        flipPrevious = (ImageButton) findViewById(R.id.flipp_previous_edit);
        filpNext = (ImageButton) findViewById(R.id.flipp_next_edit);

        filpNext.setOnClickListener(this);
        flipPrevious.setOnClickListener(this);

        reviewGalleryLoading(REVIEWID);

        viewFlipper.addView(view1);
        viewFlipper.addView(view2);
        viewFlipper.addView(view3);
        viewFlipper.addView(view4);
        viewFlipper.addView(view5);


        reviewInfoLoading (REVIEWID);



    }






















    @Override
    public void onClick(View v) {

        if (v == filpNext){
            viewFlipper.setInAnimation(this, R.anim.in_from_left);
            viewFlipper.setOutAnimation(this, R.anim.out_to_right);
            viewFlipper.showNext();

        }
        if (v == flipPrevious){
            viewFlipper.setInAnimation(this, R.anim.in_from_right);
            viewFlipper.setOutAnimation(this, R.anim.out_to_left);
            viewFlipper.showPrevious();

        }



        if (v == changeImageButton1){


            //LayoutInflater factory = LayoutInflater.from(v.getContext());
            final View view1 = getLayoutInflater().inflate(R.layout.dialog_add_photo, null);
            alertDialog.setView(view1);

            final TextView choosefromGallery = (TextView) view1.findViewById(R.id.chooseFromGallery);
            final TextView choosefromCamera = (TextView) view1.findViewById(R.id.chooseFromCamers);
            final EditText adddescription = (EditText) view1.findViewById(R.id.adddescription);


            imageViewProfile = (ImageView) view1.findViewById(R.id.preview_of_profile_pic);

            choosefromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callGallery();
                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCamera();
                }
            });

            AlertDialog.Builder change = alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //uploadPhoto();
                    if (changeButton1)
                    {
                        updatePhoto(imageID1, adddescription );
                    }
                    else
                    {
                        if(review_type.equals("Place"))
                            uploadPhotoPlace(1, adddescription);
                        else if (review_type.equals("Event"))
                            uploadPhotoEvent(1, adddescription);
                    }
                }
            });

            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialog = alertDialog.create();
            dialog.show();
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setEnabled(false);

        }
        if (v == changeImageButton2){


            //LayoutInflater factory = LayoutInflater.from(v.getContext());
            final View view1 = getLayoutInflater().inflate(R.layout.dialog_add_photo, null);
            alertDialog.setView(view1);

            final TextView choosefromGallery = (TextView) view1.findViewById(R.id.chooseFromGallery);
            final TextView choosefromCamera = (TextView) view1.findViewById(R.id.chooseFromCamers);
            final EditText adddescription = (EditText) view1.findViewById(R.id.adddescription);


            imageViewProfile = (ImageView) view1.findViewById(R.id.preview_of_profile_pic);

            choosefromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callGallery();
                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCamera();
                }
            });

            AlertDialog.Builder change = alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //uploadPhoto();
                    if (changeButton2)
                    {
                        updatePhoto(imageID2, adddescription );
                    }
                    else
                    {
                        if(review_type.equals("Place"))
                            uploadPhotoPlace(2, adddescription);
                        else if (review_type.equals("Event"))
                            uploadPhotoEvent(2, adddescription);

                    }
                }
            });

            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog = alertDialog.create();
            dialog.show();
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setEnabled(false);

        }if (v == changeImageButton3)
        {
            // Next screen comes in from right.


            //LayoutInflater factory = LayoutInflater.from(v.getContext());
            final View view1 = getLayoutInflater().inflate(R.layout.dialog_add_photo, null);
            alertDialog.setView(view1);

            final TextView choosefromGallery = (TextView) view1.findViewById(R.id.chooseFromGallery);
            final TextView choosefromCamera = (TextView) view1.findViewById(R.id.chooseFromCamers);
            final EditText adddescription = (EditText) view1.findViewById(R.id.adddescription);


            imageViewProfile = (ImageView) view1.findViewById(R.id.preview_of_profile_pic);

            choosefromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callGallery();
                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCamera();
                }
            });

            AlertDialog.Builder change = alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //uploadPhoto();
                    if (changeButton3)
                    {
                        updatePhoto(imageID3, adddescription );
                    }
                    else
                    {
                        if(review_type.equals("Place"))
                            uploadPhotoPlace(3, adddescription);
                        else if (review_type.equals("Event"))
                            uploadPhotoEvent(3, adddescription);

                    }
                }
            });

            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog = alertDialog.create();
            dialog.show();
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setEnabled(false);
        }if (v == changeImageButton4){


            //LayoutInflater factory = LayoutInflater.from(v.getContext());
            final View view1 = getLayoutInflater().inflate(R.layout.dialog_add_photo, null);
            alertDialog.setView(view1);

            final TextView choosefromGallery = (TextView) view1.findViewById(R.id.chooseFromGallery);
            final TextView choosefromCamera = (TextView) view1.findViewById(R.id.chooseFromCamers);
            final EditText adddescription = (EditText) view1.findViewById(R.id.adddescription);


            imageViewProfile = (ImageView) view1.findViewById(R.id.preview_of_profile_pic);

            choosefromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callGallery();
                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCamera();
                }
            });

            AlertDialog.Builder change = alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //uploadPhoto();
                    if (changeButton4)
                    {
                        updatePhoto(imageID4, adddescription );
                    }
                    else
                    {
                        if(review_type.equals("Place"))
                            uploadPhotoPlace(4, adddescription);
                        else if (review_type.equals("Event"))
                            uploadPhotoEvent(4, adddescription);

                    }
                }
            });

            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog = alertDialog.create();
            dialog.show();
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setEnabled(false);
        }

        if (v == changeImageButton5){


            //LayoutInflater factory = LayoutInflater.from(v.getContext());
            final View view1 = getLayoutInflater().inflate(R.layout.dialog_add_photo, null);
            alertDialog.setView(view1);

            final TextView choosefromGallery = (TextView) view1.findViewById(R.id.chooseFromGallery);
            final TextView choosefromCamera = (TextView) view1.findViewById(R.id.chooseFromCamers);
            final EditText adddescription = (EditText) view1.findViewById(R.id.adddescription);


            imageViewProfile = (ImageView) view1.findViewById(R.id.preview_of_profile_pic);

            choosefromGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callGallery();
                }
            });
            choosefromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCamera();

                }
            });

            AlertDialog.Builder change = alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //uploadPhoto();
                    if (changeButton5)
                    {
                        updatePhoto(imageID5, adddescription );
                    }
                    else
                    {
                        if(review_type.equals("Place"))
                            uploadPhotoPlace(5, adddescription);
                        else if (review_type.equals("Event"))
                            uploadPhotoEvent(5, adddescription);

                    }
                }
            });

            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog = alertDialog.create();
            dialog.show();
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setEnabled(false);
        }


        if (v == changeReviewDetails){
            v.startAnimation(buttonClick);

            String reviewText2 = reviewText.getText().toString().trim();
            Float rating = ratingbar1.getRating();
            if(reviewText2.length()<=0){
                reviewText.requestFocus();
                reviewText.setError("Required field, Please Enter Your Review!");
                reviewText.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        if (reviewText.getText().toString().length()<= 0) {
                            reviewText.setError("Required field, Please Enter Your Review!");
                        } else {
                            reviewText.setError(null);
                        }
                    }
                    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                    public void onTextChanged(CharSequence s, int start, int before, int count){
                        reviewText.setError(null);
                    }
                });
            }
            else if(rating == 0.0)
            {
                ratingbar1.requestFocus();
                showToast("Please Set a Rating!");
            }
            else
            {

                editReview();

            }

        }




    }
    /////////////////////////

    private void showToast(String meg){
        final String message = meg;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }







    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageViewProfile.setVisibility(View.VISIBLE);
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    .setEnabled(true);
            imageViewProfile.setImageBitmap(bitmap); //imageView is your ImageView
            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
            File finalFile = new File(getRealPathFromURI(tempUri));
            photoPath = finalFile.toString();
        } else if (requestCode == TAKE_GALLERY_CODE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                photoPath = cursor.getString(columnIndex);
                cursor.close();
                File tempFile = new File(photoPath);
                bitmap = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
                imageViewProfile.setVisibility(View.VISIBLE);
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                        .setEnabled(true);
                imageViewProfile.setImageBitmap(bitmap);
            }
        }
    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }







    public void reviewGalleryLoading ( int reviewId){
            final int reviewID = reviewId;

            StringRequest send = new StringRequest(Request.Method.GET,
                    Constants.URL_GET_REVIEW_Gallery + reviewID,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    loading.setVisibility(View.GONE);
                                    JSONArray arr = obj.getJSONArray("review_gallery");
                                    imagesNum = arr.length();
                                    for (int i = 0; i < arr.length(); i++) {


                                        if (imagesNum == 1) {
                                            JSONObject url = arr.getJSONObject(0);

                                            imageID1 = url.getInt("image_id");
                                            imagedescriptionText1 = url.getString("image_description");
                                            imageviewString1 = url.getString("image_url");

                                            imagedescription1.setText(imagedescriptionText1);
                                            Glide.with(getApplicationContext()).load(imageviewString1)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView1);

                                            changeImageButton2.setText("Add more Images");
                                            changeImageButton3.setText("Add more Images");
                                            changeImageButton4.setText("Add more Images");
                                            changeImageButton5.setText("Add more Images");


                                            changeButton1 = true;
                                            changeButton2 = false;
                                            changeButton3 = false;
                                            changeButton4 = false;
                                            changeButton5 = false;

                                        }
                                        if (imagesNum == 2) {
                                            JSONObject url = arr.getJSONObject(0);
                                            imageID1 = url.getInt("image_id");
                                            imagedescriptionText1 = url.getString("image_description");
                                            imageviewString1 = url.getString("image_url");


                                            JSONObject url2 = arr.getJSONObject(1);
                                            imageID2 = url2.getInt("image_id");
                                            imagedescriptionText2 = url2.getString("image_description");
                                            imageviewString2 = url2.getString("image_url");


                                            imagedescription1.setText(imagedescriptionText1);
                                            Glide.with(getApplicationContext()).load(imageviewString1)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView1);

                                            imagedescription2.setText(imagedescriptionText2);
                                            Glide.with(getApplicationContext()).load(imageviewString2)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView2);


                                            changeImageButton3.setText("Add more Images");
                                            changeImageButton4.setText("Add more Images");
                                            changeImageButton5.setText("Add more Images");

                                            changeButton1 = true;
                                            changeButton2 = true;
                                            changeButton3 = false;
                                            changeButton4 = false;
                                            changeButton5 = false;

                                        }
                                        if (imagesNum == 3) {

                                            JSONObject url = arr.getJSONObject(0);
                                            imageID1 = url.getInt("image_id");
                                            imagedescriptionText1 = url.getString("image_description");
                                            imageviewString1 = url.getString("image_url");


                                            JSONObject url2 = arr.getJSONObject(1);
                                            imageID2 = url2.getInt("image_id");
                                            imagedescriptionText2 = url2.getString("image_description");
                                            imageviewString2 = url2.getString("image_url");


                                            JSONObject url3 = arr.getJSONObject(2);
                                            imageID3 = url3.getInt("image_id");
                                            imagedescriptionText3 = url3.getString("image_description");
                                            imageviewString3 = url3.getString("image_url");



                                            imagedescription1.setText(imagedescriptionText1);
                                            Glide.with(getApplicationContext()).load(imageviewString1)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView1);

                                            imagedescription2.setText(imagedescriptionText2);
                                            Glide.with(getApplicationContext()).load(imageviewString2)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView2);

                                            imagedescription3.setText(imagedescriptionText3);
                                            Glide.with(getApplicationContext()).load(imageviewString3)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView3);



                                            changeImageButton4.setText("Add more Images");
                                            changeImageButton5.setText("Add more Images");

                                            changeButton1 = true;
                                            changeButton2 = true;
                                            changeButton3 = true;
                                            changeButton4 = false;
                                            changeButton5 = false;

                                        }
                                        if (imagesNum == 4) {
                                            JSONObject url = arr.getJSONObject(0);
                                            imageID1 = url.getInt("image_id");
                                            imagedescriptionText1 = url.getString("image_description");
                                            imageviewString1 = url.getString("image_url");


                                            JSONObject url2 = arr.getJSONObject(1);
                                            imageID2 = url2.getInt("image_id");
                                            imagedescriptionText2 = url2.getString("image_description");
                                            imageviewString2 = url2.getString("image_url");


                                            JSONObject url3 = arr.getJSONObject(2);
                                            imageID3 = url3.getInt("image_id");
                                            imagedescriptionText3 = url3.getString("image_description");
                                            imageviewString3 = url3.getString("image_url");


                                            JSONObject url4 = arr.getJSONObject(3);
                                            imageID4 = url4.getInt("image_id");
                                            imagedescriptionText4 = url4.getString("image_description");
                                            imageviewString4 = url4.getString("image_url");


                                            imagedescription1.setText(imagedescriptionText1);
                                            Glide.with(getApplicationContext()).load(imageviewString1)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView1);

                                            imagedescription2.setText(imagedescriptionText2);
                                            Glide.with(getApplicationContext()).load(imageviewString2)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView2);

                                            imagedescription3.setText(imagedescriptionText3);
                                            Glide.with(getApplicationContext()).load(imageviewString3)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView3);

                                            imagedescription4.setText(imagedescriptionText4);
                                            Glide.with(getApplicationContext()).load(imageviewString4)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))
                                                    .into(imageView4);


                                            changeImageButton5.setText("Add more Images");

                                            changeButton1 = true;
                                            changeButton2 = true;
                                            changeButton3 = true;
                                            changeButton4 = true;
                                            changeButton5 = false;

                                        }

                                        if (imagesNum == 5) {

                                            JSONObject url = arr.getJSONObject(0);
                                            imageID1 = url.getInt("image_id");
                                            imagedescriptionText1 = url.getString("image_description");
                                            imageviewString1 = url.getString("image_url");


                                            JSONObject url2 = arr.getJSONObject(1);
                                            imageID2 = url2.getInt("image_id");
                                            imagedescriptionText2 = url2.getString("image_description");
                                            imageviewString2 = url2.getString("image_url");


                                            JSONObject url3 = arr.getJSONObject(2);
                                            imageID3 = url3.getInt("image_id");
                                            imagedescriptionText3 = url3.getString("image_description");
                                            imageviewString3 = url3.getString("image_url");


                                            JSONObject url4 = arr.getJSONObject(3);
                                            imageID4 = url4.getInt("image_id");
                                            imagedescriptionText4 = url4.getString("image_description");
                                            imageviewString4 = url4.getString("image_url");


                                            JSONObject url5 = arr.getJSONObject(4);
                                            imageID5 = url5.getInt("image_id");
                                            imagedescriptionText5 = url5.getString("image_description");
                                            imageviewString5 = url5.getString("image_url");

                                            imagedescription1.setText(imagedescriptionText1);
                                            Glide.with(getApplicationContext()).load(imageviewString1)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))                                                    .into(imageView1);

                                            imagedescription2.setText(imagedescriptionText2);
                                            Glide.with(getApplicationContext()).load(imageviewString2)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))                                                    .into(imageView2);

                                            imagedescription3.setText(imagedescriptionText3);
                                            Glide.with(getApplicationContext()).load(imageviewString3)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))                                                    .into(imageView3);

                                            imagedescription4.setText(imagedescriptionText4);
                                            Glide.with(getApplicationContext()).load(imageviewString4)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))                                                    .into(imageView4);

                                            imagedescription5.setText(imagedescriptionText5);
                                            Glide.with(getApplicationContext()).load(imageviewString5)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .signature(new StringSignature(String.valueOf(url.getString("image_timeStamp"))))                                                    .into(imageView5);

                                            changeButton1 = true;
                                            changeButton2 = true;
                                            changeButton3 = true;
                                            changeButton4 = true;
                                            changeButton5 = true;
                                        }
                                    }

                                } else {
                                    if (imagesNum == 0) {
                                        loading.setVisibility(View.GONE);
                                        changeImageButton1.setText("Add Images");
                                        changeImageButton2.setText("Add Images");
                                        changeImageButton3.setText("Add Images");
                                        changeImageButton4.setText("Add Images");
                                        changeImageButton5.setText("Add Images");


                                        changeButton1 = false;
                                        changeButton2 = false;
                                        changeButton3 = false;
                                        changeButton4 = false;
                                        changeButton5 = false;

                                    }
                                    //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
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

            };
            MySingleton.getInstance(this).addToRequestQueue(send);
        }



    public void reviewInfoLoading(int reviewId){
        final int reviewID = reviewId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_REVIEW_BY_ID + reviewID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                obj.getInt("review_id");
                                reviewText.setText(obj.getString("review_text"));

                                Double rate = obj.getDouble("rate_value");
                                String rate2 = Double.toString(rate);
                                Float rate3 = Float.parseFloat(rate2);
                                ratingbar1.setRating(rate3);
                                review_type = obj.getString("review_type");
                                if(review_type.equals("Place"))
                                    placeID=obj.getInt("place_id");
                                else if (review_type.equals("Event"))
                                    eventID=obj.getInt("event_id");
                                userId = obj.getInt("user_id");

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
            public void onErrorResponse(VolleyError error) {Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();}}) {};
        MySingleton.getInstance(this).addToRequestQueue(send);

    }






    public void editReview() {
        final int reviewId= REVIEWID;
        final String reviewText2 = reviewText.getText().toString().trim();
        final Float reviewRating2 = ratingbar1.getRating();


        progressDialog.setMessage("Updating Review Information...");
        progressDialog.show();

        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_EDIT_REVIEW +reviewId ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
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
                params.put("review_text", reviewText2);
                params.put("rate_value", reviewRating2+"");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }







    public void uploadPhotoEvent(final int imageId, final TextView  imagedescription) {
        final String imageDecription = imagedescription.getText().toString().trim();
        final int eventid = eventID;
        final int reviewid = REVIEWID;
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_UPLOAD_PHOTO_REVIEW_EVENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {


                                if(imageId == 1)
                                {imagedescription1.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView1);
                                    imageID1 =obj.getInt("inserted_photo_id");
                                    changeImageButton1.setText("Change first image");
                                    changeButton1 = true;
                                }
                                if(imageId == 2)
                                {imagedescription2.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView2);
                                    imageID2 =obj.getInt("inserted_photo_id");
                                    changeImageButton2.setText("Change second image");
                                    changeButton2 = true;
                                }
                                if(imageId == 3)
                                {imagedescription3.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView3);
                                    changeImageButton3.setText("Change third image");
                                    imageID3 =obj.getInt("inserted_photo_id");
                                    changeButton3 = true;}
                                if(imageId == 4)
                                {imagedescription4.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView4);
                                    changeImageButton4.setText("Change fourth image");
                                    imageID4 =obj.getInt("inserted_photo_id");
                                    changeButton4 = true;
                                }
                                if(imageId == 5)
                                {imagedescription5.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView5);
                                    changeImageButton5.setText("Change fifth image");
                                    imageID5 =obj.getInt("inserted_photo_id");
                                    changeButton5 = true;
                                }
                                showToast(obj.getString("message"));


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
                params.put("review_id", reviewid+"");
                params.put("event_id", eventid+"");
                params.put("image_description", imageDecription);
                params.put("image", image);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);

    }


    public void uploadPhotoPlace(final int imageId, final TextView  imagedescription) {
        final String imageDecription = imagedescription.getText().toString().trim();
        final int placeId = placeID;
        final int reviewid = REVIEWID;

        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_UPLOAD_PHOTO_REVIEW_PLACE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {


                                if(imageId == 1)
                                {imagedescription1.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView1);
                                    imageID1 =obj.getInt("inserted_photo_id");
                                    changeImageButton1.setText("Change first image");
                                    changeButton1 = true;
                                }
                                if(imageId == 2)
                                {imagedescription2.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView2);
                                    imageID2 =obj.getInt("inserted_photo_id");
                                    changeImageButton2.setText("Change second image");
                                    changeButton2 = true;
                                }
                                if(imageId == 3)
                                {imagedescription3.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView3);
                                    changeImageButton3.setText("Change third image");
                                    imageID3 =obj.getInt("inserted_photo_id");
                                    changeButton3 = true;}
                                if(imageId == 4)
                                {imagedescription4.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView4);
                                    changeImageButton4.setText("Change fourth image");
                                    imageID4 =obj.getInt("inserted_photo_id");
                                    changeButton4 = true;
                                }
                                if(imageId == 5)
                                {imagedescription5.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView5);
                                    changeImageButton5.setText("Change fifth image");
                                    imageID5 =obj.getInt("inserted_photo_id");
                                    changeButton5 = true;
                                }
                                showToast(obj.getString("message"));

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
                params.put("review_id", reviewid+"");
                params.put("place_id", placeId+"");
                params.put("image_description", imageDecription);
                params.put("image", image);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);

    }




    public void updatePhoto(final int imageId, final TextView  imagedescription) {
        final String imageDecription = imagedescription.getText().toString().trim();
        final ProgressDialog loading = ProgressDialog.show(this, "Updating...", "Please wait...", false, false);

        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_UPDATE_PHOTO_Event + imageId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                if(imageId == imageID1)
                                {imagedescription1.setText(obj.getString("inserted_photo_description"));
                                Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                        .into(imageView1);}
                                if(imageId == imageID2)
                                {imagedescription2.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView2);}
                                if(imageId == imageID3)
                                {imagedescription3.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView3);}
                                if(imageId == imageID4)
                                {imagedescription4.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView4);}
                                if(imageId == imageID5)
                                {imagedescription5.setText(obj.getString("inserted_photo_description"));
                                    Glide.with(getApplicationContext()).load(obj.getString("inserted_photo_url"))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .signature(new StringSignature(String.valueOf(obj.getString("inserted_photo_url_timeStamp"))))
                                            .into(imageView5);}
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

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
                params.put("image_description", imageDecription);
                params.put("image", image);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(send);

    }

}
