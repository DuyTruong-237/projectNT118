package com.example.myapplication;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.job.JobScheduler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Asset;
import com.mapbox.mapboxsdk.module.http.HttpRequestUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    APIInterface apiInterface;
    ImageView btnsetting;
    private TextView txtto,txtHum,txtCL,txtWind, txtUV;
    private ImageView btnMap;
    private static final int JOB_ID =123 ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //HttpRequestUtil.setOkHttpClient(APIClient.getUnsafeOkHttpClient());
        txtto=view.findViewById(R.id.txtT);
        txtHum=view.findViewById(R.id.txtHum);
        txtCL=view.findViewById(R.id.txtClouds);
        txtWind=view.findViewById(R.id.txtWind);
        btnsetting=view.findViewById(R.id.setting);
        txtUV=view.findViewById(R.id.txtUV);
        btnMap=view.findViewById(R.id.mapButton);
        getData();
        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),activityAddItem.class);
                startActivity(i);
            }
        });
    }

    private void getData() {


        apiInterface = APIClient.getClient().create(APIInterface.class);
       /* Call<JsonArray> callcurrent=apiInterface.getCurrent();
        callcurrent.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> callc, Response<JsonArray> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
               JsonArray arr = response.body();

               // Log.d("API CALL", asset.type+"");
               // double t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsFloat();
                String id=arr.get(0).getAsJsonObject().get("id").getAsString();
                txtUV.setText(id);

                //txttype.setText(asset.type);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });*/
        Call<Asset> call = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");//, "Bearer "+ token);
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                Asset asset = response.body();

                Log.d("API CALL", asset.type+"");
                int t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsInt();
                String a=String.valueOf(t);
                txtto.setText(a);
                int h=asset.attributes.get("humidity").getAsJsonObject().get("value").getAsInt();
                a=String.valueOf(h);
                txtHum.setText(a);
                a=asset.attributes.get("weatherData").getAsJsonObject().get("value").getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                txtCL.setText(a);
                float w=asset.attributes.get("windSpeed").getAsJsonObject().get("value").getAsFloat();
                a=String.valueOf(w);
                txtWind.setText(a);
                //txttype.setText(asset.type);
            }


            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });
        event();
    }


    private void event() {
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveActyvity();
            }
        });
    }

    private void moveActyvity(){
      /*  ArrayList<Integer> a;
        Intent intent = new Intent(HomeFragment.this,MapActivity.class );
        //intent.putExtra("key",a);
        startActivity(intent);*/
    }

}
