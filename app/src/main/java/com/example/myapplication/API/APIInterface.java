package com.example.myapplication.API;

import com.example.myapplication.Model.Asset;
import com.example.myapplication.Model.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("api/master/asset/{assetID}")
    Call<Asset> getAsset(@Path("assetID") String assetID);
    @GET("api/master/asset/user/current")
    Call<ArrayList<JsonObject>> getCurrent();

    @GET("api/master/map/")
    Call<Map> getMap();//, @Header("Authorization") String auth);
}
