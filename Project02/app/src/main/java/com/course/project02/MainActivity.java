package com.course.project02;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    private Button button;  //instance of the button object
    float rotate = 0;

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //R is an automatically generated class for resources

        button = (Button)findViewById(R.id.button); //find the button in the view layout "activity_mail.xml"
        button.setOnClickListener(new ButtonClick());   //bind an event class to the button

        loadImage();

        setRotateButton();
    }

    private void loadImage(){


        //add image
        image = (ImageView)findViewById(R.id.imageView);
        AssetManager assetManager = getAssets();
        try {
            InputStream imgInput = assetManager.open("globe.png");
            Bitmap myBitmap = BitmapFactory.decodeStream(imgInput);
            image.setImageBitmap(myBitmap);
        }catch(IOException e){
            e.printStackTrace();
        }

        //image.getLayoutParams().height=256;
        //image.getLayoutParams().width=256;
    }

    private void setRotateButton(){
        Button buttonRotate = (Button) findViewById(R.id.buttonRotate);
        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate += 30;
                image.setRotation(rotate);
            }
        });
    }

}

class AlertDialogGetClick implements DialogInterface.OnClickListener{

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();    //hide the dialog
    }
}


class ButtonClick implements View.OnClickListener{

    private AlertDialog.Builder myDialog;   //the instance of an alert dialog
    @Override
    public void onClick(View v) {
        myDialog = new AlertDialog.Builder(v.getContext()); //create an instance of an alert dialog
        myDialog.setTitle("My first android App");  //set title of the alert dialog
        myDialog.setMessage("Hello, this is Android tutorial.");    //Change it to your name
        myDialog.setCancelable(true);   //what will happen if you change it to false
        myDialog.setPositiveButton("OK", new AlertDialogGetClick());    //set a button in the alert dialog
        myDialog.create();  //ask operating system to prepare the dialog
        myDialog.show();    //show the dialog to the front of view
    }
}

