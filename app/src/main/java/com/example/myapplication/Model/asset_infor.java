package com.example.myapplication.Model;

public class asset_infor {
    private int id;
    private String idasset;
    private String name;
    private String date;
    private float valueT;
    private float valueH;
    private float valueW;

    public asset_infor( String idasset, String name, String date, float valueT, float valueH, float valueW) {
        this.id = id;
        this.idasset = idasset;
        this.name = name;
        this.date = date;
        this.valueT = valueT;
        this.valueH = valueH;
        this.valueW = valueW;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdasset(String idasset) {
        this.idasset = idasset;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValueT(float valueT) {
        this.valueT = valueT;
    }

    public void setValueH(float valueH) {
        this.valueH = valueH;
    }

    public void setValueW(float valueW) {
        this.valueW = valueW;
    }

    public int getId() {
        return id;
    }

    public String getIdasset() {
        return idasset;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public float getValueT() {
        return valueT;
    }

    public float getValueH() {
        return valueH;
    }

    public float getValueW() {
        return valueW;
    }
}
