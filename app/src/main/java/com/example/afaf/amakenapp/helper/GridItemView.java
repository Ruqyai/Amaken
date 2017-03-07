package com.example.afaf.amakenapp.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afaf.amakenapp.R;

public class GridItemView extends FrameLayout {

    private TextView textView;
    private ImageView imageView;

    public GridItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_grid, this);
        textView = (TextView) getRootView().findViewById(R.id.text);
        imageView=(ImageView)getRootView().findViewById(R.id.imageViewGrid);
    }

    public void display(String text, int bitmap, boolean isSelected) {
        textView.setText(text);
        imageView.setImageResource(bitmap);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        imageView.setBackgroundResource(isSelected ? R.drawable.backgroundgridview : R.drawable.gray_square);
    }
}