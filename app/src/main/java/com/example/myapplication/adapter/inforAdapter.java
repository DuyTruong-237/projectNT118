package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.infoAsset;
import com.example.myapplication.R;

public class inforAdapter extends ArrayAdapter<infoAsset> {
    Activity context;
    int resource;
    public inforAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater indlafter=this.context.getLayoutInflater();
        View customView=indlafter.inflate(this.resource,null);

        TextView txtTitle=customView.findViewById(R.id.txtTitle);
        TextView txtGia=customView.findViewById(R.id.txtInfo);

        infoAsset info=getItem(position);

        txtTitle.setText(info.getTitle());
        txtGia.setText(info.getInfo());
        return customView;
    }
}
