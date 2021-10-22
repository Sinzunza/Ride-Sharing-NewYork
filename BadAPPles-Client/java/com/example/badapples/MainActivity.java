package com.example.badapples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.badapples.Helpers.Server.post;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnMainUser = findViewById(R.id.btnChooseFile_JanFeb);
        final Button btnMainDeveloper = findViewById(R.id.btnChooseFile_Dial);

        Bundle bundle = new Bundle();
        bundle.putInt("File", 0);

        String servlet = String.format("http://10.0.2.2:8080/BadAPPles/CreateTempServlet?param1=%1$s&param2=%2$s",
                                        "100", "blank");

        post(getApplicationContext(), servlet);

        // Set listeners
        btnMainUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openUserActivity = new Intent(MainActivity.this, UserActivity.class);
                openUserActivity.putExtras(bundle);
                startActivity(openUserActivity);
            }
        });

        btnMainDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDeveloperActivity = new Intent(MainActivity.this, DeveloperActivity.class);
                openDeveloperActivity.putExtras(bundle);
                startActivity(openDeveloperActivity);
            }
        });

    }

}
