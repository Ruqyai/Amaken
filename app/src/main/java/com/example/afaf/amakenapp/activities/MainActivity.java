package com.example.afaf.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.afaf.amakenapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton signUpMain;
    private ImageButton signInMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

