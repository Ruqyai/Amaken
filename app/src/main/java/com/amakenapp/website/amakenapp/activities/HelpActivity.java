package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.Help.HelpExpandableListItem;
import com.amakenapp.website.amakenapp.R;

/**
 * Created by Muha on 3/8/2017.
 */

public class HelpActivity extends Fragment implements View.OnClickListener {

    private TextView helpSecButton;
    private  TextView Business_help;
    private  TextView user_help;
    private  TextView contact_us;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_help, container, false);

        helpSecButton = (TextView) view.findViewById(R.id.helpSecButton);
        Business_help =(TextView)view.findViewById(R.id.user_help) ;
        contact_us =(TextView)view.findViewById(R.id.contact_us) ;


        helpSecButton.setOnClickListener(this);
        Business_help.setOnClickListener(this);
        contact_us.setOnClickListener(this);
        helpSecButton.setOnClickListener(this);



        return view;
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Get Help & Tips");
    }

    public void onClick (View v){
        if (v == helpSecButton){
            startActivity(new Intent(getActivity(),HelpExpandableListItem.class));
        } if (v== Business_help){
            startActivity(new Intent(getActivity(),BusinrssHelp.class));
        }if (v== contact_us){
            startActivity(new Intent(getActivity(),ConnectWithUs.class));
        }

    }
}
