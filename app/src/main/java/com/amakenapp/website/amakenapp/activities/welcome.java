package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amakenapp.website.amakenapp.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome extends AppCompatActivity {

    Button SIGNOUT;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ////


/********


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                tv = (TextView)findViewById(R.id.WelcomeUser);
                if (user != null) {
                    String name = user.getDisplayName();
                    tv.setText(name);
                }



                SIGNOUT = (Button) findViewById(R.id.singOut);
                SIGNOUT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        LoginManager.getInstance().logOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                });
*******/

        ///



    }
}
