package com.amakenapp.website.amakenapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText editEmail, editPassword;
    private Button login_SignIn,loginGoogle,loginFacebook;
    private ProgressDialog progressDialog;
    TextInputLayout emailLayout, passLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Loging_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        login_SignIn = (Button) findViewById(R.id.login_SingIn);
        loginGoogle = (Button) findViewById(R.id.login_SingInGoogle);
        loginFacebook = (Button) findViewById(R.id.login_SingInFacebook);

        editEmail = (EditText) findViewById(R.id.login_Email);
        emailLayout= (TextInputLayout) findViewById(R.id.log_Email);

        editPassword = (EditText) findViewById(R.id.login_Password);
        passLayout =(TextInputLayout) findViewById(R.id.log_Password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        login_SignIn.setOnClickListener(this);
        loginGoogle.setOnClickListener(this);
        loginFacebook.setOnClickListener(this);


        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emailLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editEmail.getText().toString().isEmpty())
                {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("Please Enter Your Email...!");
                }
                else
                    emailLayout.setErrorEnabled(false);
            }
        });



        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passLayout.setErrorEnabled(false);}
            @Override
            public void afterTextChanged(Editable editable) {
                if(editPassword.getText().toString().isEmpty())
                {
                    passLayout.setErrorEnabled(true);
                    passLayout.setError("Please Enter Your Name...!");
                }
                else
                    passLayout.setErrorEnabled(false);
            }
        });


    }

    @Override
    public void onClick(View v) {

        if (v == login_SignIn) {
            String useremail = editEmail.getText().toString().trim();
            String userpass = editPassword.getText().toString().trim();

            if(useremail.isEmpty())
            {
                emailLayout.requestFocus();
                emailLayout.setError("Please Enter Your Email...!");
            }
            else if(!isValidEmail(useremail))
            {
                emailLayout.requestFocus();
                emailLayout.setError("Hmm... This dose not look like an Email address...!");

            }
            else if(userpass.isEmpty())
            {
                passLayout.requestFocus();
                passLayout.setError("Please Enter Your Password...!");
            }
             else if (userpass.length()<6)
            {
                passLayout.requestFocus();
                passLayout.setError("Password is incorrect, your password is more than 5 chars...!");
            }
            else singInNormal();
        }
        if (v == loginGoogle) {
            startActivity(new Intent(Login.this,LoginGoogleActivity.class));
        }
        if (v == loginFacebook) {
            startActivity(new Intent(Login.this,LoginFacebookActivity.class));
        }

    }


    public final static boolean isValidEmail(CharSequence target) {
        final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        return EMAIL_ADDRESS_PATTERN.matcher(target).matches();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(0,0);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(0,0);

            return true;
        }
        return true;
    }



    public void singInNormal() {
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
                                                obj.getString("profile_pic_id"),
                                                obj.getString("profile_pic_url"),
                                                obj.getString("profile_pic_timeStamp")
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