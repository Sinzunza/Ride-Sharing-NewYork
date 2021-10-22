package com.example.badapples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.badapples.Helpers.Server.getRow;
import static com.example.badapples.Helpers.Server.post;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        // get arguments
        Bundle arguments = getIntent().getExtras(); //gets arg
        final int File = arguments.getInt("File"); //retrieves arg

        // retrieve id's from layout
        final TextView deletename = findViewById(R.id.tV_DeleteName);
        final EditText deleterow = findViewById(R.id.etDelete_RowInput);
        final TextView tvDelete_DisplayRow = findViewById(R.id.tvDelete_DisplayRow);
        final Button btnDelete = findViewById(R.id.bt_Delete);
        final Button btnDelete_SeeRow = findViewById(R.id.btnDelete_SeeRow);

        // arrays to help with dynamically setting which files
        String[] fileNames = {"UberJan-Feb-FOIL", "Other-Dial7-B0087", "Other-Firstclass-B01578",
                              "Uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};

        // set file name on screen
        deletename.setText(fileNames[File]);

        // listener for see row button
        btnDelete_SeeRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String row = deleterow.getText().toString();
                getRow(getApplicationContext(), tvDelete_DisplayRow, Integer.toString(File), row);
            }
        });

        // listener for delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String row = deleterow.getText().toString();
                String servlet = String.format("http://10.0.2.2:8080/BadAPPles/Delete?param1=%1$s&param2=%2$s", Integer.toString(File), row);
                post(getApplicationContext(), servlet);
            }
        });

    }
}
