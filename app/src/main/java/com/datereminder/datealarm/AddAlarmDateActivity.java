package com.datereminder.datealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAlarmDateActivity extends AppCompatActivity {

    private Button add_insert;
    private EditText add_year, add_month, add_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_date);


        add_year = findViewById(R.id.add_year);
        add_month = findViewById(R.id.add_month);
        add_day = findViewById(R.id.add_day);

        Date today = new Date(System.currentTimeMillis());
        add_year.setText(new SimpleDateFormat("yyyy").format(today));
        add_month.setText(new SimpleDateFormat("MM").format(today));
        add_day.setText(new SimpleDateFormat("dd").format(today));


        final mySQLiteOpenHelper dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,3);

        add_insert = findViewById(R.id.add_insert);
        add_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


                int this_year = Integer.parseInt(add_year.getText().toString());
                int this_month = Integer.parseInt(add_month.getText().toString());
                int this_day = Integer.parseInt(add_day.getText().toString());

                if((this_year<0)||(this_year>3000)){
                    Toast.makeText(getApplicationContext(), getText(R.string.check_year),Toast.LENGTH_SHORT).show();
                    return;
                }
                if((this_month<=0)||(this_month>12)){
                    Toast.makeText(getApplicationContext(), getText(R.string.check_month),Toast.LENGTH_SHORT).show();
                    return;
                }
                if((this_month==1)||(this_month==3)||(this_month==5)||(this_month==7)||(this_month==8)||(this_month==10)||(this_month==12)){
                    if((this_day<=0)||(this_day>30)){
                        Toast.makeText(getApplicationContext(), getText(R.string.check_day),Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else if((this_month==4)||(this_month==6)||(this_month==9)||(this_month==10)||(this_month==11)){
                    if((this_day<=0)||(this_day>30)){
                        Toast.makeText(getApplicationContext(), getText(R.string.check_day),Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else if(this_month==2){
                    int limit_day = 28;
                    if(this_year % 4 == 0 && this_year % 100 != 0 || this_year % 400 == 0){limit_day= 29;}
                    if((this_day<=0)||(this_day>limit_day)){
                        Toast.makeText(getApplicationContext(), getText(R.string.check_day),Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                String DDay = String.format("%4d.%2d.%2d", this_year,this_month,this_day);
                DDay = DDay.replace(' ','0');

                dbHelper.insert(name, DDay);
                finish();
            }
        });

    }
}