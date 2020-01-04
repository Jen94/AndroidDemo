package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.demo.R;

public class HandlerActivity extends Activity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_activity);

        handler = new Handler();

        Button btn = findViewById(R.id.buttonAddJobToHandler);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Delayed task executed", Toast.LENGTH_SHORT).show();
                    }
                };
                handler.postDelayed(r, 5000);
            }
        });

        btn = findViewById(R.id.buttonAddCycleToHandler);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Delayed task executed", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(this, 5000);  // запускает цикл
                    }
                };
                handler.postDelayed(r, 5000);
            }
        });

        btn = findViewById(R.id.buttonRemoveAllTasksFromHandler);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                Toast.makeText(getApplicationContext(), "Handler clear", Toast.LENGTH_SHORT).show();
            }
        });

        btn = findViewById(R.id.buttonHandlerButthurt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    public void run() {
                        long initTime = System.currentTimeMillis();
                        boolean timeElapsed = false;
                        while(!timeElapsed){
                            if(System.currentTimeMillis() - initTime > 10000 ){
                                timeElapsed = true;  // очень ресурсоемкая функция, так делать нельзя! (тут сделано специально, чтобы съесть все ресурсы). В обычных приложениях в бесконечных циклах обязательно добавляют sleep
                            }
                        }
                    }
                };
                Toast.makeText(getApplicationContext(), "Hard Delayed task started", Toast.LENGTH_SHORT).show();
                handler.postDelayed(r, 100);
            }
        });
    }
}


