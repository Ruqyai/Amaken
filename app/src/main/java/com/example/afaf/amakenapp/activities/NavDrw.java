package com.example.afaf.amakenapp.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.Constants;
import com.example.afaf.amakenapp.helper.HomeAdapter;
import com.example.afaf.amakenapp.helper.HomeListItem;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavDrw extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView userName;
    private CircleImageView userProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton addPlaceFab = (FloatingActionButton) findViewById(R.id.Adds_place);
        addPlaceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrw.this, AddPlace.class));
            }
        });
        FloatingActionButton addEventFab = (FloatingActionButton) findViewById(R.id.Adds_Event);
            addEventFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NavDrw.this, AddEvent.class));

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



       // userName = (TextView) findViewById(R.id.nav_header_user_name);
      //  userName.setText(SharedPrefManager.getInstance(getApplicationContext()).getUsername());

        //userProfilePic = (CircleImageView) findViewById(R.id.nav_header_user_profile_pic);
        //Glide.with(getApplicationContext()).load("").into(userProfilePic);
       //userProfilePic.setImageResource(SharedPrefManager.getInstance(getApplicationContext()).getKeyUserProfilePicUrl());



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       // no inspection Simplifiable If Statement
        if (id == R.id.menuSearch) {
            Fragment fragment=new SearchResult();


          //  Fragment fragment=new SearchResult();
          ////  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ////   ft .replace(R.id.content_nav_drw, fragment);
         //   ft .commit();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        Fragment fragment = null;


        int id = item.getItemId();

        if (id == R.id.nav_Home) {

          //  startActivity(new Intent(this, NavDrw.class));
            fragment= new HomeActivity();

        } else if (id == R.id.nav_events) {

            fragment = new EventsActivity();


        } else if (id == R.id.nav_notification) {

            fragment = new NotificationActivity();

        } else if (id == R.id.nav_profile) {
            
            //// TODO: 3/9/2017 get user type from shared preferences 
            //if (user_type == Constants.CODE_BUSINESS_USER)
               fragment = new BusinessProfileActivity();
           // else if (user_type == Constants.CODE_NORMAL_USER)
               //fragment = new UserProfileActivity();

        } else if (id == R.id.nav_Help) {

            fragment = new HelpActivity();

        } else if (id == R.id.nav_invites) {

         //   fragment = new InvitesActivity();

            fragment = new SearchResult();// // TODO: 3/16/2017 Just For Test
        }
         else if (id == R.id.nav_setting) {

            startActivity(new Intent(NavDrw.this, SettingsActivity.class));

        } else if (id == R.id.nav_Logout) {

            // Toast.makeText(getApplicationContext(), "Do you want to logout?", Toast.LENGTH_LONG).show();

            showDialog(Constants.DIALOG_EXIT);
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           ft .replace(R.id.content_nav_drw, fragment);
           ft .commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    protected Dialog onCreateDialog(int id) {

        if (id == Constants.DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("EXIT");

            adb.setMessage("Do you want to exit?");

            adb.setIcon(R.drawable.ic_warning_black_24dp);

            adb.setPositiveButton("YES", myClickListener);

            adb.setNeutralButton("CANCEL", myClickListener);

            adb.setCancelable(false);
            return adb.create();

        }
        return super.onCreateDialog(id);
    }
    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                 //   saveData();
                    finish();
                    startActivity(new Intent(NavDrw.this, MainActivity.class));
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };
    void saveData() {
        Toast.makeText(this, "your data was saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fragment fragment=new HomeActivity();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft .replace(R.id.content_nav_drw, fragment);
        ft .commit();

    }
}
