package com.example.myapplication;

/*import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {
    APIInterface apiInterface;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txt=findViewById(R.id.textView);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<JsonArray> callcurrent=apiInterface.getCurrent();
        callcurrent.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> callc, Response<JsonArray> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                JsonArray arr = response.body();

                // Log.d("API CALL", asset.type+"");
                // double t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsFloat();
                String id=arr.get(0).getAsJsonObject().get("id").getAsString();
                txt.setText(id);

                //txttype.setText(asset.type);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });
    }
}*/
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.Model.Asset;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Test extends AppCompatActivity {

    APIInterface apiInterface;
    //String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NjUyMDc4MzMsImlhdCI6MTY2NTEyMTQ0OCwiYXV0aF90aW1lIjoxNjY1MTIxNDMzLCJqdGkiOiIxZjc3OTFjMy00OGJmLTQ4NGUtODI3MS1kOGY0NWNiNTcyMzkiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI5MjYyNGM1Yi1iNjM3LTRjNDItOWVhYS02MzhkNmRkZDJiNjQiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiOTI2MjRjNWItYjYzNy00YzQyLTllYWEtNjM4ZDZkZGQyYjY0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyMSJ9.U0pMuxOkHW8pZKBVmTlQeIzGq-WtQoHzxCIgf2-gmB8vsAFCZlajaBvO8jJpupmS7NzegIFI6TcfAXkXFZXxPIJAmPb0a4RtFr-pthoOtERpct5TjxZvrEfJjNcC1J_EM1aumUJvOUZE7goVl4qG4UCw08Su0wIcmztWthLQu3CTgNuO5XwBqORJwGqIZAdSLyRSjhx4970nU4C_z-DRxVMtv1jlDfUyHeIHUA6c9gEiEY5PpbR6bnHDslb9Ta3MUOzFEdMd_PcXZtNxsJFKaeD_YKp-n9dmIQ45DY5-JKa6Mefo92NuAO_X9Jv5SA3FB5hj4dW1ODxjbnCqR3ZqTg";
    //String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NjUyMDg5NjQsImlhdCI6MTY2NTEyMjU3OSwiYXV0aF90aW1lIjoxNjY1MTIyNTY0LCJqdGkiOiJkNzdiNmE4Ny0yN2ViLTRiYWItYjVlNC03OGY1ZjYzYTQ4NzciLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI4N2RiYWQ0Yy1lNzU0LTQ3NzktYTY1OC0yNDc0YTcyYThjNzQiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiODdkYmFkNGMtZTc1NC00Nzc5LWE2NTgtMjQ3NGE3MmE4Yzc0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyMSJ9.jr33vmRATXCtvQ6VGLxk_Y3XTRMzfpierqSSEvsOM0kKaZP0IIAlxlvWRnjj2W2iVMJqkKX-7vY6-GqYGoXbqKwXcsdziHQMG3aTK-t-WaiE-fh_FU3Ge5z1A-9dXkLAS_gMjeVxCQZt58vycrBzDcJUSceiSaAsIpDt_DzuVWGJMGFd2-26k2SJbWN-EijQy8f--UH7p_9tZxPytFnVKxRy_QRV6cZ7bYDkDaZkccifVfZnOnwB1OZm52IlDR2YMESg9uGH6HJeGSXJ9tZ6ob5Ta5Bs1Q7PKDqs6MKyi7x3dFGYG0jbZ3KMjz1MEtYUHcVSzSuADm1gJHT592gTNw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView txt = (TextView)findViewById(R.id.textView);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<JsonObject>> call=apiInterface.getCurrent();
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                ArrayList<JsonObject> arr = response.body();

                // Log.d("API CALL", asset.type+"");
                // double t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsFloat();
                String id= arr.get(0).getAsJsonObject().get("id").getAsString();
                String a=String.valueOf(arr.size());
                txt.setText(a);
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
