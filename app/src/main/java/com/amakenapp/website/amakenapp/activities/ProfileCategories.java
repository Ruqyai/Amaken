package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.amakenapp.website.amakenapp.R;

public class ProfileCategories extends AppCompatActivity {

    private CardView
            cafeCard ,
            educationCard ,
            entertainmentCard ,
            hair_stylistsCard ,
            hospitalsCard ,
            hotelsCard ,
            mallsCard ,
            parksCard ,
            restaurantsCard,
            spaCard,
            sportCard,
            tourismCard;
    private Button  buttonEdit,buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profilecategories_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cafeCard = (CardView) findViewById(R.id.Card_cafe);
        educationCard = (CardView) findViewById(R.id.educationCard);
        entertainmentCard = (CardView) findViewById(R.id.entertainmentCard);
        hair_stylistsCard = (CardView) findViewById(R.id.hair_stylistsCard);
        hospitalsCard = (CardView) findViewById(R.id.hospitalsCard);
        hotelsCard = (CardView) findViewById(R.id.hotelsCard);
        mallsCard = (CardView) findViewById(R.id.mallsCard);
        parksCard = (CardView)findViewById(R.id.parksCard);
        restaurantsCard = (CardView) findViewById(R.id.restaurantsCard);
        spaCard = (CardView) findViewById(R.id.spaCard);
        sportCard = (CardView) findViewById(R.id.sportCard);
        tourismCard = (CardView)findViewById(R.id.tourismCard);


        buttonSave=(Button) findViewById(R.id.ButtonSave);
        buttonSave.setVisibility(View.INVISIBLE);
        buttonEdit=(Button) findViewById(R.id.ButtonEdit);
        onClickEdit();
        onClickSave();


        ////
        cafeCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        educationCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        entertainmentCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        hair_stylistsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        hospitalsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        hotelsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        mallsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        parksCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        restaurantsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        spaCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        sportCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        tourismCard.setBackgroundColor(getResources().getColor(R.color.barlight));
        ///
        cafeCard.setBackgroundColor(getResources().getColor(R.color.bar));
        // educationCard.setBackgroundColor(getResources().getColor(R.color.bar));
        entertainmentCard.setBackgroundColor(getResources().getColor(R.color.bar));
        hair_stylistsCard.setBackgroundColor(getResources().getColor(R.color.bar));
        //  hospitalsCard.setBackgroundColor(getResources().getColor(R.color.bar));
        hotelsCard.setBackgroundColor(getResources().getColor(R.color.bar));
        //  mallsCard.setBackgroundColor(getResources().getColor(R.color.bar));
        parksCard.setBackgroundColor(getResources().getColor(R.color.bar));
        ///  restaurantsCard.setBackgroundColor(getResources().getColor(R.color.bar));
        spaCard.setBackgroundColor(getResources().getColor(R.color.bar));
        //  sportCard.setBackgroundColor(getResources().getColor(R.color.bar));
        tourismCard.setBackgroundColor(getResources().getColor(R.color.bar));



    }


    //// TODO: 3/9/2017  get previous fragment (business profile activity) activity not NavDrw
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return true;
    }


    public void onClickEdit(){

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // startActivity(new Intent(getApplicationContext(), ChooseInterest.class));
                buttonSave.setVisibility(View.VISIBLE);

                cafeCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                educationCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                entertainmentCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                hair_stylistsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                hospitalsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                hotelsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                mallsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                parksCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                restaurantsCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                spaCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                sportCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                tourismCard.setBackgroundColor(getResources().getColor(R.color.barlight));
                onClickImage();

            }
        });
    }

    public void onClickSave(){

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // startActivity(new Intent(getApplicationContext(), ChooseInterest.class));
                buttonSave.setVisibility(View.INVISIBLE);


            }
        });
    }

    ///////////////////

    public void onClickImage(){

        cafeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cafeCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        educationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                educationCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        entertainmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entertainmentCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        hair_stylistsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hair_stylistsCard.setBackgroundColor(getResources().getColor(R.color.bar));
            }
        });
        hospitalsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hospitalsCard.setBackgroundColor(getResources().getColor(R.color.bar));
            }
        });
        hotelsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hotelsCard.setBackgroundColor(getResources().getColor(R.color.bar));
            }
        });
        mallsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mallsCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        parksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parksCard.setBackgroundColor(getResources().getColor(R.color.bar));
            }
        });
        restaurantsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantsCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        spaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        sportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });
        tourismCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourismCard.setBackgroundColor(getResources().getColor(R.color.bar));

            }
        });



    }

}