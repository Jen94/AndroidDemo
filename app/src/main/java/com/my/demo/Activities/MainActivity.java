package com.my.demo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // можно было все кнопки объявлять так...
        Button btn = findViewById(R.id.buttonShowToast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This is a Toast", Toast.LENGTH_LONG).show();
            }
        });

        // а можно чуть короче: выделим повторяющуюся часть в отдельную функцию
        registerListeners();
    }

    public void registerListeners() {
        // заметим, что меняется каждый раз только идентификатор кнопки (int)...
        // ... и класс активити, которую она вызывает
        registerClickListener(R.id.buttonInteractOtherActivity, FirstInteractingActivity.class);
        registerClickListener(R.id.buttonSharedPrefs, SaveRestorePrefsActivity.class);
        registerClickListener(R.id.buttonSimpleMenu, SimpleMenuActivity.class);
        registerClickListener(R.id.buttonFloatingMenu, FloatingMenuActivity.class);
        registerClickListener(R.id.buttonHardwareParams, HardwareParametersActivity.class);
        registerClickListener(R.id.buttonAudioPlayer, AudioPlayerActivity.class);
        registerClickListener(R.id.buttonWebBrowser, WebViewActivity.class);
        registerClickListener(R.id.buttonBroadcastReceiver, BroadcastReceiverActivity.class);
        registerClickListener(R.id.buttonSimpleService, SimpleServiceActivity.class);
        registerClickListener(R.id.buttonSimpleOverlay, OverlayActivity.class);
        registerClickListener(R.id.buttonShowNotifications, NotificationsActivity.class);
        registerClickListener(R.id.buttonServiceWithNotifications, ServiceWithNotificationsActivity.class);
        registerClickListener(R.id.buttonSimpleElements, SimpleComponentsActivity.class);
        registerClickListener(R.id.buttonTable, SimpleTableActivity.class);
        registerClickListener(R.id.buttonFragment, SimpleFragmentActivity.class);
        registerClickListener(R.id.buttonTableWithTambourine, HardTableActivity.class);
        registerClickListener(R.id.buttonProcessActions, AdditionalListenersActivity.class);
        registerClickListener(R.id.buttonHandler, HandlerActivity.class);
        registerClickListener(R.id.buttonThread, ThreadActivity.class);
        registerClickListener(R.id.buttonAlarm, AlarmActivity.class);
        registerClickListener(R.id.buttonDatabase, DatabaseActivity.class);
        registerClickListener(R.id.buttonLogging, LoggingActivity.class);
        registerClickListener(R.id.buttonTabs, TabsActivity.class);
    }

    public void registerClickListener(int id, final Class<?> cls) {
        // в итоге вместо повторения одинаковых строк они были вынесены в отдельную функцию
        // тип поля "cls" подсмотрел, кликнув с зажатым SHIFT по Intent
        Button btn = findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), cls);
                startActivity(intent);
            }
        });
    }
}
