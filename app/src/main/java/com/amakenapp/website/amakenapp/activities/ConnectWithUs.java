package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.chat.ChatActivity;

import jp.co.recruit_lifestyle.android.floatingview.FloatingViewListener;
import jp.co.recruit_lifestyle.android.floatingview.FloatingViewManager;

public class ConnectWithUs extends AppCompatActivity implements View.OnClickListener, FloatingViewListener {
    ImageButton cancel;
    TextView amakeen_app;
    TextView amaken_i;
    TextView Amakeen_app_facebook;
    TextView amakeen_app_Gmail;
    Intent intent =null, chooser= null;
    FloatingViewManager mFloatingViewManager;
    ImageView iconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_us);
        amakeen_app= (TextView) findViewById(R.id.Amakeen_app);

        amaken_i= (TextView) findViewById(R.id.Amaken_i);
        Amakeen_app_facebook= (TextView) findViewById(R.id.Amakeen_app_facebook) ;
        // cancel = (ImageButton) findViewById(R.id.cancel);
        amakeen_app_Gmail= (TextView) findViewById(R.id.amakeen_app_Gmail);
        final LayoutInflater inflater = LayoutInflater.from(this);
        iconView = (ImageView) inflater.inflate(R.layout.widget_chathead, null, false);
        iconView.setOnClickListener(this);

        amakeen_app.setOnClickListener(this);
        amaken_i.setOnClickListener(this);
        amakeen_app_Gmail.setOnClickListener(this);
        Amakeen_app_facebook.setOnClickListener(this);
        // cancel.setOnClickListener(this);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        mFloatingViewManager = new FloatingViewManager(this, this);
        mFloatingViewManager.setFixedTrashIconImage(R.drawable.ic_trash_fixed);
        mFloatingViewManager.setActionTrashIconImage(R.drawable.ic_trash_action);

        final FloatingViewManager.Options options = new FloatingViewManager.Options();
        options.overMargin = (int) (16 * metrics.density);
        mFloatingViewManager.addViewToWindow(iconView, options);

    }


    @Override
    public void onClick(View v) {

        if (v==iconView){

            startActivity(new Intent(this, ChatActivity.class));

        }
/*
            if (v == cancel){
                finish();
            }*/
        if (v== amakeen_app){

            String url = "https://twitter.com/AmakenApp";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url)); startActivity(i);


        }if (v== amaken_i){

            String url = "https://www.instagram.com/amaken_app/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url)); startActivity(i);
        }if (v== Amakeen_app_facebook){

            String url = "https://www.facebook.com/Amaken-1449474672018504/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url)); startActivity(i);
        }if (v== amakeen_app_Gmail){

            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("message/rfc822");
            String[] to = {"the.fabulous.team1@gmail.com","AmakenApp@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to );
            intent.putExtra(Intent.EXTRA_SUBJECT, "This was sent from my app");
            intent.putExtra(Intent.EXTRA_TEXT,"Hello, how can we serve you");

            startActivity(intent);
        }









    }

    @Override
    public void onFinishFloatingView() {

    }

    @Override
    public void onTouchFinished(boolean isFinishing, int x, int y) {

    }
}
