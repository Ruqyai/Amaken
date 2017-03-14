package com.example.afaf.amakenapp.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.HomeAdapter;
import com.example.afaf.amakenapp.helper.HomeListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeActivity extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<HomeListItem> listItems;
    Context context;

    public HomeActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.activity_home, container, false);

        recyclerView = (RecyclerView) myView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            HomeListItem listItem = new HomeListItem(
                    R.drawable.business_home_profile,
                    "Business Name  " + i+  " ",
                    R.drawable.place_home_image,
                    "Place Name",
                    "Place Category",
                    "Description",
                    "here details about the place, here details about the place, here details about the place, here details about the place, here details about the place"

                           ,
                    "Expand >>",
                    R.attr.ratingBarStyleSmall,
                    "5"
            );

            listItems.add(listItem);

        }
        adapter = new HomeAdapter(listItems, getActivity());

        recyclerView.setAdapter(adapter);
return myView;

    }

}
