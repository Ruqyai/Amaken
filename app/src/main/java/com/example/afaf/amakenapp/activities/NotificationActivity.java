package com.example.afaf.amakenapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.NotificationsAdapter;
import com.example.afaf.amakenapp.helper.NotificationsListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muha on 3/8/2017.
 */

public class NotificationActivity extends Fragment{
    private RecyclerView notifications_recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NotificationsListItem> listItems;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_notifications, container, false);


        notifications_recyclerView = (RecyclerView) myView.findViewById(R.id.notifications_recyclerView);
        notifications_recyclerView.setHasFixedSize(true);
        notifications_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            NotificationsListItem listItem = new NotificationsListItem(
                    "Notification TimeStamp",
                    R.drawable.ic_person,
                    "Notification Message, Notification Message, Notification Message, Notification Message, Notification Message, ",
                    "Notification Hidden id"
            );

            listItems.add(listItem);

        }
        adapter = new NotificationsAdapter(listItems, getActivity());

        notifications_recyclerView.setAdapter(adapter);
        return myView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Notifications");
    }
}
