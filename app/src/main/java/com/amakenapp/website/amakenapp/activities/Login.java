package com.amakenapp.website.amakenapp.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText editEmail, editPassword;
    private Button login_SignIn,loginGoogle,loginFacebook;
    private ProgressDialog progressDialog;
    TextInputLayout emailLayout, passLayout, resetPass, confirmPass, code;

    private TextView forgotPass, signup;


    private AlertDialog.Builder alertDialog;
    private AlertDialog dialog;
    private static AlertDialog dialogChangeUserDetail;


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

        forgotPass = (TextView) findViewById(R.id.forgot_pass);
        signup = (TextView) findViewById(R.id.signUp);



        editEmail = (EditText) findViewById(R.id.login_Email);
        emailLayout= (TextInputLayout) findViewById(R.id.log_Email);

        editPassword = (EditText) findViewById(R.id.login_Password);
        passLayout =(TextInputLayout) findViewById(R.id.log_Password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        login_SignIn.setOnClickListener(this);
        loginGoogle.setOnClickListener(this);
        loginFacebook.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        signup.setOnClickListener(this);


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
                else{
                    emailLayout.setErrorEnabled(false);
                    signup.setVisibility(View.GONE);
                }
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
                {
                    passLayout.setErrorEnabled(false);
                    forgotPass.setVisibility(View.GONE);
                }
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

        if(v == signup)
        {
            finish();
            startActivity(new Intent(Login.this,SignUpChooser.class));}


        if(v == forgotPass)
        {


            Toast.makeText(getApplicationContext(), " a message with a code has been sent to your email ", Toast.LENGTH_LONG).show();
            alertDialog = new AlertDialog.Builder(v.getRootView().getContext());
            alertDialog.setTitle("Reset Your Password");
            final View view1 = getLayoutInflater().inflate(R.layout.change_pass, null);
            final EditText pass = (EditText) view1.findViewById(R.id.report_reasonEdit);
            final EditText confirm = (EditText) view1.findViewById(R.id.con);
            final EditText code2 = (EditText) view1.findViewById(R.id.questask);
            resetPass =(TextInputLayout) view1.findViewById(R.id.report_reasonText);
            confirmPass =(TextInputLayout) view1.findViewById(R.id.conpass);
            code =(TextInputLayout) view1.findViewById(R.id.quest);




            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
            view1.setLayoutParams(lp);
            alertDialog.setView(view1);
            alertDialog.setPositiveButton("Reset", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which) {
                    updatePass(editEmail.getText().toString().trim(), pass.getText().toString().trim() );
                    forgotPass.setVisibility(View.GONE);
                    passLayout.requestFocus();
                    passLayout.setError("Set the New Password Here!");
                }

            });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            dialogChangeUserDetail = alertDialog.create();
            dialogChangeUserDetail.show();
            ((AlertDialog) dialogChangeUserDetail).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);



            pass.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (pass.getText().toString().isEmpty()) {
                        resetPass.setError("Please Enter a password!");
                    } else if (pass.getText().toString().length()<6){
                        resetPass.setError("Password must be at least 6 characters!");

                    }else
                        resetPass.setErrorEnabled(false);
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                public void onTextChanged(CharSequence s, int start, int before, int count){
                    resetPass.setErrorEnabled(false);
                }
            });


            confirm.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (!confirm.getText().toString().equals(pass.getText().toString())) {
                        confirmPass.setError("Passwords Don't Match");
                    }else
                        confirmPass.setErrorEnabled(false);
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                public void onTextChanged(CharSequence s, int start, int before, int count){
                    confirmPass.setErrorEnabled(false);
                }
            });


            code2.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (!code2.getText().toString().equals("123456")) {
                        code.setError("incorrect code");
                    }else
                        code.setErrorEnabled(false);
                    ((AlertDialog) dialogChangeUserDetail).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                public void onTextChanged(CharSequence s, int start, int before, int count){
                    code.setErrorEnabled(false);
                }
            });

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



        progressDialog.setMessage("Sign in...");
        progressDialog.show();
        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //adddeviceToken();
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
                            }
                            else if (!obj.getBoolean("user_email_exists"))
                            {
                                signup.setVisibility(View.VISIBLE);
                                emailLayout.requestFocus();
                                emailLayout.setError("This Email Address is Not Registered With Us!...Try to Sign Up");

                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                            else{
                                forgotPass.setVisibility(View.VISIBLE);
                                passLayout.requestFocus();
                                passLayout.setError("Password is Incorrect, if You Forgot Your Password Try to Reset it. Press on the Link Bellow!");
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




    public void updatePass(String useremail, String userpass) {
        final String userEmail = useremail;
        final String password = userpass;
        StringRequest send = new StringRequest(Request.Method.PUT,
                Constants.URL_UPDATE_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                            else{
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
                params.put("user_email", userEmail);
                params.put("pass", password);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }




    public void adddeviceToken() {
        final String userEmail = editEmail.getText().toString().trim();
        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        //Toast.makeText(getApplicationContext(), token , Toast.LENGTH_LONG).show();


        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER_DEVICE ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //insertedLocationId = obj.getInt("location_id");
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


                            } else {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
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
                params.put("email", userEmail);
                params.put("token", token);
                return params;
            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }
}