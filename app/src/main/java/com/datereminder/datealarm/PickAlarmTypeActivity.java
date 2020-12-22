package com.datereminder.datealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PickAlarmTypeActivity extends AppCompatActivity {

    Button pick_date, pick_last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_alarm_type);


        pick_date = findViewById(R.id.pick_date);
        pick_last = findViewById(R.id.pick_last);

        pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAlarmDateActivity.class);
                startActivity(intent);
                finish();
            }
        });
        pick_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAlarmLastActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}