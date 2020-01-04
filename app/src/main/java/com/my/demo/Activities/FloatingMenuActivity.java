package com.my.demo.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.my.demo.R;

public class FloatingMenuActivity extends Activity {

    String MY_PREFS_NAME = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_menu_activity);

        Button btn = findViewById(R.id.buttonShowMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    private void showPopupMenu(View v) {
        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        final PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popup_menu);

        // первым делом читаем из shared_prefs, какие галки были выставлены при последнем открытии меню, и восстанавливаем настрйоки перед показом пользователю
        MenuItem mi = popupMenu.getMenu().findItem(R.id.menu4);
        boolean menu4Checked = prefs.getBoolean("menu4Checked", false);
        mi.setChecked(menu4Checked);
        mi = popupMenu.getMenu().findItem(R.id.menu5);
        boolean menu5Checked = prefs.getBoolean("menu5Checked", false);
        mi.setChecked(menu5Checked);
        mi = popupMenu.getMenu().findItem(R.id.menu6);
        boolean menu6Checked = prefs.getBoolean("menu6Checked", false);
        mi.setChecked(menu6Checked);

        if ((!menu4Checked)&&(!menu5Checked)&&(!menu6Checked)) {
            mi = popupMenu.getMenu().findItem(R.id.menu4);
            mi.setChecked(true);
        }

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // А здесь уже обрабатываем щелчки по пунктам меню
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали PopupMenu 1",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu2:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали PopupMenu 2",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu3:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали PopupMenu 3",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu4:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали PopupMenu 4 - 5 и 6 отключаются",
                                        Toast.LENGTH_SHORT).show();
                                editor.putBoolean("menu4Checked", true);
                                editor.putBoolean("menu5Checked", false);
                                editor.putBoolean("menu6Checked", false);
                                editor.apply();
                                return true;
                            case R.id.menu5:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали PopupMenu 5 - 4 и 6 отключаются",
                                        Toast.LENGTH_SHORT).show();
                                editor.putBoolean("menu4Checked", false);
                                editor.putBoolean("menu5Checked", true);
                                editor.putBoolean("menu6Checked", false);
                                editor.apply();
                                return true;
                            case R.id.menu6:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали PopupMenu 6 - 4 и 5 отключаются",
                                        Toast.LENGTH_SHORT).show();
                                editor.putBoolean("menu4Checked", false);
                                editor.putBoolean("menu5Checked", false);
                                editor.putBoolean("menu6Checked", true);
                                editor.apply();
                                return true;
                            case R.id.submenu:
                                Toast.makeText(getApplicationContext(),
                                        "Вы зашли в submenu",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "Меню закрыто - вызван onDismiss",
                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

}
