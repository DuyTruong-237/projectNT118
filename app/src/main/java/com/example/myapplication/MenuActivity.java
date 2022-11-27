package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Asset;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    APIInterface apiInterface;
    private  TextView txtto,txtHum,txtCL,txtWind;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txtto=findViewById(R.id.txtT);
        txtHum=findViewById(R.id.txtHum);
        txtCL=findViewById(R.id.txtClouds);
        txtWind=findViewById(R.id.txtWind);
        apiInterface = APIClient.getClient().create(APIInterface.class);

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
    }
}