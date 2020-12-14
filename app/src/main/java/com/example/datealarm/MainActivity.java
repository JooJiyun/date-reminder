package com.example.datealarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements myDBAdapter.ListBtnClickListener {

    TextView main_today;
    Button main_add;
    mySQLiteOpenHelper dbHelper;
    ArrayList dbList = null;
    String chg_target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        main_today = findViewById(R.id.main_today);
        main_add = findViewById(R.id.main_add);


        dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,1);

        update_list();

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        main_today.setText(dateFormat.format(today));

        main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PickAlarmTypeActivity.class);
                startActivity(intent);
            }
        });



    }

    public void update_list(){
        dbList = dbHelper.getALLData();
        myDBAdapter adapter = new myDBAdapter(this, R.layout.list_item, dbList, this);
        ListView main_list = findViewById(R.id.main_list);
        main_list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        update_list();
    }

    @Override
    public void onListBtnClick(int position, int resourceid){
        myDB selected = (myDB)dbList.get(position);
        chg_target = selected.getName();

        switch (resourceid){
            case R.id.list_delete:
                dbHelper.delete(selected.getName());
                break;
            case R.id.list_alarm:
                //dbHelper.change_color(selected.getName(), );
                break;
            case R.id.list_icon:
                Intent intent = new Intent(getApplicationContext(), PickColorActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
        update_list();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int ret_color = data.getExtras().getInt("target_color",0);
        dbHelper.change_color(chg_target, ret_color);
    }
}