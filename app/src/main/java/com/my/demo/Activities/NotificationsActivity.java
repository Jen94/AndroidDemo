package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.my.demo.R;

public class NotificationsActivity extends Activity {

    private String CHANNEL_ID = "MyNotificationsChannel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);

        Button btn = findViewById(R.id.buttonCreateNotificationChannel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();
            }
        });

        btn = findViewById(R.id.buttonSendNotify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotify();
            }
        });

        btn = findViewById(R.id.buttonCloseAllNotifications);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAllNotifys();
            }
        });
    }

    public void setNotify() {
        try {
            // все, что можно сделать из уведомления - отправить sendBroadcast. А дальше уже ловить его в приложении и что-то с ним делать
            Intent snoozeIntent = new Intent("ActionFromNotify");
            PendingIntent snoozePendingIntent =
                    PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

            String title = "Start";
          //  createNotificationChannel();

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, title, snoozePendingIntent).build();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("MyNotification")
                    .setContentText("Text here")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setContentIntent(null)
                    .setOngoing(true)  // нельзя смахнуть
                    .setSound(null)
                    .addAction(action);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            int notificationId = (int) (System.currentTimeMillis() / 4);
            notificationManager.notify(notificationId, mBuilder.build());

        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    public void closeAllNotifys() {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    private void createNotificationChannel() {
        // на новых Андроидах (а пока мы пишем под API>=28) для уведомлений нужно создавать каналы.
        // в настройках приложения можно отключать некоторые из них - вот как раз с этой целью
        try {
            CharSequence channelName = CHANNEL_ID;
            String channelDesc = "channelDesc";
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
                channel.setDescription(channelDesc);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                assert notificationManager != null;
                NotificationChannel currChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
                if (currChannel == null) {
                    notificationManager.createNotificationChannel(channel);
                    Toast.makeText(getApplicationContext(), "channel created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "channel exists", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
    }

}
