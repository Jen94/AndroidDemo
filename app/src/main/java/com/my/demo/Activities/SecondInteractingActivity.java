package com.my.demo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.demo.R;

public class SecondInteractingActivity extends Activity {

    String MY_PREFS_NAME = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_interacting_activity);

        Button btn = findViewById(R.id.buttonSaveToPrefsAndClose);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.editTextTextToSave);
                String valueToSave = et.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("myValue", valueToSave);
                editor.apply();
                finish();
            }
        });

        btn = findViewById(R.id.buttonSendToFirstAndClose);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.editTextTextToSave);
                String valueToSave = et.getText().toString();
                Intent currentIntent = getIntent();
                currentIntent.putExtra("response", valueToSave);
                setResult(RESULT_OK, currentIntent);  // обязательно в таком формате
                finish();
            }
        });
    }

}
