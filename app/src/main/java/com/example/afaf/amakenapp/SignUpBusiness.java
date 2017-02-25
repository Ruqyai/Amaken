package com.example.afaf.amakenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class SignUpBusiness extends AppCompatActivity implements View.OnClickListener{

    private ImageButton signUpBusiness_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_business);
        signUpBusiness_Back = (ImageButton) findViewById(R.id.signUpBusiness_back);
        signUpBusiness_Back.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        if (v == signUpBusiness_Back) {
            finish();
            startActivity(new Intent(this, SignUpChooser.class));
        }

    }
}
