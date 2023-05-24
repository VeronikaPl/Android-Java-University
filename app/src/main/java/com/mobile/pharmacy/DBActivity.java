package com.mobile.pharmacy;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DBActivity extends Activity {
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh_screen();
    }

    private void refresh_screen() {
        new GetRowTask().execute((Object[]) null);
    }

    public void add_btn_clicked(View view) {
        TextView textView = findViewById(R.id.txtview);

        DatabaseConnector databaseConnector = new DatabaseConnector(DBActivity.this);
        databaseConnector.insertRow(textView.getText().toString());
        refresh_screen();

        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
    }

    final int MENU_CONTEXT_DELETE_ID = 123;
    final int MENU_CONTEXT_EDIT_ID = 124;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        if (view.getId() == R.id.listView) {
            menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, Menu.NONE, "Delete");
            menu.add(Menu.NONE, MENU_CONTEXT_EDIT_ID, Menu.NONE, "Edit");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String str = list.get(info.position);

        switch (item.getItemId()) {
            case MENU_CONTEXT_DELETE_ID: {
                Log.d(TAG, "removing item pos = " + info.position);

                long rid = Long.parseLong(str.split(",")[0].substring(3));
                DatabaseConnector databaseConnector = new DatabaseConnector(DBActivity.this);
                databaseConnector.deleteTableRow(rid);
                refresh_screen();
                return true;
            }
            case MENU_CONTEXT_EDIT_ID: {
                Log.d(TAG, "edit item pos = " + info.position);

                String txt = str.split(",")[1].substring(4);
                ((TextView) findViewById(R.id.txtview)).setText(txt);
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }

    private class GetRowTask extends AsyncTask<Object, Object, Cursor> {
        DatabaseConnector databaseConnector = new DatabaseConnector(DBActivity.this);


        @Override
        protected Cursor doInBackground(Object... objects) {
            databaseConnector.open();
            return databaseConnector.getTableAllRows();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            list = new ArrayList<>();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                list.add("id = " + cursor.getString(0) + ", txt = " + cursor.getString(1));
            }
            databaseConnector.close();

            ListAdapter listAdapter = new ArrayAdapter<>(DBActivity.this, android.R.layout.simple_list_item_1, list);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(listAdapter);
        }
    }
}
