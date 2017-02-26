package com.example.afaf.amakenapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afaf.amakenapp.helper.Constants;
import com.example.afaf.amakenapp.helper.MySingleton;
import com.example.afaf.amakenapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SignUpUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editTextUsername, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    private ImageButton signUpUser_Back;
    Spinner spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

          spinnerDialog = (Spinner) findViewById(R.id.spinner_dialog);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        spinnerDialog.setAdapter(adapter);
        spinnerDialog.setOnItemSelectedListener(this);


        editTextEmail = (EditText) findViewById(R.id.UserEmail);
        editTextUsername = (EditText) findViewById(R.id.UserName);
        editTextPassword = (EditText) findViewById(R.id.UserPassword);
        // TODO: 2/22/2017 need to complete

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.button_Register);
        buttonRegister.setOnClickListener(this);
        signUpUser_Back = (ImageButton) findViewById(R.id.signUpUser_Back);
        signUpUser_Back.setOnClickListener(this);

    }
    private void registerRegularUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER_REGULAR_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("password", password);
                params.put("name", username);

                // ... need to complete//// TODO: 2/22/2017 need to complete 


                return params;
            }
        };
       MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister)
        registerRegularUser();
        if (v == signUpUser_Back) {
            finish();
            startActivity(new Intent(this, SignUpChooser.class));
        }



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
