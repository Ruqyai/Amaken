package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.facebook.FacebookSdk;
import com.amakenapp.website.amakenapp.R;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import android.util.Log;
import android.widget.Button;


import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bolts.AppLinks;

public class InvitesActivity extends AppCompatActivity implements View.OnClickListener  {
    private static final int REQUEST_INVITE = 1;
    Button mButton;
    Button smsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invites);
        mButton = (Button) findViewById(R.id.share_button);
        mButton.setOnClickListener(this);
        smsButton = (Button) findViewById(R.id.share_button_Msg);
        smsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton){
        Intent shareIntent = new PlusShare.Builder(this)
                .setText("Hello, this Amazing App! \n \n https://play.google.com/store/apps/details?id=com.seu")
                .setType("image/png")
                .setContentDeepLinkId("testID",
                        "Test Title",
                        "Test Description",
                        Uri.parse("https://developers.google.com/+/images/interactive-post-android.png"))
                .getIntent();
        startActivityForResult(shareIntent, 0);}
        else if (v == smsButton){
            onInviteClicked();
        }
    }

private void onInviteClicked() {

    PackageManager pm=getPackageManager();
    try {

        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        String text = "Hello, this is amazing App \n \n https://play.google.com/store/apps/details?id=com.seu";

        PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
        waIntent.setPackage("com.whatsapp");

        waIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(waIntent, "Share with"));

    } catch (PackageManager.NameNotFoundException e) {
        Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                .show();
    }

}
}

