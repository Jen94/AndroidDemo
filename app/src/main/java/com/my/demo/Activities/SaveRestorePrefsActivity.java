package com.my.demo.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.demo.R;

public class SaveRestorePrefsActivity extends Activity {

    String MY_PREFS_NAME = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_restore_prefs_activity);

        // Здесь восстановление настроек делается по кнопке - но только для понимания
        // Обычно кнопок никто не делает и просто загружает имеющиеся данные в onCreate - прямо при старте Активити.
        // Пользователь открыл окно, а там уже все заполнено - вполне ожидаемое поведение. Для этого нужно вызывать restoreSettings не из обработчикаа кнопки, а прямо основном теле onCreate

        // restoreSettings(); // вот таким образом

        Button btn = findViewById(R.id.buttonSavePrefs);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.editTextName);
                String name = et.getText().toString();
                et = findViewById(R.id.editTextAge);
                String strAge = et.getText().toString();
                Integer age = -1;
                try {
                    age = Integer.valueOf(strAge);
                } catch (Exception e) { }
                et = findViewById(R.id.editTextPassword);
                String password = et.getText().toString();
                et = findViewById(R.id.editTextCVC);
                String cvc = et.getText().toString();
                et = findViewById(R.id.editTextEmail);
                String email = et.getText().toString();
                et = findViewById(R.id.editTextPhone);
                String phone = et.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putInt("age", age);
                editor.putString("password", password);
                editor.putString("cvc", cvc);
                editor.putString("email", email);
                editor.putString("phone", phone);
                editor.apply();
            }
        });

        btn = findViewById(R.id.buttonRestorePrefs);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreSettings();
            }
        });
    }

    public void restoreSettings() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        EditText et = findViewById(R.id.editTextName);
        et.setText(prefs.getString("name", ""));
        et = findViewById(R.id.editTextAge);
        Integer intAge = prefs.getInt("age", -1);
        et.setText(intAge.toString());
        et = findViewById(R.id.editTextPassword);
        et.setText(prefs.getString("password", ""));
        et = findViewById(R.id.editTextCVC);
        et.setText(prefs.getString("cvc", ""));
        et = findViewById(R.id.editTextEmail);
        et.setText(prefs.getString("email", ""));
        et = findViewById(R.id.editTextPhone);
        et.setText(prefs.getString("phone", ""));
    }

}
