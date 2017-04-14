package com.amakenapp.website.amakenapp.helper;



        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.view.ContextThemeWrapper;
        import android.support.v7.widget.PopupMenu;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
        import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
        import com.bumptech.glide.Glide;
        import com.amakenapp.website.amakenapp.R;


        import java.util.List;

        import static com.amakenapp.website.amakenapp.R.id.placeorEventRating;

/**
 * Created by USER on 3/10/2017.
 */

public class BusinessProfilePlaceOrEventAdapter extends RecyclerView.Adapter<BusinessProfilePlaceOrEventAdapter.ViewHolder> {
    private List<BusinessProfilePlaceOrEventListItem> listItems;
    private Context context;

    public BusinessProfilePlaceOrEventAdapter(List<BusinessProfilePlaceOrEventListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public BusinessProfilePlaceOrEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_profile_placesandevents_list_item, parent, false);
        return new BusinessProfilePlaceOrEventAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BusinessProfilePlaceOrEventAdapter.ViewHolder holder, int position) {

        BusinessProfilePlaceOrEventListItem listItem = listItems.get(position);


        final int placeOrEventId = listItem.getPlaceOrEventId();
        final String type = listItem.getType();





        Glide.with(context).load(listItem.getPlaceOrEventPic()).into(holder.PlaceOrEventPicture);

        holder.PlaceOrEventName.setText(listItem.getPlaceOrEventName());
        holder.PlaceOrEventCategory.setText(listItem.getPlaceOrEventCategory());

        holder.LikeLogo.setImageResource(R.drawable.ic_like_fill);
        holder.StatLikes.setText(listItem.getStatLikes());

        holder.bookmarkLogo.setImageResource(R.drawable.ic_bookmark_fill);
        holder.StatBookmarks.setText(listItem.getStatBookmark());

        holder.ratingbar.setRating(listItem.getPlaceOrEventRatingbar());
        holder.StatRatings.setText(listItem.getStatRatings());


        holder.optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popup = new PopupMenu(wrapper, holder.optionsMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.business_profile_placeandevent_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.BsPlaceView:
                                //handle menu1 click
                                if (type.equals(Constants.STRING_TYPE_PLACE))
                                {
                                   Intent myIntent = new Intent(context, ExpandDetailsMapsActivity.class);
                                   myIntent.putExtra("PLACE_ID", placeOrEventId);
                                   myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                   context.startActivity(myIntent);
                                }
                                else if (type.equals(Constants.STRING_TYPE_EVENT))
                                {
                                    Intent myIntent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                                    myIntent.putExtra("EVENT_ID", placeOrEventId);
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(myIntent);
                                }
                                break;
                            case R.id.BsPlaceEdit:
                                //handle menu2 click
                                break;
                            case R.id.BsPlaceDelete:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView PlaceOrEventId;
        public TextView type;


        public ImageView PlaceOrEventPicture;
        public TextView PlaceOrEventName;
        public TextView PlaceOrEventCategory;
        public ImageView LikeLogo;
        public ImageView bookmarkLogo;
        public RatingBar ratingbar;
        public TextView optionsMenu;
        public TextView StatBookmarks;
        public TextView StatLikes;
        public TextView StatRatings;





        /* alt+enter to creat constructor*/
        public ViewHolder(View itemView) {
            super(itemView);
            PlaceOrEventId = (TextView) itemView.findViewById(R.id.place_oe_event_id);
            type = (TextView) itemView.findViewById(R.id.type);


            PlaceOrEventPicture = (ImageView) itemView.findViewById(R.id.bookmarks_placeOrEventPicture);
            PlaceOrEventName = (TextView) itemView.findViewById(R.id.bookmarks_placeorEventName);
            PlaceOrEventCategory = (TextView) itemView.findViewById(R.id.bookmarks_placeorEventCategory);
            LikeLogo = (ImageView) itemView.findViewById(R.id.bookmarks_bookmarkLogo);
            bookmarkLogo =(ImageView)itemView.findViewById(R.id.bookmarkLogo);
            ratingbar = (RatingBar) itemView.findViewById(placeorEventRating);
            StatBookmarks = (TextView) itemView.findViewById(R.id.bookmarks_timestamp);
            StatLikes = (TextView) itemView.findViewById(R.id.statBookmarks);
            StatRatings = (TextView) itemView.findViewById(R.id.statRatings);

            optionsMenu = (TextView) itemView.findViewById(R.id.bookmarks_menuOptions);


        }
    }





}
