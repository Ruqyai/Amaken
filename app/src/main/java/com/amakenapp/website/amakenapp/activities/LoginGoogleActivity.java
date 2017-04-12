package com.amakenapp.website.amakenapp.activities;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amakenapp.website.amakenapp.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class LoginGoogleActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    private String UName;
   private String UEmail;
   private Uri UImage;
   private String UId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_google);


        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
       // signInButton.setSize(SignInButton.SIZE_STANDARD);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            UName=acct.getDisplayName();
            UEmail=acct.getEmail();
             UImage= acct.getPhotoUrl();
             UId = acct.getId();
            startActivity(new Intent(this,NavDrw.class));
            Toast.makeText(getApplicationContext(), "Welcome  " +UName, Toast.LENGTH_LONG).show();
            ////////////////


                // progressDialog.dismiss();
                // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {

                        SharedPrefManager.getInstance(getApplicationContext())
                                .userLogin(

                                        1001,
                                        "1244",
                                        acct.getEmail(),
                                        acct.getIdToken(),
                                        acct.getDisplayName(),
                                        "",
                                        "",
                                        "",
                                       40,
                                        "",
                                        100,
                                      "",
                                        1001,
                                      getUImage().toString()
                                );



                } catch (Exception e){
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }








            ////////////////////////


            updateUI(true);
        } else {
            updateUI(false);
        }
    }



    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {

                    @Override
                    public void onResult(Status status) {

                        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                        Toast.makeText(getApplicationContext(), "  Good bye  " , Toast.LENGTH_LONG).show();

                        updateUI(false);

                        Toast.makeText(getApplicationContext(), " Please come back again  " , Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please wait ...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                break;
            case R.id.sign_out_button:
                signOut();
                break;

        }
    }

    public String getUName() {
        return UName;
    }

    public String getUEmail() {
        return UEmail;
    }
    Uri defaultUri;
    public Uri getUImage() {
        if(UImage!=null){
        return UImage;}
        else
        { defaultUri= Uri.parse("https://img.clipartfest.com/76db68dca190430f68ae64dece275ad8_profile-clip-art-profile-picture-clipart_300-279.png");
            return defaultUri;}
    }

    public String getUId() {
        return UId;
    }
}