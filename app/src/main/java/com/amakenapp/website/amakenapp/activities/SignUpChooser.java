package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.chat.ChatActivity;

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
          //  startActivity(new Intent(this, SettingsActivity.class));

            startActivity(new Intent(this, AddPlace.class));
          //startActivity(new Intent(this, NavDrw.class));
          //  startActivity(new Intent(this, ChooseInterest.class));

        }

        if (v == signUpUserCard) {
            finish();
            startActivity(new Intent(this, SignUpUser.class));
           // startActivity(new Intent(this, ChatActivity.class));
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


