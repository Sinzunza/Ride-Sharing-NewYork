package com.example.badapples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_file);

        // get arguments
        Bundle arguments = getIntent().getExtras(); //gets arg
        final String Type = arguments.getString("Type"); //retrieves arg


        // retrieve id's from layout
        final Button btnFileJanFeb = findViewById(R.id.btnChooseFile_JanFeb);
        final Button btnFileDial = findViewById(R.id.btnChooseFile_Dial);
        final Button btnFileFirstClass = findViewById(R.id.btnChooseFile_FirstClass);
        final Button btnFileApr = findViewById(R.id.btnChooseFile_Apr);
        final Button btnFileFederal = findViewById(R.id.btnChooseFile_Federal);
        final Button btnFileHighClass = findViewById(R.id.btnChooseFile_HighClass);

        // create an intent to open the FileActivity and then create bundle pass File argument inside intent
        Intent openImportActivity = new Intent(ChooseFileActivity.this, ImportActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Type", Type);

        // listener for file0 button
        btnFileJanFeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("File", 0);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

        // listener for file1 button
        btnFileDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 1 to the intent and start the FileActivity
                bundle.putInt("File", 1);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

        // listener for file2 button
        btnFileFirstClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 2 to the intent and start the FileActivity
                bundle.putInt("File", 2);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

        // listener for file3 button
        btnFileApr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 3 to the intent and start the FileActivity
                bundle.putInt("File", 3);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

        // listener for file4 button
        btnFileFederal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add the File argument 3 to the intent and start the FileActivity
                bundle.putInt("File", 4);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

        // listener for file5 button
        btnFileHighClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add the File argument 3 to the intent and start the FileActivity
                bundle.putInt("File", 5);
                openImportActivity.putExtras(bundle);
                startActivity(openImportActivity);
            }
        });

    }
}