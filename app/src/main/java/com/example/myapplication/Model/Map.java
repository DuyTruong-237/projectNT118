package com.example.myapplication.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Map {

    @SerializedName("options")
    public JsonObject options;
    @SerializedName("version")
    public int version;
    @SerializedName("sources")
    public JsonObject sources;
    @SerializedName("sprite")
    public String sprite;
    @SerializedName("glyphs")
    public String glyphs;
    @SerializedName("layers")
    public ArrayList<JsonObject> layers;

}
