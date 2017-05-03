package com.amakenapp.website.amakenapp.search;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;
import com.amakenapp.website.amakenapp.activities.UserDetailsActivity;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<SearchList> searchList;
    private SearchList current;
    private int currentId;
    private String tmp;
    private String tmpName;
    AdapterView.OnItemClickListener mItemClickListener;

    public AdapterSearch(Context context, List<SearchList> searchList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.searchList = searchList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        final SearchList current = searchList.get(position );
        final String tmp = current.getSerchType();
        final int currentId = current.getSearchId();
        final String tmpName = current.getSearchName();
        myHolder.searchName.setText(current.getSearchName());
        myHolder.serchType.setText(current.getSerchType());
        myHolder.serchType.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        myHolder.searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tmp.contains("event")) {
                    Toast.makeText(context, "You clicked  " + tmp + "  " + tmpName, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ExpandDetailsMapsActivityEvent.class);
                    intent.putExtra("EVENT_ID", currentId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (tmp.contains("place")) {

                    Intent intent = new Intent(context, ExpandDetailsMapsActivity.class);
                    intent.putExtra("PLACE_ID", currentId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(context, "You clicked " + tmp + " " + tmpName, Toast.LENGTH_SHORT).show();
                }
                if (tmp.contains("user") || tmp.contains("owner")) {

                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtra("USER_ID", currentId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(context, "You clicked " + tmp + " " + tmpName, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return searchList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView searchName;
        TextView serchType;
        private TextView searchDescription, searchRate, searchOwner_id, searchCategory_id, searchLocation_id,
                searchCountry_id, searchCity_id, searchData, searchUser_type;
        private int searchId;
        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            searchName = (TextView) itemView.findViewById(R.id.textSearchName);
            serchType = (TextView) itemView.findViewById(R.id.textSearchType);

            searchDescription = (TextView) itemView.findViewById(R.id.searchDescription);
            searchRate = (TextView) itemView.findViewById(R.id.searchRate);
            searchOwner_id = (TextView) itemView.findViewById(R.id.searchOwner_id);
            searchCategory_id = (TextView) itemView.findViewById(R.id.searchCategory_id);
            searchLocation_id = (TextView) itemView.findViewById(R.id.searchLocation_id);
            searchCountry_id = (TextView) itemView.findViewById(R.id.searchCountry_id);
            searchCity_id = (TextView) itemView.findViewById(R.id.searchCity_id);
            searchData = (TextView) itemView.findViewById(R.id.searchData);
            searchUser_type = searchData = (TextView) itemView.findViewById(R.id.searchUser_type);
            int searchId;
            // itemView.setOnClickListener(this);

        }

        // Click event for all items
    }
}



