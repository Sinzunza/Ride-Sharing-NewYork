package com.example.badapples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.badapples.Helpers.Server.search;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get arguments
        //Bundle arguments = getIntent().getExtras(); //gets arg
        //final int File = arguments.getInt("File"); //retrieves arg

        // retrieve id's from layout
        final Button searchquery1 = findViewById(R.id.tVSearch_Query1);
        final Button searchquery2 = findViewById(R.id.tVSearch_Query2);
        final Button searchquery3 = findViewById(R.id.tVSearch_Query3);
        final Button searchquery4 = findViewById(R.id.tVSearch_Query4);
        final Button searchquery5 = findViewById(R.id.tVSearch_Query5);
        final Button searchquery6 = findViewById(R.id.tVSearch_Query6);
        final TextView result = findViewById(R.id.tVSearch_Result);


        String[] servlets = {"http://10.0.2.2:8080/BadAPPles/RidesOnDate", "http://10.0.2.2:8080/BadAPPles/RidesInBurrough",
                             "http://10.0.2.2:8080//BadAPPles/FirstClassServlet", "http://10.0.2.2:8080/BadAPPles/HowManyPast10Servlet",
                             "http://10.0.2.2:8080/BadAPPles/ActiveVehiclesOver1000Servlet", "http://10.0.2.2:8080/BadAPPles/StreetServlet"};


        // listener for search1 button
        searchquery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                search(getApplicationContext(), result, servlets[0]);
            }
        });
        searchquery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                search(getApplicationContext(), result, servlets[1]);
            }
        });
        searchquery3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                search(getApplicationContext(), result, servlets[2]);
            }
        });
        searchquery4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                search(getApplicationContext(), result, servlets[3]);
            }
        });
        searchquery5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                search(getApplicationContext(), result, servlets[4]);
            }
        });
        searchquery6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                search(getApplicationContext(), result, servlets[5]);
            }
        });

    }
}