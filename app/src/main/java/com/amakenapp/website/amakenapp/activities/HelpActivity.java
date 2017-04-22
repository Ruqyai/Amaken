package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amakenapp.website.amakenapp.Help.HelpExpandableListItem;
import com.amakenapp.website.amakenapp.R;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Muha on 3/8/2017.
 */

public class HelpActivity extends Fragment implements View.OnClickListener {

    private Button helpSecButton;

    public void onClick (View v){
        if (v == helpSecButton){
            startActivity(new Intent(getActivity(),HelpExpandableListItem.class));
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_help, container, false);

        helpSecButton = (Button) view.findViewById(R.id.helpSecButton);

        helpSecButton.setOnClickListener(this);



        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Get Help & Tips");
    }


}
