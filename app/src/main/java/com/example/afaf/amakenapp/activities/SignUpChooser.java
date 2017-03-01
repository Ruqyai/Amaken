package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.activities.MainActivity;
import com.example.afaf.amakenapp.activities.SignUpBusiness;
import com.example.afaf.amakenapp.activities.SignUpUser;

public class SignUpChooser extends AppCompatActivity implements View.OnClickListener {
    private CardView signUpBusinessCard;
    private CardView signUpUserCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_chooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_up_chooser_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signUpBusinessCard = (CardView) findViewById(R.id.SignUpBusinessCard);
        signUpUserCard = (CardView) findViewById(R.id.SignUpUserCard);


        signUpBusinessCard.setOnClickListener(this);
        signUpUserCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == signUpBusinessCard) {
            finish();
            startActivity(new Intent(this, SignUpBusiness.class));
        }

        if (v == signUpUserCard) {
            finish();
            startActivity(new Intent(this, SignUpUser.class));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            return true;
        }
        return true;
    }

}

