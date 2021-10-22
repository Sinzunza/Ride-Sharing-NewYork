package com.example.badapples;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.badapples.Helpers.Server.post;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // get arguments
        Bundle arguments = getIntent().getExtras(); //gets arg
        final int File = arguments.getInt("File"); //retrieves arg

        // retrieve id's from layout
        final TextView insertname = findViewById(R.id.tV_InsertName);
        final EditText insertcolumn1 = findViewById(R.id.eT_InsertColumn1);
        final EditText insertcolumn2 = findViewById(R.id.eT_InsertColumn2);
        final EditText insertcolumn3 = findViewById(R.id.eT_InsertColumn3);
        final EditText insertcolumn4 = findViewById(R.id.eT_InsertColumn4);
        final EditText insertcolumn5 = findViewById(R.id.eT_InsertColumn5);
        final EditText insertcolumn6  = findViewById(R.id.eT_InsertColumn6);
        final EditText insertcolumn7  = findViewById(R.id.eT_InsertColumn7);
        final TextView tvInsertColumn1 = findViewById(R.id.tvInsert_Column1);
        final TextView tvInsertColumn2 = findViewById(R.id.tvInsert_Column2);
        final TextView tvInsertColumn3 = findViewById(R.id.tvInsert_Column3);
        final TextView tvInsertColumn4 = findViewById(R.id.tvInsert_Column4);
        final TextView tvInsertColumn5 = findViewById(R.id.tvInsert_Column5);
        final TextView tvInsertColumn6 = findViewById(R.id.tvInsert_Column6);
        final TextView tvInsertColumn7 = findViewById(R.id.tvInsert_Column7);
        final Button insert = findViewById(R.id.bt_Insert);

        // dial = 6, firstClass = 3, jan-feb = 4, april = 4

        // arrays to help with dynamically setting which files
        String[] fileNames = {"UberJan-Feb-FOIL", "Other-Dial7-B0087", "Other-Firstclass-B01578",
                              "Uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};


        // set file name on screen
        insertname.setText(fileNames[File]);
        if (File == 0) {
            insertcolumn1.setHint("Dispatch Base Num");
            insertcolumn2.setHint("Date");
            insertcolumn3.setHint("Active Vehicles");
            insertcolumn4.setHint("Trips");
        } else if (File == 1){
            insertcolumn1.setHint("Date");
            insertcolumn2.setHint("Time");
            insertcolumn3.setHint("State");
            insertcolumn4.setHint("Pickup Borough");
            insertcolumn5.setHint("Address Num");
            insertcolumn6.setHint("Street");
        } else if (File == 2){
            insertcolumn1.setHint("Date");
            insertcolumn2.setHint("Time");
            insertcolumn3.setHint("Pickup Address");
        } else if (File == 3){
            insertcolumn1.setHint("Date/Time");
            insertcolumn2.setHint("Lat");
            insertcolumn3.setHint("Lon");
            insertcolumn4.setHint("Base");
        } else if (File == 4){
            insertcolumn1.setHint("Date");
            insertcolumn2.setHint("Time");
            insertcolumn3.setHint("Pickup Address");
            insertcolumn4.setHint("Drop Off Address");
            insertcolumn5.setHint("Routing Details");
            insertcolumn6.setHint("Pickup Address");
            insertcolumn7.setHint("Status");
        } else if (File == 5){
            insertcolumn1.setHint("Date");
            insertcolumn2.setHint("Time");
            insertcolumn3.setHint("Pickup Address");
        }


        // hide columns for files that don't need them
        if (File == 2 || File == 5){ // file 2 only has 3 columns, therefore hide the rest of the views
            ViewGroup.LayoutParams param4 = insertcolumn4.getLayoutParams();
            param4.height = 0;
            insertcolumn4.setLayoutParams(param4);
            insertcolumn4.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV4 = tvInsertColumn4.getLayoutParams();
            paramTV4.height = 0;
            tvInsertColumn4.setLayoutParams(paramTV4);
            tvInsertColumn4.setVisibility(View.GONE);

            ViewGroup.LayoutParams param5 = insertcolumn5.getLayoutParams();
            param5.height = 0;
            insertcolumn5.setLayoutParams(param5);
            insertcolumn5.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV5 = tvInsertColumn5.getLayoutParams();
            paramTV5.height = 0;
            tvInsertColumn5.setLayoutParams(paramTV5);
            tvInsertColumn5.setVisibility(View.GONE);

            ViewGroup.LayoutParams param6 = insertcolumn6.getLayoutParams();
            param6.height = 0;
            insertcolumn6.setLayoutParams(param6);
            insertcolumn6.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV6 = tvInsertColumn6.getLayoutParams();
            paramTV6.height = 0;
            tvInsertColumn6.setLayoutParams(paramTV6);
            tvInsertColumn6.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV7 = tvInsertColumn7.getLayoutParams();
            paramTV7.height = 0;
            tvInsertColumn7.setLayoutParams(paramTV7);
            tvInsertColumn7.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV7Edit = insertcolumn7.getLayoutParams();
            paramTV7Edit.height = 0;
            insertcolumn7.setLayoutParams(paramTV7Edit);
            insertcolumn7.setVisibility(View.GONE);

        }
        else if (File == 0 || File == 3){ // file 0 & 3 only have 4 columns, therefore hide the rest of the views

            ViewGroup.LayoutParams param5 = insertcolumn5.getLayoutParams();
            param5.height = 0;
            insertcolumn5.setLayoutParams(param5);
            insertcolumn5.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV5 = tvInsertColumn5.getLayoutParams();
            paramTV5.height = 0;
            tvInsertColumn5.setLayoutParams(paramTV5);
            tvInsertColumn5.setVisibility(View.GONE);

            ViewGroup.LayoutParams param6 = insertcolumn6.getLayoutParams();
            param6.height = 0;
            insertcolumn6.setLayoutParams(param6);
            insertcolumn6.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV6 = tvInsertColumn6.getLayoutParams();
            paramTV6.height = 0;
            tvInsertColumn6.setLayoutParams(paramTV6);
            tvInsertColumn6.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV7 = tvInsertColumn7.getLayoutParams();
            paramTV7.height = 0;
            tvInsertColumn7.setLayoutParams(paramTV7);
            tvInsertColumn7.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV7Edit = insertcolumn7.getLayoutParams();
            paramTV7Edit.height = 0;
            insertcolumn7.setLayoutParams(paramTV7Edit);
            insertcolumn7.setVisibility(View.GONE);

        } else if (File == 1) {
            ViewGroup.LayoutParams paramTV7 = tvInsertColumn7.getLayoutParams();
            paramTV7.height = 0;
            tvInsertColumn7.setLayoutParams(paramTV7);
            tvInsertColumn7.setVisibility(View.GONE);

            ViewGroup.LayoutParams paramTV7Edit = insertcolumn7.getLayoutParams();
            paramTV7Edit.height = 0;
            insertcolumn7.setLayoutParams(paramTV7Edit);
            insertcolumn7.setVisibility(View.GONE);
        }



        // listener for insert button
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // initiate send string with file
                String send = "";

                // all files have 3 columns, therefore concatenate them to send
                final String column1 = insertcolumn1.getText().toString();
                final String column2 = insertcolumn2.getText().toString();
                final String column3 = insertcolumn3.getText().toString();
                send += column1 + "," + column2 + "," + column3;

                if (File == 0 || File == 3){
                    // concatenate column 4 to send
                    final String column4 = insertcolumn4.getText().toString();
                    send += "," + column4;
                }
                else if (File == 1){
                    // concatenate column 4,5,6 to send
                    final String column4 = insertcolumn4.getText().toString();
                    final String column5 = insertcolumn5.getText().toString();
                    final String column6 = insertcolumn6.getText().toString();
                    send += "," + column4 + "," + column5 + "," + column6;
                } else if (File == 4){
                    // concatenate column 4,5,6 to send
                    final String column4 = insertcolumn4.getText().toString();
                    final String column5 = insertcolumn5.getText().toString();
                    final String column6 = insertcolumn6.getText().toString();
                    final String column7 = insertcolumn7.getText().toString();
                    send += "," + column4 + "," + column5 + "," + column6 + "," + column7;
                }

                String servlet = String.format("http://10.0.2.2:8080/BadAPPles/Insert?param1=%1$s&param2=%2$s",
                        Integer.toString(File), send);

                post(getApplicationContext(), servlet);
            }
        });


    }
}