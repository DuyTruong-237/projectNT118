package com.example.myapplication;




import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;




import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Map;

import com.example.myapplication.Model.Device_item;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.module.http.HttpRequestUtil;


import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Map;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment {
    private APIInterface apiInterface;
    private MapView mapView;
    private GeoJsonSource geoJsonSource;
    private Marker destinationMarker;
    private Point destinationPosition;
    private Point orPosition;
    private Location orlocation;
    private  ArrayList<JsonObject> arr;

    private LatLng point =new LatLng(10.869792504441904,106.80308672274282);
    private ArrayList<LatLng> arrLLasset=new ArrayList<LatLng>();
    private ArrayList<Double> arrlat=new ArrayList<Double>();
    private ArrayList<Double> arrlng=new ArrayList<Double>();
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
        callAssetLocation();
        //geoJsonSource.setGeoJson(Point.fromLngLat(106.8031529,10.8696993));


      /*  mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                MarkerViewManager markerViewManager = new MarkerViewManager(mapView, mapboxMap);
                MarkerView markerView = new MarkerView(new LatLng(21.0294498,105.8544441), mapView);

                markerViewManager.addMarker(markerView);
            }
        });*/



    }

    /*private void addDroneMarker(double latitude, double longitude){
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.aircraft_icon)).getBitmap();

        AnnotationPlugin annotationAPI = AnnotationPluginImplKt.getAnnotations(mapView);
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationAPI, new AnnotationConfig());

        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(bitmap);
        dronePoint = pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void updateDroneMarker(double latitude, double longitude){
        dronePoint.setPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.update(dronePoint);
    }

    private void initFlightController(){
        BaseProduct product = FPVApplication.getProductInstance();
        if (product != null && product.isConnected()) {
            if (product instanceof Aircraft) {
                mFlightController = ((Aircraft) product).getFlightController();
            }
        }

        if (mFlightController != null) {
            mFlightController.setStateCallback(new FlightControllerState.Callback() {
                @Override
                public void onUpdate(FlightControllerState djiFlightControllerCurrentState) {
                    droneLocationLat = djiFlightControllerCurrentState.getAircraftLocation().getLatitude();
                    droneLocationLng = djiFlightControllerCurrentState.getAircraftLocation().getLongitude();

                    updateDroneMarker(droneLocationLat, droneLocationLng);

                }
            });
        }
    }
   /* private void addmaker()
            
    {
        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);

// Set non-data-driven properties.
        symbolManager.setIconAllowOverlap(true);
        symbolManager.setTextAllowOverlap(true);

// Create a symbol at the specified location.
        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(new LatLng(6.687337, 0.381457))
                .withIconImage(ID_ICON_AIRPORT)
                .withIconSize(1.3f)

// Use the manager to draw the symbol.
        symbol = symbolManager.create(symbolOptions);
    }*/
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
    private void createMarker(){

    }


    private void setMapView(Map myMap) {

        Log.d("sizeARRL",String.valueOf(arrLLasset.size()));

        Gson gson = new Gson();
        mapView.getMapAsync(mapboxMap -> {
            mapboxMap.setStyle(new Style.Builder().fromUri("https://103.126.161.199/api/master/map"));

            // Add some markers to the map, and add a data object to each marker.



            double lat = 10.8696993;
            //myMap.options.get("default").getAsJsonObject().get("center").getAsJsonArray().get(1).getAsFloat();
            double lng = 106.8031529;
            //myMap.options.get("default").getAsJsonObject().get("center").getAsJsonArray().get(0).getAsFloat();
            mapboxMap.setCameraPosition(new CameraPosition.Builder()
                    .target(new LatLng(lat, lng))
                    //.zoom(myMap.options.get("default").getAsJsonObject().get("zoom").getAsInt())
                    .zoom(16.8)
                    .build());
           // Log.d("sizeARRLkkkk", String.valueOf(String.valueOf(arrLLasset.get(0).getLatitude())));
            //Log.d("sizeARRLkkkk", String.valueOf(String.valueOf(arrLLasset.get(1).getLatitude())));
          //  Log.d("sizeARRLkkkk", String.valueOf(String.valueOf(arrLLasset.get(2).getLatitude())));

            Log.d("sizeARRLkkkk", String.valueOf(arrLLasset.size()));
            LatLng item;
               for (int j=0;j<arrLLasset.size();j++) {
                   item=new LatLng(arrlat.get(j),arrlng.get(j));
                   Log.d("sizeARRLkkkk"+j, String.valueOf(String.valueOf(arrLLasset.get(j).getLatitude())));
                  destinationMarker = mapboxMap.addMarker( new MarkerOptions().position(item));

               }
            destinationPosition=Point.fromLngLat(point.getLongitude(), point.getLatitude());


           //orPosition=Point.fromLngLat(orlocation.getLongitude(),orlocation.getLatitude());
        });
    }

    private void callAssetLocation() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<JsonObject>> call=apiInterface.getCurrent();
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                arr = response.body();

                // Log.d("API CALL", asset.type+"");
                String name,id;
                // double t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsFloat();
                //String id= arr.get(0).getAsJsonObject().get("id").getAsString();
                //String a=String.valueOf(arr.size());
                boolean status;
                LatLng locationasset=new LatLng();

                Double lat,lng;
                for(int i=0;i<arr.size();i++)
                {
                     status=true;
                    try {
                        lng=arr.get(i).getAsJsonObject().get("attributes").getAsJsonObject().get("location").getAsJsonObject().get("value").getAsJsonObject().get("coordinates").getAsJsonArray().get(0).getAsDouble();
                        lat=arr.get(i).getAsJsonObject().get("attributes").getAsJsonObject().get("location").getAsJsonObject().get("value").getAsJsonObject().get("coordinates").getAsJsonArray().get(1).getAsDouble();
                        locationasset.setLatitude(lat);
                        locationasset.setLongitude(lng);
                        arrlat.add(locationasset.getLatitude());
                        arrlng.add(locationasset.getLongitude());
                        Log.d("Lat"+i, String.valueOf(locationasset.getLatitude()));
                        Log.d("Lat"+i, String.valueOf(locationasset.getLongitude()));
                        Log.d("Lat"+i, String.valueOf(arrLLasset.size()));
                        Log.d("Lat"+i, "thanh cong");

                       // Log.d("Lat"+i, "thanh cong");


                    }catch (Exception e)
                    {
                        Log.d("sai",String.valueOf(i));
                        status=false;

                    }
                    if(status==true)
                    {
                        arrLLasset.add(locationasset);
                    }


                }

                getMap();
                //txttype.setText(asset.type);
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });


    }


}