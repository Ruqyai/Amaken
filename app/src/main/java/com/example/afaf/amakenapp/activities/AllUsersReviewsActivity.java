package com.example.afaf.amakenapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.helper.HomeReviewDetailesAdapter;
import com.example.afaf.amakenapp.helper.HomeReviewDetailsListItem;

import java.util.ArrayList;
import java.util.List;

public class AllUsersReviewsActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<HomeReviewDetailsListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users_reviews);

            recyclerView = (RecyclerView)findViewById(R.id.RecyclerViewUnderMap);
        recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            listItems = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                HomeReviewDetailsListItem listItem = new  HomeReviewDetailsListItem(
                        R.drawable.profile_user_written,
                        "User Name  " + i+  " " ,
                        "User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place User Review good place ",

                        R.drawable.ic_thumb_up_black_24dp,
                        R.drawable.ic_assistant_photo_black_24dp

                );

                listItems.add(listItem);

            }
            adapter = new HomeReviewDetailesAdapter(listItems,this);

            recyclerView.setAdapter(adapter);
        }


    @Override
    public void onClick(View v) {

    }
}

