package com.example.afaf.amakenapp.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.afaf.amakenapp.R;
import com.example.afaf.amakenapp.activities.ExpandDetailsMapsActivity;

import java.util.List;

/**
 * Created by User on 3/11/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<HomeListItem> listItems;
    private Context context;

    public HomeAdapter(List<HomeListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeListItem listItem=listItems.get(position);

        holder.imageViewHomeBusinessProfileImage.setImageResource(listItem.getHomeBusinessProfileImage());
        holder.textViewNameHomeBusiness.setText(listItem.getNameHomeBusiness());
        holder.textViewNameHomeCategory.setText(listItem.getNameHomeCategory());
        holder.imageViewHomeBusinessPlaceImage.setImageResource(listItem.getHomeBusinessPlaceImage());
        holder.ratingBarHome.setRating(listItem.getRatingHome());
        holder.textViewHomeNumberOfRate.setText(listItem.getHomeNumberOfRate());
        holder.textViewHomeNameTitPlaceHome.setText(listItem.getHomeNameTitPlaceHome());
        holder.textViewHomeDescription.setText(listItem.getHomeDescription());
        holder.textViewHomeDescriptionMultiLine.setText(listItem.getHomeDescriptionMultiLine());
        holder.textViewExpandHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),
                      ExpandDetailsMapsActivity.class);
                context.startActivity(myIntent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNameHomeBusiness;
        public TextView textViewNameHomeCategory;
        public TextView textViewHomeNumberOfRate;
        public TextView textViewHomeDescription;
        public TextView textViewHomeDescriptionMultiLine;
        public TextView textViewExpandHome;
        public TextView textViewHomeNameTitPlaceHome;
        public ImageView imageViewHomeBusinessProfileImage;
        public ImageView imageViewHomeBusinessPlaceImage;
        public RatingBar ratingBarHome;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewNameHomeBusiness= (TextView) itemView.findViewById(R.id.TextNameHomeBusiness);
            textViewNameHomeCategory = (TextView) itemView.findViewById(R.id.TextNameHomeCategory) ;
            textViewHomeNumberOfRate= (TextView) itemView.findViewById(R.id.TextHomeNumberOfRate);
            textViewHomeDescription=(TextView) itemView.findViewById(R.id.TextHomeDiscretion);
            textViewHomeDescriptionMultiLine=(TextView) itemView.findViewById(R.id.TextHomeDiscretionMultiLine);
            textViewExpandHome=(TextView) itemView.findViewById(R.id.TextExpandHome);
            imageViewHomeBusinessProfileImage=(ImageView) itemView.findViewById(R.id.imageViewHomeBusinessProfile);
            imageViewHomeBusinessPlaceImage=(ImageView) itemView.findViewById(R.id.imageViewHomeBusinessPlace);
            ratingBarHome =(RatingBar) itemView.findViewById(R.id.ratingBarHome);
            textViewHomeNameTitPlaceHome=(TextView) itemView.findViewById(R.id.nameTitPlaceHome);


        }

    }
}
