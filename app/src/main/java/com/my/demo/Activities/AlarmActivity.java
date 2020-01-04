package com.my.demo.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.demo.Receivers.MyAlarmServiceReceiver;
import com.my.demo.R;

import java.util.Calendar;

public class AlarmActivity extends Activity {

    private String CHANNEL_ID = "MyAlarmNotificationsChannel";

    Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);

        Button btn = findViewById(R.id.buttonSetAlarm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "For Alarm trigger wait for 15 min", Toast.LENGTH_SHORT).show();
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(AlarmActivity.this, MyAlarmServiceReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 1);
                calendar.set(Calendar.MINUTE, 10);
                //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 1 * 60 * 1000, pendingIntent);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
            }
        });

        btn = findViewById(R.id.buttonCheckAlarmReceiver);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, MyAlarmServiceReceiver.class);
                sendBroadcast(intent);
            }
        });

        btn = findViewById(R.id.buttonCancelAlarm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(AlarmActivity.this, MyAlarmServiceReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);
            }
        });
    }

}
