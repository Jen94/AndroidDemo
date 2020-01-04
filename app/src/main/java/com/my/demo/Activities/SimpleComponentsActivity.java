package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.my.demo.R;

public class SimpleComponentsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_components_activity);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarExample);
        seekBar.setMax(100);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Integer progress = ((SeekBar)v).getProgress();
                EditText et = findViewById(R.id.editTextExample);
                et.setText(progress.toString());
                return false;
            }
        });

        EditText et = findViewById(R.id.editTextExample);
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
                SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarExample);
                try {
                    int progress = Integer.valueOf(s.toString());
                    seekBar.setProgress(progress);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "error set progress: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Switch mySwitch = findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    ProgressBar pb = findViewById(R.id.progressBar);
                    pb.setVisibility(View.GONE);
                } else {
                    ProgressBar pb = findViewById(R.id.progressBar);
                    pb.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
