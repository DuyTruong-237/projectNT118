package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Asset;
import com.example.myapplication.Model.Device_item;
import com.example.myapplication.Model.infoAsset;
import com.example.myapplication.adapter.deviceAdapter;
import com.example.myapplication.adapter.inforAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_list_Devices extends AppCompatActivity {

    ListView lvInfo;
    deviceAdapter dvadapter;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdevices);
        lvInfo=findViewById(R.id.lvDevices);
        dvadapter=new deviceAdapter(activity_list_Devices.this,R.layout.asset_info_item);
        lvInfo.setAdapter(dvadapter);
        fakeData();
        event();
    }

    private void event() {
        lvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //Object o = lvInfo.getItemAtPosition(position);
                Device_item a=(Device_item) lvInfo.getItemAtPosition(position);;

                Log.d("lvValue", a.getTitle());
                Intent i = new Intent(activity_list_Devices.this, activity_info_device.class);
                i.putExtra("idDevice",a.getInfo());
                startActivity(i);
            }
        });
    }

    private void fakeData() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<JsonObject>> call=apiInterface.getCurrent();
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                ArrayList<JsonObject> arr = response.body();

                // Log.d("API CALL", asset.type+"");
                String name,id;
                // double t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsFloat();
                //String id= arr.get(0).getAsJsonObject().get("id").getAsString();
                //String a=String.valueOf(arr.size());
                for(int i=0;i<arr.size();i++)
                {
                    name=arr.get(i).getAsJsonObject().get("name").getAsString();
                    id= arr.get(i).getAsJsonObject().get("id").getAsString();
                    dvadapter.add(new Device_item(name, id));
                }

                //txttype.setText(asset.type);
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });



        // ifadapter.add(new infoAsset("Hoa hồng Huy","18000"));
    }
}
