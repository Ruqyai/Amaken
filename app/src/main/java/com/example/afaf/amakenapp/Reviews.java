package com.example.afaf.amakenapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

import static com.example.afaf.amakenapp.R.id.ratingBar;

public class Reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        final Context x = this;

        RatingBar ratingbar1 = (RatingBar) findViewById(ratingBar);
        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(x, Float.toString(rating), Toast.LENGTH_LONG).show();
            }

        });

    }
}

