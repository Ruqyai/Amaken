package com.amakenapp.website.amakenapp.helper;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


/**
 * Created by USER on 3/10/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<NotificationsListItem> listItems;
    private Context context;

    public NotificationsAdapter(List<NotificationsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notifications_list_item, parent, false);
        return new NotificationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.ViewHolder holder, int position) {

        NotificationsListItem listItem = listItems.get(position);




        holder.notificationTimeStamp.setText(listItem.getNotificationTimeStamp());
        Glide.with(context).load(listItem.getNotification_user_profile_pic())
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .skipMemoryCache( true )
                .into(holder.notification_user_ProfilePic);
        holder.notificationMessage.setText(listItem.getNotification_message());
        holder.notification_id_hidden.setText(listItem.getNotification_id_hidden());


        holder.notificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "This click should take to the notification source", Toast.LENGTH_LONG).show();
                        //context.startActivity(new Intent(view.getContext(), NavDrw.class));


            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{



        public TextView notificationTimeStamp;

        public ImageView notification_user_ProfilePic;
        public TextView notificationMessage;
        public TextView notification_id_hidden;

        public CardView notificationCard;







        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);

            notificationTimeStamp = (TextView) itemView.findViewById(R.id.notifications_timestamp);
            notification_user_ProfilePic =(ImageView)itemView.findViewById(R.id.notifications_user_profile_pic);
            notificationMessage = (TextView) itemView.findViewById(R.id.notifications_message);

            //May not be needed
            notification_id_hidden= (TextView) itemView.findViewById(R.id.notification_id_hidden);


            // to activate the click on the card
            notificationCard = (CardView) itemView.findViewById(R.id.notification_card_id);



        }
    }



}
