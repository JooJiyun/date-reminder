package com.example.datealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmOptionActivity extends AppCompatActivity {

    Button push_pick, push_always, push_none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_option);


        push_pick = findViewById(R.id.push_pick);
        push_always = findViewById(R.id.push_always);
        push_none = findViewById(R.id.push_none);

        push_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        push_always.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        push_none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}