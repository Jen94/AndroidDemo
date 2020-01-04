package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.my.demo.R;

public class HardTableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        Button b5 = findViewById(R.id.button5);
        b5.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        TableLayout ll = findViewById(R.id.tableLayout1);
        ll.removeAllViews();
        for (Integer i = 0; i < 5; i++) {

            LayoutInflater li = LayoutInflater.from(this);
            TableRow row = (TableRow) li.inflate(R.layout.partial_table_row, null);

            TextView qty = row.findViewById(R.id.textView102);
            qty.setText("Title " + i.toString());

            qty = row.findViewById(R.id.textView100);
            qty.setText("subtext " + i.toString());

            qty = row.findViewById(R.id.textView101);
            qty.setText("some more text " + i.toString());

            Button btn = row.findViewById(R.id.buttonHide);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "you touched the row", Toast.LENGTH_LONG).show();
                }
            });
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            ll.addView(row, i, params);
        }
    }


}
