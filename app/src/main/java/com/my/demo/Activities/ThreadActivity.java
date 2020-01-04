package com.my.demo.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.demo.R;

public class ThreadActivity extends AppCompatActivity {
    String MY_PREFS_NAME = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_activity);

        Button btn = findViewById(R.id.buttonStartThread);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButthurtThread((TextView)findViewById(R.id.textViewThreadStatus));
            }
        });

        btn = findViewById(R.id.buttonDemoThreadStop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("running", true);
                editor.apply();
                startWaitingThread((TextView)findViewById(R.id.textViewThreadStatus));
            }
        });

        btn = findViewById(R.id.buttonResetSuperValue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("running", false);
                editor.apply();
            }
        });
    }

    public void startButthurtThread(final TextView statusTv) {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    statusTv.setText("Thread started");  // too bad to communicate UI elements from Thread! Use printReceiver as in SimpleServiceActivity!
                    long initTime = System.currentTimeMillis();
                    boolean timeElapsed = false;
                    while(!timeElapsed){
                        if(System.currentTimeMillis() - initTime > 10000 ){
                            timeElapsed = true;
                        }
                    }
                    statusTv.setText("Thread stopped");  // too bad!
                } catch (Exception e) {
                    statusTv.setText("Thread error " + e.toString());  // too bad!
                }
            }
        };
        thread.start();
    }

    public void startWaitingThread(final TextView statusTv) {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    statusTv.setText("Thread started");  // too bad!
                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    while (true) {
                        if (!prefs.getBoolean("running", false)) {
                            break;
                        } else {
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {}
                        }
                    }
                    statusTv.setText("Thread stopped");  // too bad!

                } catch (Exception e) {
                    statusTv.setText("Thread error " + e.toString());  // too bad!
                }
            }
        };
        thread.start();


    }

}
