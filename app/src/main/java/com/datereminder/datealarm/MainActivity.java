package com.datereminder.datealarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements myDBAdapter.ListBtnClickListener {

    private TextView main_today;
    private Button main_add;
    private mySQLiteOpenHelper dbHelper;
    private ArrayList dbList = null;
    private String chg_target;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //start ad

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        //MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //end ad



        main_today = findViewById(R.id.main_today); // today date in main activity
        main_add = findViewById(R.id.main_add); // D-day add button


        // print db of D-days
        dbHelper = new mySQLiteOpenHelper(getApplicationContext(), "ALARM.db",null,3);

        update_list();

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        main_today.setText(dateFormat.format(today));

        // D-day add button listener
        main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PickAlarmTypeActivity.class);
                startActivity(intent);
            }
        });

    }

    // update D-days in main activity layout with list view
    private void update_list(){
        dbList = dbHelper.getALLData();
        myDBAdapter adapter = new myDBAdapter(this, R.layout.list_item, dbList, this);
        ListView main_list = findViewById(R.id.main_list);
        main_list.setAdapter(adapter);
    }

    // for inserted new D-day, update D-days list in main activity layout
    @Override
    protected void onResume() {
        super.onResume();
        update_list();
    }

    // buttons in list view listeners
    @Override
    public void onListBtnClick(int position, int resourceid){
        myDB selected = (myDB)dbList.get(position);
        chg_target = selected.getName();

        switch (resourceid){
            case R.id.list_delete:
                // D-day remove button
                Toast.makeText(getApplicationContext(), getText(R.string.delete_date), Toast.LENGTH_SHORT).show();
                removeNotification(selected.get_id());
                dbHelper.delete(selected.get_id());
                break;
            case R.id.list_alarm:
                // D-day notification addition button
                //Toast.makeText(getApplicationContext(), "notification 추가",Toast.LENGTH_SHORT).show();
                String title = selected.getName();
                String date = selected.getDate();
                int color = selected.getColor();
                int _id = selected.get_id();

                createNotification(title, date, color, _id);

                break;
            case R.id.list_icon:
                // D-day icon color change button
                //Toast.makeText(getApplicationContext(), "색상 변경",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PickColorActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.list_notifyRemove:
                // D-day notification remove button
                //Toast.makeText(getApplicationContext(), "notification 제거",Toast.LENGTH_SHORT).show();
                removeNotification(selected.get_id());
                break;
        }
        update_list();
    }

    // D-day icon color setting by intent data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int ret_color = data.getExtras().getInt("target_color",-16777216);
        if(ret_color!=-16777216){
            dbHelper.change_color(chg_target, ret_color);
        }

    }


    // D-day notification set
    private void createNotification(String title, String date, int color, int _id) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.drawable.ic_event_date);
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

    // D-day notification remove
    private void removeNotification(int _id){
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        notificationManager.cancel(_id);
    }

}