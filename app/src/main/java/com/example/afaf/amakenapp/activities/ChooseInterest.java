package com.example.afaf.amakenapp.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

//// TODO: 3/5/2017  asking if this is the problem here  
import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.GridItemView;
import com.example.afaf.amakenapp.helper.GridViewAdapter;

public class ChooseInterest extends AppCompatActivity {
    private GridView gridView;
    private View btnGo;
    private ArrayList<String> selectedStrings;
    private static final String[] numbers = new String[]{
            "Cafes", "Education", "Entertainment", "Hair stylists", "Hospitals", "Hotels", "Malls", "Parks", "Restaurants", "Spa", "Sport",
            "tourism"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid);
        btnGo = findViewById(R.id.button);

        selectedStrings = new ArrayList<>();

        final GridViewAdapter adapter = new GridViewAdapter(numbers, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int selectedIndex = adapter.selectedPositions.indexOf(position);
                if (selectedIndex > -1) {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((GridItemView) v).display(false);
                    selectedStrings.remove((String) parent.getItemAtPosition(position));
                } else {
                    adapter.selectedPositions.add(position);
                    ((GridItemView) v).display(true);
                    selectedStrings.add((String) parent.getItemAtPosition(position));
                }
            }
        });

        //set listener for Button event
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}