package com.amakenapp.website.amakenapp.search;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivity;
import com.amakenapp.website.amakenapp.activities.NavDrw;
import com.amakenapp.website.amakenapp.helper.Constants;
import com.amakenapp.website.amakenapp.helper.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {

    SearchView searchView;
    SearchList listItem;
    Bundle bundle;
    private AdapterSearch mAdapter;
    private RecyclerView recyclerView;
    private List<SearchList> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        bundle = getIntent().getExtras();
        listItems = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.searchList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LinearLayout search_layout = (LinearLayout) findViewById(R.id.search_lay);
        getMenuInflater().inflate(R.menu.search_result, menu);
        getMenuInflater().inflate(R.menu.filters, menu);
        MenuItem searchFilter = menu.findItem(R.id.action_filter);
        MenuItem searchItem = menu.findItem(R.id.search_res);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, SearchResult.class)));
        searchView.setIconified(false);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_filter) {
            startActivity(new Intent(SearchResult.this, FiltersActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void parse(final String query) {


        if (bundle != null) {
            String clickFilterPlace = String.valueOf(bundle.getInt("counID_Place"));
            String clickFilterEvent = String.valueOf(bundle.getInt("counID_Event"));
            String clickFilterUser = String.valueOf(bundle.getInt("counID_User"));
            if (!clickFilterUser.contains("null")) {
                parseUser(query);
            }
            if (!clickFilterPlace.contains("null")) {
                parsePlace(query);
            }
            if (!clickFilterEvent.contains("null")) {
                parseEvent(query);
            }

        } else {
            parseWithoutFliter(query);
        }
    }


    public void parseWithoutFliter(final String query) {
        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_SEARCH + query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("place");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject SearchDetails = arr.getJSONObject(i);
                                    listItem = new SearchList();

                                    listItem.setSearchId(SearchDetails.getInt("id"));
                                    listItem.setSearchName(SearchDetails.getString("name"));
                                    listItem.setSearchDescription(SearchDetails.getString("description"));
                                    listItem.setSearchRate(SearchDetails.getString("rate"));
                                    listItem.setSearchOwner_id(SearchDetails.getString("owner_id"));
                                    listItem.setSearchCategory_id(SearchDetails.getString("category_id"));
                                    listItem.setSearchLocation_id(SearchDetails.getString("location_id"));
                                    listItem.setSearchCountry_id(SearchDetails.getString("country_id"));
                                    listItem.setSearchCity_id(SearchDetails.getString("city_id"));
                                    String x = SearchDetails.getString("data");
                                    listItem.setSearchData(x);
                                    if (!x.contains("null")) {
                                        listItem.setSerchType("event");
                                    }
                                    String y = SearchDetails.getString("user_type");
                                    if (!y.contains("null")) {
                                        if (y.contains("1245")) {
                                            listItem.setSerchType("owner");
                                        } else {
                                            listItem.setSerchType("user");
                                        }
                                    }
                                    if (x.contains("null") && y.contains("null")) {
                                        listItem.setSerchType("place");
                                    }
                                    listItems.add(listItem);
                                }

                                mAdapter = new AdapterSearch(SearchResult.this, listItems);
                                recyclerView.setAdapter(mAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(SearchResult.this));

                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MySingleton.getInstance(SearchResult.this).addToRequestQueue(send);

    }

    public void parsePlace(final String query) {

        final int counID = bundle.getInt("counID_Place");
        final int citID = bundle.getInt("citID_Place");
        final int cateID = bundle.getInt("cateID_Place");


        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_SEARCH_PLACE + query + "/" + cateID + "/" + counID + "/" + citID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("place");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject SearchDetails = arr.getJSONObject(i);
                                    listItem = new SearchList();

                                    listItem.setSearchId(SearchDetails.getInt("id"));
                                    listItem.setSearchName(SearchDetails.getString("name"));
                                    listItem.setSearchDescription(SearchDetails.getString("description"));
                                    listItem.setSearchRate(SearchDetails.getString("rate"));
                                    listItem.setSearchOwner_id(SearchDetails.getString("owner_id"));
                                    listItem.setSearchCategory_id(SearchDetails.getString("category_id"));
                                    listItem.setSearchLocation_id(SearchDetails.getString("location_id"));
                                    listItem.setSearchCountry_id(SearchDetails.getString("country_id"));
                                    listItem.setSearchCity_id(SearchDetails.getString("city_id"));
                                    listItem.setSerchType("place");

                                    listItems.add(listItem);
                                }

                                mAdapter = new AdapterSearch(SearchResult.this, listItems);
                                recyclerView.setAdapter(mAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(SearchResult.this));

                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MySingleton.getInstance(SearchResult.this).addToRequestQueue(send);
    }

    public void parseEvent(final String query) {
        final int counID = bundle.getInt("counID_Event");
        final int citID = bundle.getInt("citID_Event");
        final int cateID = bundle.getInt("cateID_Event");
        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_SEARCH_EVENT + query + "/" + cateID + "/" + counID + "/" + citID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray arr = obj.getJSONArray("event");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject SearchDetails = arr.getJSONObject(i);
                                    listItem = new SearchList();

                                    listItem.setSearchId(SearchDetails.getInt("id"));
                                    listItem.setSearchName(SearchDetails.getString("name"));
                                    listItem.setSearchDescription(SearchDetails.getString("description"));
                                    listItem.setSearchRate(SearchDetails.getString("rate"));
                                    listItem.setSearchOwner_id(SearchDetails.getString("owner_id"));
                                    listItem.setSearchCategory_id(SearchDetails.getString("category_id"));
                                    listItem.setSearchLocation_id(SearchDetails.getString("location_id"));
                                    listItem.setSearchCountry_id(SearchDetails.getString("country_id"));
                                    listItem.setSearchCity_id(SearchDetails.getString("city_id"));
                                    listItem.setSearchData(SearchDetails.getString("data"));
                                    listItem.setSerchType("event");
                                    listItems.add(listItem);
                                }

                                mAdapter = new AdapterSearch(SearchResult.this, listItems);
                                recyclerView.setAdapter(mAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(SearchResult.this));
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(SearchResult.this).addToRequestQueue(send);

    }

    public void parseUser(final String query) {
        final int counID = bundle.getInt("counID_User");
        final int citID = bundle.getInt("citID_User");
        final int user_type = bundle.getInt("user_type_User");
        StringRequest send = new StringRequest(Request.Method.GET,
                Constants.URL_SEARCH_USER + query + "/" + user_type + "/" + counID + "/" + citID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                JSONArray arr = obj.getJSONArray("user");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject SearchDetails = arr.getJSONObject(i);
                                    listItem = new SearchList();

                                    listItem.setSearchId(SearchDetails.getInt("id"));
                                    listItem.setSearchName(SearchDetails.getString("user_name"));
                                    listItem.setSearchCountry_id(SearchDetails.getString("country_id"));
                                    listItem.setSearchCity_id(SearchDetails.getString("city_id"));
                                    listItem.setSearchUser_type(SearchDetails.getString("user_type"));
                                    String y = SearchDetails.getString("user_type");
                                    if (!y.contains("null")) {
                                        if (y.contains("1245")) {
                                            listItem.setSerchType("owner");
                                        } else {
                                            listItem.setSerchType("user");
                                        }
                                    }
                                    listItems.add(listItem);
                                }

                                mAdapter = new AdapterSearch(SearchResult.this, listItems);
                                recyclerView.setAdapter(mAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(SearchResult.this));
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(SearchResult.this).addToRequestQueue(send);

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent myIntent = new Intent(SearchResult.this, NavDrw.class);
        startActivity(myIntent);
        parse(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        parse(query);
        listItems.clear();
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
        }
    }
}
