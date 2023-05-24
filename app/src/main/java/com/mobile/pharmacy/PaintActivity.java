package com.mobile.pharmacy;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class PaintActivity extends AppCompatActivity {

    static final int MENU_COLOR_WHITE = Menu.FIRST + 1;
    static final int MENU_COLOR_BLUE = Menu.FIRST + 2;
    static final int MENU_NEW_IMAGE = Menu.FIRST + 3;
    static final int MENU_SAVE = Menu.FIRST + 4;

    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = findViewById(R.id.paint_view);
        paintView.init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_COLOR_WHITE, Menu.NONE, R.string.menuitem_erase);
        menu.add(Menu.NONE, MENU_COLOR_BLUE, Menu.NONE, "Синій колір");
        menu.add(Menu.NONE, MENU_NEW_IMAGE, Menu.NONE, "Очистити");
        menu.add(Menu.NONE, MENU_SAVE, Menu.NONE, "Зберегти рисунок");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COLOR_WHITE:
                paintView.set_line_color(Color.WHITE);
                return true;
            case MENU_COLOR_BLUE:
                paintView.set_line_color(Color.BLUE);
                return true;
            case MENU_NEW_IMAGE:
                paintView.clear();
                return true;
            case MENU_SAVE:
                paintView.save_image();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}