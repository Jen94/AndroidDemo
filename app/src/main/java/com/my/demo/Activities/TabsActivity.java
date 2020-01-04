package com.my.demo.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.my.demo.R;

public class TabsActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_activity);

        // получаем TabHost
        TabHost tabHost = getTabHost();

        // инициализация была выполнена в getTabHost
        // метод setup вызывать не нужно

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Один");
        tabSpec.setContent(new Intent(this, SaveRestorePrefsActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Два");
        tabSpec.setContent(new Intent(this, FloatingMenuActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Три");
        tabSpec.setContent(new Intent(this, HardwareParametersActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag4");
        tabSpec.setIndicator("Четыре");
        tabSpec.setContent(new Intent(this, AudioPlayerActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag5");
        tabSpec.setIndicator("Пять");
        tabSpec.setContent(new Intent(this, WebViewActivity.class));
        tabHost.addTab(tabSpec);
    }

}
