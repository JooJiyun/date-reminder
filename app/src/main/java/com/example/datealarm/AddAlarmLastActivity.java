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

public class AddAlarmLastActivity extends AppCompatActivity {

    EditText add_name, add_year, add_month, add_day;
    Button add_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_last);


        add_name = findViewById(R.id.add_name);
        add_insert = findViewById(R.id.add_insert);

        add_year = findViewById(R.id.add_year);
        add_month = findViewById(R.id.add_month);
        add_day = findViewById(R.id.add_day);


        final mySQLiteOpenHelper dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,2);

        add_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_year = findViewById(R.id.add_year);
                add_month = findViewById(R.id.add_month);
                add_day = findViewById(R.id.add_day);
                String name = add_name.getText().toString();

                if(name == null || name.trim().length() == 0){
                    Toast.makeText(getApplicationContext(), "이름이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }


                Calendar cal = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                cal.setTime(new Date());
                cal.add(Calendar.YEAR, Integer.parseInt(add_year.getText().toString()));
                cal.add(Calendar.MONDAY, Integer.parseInt(add_month.getText().toString()));
                cal.add(Calendar.DATE, Integer.parseInt(add_day.getText().toString()));


                dbHelper.insert(name, dateFormat.format(cal.getTime()).toString());
                finish();
            }
        });
    }
}