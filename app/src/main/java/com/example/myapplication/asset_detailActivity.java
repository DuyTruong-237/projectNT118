package com.example.myapplication;
import android.app.DatePickerDialog;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.DB.DatabaseHelper;
import com.example.myapplication.Model.Asset;
import com.example.myapplication.Model.Thumbnail;
import com.example.myapplication.Model.ThumbnailAdapter;
import com.example.myapplication.Model.asset_infor;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class asset_detailActivity extends AppCompatActivity {
    public static DatabaseHelper db;
    APIInterface apiInterface;
    List<asset_infor> infors;
    String assetID;
    TextView txtTitle;
    LineChart lineChart;
    Spinner snThumbnail;
    Button btn1;
    Spinner spiner;
    List<asset_infor>arr3;
    Button btn2,btnView;
    int spinner_pos;
    CheckBox chkMonth;
    int img = Thumbnail.Thumbnail1.getImg();
    ThumbnailAdapter thumbnailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_asset);
        txtTitle=findViewById(R.id.txtTitle);
        btn1=(Button) findViewById(R.id.btn_from);
        btn2 =(Button) findViewById(R.id.btn_to);
        btnView=findViewById(R.id.btn_view);
        spiner=findViewById(R.id.sn_thumbnail);
        chkMonth=findViewById(R.id.chkMonth);
        Bundle extras = getIntent().getExtras();

        if (extras!=null)
          assetID=  extras.getString("assetID");

        //db.addInfor(new asset_infor("6H4PeKLRMea1L0WsRXXWp9","Weather Asset","30/11/122",Float.valueOf(15.9),Float.valueOf(15.9),2.3));

        thumbnailAdapter = new ThumbnailAdapter(
                this,
                R.layout.item_thumbnail,
                R.layout.item_selected_thumbnail
        );
        setThumbnail();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDay(btn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDay(btn2);
            }
        });
        lineChart = findViewById(R.id.chart);



       // callApiAndSave();*/
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterIF();
            }
        });





       /* ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new Entry(945f, 0));
        NoOfEmp.add(new Entry(1040f, 1));
        NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(1695f, 9));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Number Of Employees");

        ArrayList year = new ArrayList();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        PieData data = new PieData(year, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);*/
    }

    private void getDB() {
        db = new DatabaseHelper(this);
        infors = db.getAllContacts();


        for(int i=0;i<infors.size();i++)
        {

            if(infors.get(i).getIdasset().equals(assetID))
            {
                txtTitle.setText(infors.get(i).getName());

                break;
            }
        }
    }

    private void filterIF(){
        getDB();
        String a;
         spinner_pos = spiner.getSelectedItemPosition();

        Log.d("name Thum", spinner_pos+"");
        String[] dateFrom=btn1.getText().toString().split("/");
        String[] dateTo=btn2.getText().toString().split("/");
        String dayfrom=dateFrom[0]+"/"+(Integer.parseInt(dateFrom[1])-1)+"/"+(Integer.parseInt(dateFrom[2])-1900);
        String dayto=dateTo[0]+"/"+(Integer.parseInt(dateTo[1])-1)+"/"+(Integer.parseInt(dateTo[2])-1900);


        for(int i=0;i<infors.size();i++) {
            if (!infors.get(i).getIdasset().equals(assetID)) {
                Log.d("truong2", infors.get(1).getName() + "");
                infors.remove(i);
                i--;
                Log.d("truong2", infors.size() + "");
                continue;
            }
            }
        List<asset_infor> arr2;
        arr2=infors;
        boolean staticday=true;
        for (int i=0;i<infors.size();i++)
        {
            if(dayfrom.equals(infors.get(i).getDate()))
            {
                staticday=false;
            }
            if(dayto.equals(infors.get(i).getDate()))
            {
                staticday=true;
                i++;
            }
            if (staticday==true&&i<infors.size())
            {
                infors.remove(i);
                i--;
            }
        }
        if(infors.size()==0)
            infors=arr2;
        Log.d("Sizearr",infors.size()+"");
            Log.d("err3","1");
            Log.d("err4","1");
        drawLineChart(lineChart);
    }
    private void SetDay(Button btn)
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
        snThumbnail = (Spinner) findViewById(R.id.sn_thumbnail);
        snThumbnail.setAdapter(thumbnailAdapter);
        snThumbnail.setOnItemSelectedListener(
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


    private void drawLineChart(LineChart chart) {

        List<Entry> lineEntries = getDataSet();
        //Log.d("123abc",String.valueOf(lineEntries.get(0)));
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Work");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(10);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.DKGRAY);
        lineDataSet.setMode(LineDataSet.Mode.STEPPED);

        LineData lineData = new LineData(lineDataSet);
        chart.getDescription().setTextSize(12);
        chart.getDescription().setEnabled(false);
        chart.setDrawMarkers(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.animateY(1000);
        chart.getXAxis().setGranularityEnabled(true);
        chart.getXAxis().setGranularity(1.0f);
        chart.setData(lineData);

        ArrayList<String> xAxisLabel = new ArrayList<>();
        String datestatic="a";

        for(int i=0;i<infors.size();i++)
        {
            Log.d("ABC123",infors.get(i).getDate());
            if(datestatic.equals(infors.get(i).getDate()))
            {
                infors.remove(i);
                i--;


            }
            else{
                if(chkMonth.isChecked())
                {
                    xAxisLabel.add((Integer.parseInt(infors.get(i).getDate().split("/")[1])+1)+"");
                }
                else {
                    xAxisLabel.add(infors.get(i).getDate());

                }datestatic=infors.get(i).getDate();

                Log.d("truong2",infors.get(i).getDate()+"");
            }



        }
        Log.d("ABC1234",infors.size()+"");
        //infors=arr3;


        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMaximum(infors.size());
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel) {
            @Override
            public String[] getValues() {
                return super.getValues();
            }
        });

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0);

        xAxis.setAxisMinimum(0);
        switch (spinner_pos){
            case 0:
                yAxis.setAxisMaximum(35);
                break;
            case  1:
                yAxis.setAxisMaximum(100);
                break;
            case 2:
                yAxis.setAxisMaximum(5);
                break;
            default:
                yAxis.setAxisMaximum(35);
        }

        xAxis.setAxisMaximum(infors.size());

        chart.getAxisRight().setEnabled(false);

        chart.invalidate();

    }

    private List<Entry> getDataSet() {
        List<Entry> lineEntries = new ArrayList<>();
        Log.d("sizw1",infors.size()+"");
        String a="a";
        int j=0;
        for(float i=0;i<infors.size();i++)
        {
            if(!a.equals(infors.get((int)i).getDate()))
            {
                Log.d("sizw1-" ,infors.get((int)i).getValueT()+"");

                    if(spinner_pos==0)
                    {
                        //int vl=(int)infors.get((int)i).getValueT();
                        lineEntries.add(new Entry(j, infors.get((int)i).getValueT()));
                        j++;
                        Log.d("dddd", infors.get((int)i).getValueT()+"");
                    }
                if(spinner_pos==1)
                {
                    //int vl=(int)infors.get((int)i).getValueT();
                    lineEntries.add(new Entry(j, infors.get((int)i).getValueH()));
                    j++;
                    Log.d("dddd", infors.get((int)i).getValueH()+"");
                }
                if(spinner_pos==2)
                {
                    //int vl=(int)infors.get((int)i).getValueT();
                    lineEntries.add(new Entry(j, infors.get((int)i).getValueW()));
                    j++;
                    Log.d("dddd", infors.get((int)i).getValueT()+"");
                }
                    a=infors.get((int)i).getDate();

                /*   if(spinner_pos==1)
                        lineEntries.add(new Entry(i, infors.get(i).getValueH()));

                   if(spinner_pos==2)
                        lineEntries.add(new Entry(i, infors.get(i).getValueW()));

                a=infors.get(i).getDate();*/
            }


        }


        return lineEntries;
    }
}

