package com.example.datealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddAlarmDateActivity extends AppCompatActivity {

    EditText add_name, add_year, add_month, add_day;
    Button add_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_date);


        add_insert = findViewById(R.id.add_insert);

        final mySQLiteOpenHelper dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,3);

        add_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_year = findViewById(R.id.add_year);
                add_month = findViewById(R.id.add_month);
                add_day = findViewById(R.id.add_day);
                add_name = findViewById(R.id.add_name);
                String name = add_name.getText().toString();

                if(name == null || name.trim().length() == 0){
                    Toast.makeText(getApplicationContext(), "이름이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                String DDay = String.format("%4d.%2d.%2d", Integer.parseInt(add_year.getText().toString()),Integer.parseInt(add_month.getText().toString()),Integer.parseInt(add_day.getText().toString()));

                dbHelper.insert(name, DDay);
                finish();
            }
        });

    }
}