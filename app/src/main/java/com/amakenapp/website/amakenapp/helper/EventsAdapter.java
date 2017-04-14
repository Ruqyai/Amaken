package com.amakenapp.website.amakenapp.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;

import java.util.List;

/**
 * Created by User on 3/11/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<EventsListItem> listItems;
    private Context context;

    public EventsAdapter(List<EventsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latestevent_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventsListItem listItem =listItems.get(position);

        final int eventId = listItem.getEventId();
        String eventid = Integer.toString(eventId);

        holder.eventId.setText(eventid);
        String eventavalibility = listItem.getEventavaliabilty();

        String busiessProfilePic = listItem.getEventBusinessProfileImage();

        if (busiessProfilePic.equals(Constants.STRING_USER_PROFILE_PIC))
            holder.eventBusinessProfileImage.setImageResource(R.drawable.business_home_profile);
        else
            Glide.with(context).load(listItem.getEventBusinessProfileImage()).into(holder.eventBusinessProfileImage);


            holder.eventBusinessName.setText(listItem.getEventBusinessName());

        if (eventavalibility.equals(Constants.STRING_EVENT_STILL_OPEN))
            holder.event_availableOrBusyLogo.setImageResource(R.drawable.ic_event_available);
        else
            holder.event_availableOrBusyLogo.setImageResource(R.drawable.ic_event_busy);


        Glide.with(context).load(listItem.getEventPicture()).into(holder.eventPicture);

        holder.eventName.setText(listItem.getEventName());
        holder.getEventCategory.setText(listItem.getEventCategory1());

        holder.eventDescription.setText(listItem.getEventDescription());
        holder.eventDescriptionMultiLineText.setText(listItem.getEventDescriptionMultiLineText());

        holder.eventRatingStat.setText(listItem.getEventRatingStat());
        holder.ratingEvent.setRating(listItem.getRatingEvent());
        holder.eventExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(v.getContext(), ExpandDetailsMapsActivityEvent.class);
                myIntent.putExtra("EVENT_ID", eventId);
                context.startActivity(myIntent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView eventId;
        public TextView eventavalibilty;

        public ImageView eventBusinessProfileImage;
        public TextView eventBusinessName;
        public ImageView event_availableOrBusyLogo;

        public ImageView eventPicture;
        public TextView eventName;
        public TextView getEventCategory;

        public TextView eventDescription;
        public TextView eventDescriptionMultiLineText;
        public TextView eventExpand;

        public TextView eventRatingStat;
        public RatingBar ratingEvent;


        public ViewHolder(View itemView) {
            super(itemView);

            eventId= (TextView) itemView.findViewById(R.id.TextEventid);
            eventavalibilty= (TextView) itemView.findViewById(R.id.TextEventavaliability);

            eventBusinessProfileImage=(ImageView) itemView.findViewById(R.id.imageViewEventBusinessProfile);
            eventBusinessName= (TextView) itemView.findViewById(R.id.TextEventBusinessName);
            event_availableOrBusyLogo=(ImageView) itemView.findViewById(R.id.event_on_logo);


            eventPicture=(ImageView) itemView.findViewById(R.id.imageViewEventBusinessPlace);
            eventName = (TextView) itemView.findViewById(R.id.events_eventName) ;
            getEventCategory = (TextView) itemView.findViewById(R.id.events_eventCategory) ;

            //eventCategory= (TextView) itemView.findViewById(R.id.events_eventCategory);
            eventDescription=(TextView) itemView.findViewById(R.id.TextEventsDiscretion);
            eventDescriptionMultiLineText=(TextView) itemView.findViewById(R.id.TextEventDiscretionMultiLine);

            eventExpand=(TextView) itemView.findViewById(R.id.TextExpandEvent);
            ratingEvent =(RatingBar) itemView.findViewById(R.id.ratingBarEvent);
            eventRatingStat =(TextView) itemView.findViewById(R.id.TextEventNumberOfRate);



        }

    }
}
