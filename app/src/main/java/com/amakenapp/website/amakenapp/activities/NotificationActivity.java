package com.amakenapp.website.amakenapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.amakenapp.website.amakenapp.helper.NotificationsAdapter;
import com.amakenapp.website.amakenapp.helper.NotificationsListItem;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkAdapter;
import com.amakenapp.website.amakenapp.helper.ProfileBookmarkListItem;
import com.amakenapp.website.amakenapp.helper.SharedPrefManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Muha on 3/8/2017.
 */

public class NotificationActivity extends Fragment{
    private RecyclerView notifications_recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NotificationsListItem> listItems;
    Context context;


    private RelativeLayout loading;

    private LinearLayout no_noti;

    SharedPrefManager sharedPrefManager;
    private static int userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_notifications, container, false);
        context = getApplicationContext();



        notifications_recyclerView = (RecyclerView) myView.findViewById(R.id.notifications_recyclerView);
        notifications_recyclerView.setHasFixedSize(true);
        notifications_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();


        sharedPrefManager = SharedPrefManager.getInstance(context);
        userId = sharedPrefManager.getUserId();

        loading = (RelativeLayout) myView.findViewById(R.id.linlaHeaderProgress_bookmarks);
        loading.setVisibility(View.VISIBLE);

        no_noti = (LinearLayout) myView.findViewById(R.id.no_bookmarks);


        getAllUserNotifications(userId);
        return myView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Notifications");
    }

    private void showToast(String meg){
        final String message = meg;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public void getAllUserNotifications(int userId) {
        final int userID = userId;

        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_GET_NOTIFICATIONS + userID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("notifications");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject notiDetails = arr.getJSONObject(i);

                                    NotificationsListItem listItem = new NotificationsListItem();

                                    listItem.setNotification_id(notiDetails.getInt("noti_id"));
                                    listItem.setNotificationTimeStamp(notiDetails.getString("timeStamp"));
                                    listItem.setNotification_message(notiDetails.getString("noti_message"));
                                    listItem.setNotification_user_profile_pic(notiDetails.getString("user_profile_pic"));
                                    listItem.setNotiType(notiDetails.getString("noti_type"));
                                    listItem.setTargetUserId(notiDetails.getInt("target_user_id"));
                                    listItem.setGeneratorId(notiDetails.getInt("generator_user_id"));
                                    listItem.setPlaceId(notiDetails.getInt("place_id"));
                                    listItem.setEventId(notiDetails.getInt("event_id"));
                                    listItem.setReviewId(notiDetails.getInt("review_id"));
                                    listItem.setReoprtId(notiDetails.getInt("reported_review_id"));
                                    listItem.setLikeId(notiDetails.getInt("like_id"));
                                    listItem.setBookmarkId(notiDetails.getInt("bookmark_id"));
                                    listItems.add(listItem);
                                }
                                adapter = new NotificationsAdapter(listItems, getActivity());
                                notifications_recyclerView.setAdapter(adapter);
                                loading.setVisibility(View.GONE);
                            } else {
                                loading.setVisibility(View.GONE);
                                no_noti.setVisibility(View.VISIBLE);
                                showToast(obj.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) ;
        MySingleton.getInstance(context).addToRequestQueue(send);
    }

}
