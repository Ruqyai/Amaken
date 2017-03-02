
package com.example.afaf.amakenapp.activities;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.example.afaf.amakenapp.R;
        import com.example.afaf.amakenapp.helper.Constants;
        import com.example.afaf.amakenapp.helper.MySingleton;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;



public class SignUpBusiness extends AppCompatActivity implements View.OnClickListener{

    private EditText editEmail, editPassword, editPersonName,editwebsiteUrl,editPhoneNumber;
    private Button signUpBusiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_business);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sign_up_business_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        signUpBusiness = (Button) findViewById(R.id.SignUpBusiness);
        signUpBusiness.setOnClickListener(this);



        editEmail = (EditText) findViewById(R.id.businessEmail);
        editPassword = (EditText) findViewById(R.id.businessPassword);
        editPersonName = (EditText) findViewById(R.id.businessName);
        editwebsiteUrl = (EditText) findViewById(R.id.businessWebUrl);
        editPhoneNumber = (EditText) findViewById(R.id.businessPhoneNumber);


    }


    @Override
    public void onClick(View v) {
        if (v == signUpBusiness) {
            singUp();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), SignUpChooser.class));

            return true;
        }
        return true;
    }

    public void singUp() {
        final int userType = Constants.CODE_BUSINESS_USER;

        final String userEmail = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        final String personName = editPersonName.getText().toString().trim();
        final int  Gender = 1;
        final String WebsiteUrl = editwebsiteUrl.getText().toString().trim();
        final String phoneNumber = editPhoneNumber.getText().toString().trim();
        final int countryID = 1;
        final int cityID = 2;

//        progressDialog.show();

        StringRequest send = new StringRequest(Request.Method.POST,
                Constants.URL_SINGUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), com.example.afaf.amakenapp.activities.NavDrw.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressDialog.dismiss();

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
                params.put("user_Type", userType+"" );
                params.put("email", userEmail);
                params.put("password", password);
                params.put("name", personName);
                params.put("gender", Gender+"" );
                params.put("web_url", WebsiteUrl);
                params.put("phone_number", phoneNumber);
                params.put("country_id", countryID+"" );
                params.put("city_id", cityID+"" );

                return params;


            }

        };

        MySingleton.getInstance(this).addToRequestQueue(send);

    }

}

