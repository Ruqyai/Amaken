package com.example.afaf.amakenapp.helper;



        import android.content.Context;
        import android.support.v7.widget.PopupMenu;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.afaf.amakenapp.R;


        import java.util.List;

        import static com.example.afaf.amakenapp.R.id.placeorEventRating;

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

        Glide.with(context).load(listItem.getBusinessProfilePlaceOrEventPic()).into(holder.PlaceOrEventPicture);
        //holder.PlaceOrEventPicture.setImageResource(listItem.getBusinessProfilePlaceOrEventPic());

        holder.PlaceOrEventName.setText(listItem.getBusinessProfilePlaceOrEventName());
        holder.PlaceOrEventCategory.setText(listItem.getBusinessProfilePlaceOrEventCategory());

        Glide.with(context).load(listItem.getBusinessProfilePlaceOrEventLikeLogo()).into(holder.LikeLogo);
        //holder.LikeLogo.setImageResource(listItem.getBusinessProfilePlaceOrEventLikeLogo());

        Glide.with(context).load(listItem.getBusinessProfilePlaceOrEventBookmarkLogo()).into(holder.bookmarkLogo);

        //holder.bookmarkLogo.setImageResource(listItem.getBusinessProfilePlaceOrEventBookmarkLogo());
        holder.ratingbar.setRating(listItem.getBusinessProfilePlaceOrEventRatingbarLogo());
        holder.StatBookmarks.setText(listItem.getStatBookmark());
        holder.StatLikes.setText(listItem.getStatLikes());
        holder.StatRatings.setText(listItem.getStatRatings());
        holder.optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.optionsMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.business_profile_placeandevent_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.BsPlaceView:
                                //handle menu1 click
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
