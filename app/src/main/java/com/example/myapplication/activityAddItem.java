package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Thumbnail;
import com.example.myapplication.Model.ThumbnailAdapter;

public class activityAddItem extends AppCompatActivity {
    Spinner spiner;
    RadioButton rdo1,rdo2;
    EditText edtTitle,edtDes;
    Button btnDate,btnAdd;
    Button btn2,btnView;
    int spinner_pos;
    int img = Thumbnail.Thumbnail1.getImg();
    ThumbnailAdapter thumbnailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_node);
        edtTitle=findViewById(R.id.edt_title);




        btnAdd=findViewById(R.id.btn_add);
        thumbnailAdapter = new ThumbnailAdapter(
                this,
                R.layout.item_thumbnail,
                R.layout.item_selected_thumbnail
        );
        setThumbnail();







    }
    private void Chonngay(Button btn)
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btn.setText(dayOfMonth + "/" + (month+1) +"/"+year);
            }
        }, 2022, 11, 31);
        datePickerDialog.show();
    }
    private void setThumbnail() {
        spiner = (Spinner) findViewById(R.id.sn_thumbnail);
        spiner.setAdapter(thumbnailAdapter);
        spiner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i) {
                            case 0:
                                img = Thumbnail.Thumbnail1.getImg();
                                break;
                            case 1:
                                img = Thumbnail.Thumbnail2.getImg();
                                break;
                            case 2:
                                img = Thumbnail.Thumbnail3.getImg();
                                break;
                            /*case 3:
                                img = Thumbnail.Thumbnail4.getImg();
                                break;*/
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                }
        );


    }
}
