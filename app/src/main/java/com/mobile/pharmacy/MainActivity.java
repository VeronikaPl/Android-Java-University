package com.mobile.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    Button applyButton, addNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(this);

        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        applyButton = findViewById(R.id.buttonApply);
        addNumberButton = findViewById(R.id.buttonAddNumber);

//        radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.radioButton:
//                        Toast.makeText(MainActivity.this, "radioButton 1", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.radioButton2:
//                        Toast.makeText(MainActivity.this, "radioButton 2", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });

        applyButton.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (radioButton1.isClickable()) {
            addNumberButton.setVisibility(View.VISIBLE);
        }
        if (radioButton2.isClickable()) {
            addNumberButton.setVisibility(View.INVISIBLE);
        }
    }
}