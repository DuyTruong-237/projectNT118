package com.example.myapplication;




import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.module.http.HttpRequestUtil;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment {
    private APIInterface apiInterface;
    private MapView mapView;

    public MapFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), getString(R.string.token));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HttpRequestUtil.setOkHttpClient(APIClient.getUnsafeOkHttpClient());
        mapView = view.findViewById(R.id.mapView);
        getMap();
    }

    private void getMap() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        apiInterface.getMap().enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                int statusCode = response.code();
                Log.e("Code: ", String.valueOf(statusCode));
                if (statusCode == 200) {
                    Map myMap = response.body();
                    setMapView(myMap);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.e("API LOG ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    private void setMapView(Map myMap) {
        Gson gson = new Gson();
        mapView.getMapAsync(mapboxMap -> {
            mapboxMap.setStyle(new Style.Builder().fromUri("https://103.126.161.199/api/master/map"));

            double lat = 10.8696993;
            //myMap.options.get("default").getAsJsonObject().get("center").getAsJsonArray().get(1).getAsFloat();
            double lng = 106.8031529;
            //myMap.options.get("default").getAsJsonObject().get("center").getAsJsonArray().get(0).getAsFloat();
            mapboxMap.setCameraPosition(new CameraPosition.Builder()
                    .target(new LatLng(lat, lng))
                    //.zoom(myMap.options.get("default").getAsJsonObject().get("zoom").getAsInt())
                    .zoom(16.8)
                    .build());
        });
    }
}