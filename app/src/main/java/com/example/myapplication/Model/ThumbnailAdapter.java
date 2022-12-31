package com.example.myapplication.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class ThumbnailAdapter extends BaseAdapter {
    Context myContext;
    int myDropdownLayout;
    int mySelectedLayout;


    public ThumbnailAdapter(Context context, int dropdownLayout, int selectedLayout) {
        myContext = context;
        myDropdownLayout = dropdownLayout;
        mySelectedLayout = selectedLayout;
    }

    @Override
    public int getCount() {
        return Thumbnail.values().length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(mySelectedLayout, null);

        TextView tvThumbnail = (TextView) view.findViewById(R.id.item_selected_thumbnail_tv);

        switch (i) {
            case 0:
                tvThumbnail.setText(Thumbnail.Thumbnail1.getName());
                break;
            case 1:
                tvThumbnail.setText(Thumbnail.Thumbnail2.getName());
                break;
            case 2:
                tvThumbnail.setText(Thumbnail.Thumbnail3.getName());
                break;
            /*case 3:
                tvThumbnail.setText(Thumbnail.Thumbnail4.getName());
                break;*/
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myDropdownLayout, null);

        TextView tvThumbnail = (TextView) convertView.findViewById(R.id.item_thumbnail_tv);
        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.item_thumbnail_iv);
        switch (position) {
            case 0:
                tvThumbnail.setText(Thumbnail.Thumbnail1.getName());
                ivThumbnail.setImageResource(Thumbnail.Thumbnail1.getImg());
                break;
            case 1:
                tvThumbnail.setText(Thumbnail.Thumbnail2.getName());
                ivThumbnail.setImageResource(Thumbnail.Thumbnail2.getImg());
                break;
            case 2:
                tvThumbnail.setText(Thumbnail.Thumbnail3.getName());
                ivThumbnail.setImageResource(Thumbnail.Thumbnail3.getImg());
                break;
            /*case 3:
                tvThumbnail.setText(Thumbnail.Thumbnail4.getName());
                ivThumbnail.setImageResource(Thumbnail.Thumbnail4.getImg());
                break;*/
        }

        return convertView;
    }
}
