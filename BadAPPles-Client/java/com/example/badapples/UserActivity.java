package com.example.badapples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final Button searching = findViewById(R.id.btnUser_Search);
        final Button graphing = findViewById(R.id.btnUser_Analytics);

        Intent openFileActivity = new Intent(UserActivity.this, SearchActivity.class);
        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(openFileActivity);
            }
        });

        Intent openFileActivity2 = new Intent(UserActivity.this, AnalyticActivity.class);
        graphing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(openFileActivity2);
            }
        });
    }
}