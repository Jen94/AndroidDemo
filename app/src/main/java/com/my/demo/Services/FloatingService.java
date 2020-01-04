package com.my.demo.Services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Rect;
import android.app.ActivityOptions;
import android.os.Build;
import android.content.pm.PackageManager;

import com.my.demo.R;

public class FloatingService extends Service {

    private WindowManager mWindowManager;
    private View mFloatingView;
    private ImageView mainButton;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // сервис, слушающий щелчки по плавающей кнопке (даже когда приложение закрыто)
        super.onCreate();
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.floating, null);

        int LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        // Set the position to the top right corner of the screen
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 50;
        params.y = 50;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);

        mainButton = (ImageView) mFloatingView.findViewById(R.id.mainButton);

        //Drag and move floating view using user's touch action.
        mainButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You touched me", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mFloatingView && null != mWindowManager)
            mWindowManager.removeView(mFloatingView);
    }
}
