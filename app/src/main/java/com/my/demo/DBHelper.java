package com.my.demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, int version) {
        super(context, "caloriesCounter01.db", null, version); // myDB – имя базы данных
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Accounts (id integer primary key autoincrement, name text, gender integer, weight integer, growth integer, activity string, age integer, target string)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Account> accountQuery(SQLiteDatabase db, String query) {
        Cursor c; // Создаем курсор
        c = db.rawQuery(query, null); //Выполняем запрос из базы
        List<Account> result = new ArrayList<Account>();
        if (c.moveToFirst()) { //переходим на первый элемент если он есть
            do {
                int ColIndex = c.getColumnIndex("id");  // получаем индекс поля по названию
                int NameIndex = c.getColumnIndex("name");
                int GenderIndex = c.getColumnIndex("gender");
                int WeightIndex = c.getColumnIndex("weight");
                int GrowthIndex = c.getColumnIndex("growth");
                int ActivityIndex = c.getColumnIndex("activity");
                int AgeIndex = c.getColumnIndex("age");
                int TargetIndex = c.getColumnIndex("target");

                Account acc = new Account();
                acc._id = c.getInt(ColIndex);
                acc.name = c.getString(NameIndex);
                acc.gender = c.getString(GenderIndex);
                acc.weight = c.getInt(WeightIndex);
                acc.growth = c.getInt(GrowthIndex);
                acc.activity = c.getString(ActivityIndex);
                acc.age = c.getInt(AgeIndex);
                acc.target = c.getString(TargetIndex);

                result.add(acc);

            } while (c.moveToNext()); //переходим к следующему элементу
            c.close();
        }
        return result;
    }

    public void saveAccount(SQLiteDatabase db, Account acc) {
        List<Account> accs = accountQuery(db, ("SELECT * FROM Accounts WHERE id=" + acc._id));
        if (accs.size() != 0) {
            // acc._id = accs.get(0)._id; // in this case, only 1 acc will be stored
            db.execSQL(String.format("UPDATE Accounts SET name='%s', gender='%s', weight='%s', growth='%s', age='%s', target='%s', activity='%s' WHERE id='%s'", acc.name, acc.gender, acc.weight, acc.growth, acc.age, acc.target, acc.activity, acc._id));
        }
        else {
            db.execSQL(String.format("INSERT INTO Accounts ( 'name', 'gender', 'weight', 'growth', 'age', 'target', 'activity') VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", acc.name, acc.gender, acc.weight, acc.growth, acc.age, acc.target, acc.activity));
        }
    }

}


