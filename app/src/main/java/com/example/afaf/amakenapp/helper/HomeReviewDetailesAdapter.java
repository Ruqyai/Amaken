package com.example.afaf.amakenapp.helper;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afaf.amakenapp.R;

import java.util.List;

/**
 * Created by User on 3/12/2017.
 */

public class HomeReviewDetailesAdapter extends RecyclerView.Adapter<HomeReviewDetailesAdapter.ViewHolder> {

    private List<HomeReviewDetailsListItem> listItems;
    private Context context;

    public HomeReviewDetailesAdapter(List<HomeReviewDetailsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    /*
        public HomeReviewDetailesAdapter(List<HomeReviewDetailsListItem> listItems, Context context) {
            this.listItems = listItems;
            this.context = context;
        }
    **/
    @Override
    public HomeReviewDetailesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users_reviews_exapand_detailes,parent,false);
        return new HomeReviewDetailesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeReviewDetailesAdapter.ViewHolder holder, int position) {
        HomeReviewDetailsListItem listItem=listItems.get(position);
        holder.imageViewElandUserWriteReviewProfileImage.setImageResource(listItem.getElandUserWriteReviewProfileImage());
        holder.textViewElandNameUserWriteReview.setText(listItem.getElandNameUserWriteReview());
        holder.textViewExpandTheReview.setText(listItem.getExpandTheReview());
        holder.imageViewElandIconLikeImage.setImageResource(listItem.getElandIconLikeImage());
        holder.imageViEwlandIconFlagImage.setImageResource(listItem.getElandIconFlagImage());


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewElandUserWriteReviewProfileImage;
        public TextView textViewElandNameUserWriteReview;
        public TextView textViewExpandTheReview;
        public ImageView imageViewElandIconLikeImage;
        public ImageView imageViEwlandIconFlagImage;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewElandNameUserWriteReview= (TextView) itemView.findViewById(R.id.textNameUserWroteReview);
            textViewExpandTheReview = (TextView) itemView.findViewById(R.id.textViewUserWhatIsWrite) ;

            imageViewElandUserWriteReviewProfileImage=(ImageView) itemView.findViewById(R.id.imageViewUserWrite);
            imageViewElandIconLikeImage=(ImageView) itemView.findViewById(R.id.imageViewLikeReview);
            imageViEwlandIconFlagImage=(ImageView) itemView.findViewById(R.id.imageViewFlagReview);




        }

    }
}
