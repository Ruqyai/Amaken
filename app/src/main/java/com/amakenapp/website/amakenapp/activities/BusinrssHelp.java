package com.amakenapp.website.amakenapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import com.amakenapp.website.amakenapp.R;

public class BusinrssHelp extends AppCompatActivity implements View.OnClickListener{
    ViewFlipper viewFlipper;
    ImageButton filpNext, flipPrevious, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businrss_help);



        viewFlipper = (ViewFlipper) findViewById(R.id.help_business);


        flipPrevious = (ImageButton) findViewById(R.id.previous);
        filpNext = (ImageButton) findViewById(R.id.next);
        cancel = (ImageButton) findViewById(R.id.cancel);





        filpNext.setOnClickListener(this);
        flipPrevious.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == filpNext){
            viewFlipper.setInAnimation(this, R.anim.in_from_right);
            viewFlipper.setOutAnimation(this, R.anim.out_to_left);
            viewFlipper.showNext();

        }
        if (v == flipPrevious){
            viewFlipper.setInAnimation(this, R.anim.in_from_left);
            viewFlipper.setOutAnimation(this, R.anim.out_to_right);
            viewFlipper.showPrevious();

        }

        if (v == cancel){
            finish();
        }


    }
}
