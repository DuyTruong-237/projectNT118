package com.example.myapplication;


import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.Toast;

public class load extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        moveActivity();
    }

    private void moveActivity() {
        CountDownTimer countDownTimert=new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(load.this,activity_login.class );
                startActivity(intent);
            }
        };
        countDownTimert.start();
    }
}