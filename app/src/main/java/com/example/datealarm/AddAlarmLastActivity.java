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

    Button add_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_last);

        add_insert = findViewById(R.id.add_insert);

        final mySQLiteOpenHelper dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,3);

        add_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText add_year = findViewById(R.id.add_year);
                EditText add_month = findViewById(R.id.add_month);
                EditText add_day = findViewById(R.id.add_day);
                EditText add_name = findViewById(R.id.add_name);
                String name = add_name.getText().toString();

                if(name == null || name.trim().length() == 0){
                    Toast.makeText(getApplicationContext(), getText(R.string.nameIsNull),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(name.length()>10) {
                    Toast.makeText(getApplicationContext(), getText(R.string.nameIsLong), Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean YearIsNumeric =  add_year.getText().toString().matches("[+-]?\\d*(\\.\\d+)?");
                boolean MonthIsNumeric =  add_month.getText().toString().matches("[+-]?\\d*(\\.\\d+)?");
                boolean DayIsNumeric =  add_day.getText().toString().matches("[+-]?\\d*(\\.\\d+)?");

                if(!YearIsNumeric||(add_year.getText().toString().trim().length()==0)){
                    Toast.makeText(getApplicationContext(), getText(R.string.check_year),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!MonthIsNumeric||(add_month.getText().toString().trim().length()==0)){
                    Toast.makeText(getApplicationContext(), getText(R.string.check_month),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!DayIsNumeric||(add_day.getText().toString().trim().length()==0)){
                    Toast.makeText(getApplicationContext(), getText(R.string.check_day),Toast.LENGTH_SHORT).show();
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