package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.my.demo.R;

public class AdditionalListenersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additional_listeners_activity);
        Toast.makeText(getApplicationContext(), "onCreate - создание активити", Toast.LENGTH_SHORT).show();

        EditText et = findViewById(R.id.editTextChangeExample);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                printMsg("editText - onTextChanged: new value is " + s.toString());
            }
        });

        CheckBox cb = findViewById(R.id.checkBoxChangeExample);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                printMsg("CheckBox - OnCheckedChangeListener: new value is checked = " + isChecked);
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarChangeExample);
        seekBar.setMax(100);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Integer progress = ((SeekBar)v).getProgress();
               printMsg("SeekBar - OnTouchListener: new value is = " + progress.toString());
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume - активити на переднем плане", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy - уничтожение активити", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause - активити больше не на переднем плане", Toast.LENGTH_SHORT).show();
    }

    public void printMsg(String msg) {
        TextView tv = findViewById(R.id.textViewAdditionalListenersStatus);
        tv.setText(msg);
    }
}
