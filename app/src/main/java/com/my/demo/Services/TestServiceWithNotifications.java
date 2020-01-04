package com.my.demo.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.my.demo.Activities.MainActivity;
import com.my.demo.R;

public class TestServiceWithNotifications extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    String MY_PREFS_NAME = "myprefs";
    private String CHANNEL_ID = "MyNotificationsChannel";


    public BroadcastReceiver MyServiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), "Toast from Service: I hear you!", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created!", Toast.LENGTH_SHORT).show();

        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("MyCustomServiceActionName");
            registerReceiver(MyServiceReceiver, filter);
        } catch (Exception e) {}


        handler = new Handler();  // can not work when app closed - use AlarmManager to be best
        runnable = new Runnable() {
            public void run() {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                Boolean running = prefs.getBoolean("serviceWithNotificationsRunning", false);
                if (running) {
                    setNotify("Notify from Service handler");
                    handler.postDelayed(runnable, 5000);
                }
            }
        };

        handler.postDelayed(runnable, 5000);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(context, "Service stopped", Toast.LENGTH_SHORT).show();
        try {
            unregisterReceiver(MyServiceReceiver);
        } catch (Exception e) {}
        super.onDestroy();
    }

    public void setNotify(String msg) {
        try {
            Intent snoozeIntent = new Intent("ActionFromNotify");
            PendingIntent snoozePendingIntent =
                    PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

            String title = msg;
            createNotificationChannel();

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, title, snoozePendingIntent).build();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("MyNotification from service")
                    .setContentText("Service is still with you")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setContentIntent(null)
                    .setOngoing(false)  // можно смахнуть
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
                   // Toast.makeText(getApplicationContext(), "channel exists", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
    }
}