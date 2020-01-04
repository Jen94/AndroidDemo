package com.my.demo.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.my.demo.R;
import com.my.demo.Services.TestService;
import com.my.demo.Services.TestServiceForeground;

public class SimpleServiceActivity extends Activity {

    String MY_PREFS_NAME = "myprefs";

    public BroadcastReceiver MyPrintReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), "pong", Toast.LENGTH_SHORT).show();

            TextView tv = findViewById(R.id.textViewSimpleServiceStatus);
            String msg = intent.getStringExtra("msg");
            tv.setText(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_service_activity);

        IntentFilter filter = new IntentFilter();
        filter.addAction("printMsgFromService");
        registerReceiver(MyPrintReceiver, filter);

        Button btn = findViewById(R.id.buttonStartForegroundService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startForegroundService(new Intent(getApplicationContext(), TestServiceForeground.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "error start service: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn = findViewById(R.id.buttonStartService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startService(new Intent(getApplicationContext(), TestService.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "error start service: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn = findViewById(R.id.buttonStopService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stopService(new Intent(getApplicationContext(), TestService.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "error start service: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn = findViewById(R.id.buttonStopForegroundService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stopService(new Intent(getApplicationContext(), TestServiceForeground.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "error start service: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn = findViewById(R.id.buttonSendBroadcastToService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendBroadcast(new Intent("MyCustomServiceActionName"));
            }
        });
    }

}
