package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.amakenapp.website.amakenapp.R;

import static android.app.Activity.RESULT_OK;

public class DialogPlaceFragment extends DialogFragment implements View.OnClickListener {
    View view;
    Button Gall;
    Button Takephoto;
    Button Clearphoto;
    Button Done;
    ImageView imageView;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_photo, container, false);


        view = inflater.inflate(R.layout.dialog_photo, container, false);
        //gallery button
        Gall = (Button) view.findViewById(R.id.buttonFromGall);
        Gall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGallery();
            }

        });
        Takephoto = (Button) view.findViewById(R.id.buttonTakephoto);
        Clearphoto = (Button) view.findViewById(R.id.buttonClearphoto);

        imageView = (ImageView) view.findViewById(R.id.image_view);

        //done button
        Done = (Button) view.findViewById(R.id.buttonDone);
        Done.setOnClickListener(this);
        // Do something else
        return view;


    }

    private void openGallery(){
        Intent gallery = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    public void onClick(View v ){
        this.dismiss();
    }
    @Override
    public void onActivityResult(int  requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

    }


}