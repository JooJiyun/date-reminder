package com.datereminder.datealarm;

public class myDB {
    private  int _id;
    private String name;
    private String date;
    private int color;

    public myDB() {
    }

    public myDB(String name, String date) {
        this.name = name;
        this.date = date;
        this.color = -16777216;
    }

    public myDB(String name, String date, int color) {
        this.name = name;
        this.date = date;
        this.color = color;
    }

    public myDB(int _id, String name, String date, int color) {
        this._id = _id;
        this.name = name;
        this.date = date;
        this.color = color;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getColor() { return color; }

    public void setColor(int color) { this.color = color; }
}
