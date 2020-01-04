package com.my.demo.Receivers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.my.demo.Activities.MainActivity;
import com.my.demo.R;

public class MyAlarmServiceReceiver extends BroadcastReceiver {
    private String CHANNEL_ID = "MyNotificationsChannel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MyAlarmServiceReceiver onReceive", Toast.LENGTH_SHORT).show();
        setNotify("Notify from AlarmManager", context);
    }

    public void setNotify(String msg, Context ctxt) {
        try {
            Intent snoozeIntent = new Intent("ActionFromNotify");
            PendingIntent snoozePendingIntent =
                    PendingIntent.getBroadcast(ctxt, 0, snoozeIntent, 0);

            createNotificationChannel(ctxt);

            Intent intent = new Intent(ctxt, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, msg, snoozePendingIntent).build();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctxt, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(msg)
                    .setContentText(msg)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setContentIntent(null)
                    .setOngoing(false)  // можно смахнуть
                    .setSound(null)
                    .addAction(action);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctxt);
            int notificationId = (int) (System.currentTimeMillis() / 4);
            notificationManager.notify(notificationId, mBuilder.build());

        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }


    private void createNotificationChannel(Context ctxt) {
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
                NotificationManager notificationManager = ctxt.getSystemService(NotificationManager.class);
                assert notificationManager != null;
                NotificationChannel currChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
                if (currChannel == null) {
                    notificationManager.createNotificationChannel(channel);
                    Toast.makeText(ctxt, "channel created", Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(ctxt, "channel exists", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
    }
}
