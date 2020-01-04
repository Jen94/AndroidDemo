package com.my.demo.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.my.demo.R;

public class SimpleMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_menu_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_one:
                Toast.makeText(getApplicationContext(), "Обнаружен выбор пункта меню: 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_two:
                Toast.makeText(getApplicationContext(), "Обнаружен выбор пункта меню: 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_help:
                Toast.makeText(getApplicationContext(), "Обнаружен выбор пункта меню: 3", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
