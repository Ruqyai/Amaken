package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;

import com.example.afaf.amakenapp.R;

public class SignUpChooser extends AppCompatActivity implements View.OnClickListener{

    private ImageButton signUpChooserBack;
    private CardView signUpBusinessCard;
    private CardView signUpUserCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_chooser);

        signUpChooserBack = (ImageButton) findViewById(R.id.signUpchooserBack);
        signUpBusinessCard = (CardView) findViewById(R.id.SignUpBusinessCard);
        signUpUserCard = (CardView) findViewById(R.id.SignUpUserCard);


        signUpChooserBack.setOnClickListener(this);
        signUpBusinessCard.setOnClickListener(this);
        signUpUserCard.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        if (v == signUpChooserBack) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        if (v == signUpBusinessCard) {
            finish();
            startActivity(new Intent(this, SignUpBusiness.class));
        }

        if (v == signUpUserCard) {
            finish();
            startActivity(new Intent(this, SignUpUser.class));
        }

    }
}
