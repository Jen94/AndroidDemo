package com.my.demo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.my.demo.R;

public class FirstInteractingActivity extends Activity {

    String MY_PREFS_NAME = "myprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_interacting_activity);

        Button btn = findViewById(R.id.buttonOpenSecondActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondInteractingActivity.class);
                startActivityForResult(intent, 1);  // requestCode > 0 !
            }
        });

        btn = findViewById(R.id.buttonUpdateFromShared);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String storedValue = prefs.getString("myValue", "NOT_FOUND");
                TextView tv = findViewById(R.id.textViewFromSharedPrefs);
                tv.setText(storedValue);
                Toast.makeText(getApplicationContext(), "Значение из shared_prefs прочитано", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (data != null) {
                String response = data.getStringExtra("response");
                TextView tv = findViewById(R.id.textViewFromSecondActivity);
                tv.setText(response);
            }
            Toast.makeText(getApplicationContext(), "onActivityResult called", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "onActivityResult error " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
