package com.amakenapp.website.amakenapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
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
 /*
public class DialogPlaceFragment extends DialogFragment implements View.OnClickListener {


    View view;
    Button Gall;
    Button Takephoto;
    
    Button Done;
    ImageView imageView;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_photo, container, false);

// inflater used to display the dialog photo

        view = inflater.inflate(R.layout.dialog_photo, container, false);
        //gallery button
        Gall = (Button) view.findViewById(R.id.buttonFromGall);
        Gall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGallery();
            }

        });
         //Takephoto button
        Takephoto = (Button) view.findViewById(R.id.buttonTakephoto);
        //clear photo button
        Clearphoto = (Button) view.findViewById(R.id.buttonClearphoto);
        
        //Image View 
        imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraPicture();
            }
        });

        //done button
        Done = (Button) view.findViewById(R.id.buttonDone);
         Done.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        // return to the main page 
        return rootView;


    }
 /**  method used to open a gallery from the device  */
/*
    private void openGallery(){
        Intent gallery = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
   
     /**
     * switching cases for the images collected from gallery or the camera
     */

   // @Override
   // public void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  switch (requestCode) {
       //     case 0:

          //      super.onActivityResult(requestCode, resultCode, data);
          //      if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
          //          imageUri = data.getData();
          //          imageView.setImageURI(imageUri);
           //     }

           // case 1:
          //      super.onActivityResult(requestCode, resultCode, data);
         //       Bitmap bp = (Bitmap) data.getExtras().get("data");
          //      imageView.setImageBitmap(bp);
//

     //   }
  //  }
 /**
     * Called when the user taps the Camera button
     */

   // public void CameraPicture() {
   //     Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
   //     startActivityForResult(intent, 0);
   // }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bp);}
}


}
**/
