package com.amakenapp.website.amakenapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amakenapp.website.amakenapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginFacebookActivity extends AppCompatActivity  {
    private LoginButton loginButton ;
    private CallbackManager callbackManager ;
    private AccessTokenTracker accessTokenTracker ;
   // TextView facebookName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_facebook);

        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.login_button);
     //   facebookName=(TextView) findViewById(R.id.name);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                graphRequest(loginResult.getAccessToken());





            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"errrrrrrrrrrrr",Toast.LENGTH_LONG).show();

            }
        });

    }



    public void graphRequest(AccessToken token){
        GraphRequest request = GraphRequest.newMeRequest(token,new GraphRequest.GraphJSONObjectCallback(){

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
              //  Log.i("fb", "fb user: "+ user.toString());

                try {

                    Profile profile = Profile.getCurrentProfile();
                    profile.getFirstName();
                    String x=  object.get("first_name").toString();
                    Toast.makeText(getApplicationContext(),"welcome  " + profile.getFirstName(),Toast.LENGTH_LONG).show();


                    //   String x=  object.get("first_name").toString();
                 //   facebookName.setText(x);
                //    String email = response.getJSONObject().getString("email");
                 //   String firstName = response.getJSONObject().getString("first_name");
                 //   String lastName = response.getJSONObject().getString("last_name");
                //    String gender = response.getJSONObject().getString("gender");
                //    facebookName.setText(firstName);
                    startActivity(new Intent(LoginFacebookActivity.this,NavDrw.class));
                    finish();
               } catch (JSONException e) {
                  e.printStackTrace();
               }


              //  Toast.makeText(getApplicationContext(),object.toString(),Toast.LENGTH_LONG).show();
                // startActivity(new Intent(LoginFacebookActivity.this,NavDrw.class));

            }
        });

        Bundle b = new Bundle();
        b.putString("fields","id,email,first_name,last_name,picture.type(large)");
        request.setParameters(b);
        request.executeAsync();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);


    }
}