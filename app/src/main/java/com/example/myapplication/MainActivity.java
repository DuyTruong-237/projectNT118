package com.example.myapplication;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    BottomNavigationView botNav;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botNav=findViewById(R.id.bottom_navi);
        vp= findViewById(R.id.viewPager);
        setupViewPager();
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.item_mainhome:
                       vp.setCurrentItem(0);
                        break;
                    case R.id.item_map:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.item_Device:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.item_setting:
                        vp.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
        //moveActivity();
    }
    private void setupViewPager()
    {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
      vp.setAdapter(viewPagerAdapter);

    }

    private void moveActivity() {
        CountDownTimer countDownTimert=new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(MainActivity.this,MenuActivity.class );
                startActivity(intent);
            }
        };
        countDownTimert.start();
    }
}
