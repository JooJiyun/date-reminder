package com.example.datealarm;

public class myDB {
    private String name;
    private String date;
    private int icon_color;

    public myDB() {
    }

    public myDB(String name, String date) {
        this.name = name;
        this.date = date;
        this.icon_color = 0;
    }

    public myDB(String name, String date, int icon_color) {
        this.name = name;
        this.date = date;
        this.icon_color = icon_color;
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

    public int getIcon_color() { return icon_color; }

    public void setIcon_color(int icon_color) { this.icon_color = icon_color; }
}
