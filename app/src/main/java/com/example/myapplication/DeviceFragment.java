package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Device_item;
import com.example.myapplication.adapter.deviceAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceFragment extends Fragment {
    ListView lvInfo;
    deviceAdapter dvadapter;
    APIInterface apiInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_devices,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //HttpRequestUtil.setOkHttpClient(APIClient.getUnsafeOkHttpClient());
        lvInfo=view.findViewById(R.id.lvDevices);
        dvadapter=new deviceAdapter(this.getActivity(),R.layout.device_item);
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
               // Activity a=this.get
                Intent i = new Intent(getActivity(), activity_info_device.class);
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
