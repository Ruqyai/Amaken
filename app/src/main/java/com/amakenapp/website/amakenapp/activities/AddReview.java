package com.amakenapp.website.amakenapp.activities;

        import android.content.Context;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.widget.RatingBar;
        import android.widget.Toast;

        import com.amakenapp.website.amakenapp.R;

        import static com.amakenapp.website.amakenapp.R.id.ratingBar;

public class AddReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_review_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Context x = this;

        RatingBar ratingbar1 = (RatingBar) findViewById(ratingBar);
        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(x, Float.toString(rating), Toast.LENGTH_LONG).show();
            }

        });

    }
}

