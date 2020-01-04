package com.my.demo.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.demo.R;

public class BroadcastReceiverActivity extends Activity {

    public BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), "Broadcast receiver: received!", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver_activity);

        Button btn = findViewById(R.id.buttonRegisterReceiver);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    registerReceiver();
                    Toast.makeText(getApplicationContext(), "Register receiver: OK", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error registering receiver: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btn = findViewById(R.id.buttonSendBroadcast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mySendBroadcast();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error send broadcast: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btn = findViewById(R.id.buttonUnregisterReceiver);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    unregisterReceiver(MyReceiver);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error unregister Receiver: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("MyCustomActionName");
        registerReceiver(MyReceiver, filter);
    }

    public void mySendBroadcast() {
        sendBroadcast(new Intent("MyCustomActionName"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(MyReceiver);
        } catch (Exception e) {}
    }
}
