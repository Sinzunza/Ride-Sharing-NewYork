package com.example.badapples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.badapples.Helpers.Server.post;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // get arguments
        Bundle arguments = getIntent().getExtras(); //gets arg
        final int File = arguments.getInt("File"); //retrieves arg

        // retrieve id's from layout
        final TextView updatename = findViewById(R.id.tv_UpdateName);
        final EditText updaterow = findViewById(R.id.et_UpdateRow);
        final EditText updatecolumn = findViewById(R.id.et_UpdateColumn);
        final EditText updateinput = findViewById(R.id.etUpdate_Value);
        final Button update = findViewById(R.id.bt_Update);

        // arrays to help with dynamically setting which files
        String[] fileNames = {"UberJan-Feb-FOIL", "Other-Dial7-B0087", "Other-Firstclass-B01578",
                               "Uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};

        // set file name on screen
        updatename.setText(fileNames[File]);

        // listener for update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String row = updaterow.getText().toString(); //gets user input
                final String column = updatecolumn.getText().toString();
                final String input = updateinput.getText().toString();
                String servlet = String.format("http://10.0.2.2:8080/BadAPPles/Update?param1=%1$s&param2=%2$s&param3=%3$s&param4=%4$s",
                                                Integer.toString(File), row, column, input);
                post(getApplicationContext(), servlet);
            }
        });

    }
}
