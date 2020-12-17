package com.example.datealarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements myDBAdapter.ListBtnClickListener {

    TextView main_today;
    Button main_add;
    mySQLiteOpenHelper dbHelper;
    ArrayList dbList = null;
    String chg_target;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;


        main_today = findViewById(R.id.main_today);
        main_add = findViewById(R.id.main_add);


        dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,2);

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
                Toast.makeText(getApplicationContext(), "D-day 제거",Toast.LENGTH_SHORT).show();
                removeNotification(position);
                dbHelper.delete(selected.getName());
                break;
            case R.id.list_alarm:
                Toast.makeText(getApplicationContext(), "notification 추가",Toast.LENGTH_SHORT).show();

                String title = selected.getName();
                String date = selected.getDate();
                int color = selected.getColor();


                createNotification(title, date, color, position);

                break;
            case R.id.list_icon:
                Toast.makeText(getApplicationContext(), "색상 변경",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PickColorActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.list_notifyRemove:
                Toast.makeText(getApplicationContext(), "notification 제거",Toast.LENGTH_SHORT).show();
                removeNotification(position);
                break;
        }
        update_list();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int ret_color = data.getExtras().getInt("target_color",-16777216);
        if(ret_color!=-16777216){
            dbHelper.change_color(chg_target, ret_color);
        }

    }


    private void createNotification(String title, String date, int color, int _id) {

        String D_dayText = "";
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy.MM.dd");
        String today = dateFormat.format(now);
        try {
            Date firstDate = dateFormat.parse(today);
            Date secondDate = dateFormat.parse(date);

            long calDate = secondDate.getTime()-firstDate.getTime();
            long calDays = calDate/(24*60*60*1000);

            if(calDays<0){
                D_dayText = "D+"+Math.abs(calDays);
            }
            else{
                D_dayText = "D-"+calDays;
            }

        }catch(ParseException e){}


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.drawable.ic_calendar_24);
        builder.setColor(color);
        builder.setContentTitle(title + " " + D_dayText);
        builder.setAutoCancel(true);
        builder.setOngoing(true);


        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        notificationManager.notify(_id, builder.build());
    }

    private void removeNotification(int _id){
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        notificationManager.cancel(_id);
    }

}