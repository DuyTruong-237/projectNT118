package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class activity_info_device extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infodevice);

        ImageView iv_back = findViewById(R.id.iv_back);
        ImageView iv_setting = findViewById(R.id.iv_setting);
        TextView title = findViewById(R.id.tv_Info);

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
