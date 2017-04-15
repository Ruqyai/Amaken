package com.amakenapp.website.amakenapp.activities;

import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.amakenapp.website.amakenapp.helper.Constants;
        import com.amakenapp.website.amakenapp.helper.MySingleton;
        import com.amakenapp.website.amakenapp.R;
        import com.amakenapp.website.amakenapp.helper.SharedPrefManager;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText editEmail, editPassword;
    private Button login_SignIn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Loging_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        login_SignIn = (Button) findViewById(R.id.login_SingIn);
        editEmail = (EditText) findViewById(R.id.login_Email);
        editPassword = (EditText) findViewById(R.id.login_Password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        login_SignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == login_SignIn) {
            singIn();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            return true;
        }
        return true;
    }



    public void singIn() {
        final String userEmail = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                int userId = obj.getInt("id");
                                String userIdString = Integer.toString(userId);
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                userIdString,
                                                obj.getString("user_type"),
                                                obj.getString("user_email"),
                                                obj.getString("user_password"),
                                                obj.getString("user_name"),
                                                TextUtils.isEmpty(obj.getString("gender"))?"":obj.getString("gender"),
                                                TextUtils.isEmpty(obj.getString("web_url"))?"":obj.getString("web_url"),
                                                TextUtils.isEmpty(obj.getString("phone_number"))?"":obj.getString("phone_number"),
                                                obj.getInt("country_id"),
                                                obj.getString("country_name"),
                                                obj.getInt("city_id"),
                                                obj.getString("city_name"),
                                                TextUtils.isEmpty(obj.getString("profile_pic_id"))?"":obj.getString("profile_pic_id"),
                                                TextUtils.isEmpty(obj.getString("profile_pic_url"))?"":obj.getString("profile_pic_url")
                                        );

                                startActivity(new Intent(getApplicationContext(), NavDrw.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userEmail", userEmail);
                params.put("password", password);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }
}