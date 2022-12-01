package com.example.myapplication.Model;

import java.io.Serializable;

public class infoAsset implements Serializable {


    private String title;
    private  String info;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public infoAsset(String title, String info) {
        this.title = title;
        this.info = info;
    }
}
