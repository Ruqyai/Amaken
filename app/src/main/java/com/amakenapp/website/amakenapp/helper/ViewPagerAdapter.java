package com.amakenapp.website.amakenapp.helper;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.store.Photo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.store.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muha on 4/1/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    Context context;
    private List<Photo> images;



    public ViewPagerAdapter (){

    }

    public ViewPagerAdapter(Context context, List<Photo> images ) {
        this.images = images;
        this.context = context;

    }


        @Override
    public int getCount() {
         return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position ) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.view_pager_item, container, false);

        ImageView galleryView = (ImageView) itemView.findViewById(R.id.Expand_event_or_place_photos);
        Photo image = images.get(position);

        Glide.with(context).load(image.getPhoto_url())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .skipMemoryCache( true )
                .into(galleryView);
        container.addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager)container).removeView((RelativeLayout)object);

    }


}
