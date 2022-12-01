package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Asset;
import com.example.myapplication.Model.infoAsset;
import com.example.myapplication.adapter.inforAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_info_device extends AppCompatActivity {
    ListView lvInfo;
    inforAdapter ifadapter;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infodevice);
         lvInfo=findViewById(R.id.lvInfo);
        ifadapter=new inforAdapter(activity_info_device.this,R.layout.asset_info_item);
        lvInfo.setAdapter(ifadapter);
        ImageView iv_back = findViewById(R.id.iv_back);
        ImageView iv_setting = findViewById(R.id.iv_setting);
        TextView title = findViewById(R.id.tv_Info);
        fakeData();
        //tao event click for iv_back
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_info_device.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });

        //tao event click for iv_setting
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMenu(view);
            }
        });
    }

    private void fakeData() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Asset> call = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");//, "Bearer "+ token);
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                Asset asset = response.body();

                Log.d("API CALL", asset.type+"");



                JsonObject att=asset.attributes;


                Log.d("truong", String.valueOf(att.size()));
                att.keySet().forEach(keyStr ->
                {
                    JsonObject keyvalue = att.get(keyStr).getAsJsonObject();

                    Log.d("truong1", keyvalue.get("name").getAsString());
                    if(keyvalue.get("name").getAsString()!="Xom Pho") {
                        Double t;
                        String a,tt;
                           try {

                                t=keyvalue.get("value").getAsDouble();
                                a=String.valueOf(t);
                            }catch (Exception e)
                            {
                                Log.d("truongdeptrai", "onResponse: ");
                                a="null";
                            }
                            //Log.d("truong2",keyvalue.get("value").getAsString() );
                        if(a!="null")
                            ifadapter.add(new infoAsset(keyvalue.get("name").getAsString(), a));

                    }

                });
               // a=String.valueOf(key.size());
               //ifadapter.add(new infoAsset("123",a));

                /*JSONObject objects = new JSONObject ();
                JSONArray key = objects.names ();
                for (int i = 0; i < key.length (); ++i) {
                    String keys = key.getString (i);
                    String value = objects.getString (keys);
                }*/
                /*for (int i=0;i<att.size();i++) {
                    tt=att.get("name").getAsString();
                    if(tt=="weatherData") {
                        continue;
                    }


                  if(att.get("value")!=null)
                      t=att.get("value").getAsInt();
                  else {
                      t=-1;
                  }


                    if(t==-1)
                    {
                        a="null";
                    }
                    else {
                        a = String.valueOf(t);
                    }
                    ifadapter.add(new infoAsset(tt,a));

                }*/
               /* t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsInt();
                tt=asset.attributes.get("temperature").getAsJsonObject().get("name").getAsString();
                a=String.valueOf(t);
                ifadapter.add(new infoAsset(tt,a));
                t=asset.attributes.get("humidity").getAsJsonObject().get("value").getAsInt();
                tt=asset.attributes.get("humidity").getAsJsonObject().get("name").getAsString();
                a=String.valueOf(t);
                a=a.toUpperCase();
                tt=asset.attributes.getAsJsonObject().getName.getAsString();
                ifadapter.add(new infoAsset(tt,a));*/

                /*txtto.setText(a);
                int h=asset.attributes.get("humidity").getAsJsonObject().get("value").getAsInt();
                a=String.valueOf(h);
                txtHum.setText(a);
                a=asset.attributes.get(weatherData").getAsJsonObject().get("value").getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                txtCL.setText(a);
                float w=asset.attributes.get("windSpeed").getAsJsonObject().get("value").getAsFloat();
                a=String.valueOf(w);
                txtWind.setText(a);*/
                //txttype.setText(asset.type);
            }


            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });

       // ifadapter.add(new infoAsset("Hoa hồng Huy","18000"));
    }

    private  void showMenu(View v)
    {
        PopupMenu popupMenu = new PopupMenu(this,v );
        popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.item_home)
                    Toast.makeText(activity_info_device.this, "Trang Chủ", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.item_profile)
                    Toast.makeText(activity_info_device.this, "Trang Cá Nhân", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.item_map)
                    Toast.makeText(activity_info_device.this, "Bản Đồ", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.item_about)
                    Toast.makeText(activity_info_device.this, "Thông Tin Thêm", Toast.LENGTH_SHORT).show();
                if(menuItem.getItemId() == R.id.item_exit)
                    Toast.makeText(activity_info_device.this, "Thoát", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popupMenu.show();
    }





}
