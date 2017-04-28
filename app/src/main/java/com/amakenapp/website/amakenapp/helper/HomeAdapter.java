package com.amakenapp.website.amakenapp.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.squareup.picasso.Picasso;

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

        final int placeId = listItem.getPlaceId();
        String placeid = Integer.toString(placeId);

        holder.placeId.setText(placeid);

        String busiessProfilePic = listItem.getPlaceBusinessProfileImage();

        if (busiessProfilePic.equals(Constants.STRING_USER_PROFILE_PIC))
            holder.placeBusinessProfileImage.setImageResource(R.drawable.business1);
        else
            Glide.with(context).load(listItem.getPlaceBusinessProfileImage())
                    .diskCacheStrategy( DiskCacheStrategy.NONE )
                    .skipMemoryCache( true )
                    .into(holder.placeBusinessProfileImage);


        holder.placeBusinessName.setText(listItem.getPlaceBusinessName());



        Glide.with(context).load(listItem.getPlacePicture())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(holder.placePicture);

        holder.placeName.setText(listItem.getPlaceName());
        holder.placeCategory.setText(listItem.getPlaceCategory());

        holder.placeDescription.setText(listItem.getPlaceDescription());
        holder.placeDescriptionMultiLineText.setText(listItem.getPlaceDescriptionMultiLineText());

        holder.placeRatingStat.setText(listItem.getPlaceRatingStat());
        holder.ratingPlace.setRating(listItem.getRatingplace());
        holder.placeExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(v.getContext(), ExpandDetailsMapsActivity.class);
                myIntent.putExtra("PLACE_ID", placeId);
                context.startActivity(myIntent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView placeId;

        public ImageView placeBusinessProfileImage;
        public TextView placeBusinessName;

        public ImageView placePicture;
        public TextView placeName;
        public TextView placeCategory;

        public TextView placeDescription;
        public TextView placeDescriptionMultiLineText;
        public TextView placeExpand;

        public TextView placeRatingStat;
        public RatingBar ratingPlace;


        public ViewHolder(View itemView) {
            super(itemView);


            placeId= (TextView) itemView.findViewById(R.id.Textplaceid);

            placeBusinessProfileImage=(ImageView) itemView.findViewById(R.id.imageViewHomeBusinessProfile);
            placeBusinessName= (TextView) itemView.findViewById(R.id.TextNameHomeBusiness);


            placePicture=(ImageView) itemView.findViewById(R.id.imageViewHomeBusinessPlace);
            placeName = (TextView) itemView.findViewById(R.id.nameTitPlaceHome) ;
            placeCategory = (TextView) itemView.findViewById(R.id.TextNameHomeCategory) ;

            placeDescription=(TextView) itemView.findViewById(R.id.TextHomeDiscretion);
            placeDescriptionMultiLineText=(TextView) itemView.findViewById(R.id.TextHomeDiscretionMultiLine);

            placeExpand=(TextView) itemView.findViewById(R.id.TextExpandHome);
            ratingPlace =(RatingBar) itemView.findViewById(R.id.ratingBarHome);
            placeRatingStat =(TextView) itemView.findViewById(R.id.TextHomeNumberOfRate);




        }

    }
}
