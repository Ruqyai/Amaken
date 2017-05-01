package com.amakenapp.website.amakenapp.Help;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.activities.AddReview;
import com.amakenapp.website.amakenapp.activities.HelpActivity;
import com.amakenapp.website.amakenapp.activities.NavDrw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpExpandableListItem extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_expandable_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar00);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                // TODO Auto-generated method stub

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            public void onGroupCollapse(int groupPosition) {
//              Toast.makeText(getApplicationContext(),
//                      listDataHeader.get(groupPosition) + " Collapsed",
//                      Toast.LENGTH_SHORT).show();

            }
        });


////////////////////////////  on child click ///////////////////////////////////////

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                if (groupPosition == 0 && childPosition == 0) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpOverview00.class));
                    overridePendingTransition(0,0);

                }else if (groupPosition == 0 && childPosition == 1) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpOverview01.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 0 && childPosition == 2) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpOverview02.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 1 && childPosition == 0) {
                //so this code only executes if the 2nd child in the 2nd group is clicked
                startActivity(new Intent(HelpExpandableListItem.this, HelpSigningUp00.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 1 && childPosition == 1) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpSigningUp01.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 1 && childPosition == 2) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpSigningUp02.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 1 && childPosition == 3) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpSigningUp03.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 2 && childPosition == 0) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpLegalPrivacy00.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 2 && childPosition == 1) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpLegalPrivacy01.class));
                    overridePendingTransition(0,0);

                }else if (groupPosition == 2 && childPosition == 2) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpLegalPrivacy02.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 3 && childPosition == 0) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpUsingAmaken00.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 3 && childPosition == 1) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpUsingAmaken01.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 3 && childPosition == 2) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpUsingAmaken02.class));
                    overridePendingTransition(0,0);


                }else if (groupPosition == 3 && childPosition == 3) {
                    //so this code only executes if the 2nd child in the 2nd group is clicked
                    startActivity(new Intent(HelpExpandableListItem.this, HelpUsingAmaken03.class));
                    overridePendingTransition(0,0);


                }

                /*  //
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Overview");
        listDataHeader.add("Signing Up");
        listDataHeader.add("Legal and Privacy");
        listDataHeader.add("Using Amaken App");

        // Adding child data
        List<String> Overview = new ArrayList<String>();
        Overview.add("How dose Amaken work");
        Overview.add("Dose Amaken cover my city");
        Overview.add("Is Amaken available international");


        List<String> signingUp = new ArrayList<String>();
        signingUp.add("Download Amaken For Android");
        signingUp.add("Create Amaken Account");
        signingUp.add("Select Categories");
        signingUp.add("Add Buisness Place or Event");

        List<String> legalAndPrivacy = new ArrayList<String>();
        legalAndPrivacy.add("Amaken's Community Guidlines");
        legalAndPrivacy.add("Privacy Policy");
        legalAndPrivacy.add("Amaken Permission");

        List<String> usingAmaken = new ArrayList<String>();
        usingAmaken.add("Home Page");
        usingAmaken.add("Add Review and Photo of Place/Event");
        usingAmaken.add("Invite Friends to Use Amaken");
        usingAmaken.add("Profile");


        listDataChild.put(listDataHeader.get(0), Overview); // Header, Child data
        listDataChild.put(listDataHeader.get(1), signingUp);
        listDataChild.put(listDataHeader.get(2), legalAndPrivacy);
        listDataChild.put(listDataHeader.get(3), usingAmaken);

    }
}