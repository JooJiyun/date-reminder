package com.example.datealarm;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class PickColorActivity extends AppCompatActivity {

    ArrayList<Integer> colors;
    Button color_pick1, color_pick2, color_pick3, color_pick4, color_pick5;
    Button color_pick6, color_pick7, color_pick8, color_pick9, color_pick10;
    Button color_pick11, color_pick12, color_pick13, color_pick14, color_pick15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color);


        color_pick1 = findViewById(R.id.color_pick1);
        color_pick2 = findViewById(R.id.color_pick2);
        color_pick3 = findViewById(R.id.color_pick3);
        color_pick4 = findViewById(R.id.color_pick4);
        color_pick5= findViewById(R.id.color_pick5);
        color_pick6 = findViewById(R.id.color_pick6);
        color_pick7 = findViewById(R.id.color_pick7);
        color_pick8 = findViewById(R.id.color_pick8);
        color_pick9 = findViewById(R.id.color_pick9);
        color_pick10 = findViewById(R.id.color_pick10);
        color_pick11 = findViewById(R.id.color_pick11);
        color_pick12 = findViewById(R.id.color_pick12);
        color_pick13 = findViewById(R.id.color_pick13);
        color_pick14 = findViewById(R.id.color_pick14);
        color_pick15 = findViewById(R.id.color_pick15);

        colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this, R.color.colorPick1));
        colors.add(ContextCompat.getColor(this, R.color.colorPick2));
        colors.add(ContextCompat.getColor(this, R.color.colorPick3));
        colors.add(ContextCompat.getColor(this, R.color.colorPick4));
        colors.add(ContextCompat.getColor(this, R.color.colorPick5));
        colors.add(ContextCompat.getColor(this, R.color.colorPick6));
        colors.add(ContextCompat.getColor(this, R.color.colorPick7));
        colors.add(ContextCompat.getColor(this, R.color.colorPick8));
        colors.add(ContextCompat.getColor(this, R.color.colorPick9));
        colors.add(ContextCompat.getColor(this, R.color.colorPick10));
        colors.add(ContextCompat.getColor(this, R.color.colorPick11));
        colors.add(ContextCompat.getColor(this, R.color.colorPick12));
        colors.add(ContextCompat.getColor(this, R.color.colorPick13));
        colors.add(ContextCompat.getColor(this, R.color.colorPick14));
        colors.add(ContextCompat.getColor(this, R.color.colorPick15));



        color_pick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(0); }
        });
        color_pick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(1); }
        });
        color_pick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(2); }
        });
        color_pick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(3); }
        });
        color_pick5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(4); }
        });
        color_pick6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(5); }
        });
        color_pick7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(6); }
        });
        color_pick8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(7); }
        });
        color_pick9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(8); }
        });
        color_pick10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(9); }
        });
        color_pick11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(10); }
        });
        color_pick12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(11); }
        });
        color_pick13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(12); }
        });
        color_pick14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(13); }
        });
        color_pick15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { return_color(14); }
        });


        Intent data = new Intent();
        data.putExtra("target_color", -16777216);
        setResult(0, data);
    }

    void return_color(int idx) {
        Intent data = new Intent();
        data.putExtra("target_color",colors.get(idx));
        setResult(0, data);
        finish();;
    }
}