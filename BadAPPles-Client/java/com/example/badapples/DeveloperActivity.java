package com.example.badapples;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.badapples.Helpers.Server.post;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        // get arguments
        Bundle arguments = getIntent().getExtras(); //gets arg
        final int File = arguments.getInt("File"); //retrieves arg

        final TextView tvDeveloperFileName = findViewById(R.id.tvDeveloper_FileName);
        final Button btnDeveloperImport = findViewById(R.id.btnDeveloper_Import);
        final Button btnDeveloperInsert = findViewById(R.id.btnDeveloper_Insert);
        final Button btnDeveloperUpdate = findViewById(R.id.btnDeveloper_Update);
        final Button btnDeveloperDelete = findViewById(R.id.btnDeveloper_Delete);
        final Button btnDeveloperSave = findViewById(R.id.btnDeveloper_Save);

        // arrays to help with dynamically setting which files
        String[] fileNames = {"UberJan-Feb-FOIL", "Other-Dial7-B0087", "Other-Firstclass-B01578",
                              "Uber-raw-data-apr14", "other-Federal_02216", "other-Highclass_B01717"};

        // set file name on screen
        tvDeveloperFileName.setText(fileNames[File]);

        Bundle bundle = new Bundle();
        bundle.putInt("File", File);
        bundle.putString("Type", "Developer");

        // Set listeners
        btnDeveloperImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openImportActivity = new Intent(DeveloperActivity.this, ChooseFileActivity.class);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

        btnDeveloperInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openInsertActivity = new Intent(DeveloperActivity.this, InsertActivity.class);
                openInsertActivity.putExtras(bundle);
                startActivity(openInsertActivity);
            }
        });

        btnDeveloperUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openUpdateActivity = new Intent(DeveloperActivity.this, UpdateActivity.class);
                openUpdateActivity.putExtras(bundle);
                startActivity(openUpdateActivity);
            }
        });

        btnDeveloperDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDeleteActivity = new Intent(DeveloperActivity.this, DeleteActivity.class);
                openDeleteActivity.putExtras(bundle);
                startActivity(openDeleteActivity);
            }
        });

        btnDeveloperSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send file number to servlet that moves temp to /back-UpFileNum
                String servlet = String.format("http://10.0.2.2:8080/BadAPPles/BackUp?param1=%1$s", Integer.toString(File));
                post(getApplicationContext(), servlet);
            }
        });

    }
}