package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;



import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton signUpMain;
    private ImageButton signInMain;
    private static Context mCtx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // SharedPreferences sharedPreferences = mCtx.getSharedPreferences("mysharedpref12", Context.MODE_PRIVATE);
        SharedPrefManager sharedPrefManager=SharedPrefManager.getInstance(this);
        if (sharedPrefManager.isLoggedIn()){
            startActivity(new Intent(MainActivity.this,NavDrw.class));

        } // TODO: 4/9/2017 here we should check if user already exist in cache memory if yes go to HomeActivity without sign up or sign in

        signUpMain= (ImageButton) findViewById(R.id.signUpMain);
        signInMain = (ImageButton) findViewById(R.id.signInMain);

        signUpMain.setOnClickListener(this);
        signInMain.setOnClickListener(this);
        



    }


    @Override
    public void onClick(View v) {

        if (v == signUpMain) {
            finish();
            startActivity(new Intent(this, SignUpChooser.class));
        }
        if (v == signInMain) {
            finish();
            startActivity(new Intent(this, Login.class));
        }

    }
}

