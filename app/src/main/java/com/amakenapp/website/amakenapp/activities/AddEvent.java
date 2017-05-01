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
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.kosalgeek.android.photoutil.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddEvent extends AppCompatActivity implements
    OnMapReadyCallback,
    View.OnClickListener,
    AdapterView.OnItemSelectedListener,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    LocationListener

    {

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
        SharedPrefManager sharedPrefManager;


        private static int eventID;
        private static int userId;
        private ProgressDialog progressDialog;



        private TextView imagedescription1, imagedescription2, imagedescription3, imagedescription4, imagedescription5;
        private TextView show_photo, changeplacename, chnageplacedescription, changeplacecategory, changeplacecountry, changeplacecit, changeplaceaddress, changeplacelocation, changedate;


        private static TextView startDate, endDate, startTime, endTime, days;

        private EditText placeName,
                placeDescription,
                locationAdress;

        Spinner spinnerDialog, spinnerDialog1, spinnerDialog2;
        private ArrayList<String> countries, cities, categories;
        private ArrayList<Integer> countryIds, citiesIds, categoriesIds;
        private ArrayList<String> imgaeList, imageDscription;

        private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageViewProfile ;
        private ImageButton filpNext, flipPrevious, getmylocation, add_photo;
        private Button changeLocationDetails;
        private LinearLayout loading;

        private static int insertedLocationId=0, insertedDateId=0, insertedEventId=0;

        private Boolean changeButton1,
                changeButton2,
                changeButton3,
                changeButton4,
                changeButton5;

        private ViewFlipper viewFlipper;
        private  int imagesNum =0;


        private View  parentLayout;

        private AlertDialog.Builder alertDialog;
        FragmentManager fm = getSupportFragmentManager();
        private static Calendar myCalendar;
        private AlertDialog dialog;
        public static final int CAMERA_REQUEST = 1888;
        public static final int TAKE_GALLERY_CODE = 1;


        CameraPhoto cameraPhoto;
        GalleryPhoto galleryPhoto;
        private Bitmap bitmap;
        String photoPath, userName;

        LinearLayout container;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addevent_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        context = this.getApplicationContext();
        parentLayout = findViewById(R.id.root_view);

        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_edit);
        mapFragment.getMapAsync(this);

        //get user id from shared preferences
        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();
        userName = sharedPrefManager.getUsername();


        ////////////////////////////////////////////////////


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        changeLocationDetails = (Button) findViewById(R.id.change_address_details);
        add_photo = (ImageButton) findViewById(R.id.add_photo);
        container = (LinearLayout)findViewById(R.id.container);


        changeLocationDetails.setOnClickListener(this);
        add_photo.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);






        changeplacename = (TextView) findViewById(R.id.show_deatils_change_place_name);
        show_photo = (TextView) findViewById(R.id.show_detia_photo);

        chnageplacedescription = (TextView) findViewById(R.id.show_detils_change_description);
        changeplacecategory = (TextView) findViewById(R.id.show_details_change_category) ;
        changeplacecountry = (TextView) findViewById(R.id.show_detial_change_country);
        changeplacecit = (TextView) findViewById(R.id.show_details_change_city);
        changeplaceaddress = (TextView) findViewById(R.id.show_details_changeaddress);
        changeplacelocation = (TextView) findViewById(R.id.show_detia_changelocation);
        changedate = (TextView) findViewById(R.id.show_detia_changedate);

//////////////////////////////////////////////////

        startDate = (TextView) findViewById(R.id.event_startDate);
        endDate = (TextView) findViewById(R.id.event_endDate);
        startTime = (TextView) findViewById(R.id.event_startTime);
        endTime = (TextView) findViewById(R.id.event_endTime);
        days = (TextView) findViewById(R.id.event_days);

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        days.setOnClickListener(this);




/////////////////////////////////////////////


        changeplacename.setOnClickListener(this);
        show_photo.setOnClickListener(this);

        chnageplacedescription.setOnClickListener(this);
        changeplacecategory.setOnClickListener(this);
        changeplacecountry.setOnClickListener(this);
        changeplacecit.setOnClickListener(this);
        changeplaceaddress.setOnClickListener(this);
        changeplacelocation.setOnClickListener(this);
        changedate.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////
        ///////find views by id for textViews


        placeName = (EditText) findViewById(R.id.place_name_edit);
        placeDescription = (EditText) findViewById(R.id.place_description_edit);
        locationAdress = (EditText) findViewById(R.id.place_address_edit);



        spinnerDialog = (Spinner) findViewById(R.id.place_category);
        spinnerDialog1 = (Spinner) findViewById(R.id.sppiner_country);
        spinnerDialog2 = (Spinner) findViewById(R.id.spiner_city);

        categories = new ArrayList<>();
        categoriesIds = new ArrayList<>();

        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();

        imgaeList = new ArrayList<>();
        imageDscription = new ArrayList<>();



        spinnerDialog1.setOnItemSelectedListener(this);
        ///////






        loadeventCategories();
        loadCountries();

    }
    //

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, NavDrw.class));

            return true;
        }
        return true;
    }

        protected void onStart() {
        super.onStart();
    }

        @Override
        protected void onResume() {
            super.onResume();
            //Now lets connect to the API
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.v(this.getClass().getSimpleName(), "onPause()");

            //Disconnect from API onPause()
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
        }

        protected void onStop() {
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
            super.onStop();
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            // Add a default marker in saudi arabia  and move the camera
            addressPoint = new LatLng(23.497085, 44.870015);
            mMarker =  mMap.addMarker(new MarkerOptions().position(addressPoint).title("Default Marker in Saudi Arabia"));
            mMarker.setDraggable(true);
            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {
                    Toast.makeText(getApplicationContext(),"Marker Drag start at \n" +"Latitude: " +currentLatitude+ "\nLongitude: " +currentLongitude,
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                    currentLatitude = marker.getPosition().latitude;
                    currentLongitude = marker.getPosition().longitude;
                    Toast.makeText(getApplicationContext(),"Marker Drop at \n" +"Latitude: " +currentLatitude+ "\nLongitude: " +currentLongitude, Toast.LENGTH_LONG).show();
                }
            });
            mMap.moveCamera(CameraUpdateFactory.newLatLng(addressPoint));
            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setAllGesturesEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);



            //Initialize Google Play Services
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    mMap.setMyLocationEnabled(true);
                    mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                        @Override
                        public boolean onMyLocationButtonClick() {
                            Toast.makeText(AddEvent.this, "Current Location Requested!",
                                    Toast.LENGTH_SHORT).show();
                            buildGoogleApiClient();
                            return false;
                        }
                    });
                } else {
                    //Request Location Permission
                    checkLocationPermission();
                }
            }
            else {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        Toast.makeText(AddEvent.this, "Current Location Requested", Toast.LENGTH_SHORT).show();
                        buildGoogleApiClient();
                        return false;
                    }
                });}

        }

        protected synchronized void buildGoogleApiClient() {
            final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
            if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) || !manager.isProviderEnabled( LocationManager.NETWORK_PROVIDER ) ) {
                displayPromptForEnablingGPS(this);
            }
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();

        }

        public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
        private void checkLocationPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    new AlertDialog.Builder(this)
                            .setTitle("Location Permission Needed")
                            .setMessage("This app needs the Location permission, please accept to use location functionality")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Prompt the user once explanation has been shown
                                    ActivityCompat.requestPermissions(AddEvent.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            MY_PERMISSIONS_REQUEST_LOCATION );
                                }
                            })
                            .create()
                            .show();


                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION );
                }
            }
        }


        public  void displayPromptForEnablingGPS(final Activity activity) {

            String locationProviders = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (locationProviders == null || locationProviders.equals("")) {


                final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
                final String message = "Enable either GPS or any other location"
                        + " service to find current location.  Click OK to go to"
                        + " location services settings to let you do so.";
                final Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_INDEFINITE);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);  // show multiple line
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.startActivity(new Intent(action));
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }}


        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // location-related task you need to do.
                        if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {


                            mMap.setMyLocationEnabled(true);
                            mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                                @Override
                                public boolean onMyLocationButtonClick() {
                                    Toast.makeText(AddEvent.this, "Current Location Requested!",
                                            Toast.LENGTH_SHORT).show();
                                    if (mGoogleApiClient == null) {
                                        buildGoogleApiClient();}
                                    return false;
                                }
                            }); }

                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }














        @Override
        public void onClick(View v) {

            if (v == add_photo){

                    alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Add Event Photo");

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

                    AlertDialog.Builder change = alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            LayoutInflater layoutInflater =
                                    (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            final View addView = layoutInflater.inflate(R.layout.image_one, null);
                            final ImageView imageView = (ImageView) addView.findViewById(R.id.image1);
                            TextView textOut = (TextView) addView.findViewById(R.id.text1);
                            TextView textOut2 = (TextView) addView.findViewById(R.id.text2);

                            imageView.setImageBitmap(bitmap);
                            textOut.setText(adddescription.getText().toString());
                            textOut2.setText(photoPath);

                            TextView buttonRemove = (TextView) addView.findViewById(R.id.change1);
                            buttonRemove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((LinearLayout) addView.getParent()).removeView(addView);
                                    imagesNum--;

                                }
                            });
                            if(imagesNum<5)
                            {container.addView(addView);
                                imagesNum++;}
                            else
                                Toast.makeText(getApplicationContext(), "Can't add more photos!", Toast.LENGTH_LONG).show();

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

            if (v == chnageplacedescription){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event Description\nWrite Up to 300 char, Keep it Clear\nrequired field!!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();
            }

            if (v == changeplacename){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event Name\nKeep it Descriptive and Clear for easy Search\nrequired field!!\npress save after completing all changes", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();
            }
            if (v == changeplacecategory){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event Category that matches your event services\nrequired!!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();
            }

            if (v == changeplacecountry){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event Country\nThis is necessary for user's recommendations\nrequired!!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();
            }

            if (v == changeplacecit){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event City\nThis is necessary for user's recommendations\nrequired!!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();
            }
            if (v == changeplaceaddress){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event Address\nThis is optional for more clarifications\noptional field!!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();
            }

            if (v == show_photo){
                final Snackbar snackbar = Snackbar.make(parentLayout, "Add photos using gallery or camera along with thier descriptions\ncan remove photos before adding the event\nRequired!!", Snackbar.LENGTH_INDEFINITE);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);  // show multiple line
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
            if (v == changeplacelocation){
                final Snackbar snackbar = Snackbar.make(parentLayout, "Choose drag and drop of marker\nor press my location button on the map to display your location on the map and press save when done\nRequired!!", Snackbar.LENGTH_INDEFINITE);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);  // show multiple line
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }


            if (v == changedate){
                Snackbar snackbar = Snackbar.make(parentLayout, "Add Event Date, Time, and days\nselect from picker the corresponding date you want to change to\n press save changes when done!", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);
                snackbar.show();
            }


            if (v == startDate){
                myCalendar = Calendar.getInstance();
                //To show current date in the datepicker
                int mYear = myCalendar.get(Calendar.YEAR);
                int mMonth = myCalendar.get(Calendar.MONTH);
                int mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddEvent.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);

                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        startDate.setText(sdf.format(myCalendar.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Start Date");
                mDatePicker.show();

            }
            if (v == endDate){

                myCalendar = Calendar.getInstance();

                //To show current date in the datepicker
                int mYear = myCalendar.get(Calendar.YEAR);
                int mMonth = myCalendar.get(Calendar.MONTH);
                int mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddEvent.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);

                        String myFormat = "yyyy-MM-dd"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        endDate.setText(sdf.format(myCalendar.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select End Date");
                mDatePicker.show();

            }
            if (v == startTime){
                myCalendar = Calendar.getInstance();
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();
            }

            if (v == endTime){
                myCalendar = Calendar.getInstance();
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select End Time");
                mTimePicker.show();
            }

            if (v == days)
            {
                AlertDialog dialog;
                //following code will be in your activity.java file
                final CharSequence[] items = {" Saturday "," Sunday "," Monday "," Tuesday ", " Thursday ", " Friday "};
                // arraylist to keep the selected items
                final ArrayList<Integer> selectedStrings = new ArrayList<Integer>();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select The Days");
                builder.setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            // indexSelected contains the index of item (of which checkbox checked)
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected,
                                                boolean isChecked) {
                                if (isChecked) {
                                    if(!selectedStrings.contains(indexSelected))
                                        selectedStrings.add(indexSelected);
                                    else
                                        selectedStrings.remove(indexSelected);
                                }
                            }
                        })
                        // Set the action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String item = "";
                                for (int i = 0; i<selectedStrings.size(); i++)
                                {    item = item + items[selectedStrings.get(i)];
                                    if ( i!= selectedStrings.size()-1)
                                        item = item + ", ";
                                }
                                days.setText(item);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Your code when user clicked on Cancel
                                dialog.dismiss();

                            }
                        });

                dialog = builder.create();//AlertDialog dialog; create like this outside onClick
                dialog.show();

            }

            if (v == changeLocationDetails){
                String placeNamechanged = placeName.getText().toString().trim();
                String placeDescriptionchanged = placeDescription.getText().toString().trim();
                int chosenCategory = categoriesIds.get(spinnerDialog.getSelectedItemPosition());
                int chosenCountry = countryIds.get(spinnerDialog1.getSelectedItemPosition());
                int chosenCity = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

                String placeAddressChanged = locationAdress.getText().toString().trim();
                Double latitude = currentLatitude;
                Double longitude  = currentLongitude;

                String startDate2 = startDate.getText().toString().trim();
                String endDate2 = endDate.getText().toString().trim();
                String startTime2  = startTime.getText().toString().trim();
                String endTime2  = endTime.getText().toString().trim();
                String days2 = days.getText().toString().trim();

                if(placeNamechanged.length()<=0) {
                    placeName.requestFocus();
                    placeName.setError("Required field, Please Enter Event Name!");
                    placeName.addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable s) {
                            if (placeName.getText().toString().length()<= 0) {
                                placeName.setError("*required field, Please Enter Event Name!");
                            } else {
                                placeName.setError(null);
                            }
                        }
                        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                        public void onTextChanged(CharSequence s, int start, int before, int count){
                            placeName.setError(null);
                        }
                    });
                }
                else if(placeDescriptionchanged.length()<=0){
                    placeDescription.requestFocus();
                    placeDescription.setError("Required field, Please Enter Event Description!");
                    placeDescription.addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable s) {
                            if (placeDescription.getText().toString().length()<= 0) {
                                placeDescription.setError("*required field, Please Enter Event Description!");
                            } else {placeDescription.setError(null);}
                        }
                        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                        public void onTextChanged(CharSequence s, int start, int before, int count){
                            placeName.setError(null);
                        }
                    });
                }
                else if(startDate2.equals("start date")){
                    startDate.requestFocus();
                    showToast("Please Select a Start Date for the Event!");}
                else if(endDate2.equals("end date"))
                {endDate.requestFocus();
                    showToast("Please Select an End Date for the Event!");}
                else if (container.getChildCount()<=0)
                {add_photo.requestFocus();
                    showToast("Please Add at least one photo for the Event!");}
                else if(placeAddressChanged.length()<=0){
                    locationAdress.requestFocus();
                    locationAdress.setError("Required field, Please Enter Event Address!");
                    locationAdress.addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable s) {
                            if (locationAdress.getText().toString().length()<= 0) {
                                locationAdress.setError("Required field, Please Enter Event Address!");
                            } else {
                                locationAdress.setError(null);
                            }
                        }
                        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                        public void onTextChanged(CharSequence s, int start, int before, int count){
                            locationAdress.setError(null);
                        }
                    });
                }
                else if(latitude==0.0 && longitude ==0.0){
                    changeplaceaddress.requestFocus();
                    showToast("Please Insert a Location for the Event!");
                }
                else {
                    progressDialog.setMessage("Uploading Event Details...");
                    progressDialog.show();
                    addEventDate();
                    addEventLocation();

                    final int interval = 7000; // 3 Second
                     Handler handler = new Handler();
                     Runnable runnable = new Runnable(){
                        public void run() {
                            if(insertedDateId != 0 && insertedLocationId != 0)
                                addeventDetails();

                        }
                    };
                    handler.postAtTime(runnable, System.currentTimeMillis()+interval);
                    handler.postDelayed(runnable, interval);



                }

            }





        }
        /////////////////////////



        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.main,menu);
            return super.onCreateOptionsMenu(menu);
        }




        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int countryId = countryIds.get(position);
            loadCities(countryId);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onConnected(@Nullable Bundle bundle) {

            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
          /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
            if (connectionResult.hasResolution()) {
                try {
                    // Start an Activity that tries to resolve the error
                    connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
                } catch (IntentSender.SendIntentException e) {
                    // Log the error
                    e.printStackTrace();
                }
            } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
                Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
            }


        }

        @Override
        public void onLocationChanged(Location location) {
            mLastLocation = location;
            if (mMarker != null) {
                mMarker.remove();
            }

            //Place current location marker
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            LatLng latLng = new LatLng(currentLatitude, currentLongitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Your Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            mMarker = mMap.addMarker(markerOptions);
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
        }


        private void showToast(String meg){
            final String message = meg;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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




        // this is for loading all countries
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

                        ArrayAdapter adapter = new ArrayAdapter<String>(AddEvent.this, android.R.layout.simple_spinner_dropdown_item, countries);
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

            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
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
                        ArrayAdapter adapter = new ArrayAdapter<String>(AddEvent.this, android.R.layout.simple_spinner_dropdown_item, cities);
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

            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }

        // this is for loading all countries
        private void loadeventCategories() {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_Event_CATEGORIES, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);

                        JSONArray arr = obj.getJSONArray("categories");

                        for (int i = 0; i < arr.length(); i++) {
                            categories.add(arr.getJSONObject(i).getString("category_type"));

                            categoriesIds.add(arr.getJSONObject(i).getInt("id"));
                        }

                        ArrayAdapter adapter = new ArrayAdapter<String>(AddEvent.this, android.R.layout.simple_spinner_dropdown_item, categories);
                        spinnerDialog.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }







        public void addEventDate() {
            final String startDate2 = startDate.getText().toString().trim();
            final String endDate2 = endDate.getText().toString().trim();
            final String startTime2  = startTime.getText().toString().trim();
            final String endTime2  = endTime.getText().toString().trim();
            final String days2 = days.getText().toString().trim();


            StringRequest send = new StringRequest(Request.Method.POST,
                    Constants.URL_ADD_EVENT_DATE ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    insertedDateId = obj.getInt("inserted_date_id");
                                    //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


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
                    params.put("start_date", startDate2);
                    params.put("end_date", endDate2);
                    params.put("start_time", startTime2);
                    params.put("end_time", endTime2);
                    params.put("days", days2);

                    return params;
                }

            };

            MySingleton.getInstance(this).addToRequestQueue(send);

        }

        public void addEventLocation() {
            final String placeAddressChanged = locationAdress.getText().toString().trim();
            final Double latitude = currentLatitude;
            final Double longitude  = currentLongitude;




            StringRequest send = new StringRequest(Request.Method.POST,
                    Constants.URL_ADD_EVENT_LOCATION ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    insertedLocationId = obj.getInt("location_id");
                                   // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


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
                    params.put("address", placeAddressChanged);
                    params.put("latitude", latitude+"");
                    params.put("longitude", longitude+"");
                    return params;
                }

            };

            MySingleton.getInstance(this).addToRequestQueue(send);

        }

        public void addeventDetails() {
            final int ownerId = userId;
            final int dateId = insertedDateId;
            final int locationId = insertedLocationId;


            final String placeNamechanged = placeName.getText().toString().trim();
            final String placeDescriptionchanged = placeDescription.getText().toString().trim();
            final int chosenCategory = categoriesIds.get(spinnerDialog.getSelectedItemPosition());
            final int chosenCountry = countryIds.get(spinnerDialog1.getSelectedItemPosition());
            final int chosenCity = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

            StringRequest send = new StringRequest(Request.Method.POST,
                    Constants.URL_ADD_EVENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    insertedEventId = obj.getInt("inserted_event_id");

                                    uploadPhoto(insertedEventId);

                                    addNotification(0, userId, userName+" added a new event "+placeName.getText().toString().trim()+" to your city ", "event add", 0, insertedEventId, 0, 0, 0, 0 );

                                    finish();
                                    overridePendingTransition(0, 0);
                                    Intent myIntent3 = new Intent(context, BusinessProfileEvents.class);
                                    startActivity(myIntent3);
                                    overridePendingTransition(0, 0);

                                    //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
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
                    params.put("name", placeNamechanged);
                    params.put("description", placeDescriptionchanged);
                    params.put("owner_id", ownerId+"");
                    params.put("location_id", locationId+"");
                    params.put("date_id", dateId+"");
                    params.put("category_id", chosenCategory+"");
                    params.put("country_id", chosenCountry+"");
                    params.put("city_id", chosenCity+"");
                    return params;
                }

            };

            MySingleton.getInstance(this).addToRequestQueue(send);

        }


        final MyCommand myCommand = new MyCommand(this);
        public void uploadPhoto(int insertedEventId1) {
            final int insertedeventid = insertedEventId1;

            for(int index = 0; index<((ViewGroup)container).getChildCount(); ++index){
                View nextChild = ((ViewGroup)container).getChildAt(index);

                TextView text = (TextView)nextChild.findViewById(R.id.text1);
                final String st = text.getText().toString().trim();

                TextView text2 = (TextView)nextChild.findViewById(R.id.text2);
                final String st2 = text2.getText().toString().trim();

                File tempFile = new File(st2);
                final Bitmap bitmap2 = BitmapFactory.decodeFile(tempFile.getAbsolutePath());

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_UPLOAD_PHOTO_Event,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if (!obj.getBoolean("error")) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Event Created Successfully", Toast.LENGTH_SHORT).show();
                                    } else {

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }}}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        String image = getStringImage(bitmap2);
                        Map<String, String> params = new HashMap<>();
                        params.put("event_id", insertedeventid+"");
                        params.put("image_description", st);
                        params.put("image", image);
                        return params;
                    }
                };

                myCommand.add(stringRequest);
            }
            myCommand.execute();
        }


        public void addNotification(int targeId, int generatorId, String notificationmessage, String notiType, int placeid, int eventid, int reviewid, int reprtedreviewid, int likeid , int bookmarkid  ) {
            final int targetUserID= targeId;
            final int generatorID= generatorId;
            final String notificationMessage = notificationmessage;
            final String type = notiType;
            final int placeID2 = placeid;
            final int eventID2 = eventid;
            final int reviewID2 = reviewid;
            final int reported_reviewID2 = reprtedreviewid;
            final int likeID2 = likeid;
            final int bookmark2 = bookmarkid;


            StringRequest send = new StringRequest(Request.Method.POST,
                    Constants.URL_ADD_NOTIFICATION_PLACE_ADD,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    JSONArray arr = obj.getJSONArray("user");
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject placeDetails = arr.getJSONObject(i);

                                        //showToast(placeDetails.getInt("inserted_notification_id") + "");
                                        //showToast(placeDetails.getString("message"));
                                    }

                                } else {
                                    showToast(obj.getString("message"));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("target_user_id", targetUserID+"");
                    params.put("generator_user_id", generatorID+"");
                    params.put("notification_message", notificationMessage);
                    params.put("type", type);
                    params.put("place_id", placeID2+"");
                    params.put("event_id", eventID2+"");
                    params.put("review_id", reviewID2+"");
                    params.put("reported_review_id", reported_reviewID2+"");
                    params.put("like_id", likeID2+"");
                    params.put("bookmark_id", bookmark2+"");





                    return params;
                }

            };

            MySingleton.getInstance(context).addToRequestQueue(send);

        }

    }
