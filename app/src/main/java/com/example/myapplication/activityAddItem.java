package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Thumbnail;
import com.example.myapplication.Model.ThumbnailAdapter;

public class activityAddItem extends AppCompatActivity {
    Spinner spiner;
    RadioButton rdo1,rdo2;
    EditText edtMaxt,edtMaxh,edtMaxw,edtMint,edtMinh,edtMinw,titlet,titleh,titlew;

    Button btnDate,btnAdd;
    Button btn2,btnView;
    int spinner_pos;
    int img = Thumbnail.Thumbnail1.getImg();
    ThumbnailAdapter thumbnailAdapter;
    SharedPreferences myPreferences;
    SharedPreferences.Editor myEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_node);
        edtMaxt=findViewById(R.id.valueMaxT);
        edtMaxh=findViewById(R.id.valueMaxh);
        edtMaxw=findViewById(R.id.valueMaxW);
        edtMint=findViewById(R.id.valueMinT);
        edtMinh=findViewById(R.id.valueMinh);
        edtMinw=findViewById(R.id.valueMinW);
        titlet=findViewById(R.id.edt_title);
        titleh=findViewById(R.id.edt_title1);
        titlew=findViewById(R.id.edt_title2);

         myPreferences
                = PreferenceManager.getDefaultSharedPreferences(activityAddItem.this);


        myEditor = myPreferences.edit();


        btnAdd=findViewById(R.id.btn_add);
        thumbnailAdapter = new ThumbnailAdapter(
                this,
                R.layout.item_thumbnail,
                R.layout.item_selected_thumbnail
        );
        //setThumbnail();
        if(myPreferences.getInt("size",0)==3)
        {
            getData();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                Toast.makeText(activityAddItem.this,"Updated successful",Toast.LENGTH_SHORT).show();
            }


        });




    }

    private void getData() {
        for(int i=0;i<3;i++)
        {
            String title="title"+i;
            String min="min"+i;
            String max="max"+i;
            float h=0;
            switch (i){
                case 0:
                    titlet.setText(myPreferences.getString(title,"unknow"));
                    edtMaxt.setText(String.valueOf(myPreferences.getFloat(max,0)));
                    edtMint.setText(String.valueOf(myPreferences.getFloat(min,0)));break;
                case 1:  titleh.setText(myPreferences.getString(title,"unknow"));
                    edtMaxh.setText(String.valueOf(myPreferences.getFloat(max,0)));
                    edtMinh.setText(String.valueOf(myPreferences.getFloat(min,0)));break;
                case 2:  titlew.setText(myPreferences.getString(title,"unknow"));
                    edtMaxw.setText(String.valueOf(myPreferences.getFloat(max,0)));
                    edtMinw.setText(String.valueOf(myPreferences.getFloat(min,0)));break;

            }

        }
    }

    private void setData() {
        myEditor.putInt("size",0);
        int size=myPreferences.getInt("size",0);
        for(int i=0;i<3;i++)
        {
            String title="title"+i;
            String min="min"+i;
            String max="max"+i;
            float h=0;
            switch (i){
            case 0: myEditor.putString(title,titlet.getText().toString());
                myEditor.putFloat(min,Float.valueOf(edtMint.getText().toString()));
                myEditor.putFloat(max,Float.valueOf(edtMaxt.getText().toString()));
              ;break;
            case 1: myEditor.putString(title,titleh.getText().toString());
                myEditor.putFloat(min,Float.valueOf(edtMinh.getText().toString()));
                myEditor.putFloat(max,Float.valueOf(edtMaxh.getText().toString()));break;
            case 2: myEditor.putString(title,titlew.getText().toString());
                myEditor.putFloat(min,Float.valueOf(edtMinw.getText().toString()));
                myEditor.putFloat(max,Float.valueOf(edtMaxw.getText().toString()));break;

            }
            size++;
        }
        myEditor.putInt("size",3);
        myEditor.commit();

        Log.d("test12",myPreferences.getFloat("max0",0)+"");






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
