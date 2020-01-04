package com.my.demo.Activities;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.demo.Account;
import com.my.demo.DBHelper;
import com.my.demo.R;

import java.util.List;
import java.util.Random;

public class DatabaseActivity extends Activity {

    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);

        dbh = new DBHelper(getApplicationContext(), 1);

        Button btn = findViewById(R.id.buttonSaveToSql);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbh.getWritableDatabase(); // Открываем базу для записи
                Account acc = new Account();  // экземпляр класса Account
                Random r = new Random();
                int i1 = r.nextInt() % 10000;  // id - уникальный ключ, поэтому генерируем новый
                acc._id = i1;
                acc.name = "name";  // и заполняем остальные поля всякой ерундой
                acc.gender = "m";
                acc.weight = 10;
                acc.growth = 10;
                acc.activity = "act";
                acc.age = 14;
                acc.target = "target";

                dbh.saveAccount(db, acc);  // сохраняем аккаунт в БД
                Toast.makeText(getApplicationContext(), "Account successfully stored", Toast.LENGTH_SHORT).show();
            }
        });

        btn = findViewById(R.id.buttonReadFromSql);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbh.getWritableDatabase(); // Открываем базу для записи
                List<Account> accs = dbh.accountQuery(db, "SELECT * FROM Accounts");  // запрашиваем все аккаунты
                for (Account acc : accs) {  // и получаем их в виде готового списка классов. Таким образом, коннектор к БД реализован в одном месте - в DBHelper, а в остальных местах мы и не вспоминаем, как работать с БД, просто пользуемся готовыми методами
                    // acc is stored account
                }
                Toast.makeText(getApplicationContext(), "Accounts successfully read, total = " + accs.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
