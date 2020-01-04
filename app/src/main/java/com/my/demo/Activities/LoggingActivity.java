package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.my.demo.R;

public class LoggingActivity extends Activity {

    String LOG_TAG = "MYDEMOAPP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logging_activity);

        Button btn = findViewById(R.id.buttonLogInfo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "Test info log");
            }
        });

        btn = findViewById(R.id.buttonLogInfo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Test debug log");
            }
        });

        btn = findViewById(R.id.buttonLogError);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_TAG, "Test error log");
            }
        });

        btn = findViewById(R.id.buttonCrashApp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                i = i/i;
            }
        });
    }
}
