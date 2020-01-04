package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;

import com.my.demo.R;

public class SimpleFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragment_activity);
    }

}
