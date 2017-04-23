package com.amakenapp.website.amakenapp.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
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
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditEventActivity extends FragmentActivity implements
        OnMapReadyCallback,
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        ConnectionCallbacks,
        OnConnectionFailedListener,
        LocationListener {

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


    private double currentLatitude;
    private double currentLongitude;
    Context context;

    private LatLng addressPoint;
    SharedPrefManager sharedPrefManager;


    private static int eventID;
    private static int userId;
    private ProgressDialog progressDialog;



    private TextView imagedescription1, imagedescription2, imagedescription3, imagedescription4, imagedescription5;
    private TextView changeplacename, chnageplacedescription, changeplacecategory, changeplacecountry, changeplacecit, changeplaceaddress, changeplacelocation, changedate;


    private static TextView startDate, endDate, startTime, endTime, days;

    private EditText placeName,
                    placeDescription,
                    locationAdress;

    Spinner spinnerDialog, spinnerDialog1, spinnerDialog2;
    private ArrayList<String> countries, cities, categories;
    private ArrayList<Integer> countryIds, citiesIds, categoriesIds;

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5 ;
    private ImageButton filpNext, flipPrevious, getmylocation;
    private Button changeImageButton1,
                   changeImageButton2,
                   changeImageButton3,
                    changeImageButton4,
                    changeImageButton5,
                    changePlaceDetails,
                    changeLocationDetails,
                    changeeventdate;
    private LinearLayout loading;



    private ViewFlipper viewFlipper;
    private static int imagesNum, locationID, dateID, categoryID, countryID, cityID, imageID1, imageID2, imageID3, imageID4, imageID5;
    private static String imagedescriptionText1, imagedescriptionText2, imagedescriptionText3, imagedescriptionText4, imagedescriptionText5;
    private static String imageviewString1, imageviewString2, imageviewString3, imageviewString4, imageviewString5, placeName1, placeDescription1;


    private View  parentLayout, view1, view2, view3, view4, view5;

    private AlertDialog.Builder alertDialog;
    FragmentManager fm = getSupportFragmentManager();
    private static Calendar myCalendar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        context = this.getApplicationContext();
        parentLayout = findViewById(R.id.root_view);
        loading = (LinearLayout) findViewById(R.id.linlaHeaderProgress_EDITEVENT);
        loading.setVisibility(View.VISIBLE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_edit);
        mapFragment.getMapAsync(this);

        //get user id from shared preferences
        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();
        eventID = getIntent().getExtras().getInt("EVENT_ID");

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


        changePlaceDetails = (Button) findViewById(R.id.change_place_details);
        changeLocationDetails = (Button) findViewById(R.id.change_address_details);
        changeeventdate = (Button) findViewById(R.id.change_date_details);

        progressDialog = new ProgressDialog(this);


        changeImageButton1.setOnClickListener(this);
        changeImageButton2.setOnClickListener(this);
        changeImageButton3.setOnClickListener(this);
        changeImageButton4.setOnClickListener(this);
        changeImageButton5.setOnClickListener(this);

        changePlaceDetails.setOnClickListener(this);
        changeLocationDetails.setOnClickListener(this);
        changeeventdate.setOnClickListener(this);


        changeplacename = (TextView) findViewById(R.id.show_deatils_change_place_name);
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
        placeName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (placeName.getText().toString().length()<= 0) {
                    placeName.setError("*required field, Please Enter Event Name!");
                    changePlaceDetails.setEnabled(false);
                } else {
                    placeName.setError(null);
                    changePlaceDetails.setEnabled(true);

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                placeName.setError(null);
            }
        });
        placeDescription.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (placeDescription.getText().toString().length()<= 0) {
                    placeDescription.setError("*required field, Please Enter Event Description!");
                    changePlaceDetails.setEnabled(false);
                } else {
                    placeDescription.setError(null);
                    changePlaceDetails.setEnabled(true);

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                placeName.setError(null);
            }
        });

        spinnerDialog = (Spinner) findViewById(R.id.place_category);
        spinnerDialog1 = (Spinner) findViewById(R.id.sppiner_country);
        spinnerDialog2 = (Spinner) findViewById(R.id.spiner_city);

        categories = new ArrayList<>();
        categoriesIds = new ArrayList<>();

        countries = new ArrayList<>();
        countryIds = new ArrayList<>();

        cities = new ArrayList<>();
        citiesIds = new ArrayList<>();

        spinnerDialog1.setOnItemSelectedListener(this);
        ///////

        flipPrevious = (ImageButton) findViewById(R.id.flipp_previous_edit);
        filpNext = (ImageButton) findViewById(R.id.flipp_next_edit);




        filpNext.setOnClickListener(this);
        flipPrevious.setOnClickListener(this);

        eventGalleryLoading(eventID);
        loadeventCategories();
        loadCountries();
        eventInformationLoading(eventID, userId);




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
        mMap.setMyLocationEnabled(true);
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
                        Toast.makeText(EditEventActivity.this, "This is button clicked",
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
                    Toast.makeText(EditEventActivity.this, "Current Location Requested", Toast.LENGTH_SHORT).show();
                    buildGoogleApiClient();
                    return false;
                }
            });}

    }

    protected synchronized void buildGoogleApiClient() {
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
                                ActivityCompat.requestPermissions(EditEventActivity.this,
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
                                Toast.makeText(EditEventActivity.this, "Current Location Requested!",
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

        if (v == filpNext){
            viewFlipper.setInAnimation(this, R.anim.in_from_right);
            viewFlipper.setOutAnimation(this, R.anim.out_to_left);
            viewFlipper.showNext();

        }
        if (v == flipPrevious){
            viewFlipper.setInAnimation(this, R.anim.in_from_left);
            viewFlipper.setOutAnimation(this, R.anim.out_to_right);
            viewFlipper.showPrevious();

        }

        if (v == chnageplacedescription){
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event  Description\nWrite Up to 300 char, Keep it Clear\nrequired field!!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();
        }

        if (v == changeplacename){
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event Name\nKeep it Descriptive and Clear for easy Search\nrequired field!!\npress save after completing all changes", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();
        }
        if (v == changeplacecategory){
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event Category that matches your event services\nrequired!!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();
        }

        if (v == changeplacecountry){
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event Country\nThis is necessary for user's recommendations\nrequired!!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();
        }

        if (v == changeplacecit){
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event City\nThis is necessary for user's recommendations\nrequired!!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();
        }
        if (v == changeplaceaddress){
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event Address\nThis is optional for more clarifications\noptional field!!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
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
            Snackbar snackbar = Snackbar.make(parentLayout, "Change Event Date, Time, and days\nselect from picker the corresponding date you want to change to\n press save changes when done!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(6);
            snackbar.show();
        }

        if (v == changeImageButton1){
            // Next screen comes in from right.
            Toast.makeText(getApplication(), "images id "+ imageID1, Toast.LENGTH_LONG).show();

        }
        if (v == changeImageButton2){
            // Next screen comes in from right.
            Toast.makeText(getApplication(), "images id "+ imageID2, Toast.LENGTH_LONG).show();

        }if (v == changeImageButton3){
            // Next screen comes in from right.
            Toast.makeText(getApplication(), "images id "+ imageID3, Toast.LENGTH_LONG).show();

        }if (v == changeImageButton4){
            // Next screen comes in from right.
            Toast.makeText(getApplication(), "images id "+ imageID4, Toast.LENGTH_LONG).show();

        }if (v == changeImageButton5){
            // Next screen comes in from right.
            Toast.makeText(getApplication(), "images id "+ imageID5, Toast.LENGTH_LONG).show();

        }

        if (v == startDate){
            myCalendar = Calendar.getInstance();
            //To show current date in the datepicker
            int mYear = myCalendar.get(Calendar.YEAR);
            int mMonth = myCalendar.get(Calendar.MONTH);
            int mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mDatePicker;
            mDatePicker = new DatePickerDialog(EditEventActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            mDatePicker = new DatePickerDialog(EditEventActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            mDatePicker.setTitle("Select Start Date");
            mDatePicker.show();

        }
        if (v == startTime){
            myCalendar = Calendar.getInstance();
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(EditEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    startTime.setText( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }

        if (v == endTime){
            myCalendar = Calendar.getInstance();
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(EditEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    endTime.setText( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }

        if (v == days){
            AlertDialog dialog;
             //following code will be in your activity.java file
            final CharSequence[] items = {" Easy "," Medium "," Hard "," Very Hard "};
            // arraylist to keep the selected items
            final ArrayList seletedItems=new ArrayList();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select The Difficulty Level");
            builder.setMultiChoiceItems(items, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        // indexSelected contains the index of item (of which checkbox checked)
                        @Override
                        public void onClick(DialogInterface dialog, int indexSelected,
                                            boolean isChecked) {
                            if (isChecked) {
                                // If the user checked the item, add it to the selected items
                                // write your code when user checked the checkbox
                                seletedItems.add(indexSelected);
                            } else if (seletedItems.contains(indexSelected)) {
                                // Else, if the item is already in the array, remove it
                                // write your code when user Uchecked the checkbox
                                seletedItems.remove(Integer.valueOf(indexSelected));
                            }
                        }
                    })
                    // Set the action buttons
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //  Your code when user clicked on OK
                            //  You can write the code  to save the selected item here

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

        if (v == changePlaceDetails){
            String placeNamechanged = placeName.getText().toString().trim();
            String placeDescriptionchanged = placeDescription.getText().toString().trim();
            int chosenCategory = categoriesIds.get(spinnerDialog.getSelectedItemPosition());
            int chosenCountry = countryIds.get(spinnerDialog1.getSelectedItemPosition());
            int chosenCity = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

            Boolean con1 = placeNamechanged.equals(placeName1);
            Boolean con2 = placeDescriptionchanged.equals(placeDescription1);
            Boolean con3 = categoryID == chosenCategory;
            Boolean con4 = countryID == chosenCountry;
            Boolean con5 = cityID == chosenCity;


       /* if ( con1 && con2 && con3 && con4 && con5 )
        Toast.makeText(getApplication(), "No Changes Detected!!" + categoriesIds.get(spinnerDialog.getSelectedItemPosition()), Toast.LENGTH_LONG).show();

        else
        Toast.makeText(getApplication(), "saving "+ con1+ " "+ con2+ " "+con3+ " "+con4+ " "+con5 + " " +placeNamechanged + " "+ placeDescriptionchanged + " " + chosenCategory + " " + chosenCountry+ " " + chosenCity, Toast.LENGTH_LONG).show();
*/

            editeventDetails(eventID);


        }

        if (v == changeLocationDetails){
            editEventAddress();

        }
        if (v == changeeventdate){
            Toast.makeText(getApplication(), "saving ", Toast.LENGTH_LONG).show();

            //editPlaceAddress(placeID);

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











    public void eventGalleryLoading ( int eventId){
            final int eventID = eventId;

            StringRequest send = new StringRequest(Request.Method.GET,
                    Constants.URL_EVENT_GALLERY + eventID,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    loading.setVisibility(View.GONE);
                                    JSONArray arr = obj.getJSONArray("event_gallery");
                                    imagesNum = arr.length();
                                    for (int i = 0; i < arr.length(); i++) {

                                        if (imagesNum == 1) {
                                            JSONObject url = arr.getJSONObject(0);

                                            imageID1 = url.getInt("image_id");
                                            imagedescriptionText1 = url.getString("image_description");
                                            imageviewString1 = url.getString("image_url");


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

                                        }
                                    }

                                    if (imagesNum == 1) {
                                        imagedescription1.setText(imagedescriptionText1);
                                        Glide.with(getApplicationContext()).load(imageviewString1).into(imageView1);
                                        viewFlipper.addView(view1);


                                    }
                                    if (imagesNum == 2) {
                                        imagedescription1.setText(imagedescriptionText1);
                                        Glide.with(getApplicationContext()).load(imageviewString1).into(imageView1);

                                        imagedescription2.setText(imagedescriptionText2);
                                        Glide.with(getApplicationContext()).load(imageviewString2).into(imageView2);

                                        viewFlipper.addView(view1);
                                        viewFlipper.addView(view2);

                                    }
                                    if (imagesNum == 3) {

                                        imagedescription1.setText(imagedescriptionText1);
                                        Glide.with(getApplicationContext()).load(imageviewString1).into(imageView1);

                                        imagedescription2.setText(imagedescriptionText2);
                                        Glide.with(getApplicationContext()).load(imageviewString2).into(imageView2);

                                        imagedescription3.setText(imagedescriptionText3);
                                        Glide.with(getApplicationContext()).load(imageviewString3).into(imageView3);

                                        viewFlipper.addView(view1);
                                        viewFlipper.addView(view2);
                                        viewFlipper.addView(view3);
                                    }
                                    if (imagesNum == 4) {
                                        imagedescription1.setText(imagedescriptionText1);
                                        Glide.with(getApplicationContext()).load(imageviewString1).into(imageView1);

                                        imagedescription2.setText(imagedescriptionText2);
                                        Glide.with(getApplicationContext()).load(imageviewString2).into(imageView2);

                                        imagedescription3.setText(imagedescriptionText3);
                                        Glide.with(getApplicationContext()).load(imageviewString3).into(imageView3);

                                        imagedescription4.setText(imagedescriptionText4);
                                        Glide.with(getApplicationContext()).load(imageviewString4).into(imageView4);

                                        viewFlipper.addView(view1);
                                        viewFlipper.addView(view2);
                                        viewFlipper.addView(view3);
                                        viewFlipper.addView(view4);

                                    }

                                    if (imagesNum == 5) {
                                        imagedescription1.setText(imagedescriptionText1);
                                        Glide.with(getApplicationContext()).load(imageviewString1).into(imageView1);

                                        imagedescription2.setText(imagedescriptionText2);
                                        Glide.with(getApplicationContext()).load(imageviewString2).into(imageView2);

                                        imagedescription3.setText(imagedescriptionText3);
                                        Glide.with(getApplicationContext()).load(imageviewString3).into(imageView3);

                                        imagedescription4.setText(imagedescriptionText4);
                                        Glide.with(getApplicationContext()).load(imageviewString4).into(imageView4);

                                        imagedescription5.setText(imagedescriptionText5);
                                        Glide.with(getApplicationContext()).load(imageviewString5).into(imageView5);

                                        viewFlipper.addView(view1);
                                        viewFlipper.addView(view2);
                                        viewFlipper.addView(view3);
                                        viewFlipper.addView(view4);
                                        viewFlipper.addView(view5);


                                    }


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

            };
            MySingleton.getInstance(this).addToRequestQueue(send);
        }



    public void eventInformationLoading(int eventId, int userId) {
        final int eventID = eventId;
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_EVENT_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                obj.getInt("event_id");

                                locationID = obj.getInt("location_id");
                                dateID = obj.getInt("event_date_id");

                                placeName1 = obj.getString("event_name");
                                placeName.setText(obj.getString("event_name"));
                                placeDescription1 = obj.getString("event_description");
                                placeDescription.setText(obj.getString("event_description"));

                                categoryID = obj.getInt("event_category_id");


                                switch (categoryID) {
                                    case 1:spinnerDialog.setSelection(0);break;
                                    case 2:spinnerDialog.setSelection(1);break;
                                    case 3:spinnerDialog.setSelection(2);break;
                                    case 4:spinnerDialog.setSelection(3);break;
                                    case 5:spinnerDialog.setSelection(4);break;
                                    case 6:spinnerDialog.setSelection(5);break;
                                    case 7:spinnerDialog.setSelection(6);break;
                                    case 8:spinnerDialog.setSelection(7);break;
                                    case 9:spinnerDialog.setSelection(8);break;
                                    case 10:spinnerDialog.setSelection(9);break;
                                    case 11:spinnerDialog.setSelection(10);break;
                                    case 12:spinnerDialog.setSelection(11);break;
                                    default:break;
                                }
                                countryID = obj.getInt("country_id");

                                switch (countryID) {
                                    case 1:spinnerDialog1.setSelection(0);break;
                                    case 2:spinnerDialog1.setSelection(1);break;
                                    case 3:spinnerDialog1.setSelection(2);break;
                                    case 4:spinnerDialog1.setSelection(3);break;
                                    case 5:spinnerDialog1.setSelection(4);break;
                                    case 6:spinnerDialog1.setSelection(5);break;
                                    case 7:spinnerDialog1.setSelection(6);break;
                                    case 8:spinnerDialog1.setSelection(7);break;
                                    case 9:spinnerDialog1.setSelection(8);break;
                                    case 10:spinnerDialog1.setSelection(9);break;
                                    case 11:spinnerDialog1.setSelection(10);break;
                                    case 12:spinnerDialog1.setSelection(11);break;
                                    default:break;
                                }
                                // todo needs fixing
                                cityID = obj.getInt("city_id");


                                locationAdress.setText(obj.getString("address"));
                                currentLatitude = obj.getDouble("latitude");
                                currentLongitude = obj.getDouble("longitude");
                                mMap.clear();
                                addressPoint = new LatLng(currentLatitude, currentLongitude);
                                mMarker = mMap.addMarker(new MarkerOptions().position(addressPoint).title(obj.getString("address")));
                                mMarker.setDraggable(true);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(addressPoint));
                                mMap.getUiSettings().setZoomControlsEnabled(true);
                                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                                    @Override
                                    public void onMarkerDragStart(Marker marker) {
                                        Toast.makeText(getApplicationContext(),"Marker Drag Start..."+marker.getPosition().latitude+"..."+marker.getPosition().longitude,
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
                params.put("event_id", eventID + "");
                params.put("user_id", userID + "");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

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

                    ArrayAdapter adapter = new ArrayAdapter<String>(EditEventActivity.this, android.R.layout.simple_spinner_dropdown_item, countries);
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
                    ArrayAdapter adapter = new ArrayAdapter<String>(EditEventActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);
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

                    ArrayAdapter adapter = new ArrayAdapter<String>(EditEventActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);
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



    public void editeventDetails(int eventId) {
        final int eventID = eventId;
        final String placeNamechanged = placeName.getText().toString().trim();
        final String placeDescriptionchanged = placeDescription.getText().toString().trim();
        final int chosenCategory = categoriesIds.get(spinnerDialog.getSelectedItemPosition());
        final int chosenCountry = countryIds.get(spinnerDialog1.getSelectedItemPosition());
        final int chosenCity = citiesIds.get(spinnerDialog2.getSelectedItemPosition());

        progressDialog.setMessage("Updating Event Details...");
        progressDialog.show();

        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_EDIT_EVENT_DETAILS +eventID ,
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
                params.put("name", placeNamechanged);
                params.put("description", placeDescriptionchanged);
                params.put("category_id", chosenCategory+"");
                params.put("country_id", chosenCountry+"");
                params.put("city_id", chosenCity+"");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }

    public void editEventAddress() {
        final String placeAddressChanged = locationAdress.getText().toString().trim();
        final Double latitude = currentLatitude;
        final Double longitude  = currentLongitude;


        progressDialog.setMessage("Updating Event Location...");
        progressDialog.show();

        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_EDIT_PLACE_LOCATION +locationID ,
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
                params.put("address", placeAddressChanged);
                params.put("latitude", latitude+"");
                params.put("longitude", longitude+"");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }
}
