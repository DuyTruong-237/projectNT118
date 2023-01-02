package com.example.myapplication;


import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DB.DatabaseHelper;

public class load extends AppCompatActivity {
    ImageView img;
    private static final int JOB_ID =123 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        onClickStartScheduleJop();

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
    private void onClickStartScheduleJop() {

        ComponentName componentName=new ComponentName(this, MyJobService.class);
        JobInfo jobInfo=new JobInfo.Builder(JOB_ID, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15*60 *1000)
                .build();
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule((jobInfo));
    }
    private void onClickCancelScheduleJop() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel((JOB_ID));

    }
}
