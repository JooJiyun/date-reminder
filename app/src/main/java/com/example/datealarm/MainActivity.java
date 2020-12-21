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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        main_today = findViewById(R.id.main_today);
        main_add = findViewById(R.id.main_add);


        dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,3);

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

    private void update_list(){
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
                Toast.makeText(getApplicationContext(), getText(R.string.delete_date), Toast.LENGTH_SHORT).show();
                removeNotification(selected.get_id());
                dbHelper.delete(selected.getName());
                break;
            case R.id.list_alarm:
                //Toast.makeText(getApplicationContext(), "notification 추가",Toast.LENGTH_SHORT).show();
                String title = selected.getName();
                String date = selected.getDate();
                int color = selected.getColor();
                int _id = selected.get_id();

                createNotification(title, date, color, _id);

                break;
            case R.id.list_icon:
                //Toast.makeText(getApplicationContext(), "색상 변경",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PickColorActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.list_notifyRemove:
                //Toast.makeText(getApplicationContext(), "notification 제거",Toast.LENGTH_SHORT).show();
                removeNotification(selected.get_id());
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

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.drawable.ic_calendar_24);
        builder.setColor(color);
        builder.setContentTitle(title + " " + date);
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