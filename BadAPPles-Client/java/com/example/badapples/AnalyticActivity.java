
package com.example.badapples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnalyticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);

        final Button btnAnalyticsPeakHour = findViewById(R.id.btnAnalytics_PeakHours);
        final Button btnAnalyticsPickUpDay = findViewById(R.id.btnAnalytics_PickUpDay);
        final Button btnAnalyticsPickUpTime = findViewById(R.id.btnAnalytics_PickUpTime);
        final Button btnAnalyticsDestStat = findViewById(R.id.btnAnalytics_DestStat);
        final Button btnAnalyticsDestCoord = findViewById(R.id.btnAnalytics_DestCoord);
        final Button btnAnalyticsClassAM = findViewById(R.id.btnAnalytics_ClassAM);
        final Button btnAnalyticsClassPM = findViewById(R.id.btnAnalytics_ClassPM);

        Intent openGraphActivity = new Intent(AnalyticActivity.this, GraphActivity.class);
        Bundle bundle = new Bundle();

        btnAnalyticsPeakHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 0);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );
            }
        });

        btnAnalyticsPickUpDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 1);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );
            }
        });

        btnAnalyticsPickUpTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 2);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );
            }
        });

        btnAnalyticsDestStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 3);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );
            }
        });

        btnAnalyticsDestCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 4);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );
            }
        });

        btnAnalyticsClassAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 5);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );
            }
        });
        btnAnalyticsClassPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // add the File argument 0 to the intent and start the FileActivity
                bundle.putInt("Analytics", 6);
                openGraphActivity .putExtras(bundle);
                startActivity(openGraphActivity );;
            }
        });

    }
}