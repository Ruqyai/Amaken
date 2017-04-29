package com.amakenapp.website.amakenapp.activities;

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
        import android.os.Handler;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Base64;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.AlphaAnimation;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import android.widget.Toast;

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
        import com.kosalgeek.android.photoutil.CameraPhoto;
        import com.kosalgeek.android.photoutil.GalleryPhoto;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.util.HashMap;
        import java.util.Map;

        import de.hdodenhof.circleimageview.CircleImageView;

        import static com.amakenapp.website.amakenapp.R.id.ratingBar;

public class AddReview extends AppCompatActivity implements View.OnClickListener{



    private AlertDialog.Builder alertDialog;
    private AlertDialog dialog;
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
        setContentView(R.layout.activity_add_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_review_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        review_type = getIntent().getExtras().getString("REVIEW_TYPE");
        if(review_type.equals(Constants.STRING_TYPE_EVENT))
            eventID = getIntent().getExtras().getInt("EVENT_ID");
        else if (review_type.equals(Constants.STRING_TYPE_PLACE))
            placeID = getIntent().getExtras().getInt("PLACE_ID");




        final Context x = this;
        userProfilePic = (CircleImageView) findViewById(R.id.imageView3);
        reviewText = (EditText) findViewById(R.id.editText2) ;
        cancel = (Button) findViewById(R.id.button);
        post = (Button) findViewById(R.id.button2);
        add_photo = (ImageButton) findViewById(R.id.imageButton);
        container = (LinearLayout)findViewById(R.id.container);
        progressDialog = new ProgressDialog(this);
        ratingbar1 = (RatingBar) findViewById(ratingBar);


        sharedPrefManager = SharedPrefManager.getInstance(this);
        userId = sharedPrefManager.getUserId();
        userType= sharedPrefManager.getUserType();
        String userProfilePicUrl=sharedPrefManager.getKeyUserProfilePicUrl();
        String userProfilePicIdTimeStamp = sharedPrefManager.getKeyUserProfilePicUrlTimeStamp();


        userProfilePicId = sharedPrefManager.getUserProfilePicId();

        if(userProfilePicId==0 )
        {if(userType== Constants.CODE_BUSINESS_USER)
            userProfilePic.setImageResource(R.drawable.business1);
        else if(userType==Constants.CODE_NORMAL_USER)
            userProfilePic.setImageResource(R.drawable.ic_person);
        }
        else
            Glide.with(getApplicationContext()).load(userProfilePicUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(userProfilePic);



        add_photo.setOnClickListener(this);
        cancel.setOnClickListener(this);
        post.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (v == add_photo){
            v.startAnimation(buttonClick);

            alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Add Review Photo");

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

        if (v == cancel){
            v.startAnimation(buttonClick);
            finish();
            overridePendingTransition(0,0);
        }

        if (v == post){
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
                progressDialog.setMessage("Posting Review Details...");
                progressDialog.show();

                if(review_type.equals(Constants.STRING_TYPE_EVENT))
                {
                     addreviewtDetailsEvent();
                }

                else if (review_type.equals(Constants.STRING_TYPE_PLACE))
                {
                    addreviewtDetailsPlace();


                }


            }

        }

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





    final MyCommand myCommand1 = new MyCommand(this);
    public void uploadPhotoReviewPlace(int insertedreviewId1, int placeID) {
        final int placeId = placeID;
        final int insertedreviewtid = insertedreviewId1;

        for(int index = 0; index<((ViewGroup)container).getChildCount(); ++index){
            View nextChild = ((ViewGroup)container).getChildAt(index);

            TextView text = (TextView)nextChild.findViewById(R.id.text1);
            final String st = text.getText().toString().trim();

            TextView text2 = (TextView)nextChild.findViewById(R.id.text2);
            final String st2 = text2.getText().toString().trim();

            File tempFile = new File(st2);
            final Bitmap bitmap2 = BitmapFactory.decodeFile(tempFile.getAbsolutePath());

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_UPLOAD_PHOTO_REVIEW_PLACE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    progressDialog.dismiss();
                                    showToast("Review and "+obj.getString("message"));
                                } else {
                                    progressDialog.dismiss();
                                    showToast("Review and "+obj.getString("message"));
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
                    params.put("review_id", insertedreviewtid+"");
                    params.put("place_id", placeId+"");
                    params.put("image_description", st);
                    params.put("image", image);
                    return params;
                }
            };

            myCommand1.add(stringRequest);
        }
        myCommand1.execute();
    }




    final MyCommand myCommand2 = new MyCommand(this);
    public void uploadPhotoReviewEvent(int insertedreviewId1, int eventID) {
        final int eventid = eventID;
        final int insertedreviewid = insertedreviewId1;

        for(int index = 0; index<((ViewGroup)container).getChildCount(); ++index){
            View nextChild = ((ViewGroup)container).getChildAt(index);

            TextView text = (TextView)nextChild.findViewById(R.id.text1);
            final String st = text.getText().toString().trim();

            TextView text2 = (TextView)nextChild.findViewById(R.id.text2);
            final String st2 = text2.getText().toString().trim();

            File tempFile = new File(st2);
            final Bitmap bitmap2 = BitmapFactory.decodeFile(tempFile.getAbsolutePath());

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_UPLOAD_PHOTO_REVIEW_EVENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    progressDialog.dismiss();
                                    showToast("Review and "+obj.getString("message"));
                                } else {
                                    progressDialog.dismiss();
                                    showToast("Review and "+obj.getString("message"));
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
                    params.put("review_id", insertedreviewid+"");
                    params.put("event_id", eventid+"");
                    params.put("image_description", st);
                    params.put("image", image);
                    return params;
                }
            };

            myCommand2.add(stringRequest);
        }
        myCommand2.execute();
    }





    public void addreviewtDetailsPlace() {
        final int userID= userId;
        final int placeID2 = placeID;
        final String reviewText2 = reviewText.getText().toString().trim();
        final Float reviewRating2 = ratingbar1.getRating();

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_INSERT_REVIEW_ON_PLACE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                insertedReviewID = obj.getInt("inserted_review_id");
                                if(imagesNum>0)
                                { uploadPhotoReviewPlace(insertedReviewID, placeID);
                                finish();
                                overridePendingTransition(0, 0);
                                Intent myIntent3 = new Intent(getApplicationContext(), ProfileReviews.class);
                                startActivity(myIntent3);
                                overridePendingTransition(0, 0);}
                                else
                                   {progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                       finish();
                                       overridePendingTransition(0, 0);
                                       Intent myIntent3 = new Intent(getApplicationContext(), ProfileReviews.class);
                                       startActivity(myIntent3);
                                       overridePendingTransition(0, 0);}
                            } else {
                                progressDialog.dismiss();
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("review_text", reviewText2);
                params.put("rate_value", reviewRating2+"");
                params.put("place_id", placeID2+"");
                params.put("user_id", userID+"");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }


    public void addreviewtDetailsEvent() {
        final int userID= userId;
        final int eventID2 = eventID;
        final String reviewText2 = reviewText.getText().toString().trim();
        final Float reviewRating2 = ratingbar1.getRating();

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_INSERT_REVIEW_ON_EVENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                insertedReviewID = obj.getInt("inserted_review_id");
                                if(imagesNum>0){
                                 uploadPhotoReviewEvent(insertedReviewID, eventID);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    Intent myIntent3 = new Intent(getApplicationContext(), ProfileReviews.class);
                                    startActivity(myIntent3);
                                    overridePendingTransition(0, 0);}
                                else
                                {progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                    finish();
                                    overridePendingTransition(0, 0);
                                    Intent myIntent3 = new Intent(getApplicationContext(), ProfileReviews.class);
                                    startActivity(myIntent3);
                                    overridePendingTransition(0, 0);}
                            } else {
                                progressDialog.dismiss();
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("review_text", reviewText2);
                params.put("rate_value", reviewRating2+"");
                params.put("event_id", eventID2+"");
                params.put("user_id", userID+"");
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }

}

