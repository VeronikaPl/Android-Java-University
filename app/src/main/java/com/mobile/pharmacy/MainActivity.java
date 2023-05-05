package com.mobile.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] strArray = new String[250];
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = "Number №" + (i + 1);
        }

        ListView listView = ((ListView) findViewById(R.id.listView));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((arg0, v, arg2, arg3) -> {
            String itemStr = adapter.getItem(arg2);
            Toast.makeText(getApplicationContext(), itemStr, Toast.LENGTH_SHORT).show();
        });
    }
}