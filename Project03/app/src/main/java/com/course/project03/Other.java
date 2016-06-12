package com.course.project03;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wynter on 6/12/2016.
 * This file contains the correct solution for your reference
 */
public class Other extends AppCompatActivity implements SensorEventListener {


    private Button button;  //instance of the button object
    float rotate = 0;

    private ImageView image;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.course.project03.R.layout.activity_main); //R is an automatically generated class for resources

        button = (Button)findViewById(com.course.project03.R.id.button); //find the button in the view layout "activity_mail.xml"
        button.setOnClickListener(new ButtonClick());   //bind an event class to the button

        loadImage();

        setRotateButton();

        setSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //for(int i=0; i<event.values.length; ++i){
        //   Log.v("sensor","value["+i+"]:"+event.values[i]);
        //}
        double x = event.values[0]; //positive direction (x axis): from right side to the left side of the tablet
        //Log.v("degree",""+degree);
        double degree = Math.toDegrees(Math.acos(x/9.80665));

        this.image.setRotation(this.rotate+(float)degree-90);
    }

    private void loadImage(){


        //add image
        image = (ImageView)findViewById(com.course.project03.R.id.imageView);
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
        Button buttonRotate = (Button) findViewById(com.course.project03.R.id.buttonRotate);
        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate += 30;
                image.setRotation(rotate);
            }
        });
    }

    private void setSensor(){
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, sensor);
        super.onPause();
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

}

