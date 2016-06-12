package com.course.project01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by wynter on 6/12/2016.
 */
public class Other  extends AppCompatActivity {


    private Button button;  //instance of the button object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //R is an automatically generated class for resources

        button = (Button)findViewById(R.id.button); //find the button in the view layout "activity_mail.xml"
        button.setOnClickListener(new ButtonClick());   //bind an event class to the button

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
            myDialog.setTitle("My first android App (Other)");  //set title of the alert dialog
            myDialog.setMessage("Hello, this is Android tutorial.");    //Change it to your name
            myDialog.setCancelable(true);   //what will happen if you change it to false
            myDialog.setPositiveButton("OK", new AlertDialogGetClick());    //set a button in the alert dialog
            myDialog.create();  //ask operating system to prepare the dialog
            myDialog.show();    //show the dialog to the front of view
        }
    }


}


