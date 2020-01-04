package com.my.demo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.demo.Services.FloatingService;
import com.my.demo.R;

public class OverlayActivity extends Activity {
    private static final int APP_OVERLAY_PERMISSION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overlay_activity);

        Button btn = findViewById(R.id.buttonRequestPermissions);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });

        btn = findViewById(R.id.buttonDrawOverlay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), FloatingService.class));
            }
        });

        btn = findViewById(R.id.buttonRemoveOverlay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), FloatingService.class));
            }
        });
    }

    public void requestPermission() {
        // Нельзя просто объявить разрешение Поверх всех окон в манифесте - нужно явно запросить его у пользователя
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, APP_OVERLAY_PERMISSION);
        } else {
            Toast.makeText(getApplicationContext(), "permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    Boolean checkIfOverlayPermissionGranted() {
        return Settings.canDrawOverlays(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Когда запрос на получение разрешения был закрыт - проваливаемся сюда. И пытаемся понять - дали нам разрешение или нет...
        if (requestCode == APP_OVERLAY_PERMISSION) {
            if (checkIfOverlayPermissionGranted()) {
                Toast.makeText(getApplicationContext(), "permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "permission not granted", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
