package com.example.datealarm;

public class myDB {
    private String name;
    private String date;
    private int color;

    public myDB() {
    }

    public myDB(String name, String date) {
        this.name = name;
        this.date = date;
        this.color = 0;
    }

    public myDB(String name, String date, int color) {
        this.name = name;
        this.date = date;
        this.color = color;
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
