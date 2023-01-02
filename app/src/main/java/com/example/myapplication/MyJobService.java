package com.example.myapplication;



import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;
import android.app.Notification;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.DB.DatabaseHelper;
import com.example.myapplication.Model.Asset;
import com.example.myapplication.Model.Device_item;
import com.example.myapplication.Model.asset_infor;
import com.example.myapplication.Model.infoAsset;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyJobService extends JobService {
    public static final String TAG = MyJobService.class.getName();
    private boolean jobCancelled;
    public static DatabaseHelper db;
    int j;
    private NotificationManagerCompat notificationManagerCompat;
    APIInterface apiInterface;
    List<asset_infor> infors;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG,"JOB STARTED");
        db = new DatabaseHelper(this);
        this.notificationManagerCompat = NotificationManagerCompat.from(this);

        Log.d(TAG,"JOB STARTED1");
        doBackgroundWork(jobParameters);
        return true;
    }
    private void doBackgroundWork(final JobParameters jobParameters)
    {
        new Thread((new Runnable() {
            @Override
            public void run() {

                    if (jobCancelled){
                        return;
                    }
                    //callApiAndSave();
                    fakeData();
                Log.d(TAG,"JOB finished");
                jobFinished(jobParameters,false);
            }
        })).start();
    }

    private void callApiAndSave() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Asset> call = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");//, "Bearer "+ token);
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                Asset asset = response.body();

                Log.d("API CALL", asset.type+"");
                float t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsInt();
                String a=String.valueOf(t);
                Date date=new Date();
                String day=date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
              // db.addInfor(new asset_infor("temperature","t",day,t));
               infors = db.getAllContacts();
                //Log.d(TAG,"truong"+infors.get(0).getId()+infors.get(0).getIdasset()+"-"+infors.get(0).getDate1()+"-"+infors.get(0).getValue() );
                /*int h=asset.attributes.get("humidity").getAsJsonObject().get("value").getAsInt();
                a=String.valueOf(h);
                Log.d(TAG, a);
                a=asset.attributes.get("weatherData").getAsJsonObject().get("value").getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                Log.d(TAG, a);
                float w=asset.attributes.get("windSpeed").getAsJsonObject().get("value").getAsFloat();
                a=String.valueOf(w);
                Log.d(TAG, a);*/
                //txttype.setText(asset.type);
            }


            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });
    }
    private void sendOnChannel1( float t)  {
        String title = "T";
        String message = String.valueOf(t);

        Notification notification = new NotificationCompat.Builder(this, NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_temp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG,"JOB STOPPED");

        jobCancelled=true;
        return true;
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
                 j=0;
                for(int i=0;i<arr.size();i++)
                {
                    String nameasset=arr.get(i).getAsJsonObject().get("name").getAsString();
                    id= arr.get(i).getAsJsonObject().get("id").getAsString();
                    JsonObject att=arr.get(i).get("attributes").getAsJsonObject();


                    Log.d("truong", String.valueOf(att.size()));
                     List<Float> arrValue=new ArrayList<Float>();
                    att.keySet().forEach(keyStr ->
                    {
                        Float t,h,w;
                        String namevalue="",tt;
                        JsonObject keyvalue = att.get(keyStr).getAsJsonObject();

                        Log.d("truong1", keyvalue.get("name").getAsString());
                        if(keyvalue.get("name").getAsString()!="Xom Pho") {

                            try {

                               /* t=keyvalue.get("value").getAsDouble();
                                a=String.valueOf(t);*/

                                namevalue=keyvalue.get("name").getAsString();
                                Log.d("logvalue",  namevalue+"");
                                if(namevalue.equals("temperature")||namevalue.equals("humidity")||namevalue.equals("windSpeed"))
                                {
                                    Log.d("logvalue1",  "abc");
                                    t=keyvalue.get("value").getAsFloat();
                                    Log.d("logvalue1",  t+"");
                                    if (j==0)
                                    {
                                        sendOnChannel1(t);
                                        j++;
                                        Log.d("logvalue3",  t+"");
                                    }
                                    arrValue.add(t);
                                }
                            }catch (Exception e)
                            {

                                Log.d("message","errr");
                            }
                            //Log.d("truong2",keyvalue.get("value").getAsString() );
                            //if(a!="null")


                        }

                    });
                    try {
                        Date date=new Date();
                        String day=date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
                        Log.d("truong2", id+"-"+"-"+day+"-"+arrValue.get(0)+"-"+arrValue.get(1)+"-"+arrValue.get(2));
                        db.addInfor(new asset_infor(id,nameasset,day,arrValue.get(0),arrValue.get(1),arrValue.get(2)));




                    }catch (Exception e)
                    {

                        Log.d("message","errr"+ i);
                    }

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

