package com.example.afaf.amakenapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.EventsAdapter;
import com.example.afaf.amakenapp.helper.EventsListItem;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Muha on 3/8/2017.
 */

public class LatestEventsActivity extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<EventsListItem> listItems;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.activity_events, container, false);

        recyclerView = (RecyclerView) myView.findViewById(R.id.events_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        //// TODO: 3/15/2017  check for event date
        // (if still available set event icon to (ic_event_available_) if not set to ( ic_event_busy)


        for (int i = 0; i < 10; i++) {
            EventsListItem listItem = new EventsListItem(
                    R.drawable.business1_icon,
                    "Business Name  " + i+  " ",
                    R.drawable.ic_event_available,
                    R.drawable.store,
                    "Event Name",
                    "Event Category",
                    "Description",
                    "here details about the event, here details about the event, here details about the event..here details about the event, here details about the event",
                    "Expand >>",
                    "5",
                    R.attr.ratingBarStyleSmall
            );

            listItems.add(listItem);

        }
        adapter = new EventsAdapter (listItems, getActivity());

        recyclerView.setAdapter(adapter);
        return myView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Latest Events");
    }



}
