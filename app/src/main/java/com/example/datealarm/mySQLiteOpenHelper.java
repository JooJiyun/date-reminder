package com.example.datealarm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class mySQLiteOpenHelper extends SQLiteOpenHelper {
    public mySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ALARM (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT, color INT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ALARM");
        onCreate(db);
    }

    public void insert(String name, String date, int i_color){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO ALARM VALUES(null, '" + name + "','"+ date  + "'," + i_color + ");");
        db.close();
    }
    public void insert(String name, String date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO ALARM VALUES(null, '" + name + "','"+ date  + "', 0);");
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ALARM WHERE name='"+name+"';");
        db.close();
    }

    public ArrayList getALLData(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList result = new ArrayList<myDB>();
        Cursor cursor = db.rawQuery("SELECT * FROM ALARM",null);
        while(cursor.moveToNext()){
            result.add(new myDB(cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
        }
        return result;
    }

    public void change_color(String name, int i_color){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE ALARM SET color = " +i_color+ " WHERE name = '"+name+"'");
    }
}
