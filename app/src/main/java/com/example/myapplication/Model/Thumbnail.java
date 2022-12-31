package com.example.myapplication.Model;

import com.example.myapplication.R;

public enum Thumbnail {
    Thumbnail1("Temperature", R.drawable.ic_temp),
    Thumbnail2("Humidity", R.drawable.ic_humid),
    Thumbnail3("Wind Speed", R.drawable.ic_windspeed);
    /*Thumbnail4("Thumbnail 4", R.drawable.tokbokki);*/

    private String name;
    private int img;

    Thumbnail(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }
}
