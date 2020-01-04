package com.my.demo.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.my.demo.R;

public class HardwareParametersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardware_parameters_activity);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Integer width = metrics.widthPixels;
        Integer height = metrics.heightPixels;
        Integer densityDpi = (int)(metrics.density * 160f);
        Integer apiVersion = android.os.Build.VERSION.SDK_INT;

        TextView tv = findViewById(R.id.textViewScreenSizeHeight);
        tv.setText(height.toString());
        tv = findViewById(R.id.textViewScreenSizeWidth);
        tv.setText(width.toString());
        tv = findViewById(R.id.textViewDpi);
        tv.setText(densityDpi.toString());
        tv = findViewById(R.id.textViewAndroidApiVersion);
        tv.setText(apiVersion.toString());
    }
}
