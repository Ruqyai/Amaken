package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.R;

public class ConnectWithUs extends AppCompatActivity implements View.OnClickListener {
    ImageButton cancel;
     TextView amakeen_app;
     TextView amaken_i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_us);
        amakeen_app= (TextView) findViewById(R.id.Amakeen_app);
        amaken_i= (TextView) findViewById(R.id.Amaken_i);
        cancel = (ImageButton) findViewById(R.id.cancel);

        amakeen_app.setOnClickListener(this);
        amaken_i.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {


            if (v == cancel){
                finish();
            }if (v== amakeen_app){

                String url = "https://twitter.com/?request_context=signup";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url)); startActivity(i);


            }if (v== amaken_i){

                String url = "https://www.instagram.com/amakenapp/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url)); startActivity(i);}









            }
}
