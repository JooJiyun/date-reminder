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

import java.util.ArrayList;

public class PickColorActivity extends AppCompatActivity {

    RadioGroup color_group;
    ArrayList<Integer> colors;
    int target_color;
    Button color_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color);


        color_insert = findViewById(R.id.color_insert);

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

        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i) {
                    case R.id.color_pick1:
                        target_color = colors.get(0);
                        break;
                    case R.id.color_pick2:
                        target_color = colors.get(1);
                        break;
                    case R.id.color_pick3:
                        target_color = colors.get(2);
                        break;
                    case R.id.color_pick4:
                        target_color = colors.get(3);
                        break;
                    case R.id.color_pick5:
                        target_color = colors.get(4);
                        break;
                    case R.id.color_pick6:
                        target_color = colors.get(5);
                        break;
                    case R.id.color_pick7:
                        target_color = colors.get(6);
                        break;
                    case R.id.color_pick8:
                        target_color = colors.get(7);
                        break;
                    case R.id.color_pick9:
                        target_color = colors.get(8);
                        break;
                    case R.id.color_pick10:
                        target_color = colors.get(9);
                        break;
                    case R.id.color_pick11:
                        target_color = colors.get(10);
                        break;
                    case R.id.color_pick12:
                        target_color = colors.get(11);
                        break;
                    case R.id.color_pick13:
                        target_color = colors.get(12);
                        break;
                    case R.id.color_pick14:
                        target_color = colors.get(13);
                        break;
                    case R.id.color_pick15:
                        target_color = colors.get(14);
                        break;

                }
            }
        };
        color_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("target_color", target_color);
                setResult(0, data);
                finish();
            }
        });


    }
}