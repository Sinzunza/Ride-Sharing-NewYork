package com.example.badapples;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.badapples.Helpers.Server.getFiles;

public class ImportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        // get arguments
        Bundle arguments = getIntent().getExtras(); //gets arg
        final int File = arguments.getInt("File"); //retrieves arg
        final String Type = arguments.getString("Type");

        final TextView importname = findViewById(R.id.tvImport_Name);
        final RecyclerView rvImport = findViewById(R.id.rvImport_Recycyler);

        // arrays to help with dynamically setting which files
        String[] fileNames = {"UberJan-Feb-FOIL", "Other-Dial7-B0087", "Other-Firstclass-B01578",
                              "Uber-raw-data-apr14", "other-Federal_02216", "other-Highclass_B01717"};

        // set file name on screen
        importname.setText(fileNames[File]);

        // servlet with parameter
        String servlet = String.format("http://10.0.2.2:8080/BadAPPles/ListFiles?param1=%1$s",
                                        Integer.toString(File));

        // getFiles function call to server, also display file names inside adapter
        getFiles(getApplicationContext(), servlet, rvImport, File, Type);

    }

}

