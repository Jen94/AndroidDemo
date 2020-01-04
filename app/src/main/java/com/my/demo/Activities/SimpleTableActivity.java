package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.my.demo.R;

public class SimpleTableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_table_activity);

        fillTable();
    }

    public void fillTable() {
        try {
            // шапка таблицы
            TableLayout table = findViewById(R.id.tableLayoutExample);
            table.removeAllViews();
            TableRow _row=new TableRow(getApplicationContext());  // создаем экземпляр строки таблицы интерфейса
            TextView _tv1=new TextView(getApplicationContext());
            _tv1.setText("Название");
            TextView _tv2=new TextView(getApplicationContext());
            _tv2.setText("   Обозначение");
            TextView _tv3=new TextView(getApplicationContext());
            _tv3.setText("   Указание");
            TextView _tv4 =new TextView(getApplicationContext());
            _tv4.setText("   Показание");
            _row.addView(_tv1);  // заполняем строку таблицы интерфейса
            _row.addView(_tv2);
            _row.addView(_tv3);
            _row.addView(_tv4);
            table.addView(_row);  // добавляем строку в таблицу
            for (Integer i=0; i<100; i++) {
                // тело таблицы
                TableRow row = new TableRow(getApplicationContext());  // создаем экземпляр строки таблицы интерфейса
                TextView tv1 = new TextView(getApplicationContext());
                tv1.setText("a"+i.toString());
                TextView tv2 = new TextView(getApplicationContext());
                tv2.setText("b"+i.toString());
                TextView tv3 = new TextView(getApplicationContext());
                tv3.setText("c"+i.toString());
                TextView tv4 = new TextView(getApplicationContext());
                tv4.setText("d"+i.toString());
                row.addView(tv1);  // заполняем строку таблицы интерфейса
                row.addView(tv2);
                row.addView(tv3);
                row.addView(tv4);
                table.addView(row);  // добавляем строку в таблицу
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Ошибка: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
