package com.my.demo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.demo.R;
import com.my.demo.Services.TestServiceWithNotifications;

public class ServiceWithNotificationsActivity extends Activity {
    String MY_PREFS_NAME = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_with_notifications_activity);

        Button btn = findViewById(R.id.buttonStartNotificationsService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("serviceWithNotificationsRunning", true);
                editor.apply();
                startService(new Intent(getApplicationContext(), TestServiceWithNotifications.class));

            }
        });

        btn = findViewById(R.id.buttonStopNotificationsService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("serviceWithNotificationsRunning", false);
                editor.apply();
                stopService(new Intent(getApplicationContext(), TestServiceWithNotifications.class));
            }
        });
    }

}
