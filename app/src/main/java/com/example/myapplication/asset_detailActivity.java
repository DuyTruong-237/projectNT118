package com.example.myapplication;
import android.app.DatePickerDialog;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button btn2;
    Button btn_xem;
    int img = Thumbnail.Thumbnail1.getImg();
    ThumbnailAdapter thumbnailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_asset);
        txtTitle=findViewById(R.id.txtTitle);
        btn_xem = (Button) findViewById(R.id.btn_xem) ;

        Bundle extras = getIntent().getExtras();
        if (extras!=null)
          assetID= extras.getString("idDevice");
        thumbnailAdapter = new ThumbnailAdapter(
                this,
                R.layout.item_thumbnail,
                R.layout.item_selected_thumbnail
        );
        setThumbnail();
        btn1=(Button) findViewById(R.id.btn_from);
        btn2 =(Button) findViewById(R.id.btn_to);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chonngay(btn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chonngay(btn2);
            }
        });
        lineChart = findViewById(R.id.chart);
        db = new DatabaseHelper(this);

        infors = db.getAllContacts();
       // callApiAndSave();
      for(int i=0;i<infors.size();i++)
      {
          if (!infors.get(i).getIdasset().equals(assetID))
          {
              infors.remove(i);
          }
      }
      txtTitle.setText(infors.get(0).getName());
        drawLineChart(lineChart);



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
    private void Chonngay(Button btn)
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btn.setText(dayOfMonth + "/" + month +"/"+year);
            }
        }, 2022, 01, 29);
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

    private void callApiAndSave() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Asset> call = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");//, "Bearer "+ token);
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Log.d("API CALL", response.code()+"");
                //Log.d ("API CALL", response.toString());
                Asset asset = response.body();

                Log.d("API CALL", asset.type+"");
                float t=asset.attributes.get("temperature").getAsJsonObject().get("value").getAsInt();
                String a=String.valueOf(t);
                Date date=new Date();
                String day=date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
                //db.addInfor(new asset_infor(1,"temperature","t",day,t));
                infors = db.getAllContacts();
                //Log.d("hello",infors.get(0).getId()+infors.get(0).getIdasset()+"-"+infors.get(0).getDate1()+"-"+infors.get(0).getValue() );
                /*int h=asset.attributes.get("humidity").getAsJsonObject().get("value").getAsInt();
                a=String.valueOf(h);
                Log.d(TAG, a);
                a=asset.attributes.get("weatherData").getAsJsonObject().get("value").getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                Log.d(TAG, a);
                float w=asset.attributes.get("windSpeed").getAsJsonObject().get("value").getAsFloat();
                a=String.valueOf(w);
                Log.d(TAG, a);*/
                //txttype.setText(asset.type);

            }


            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALL", t.getMessage().toString());

                //t.printStackTrace();

            }
        });
    }
    private void drawLineChart(LineChart chart) {

        List<Entry> lineEntries = getDataSet();
        Log.d("123abc",String.valueOf(lineEntries.get(0)));
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Work");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(6);
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
        xAxisLabel.add("T");
        xAxisLabel.add("Time");
        xAxisLabel.add("2-up");

        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMaximum(3);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel) {
            @Override
            public String[] getValues() {
                return super.getValues();
            }
        });

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(32);

        chart.getAxisRight().setEnabled(false);

        chart.invalidate();

    }

    private List<Entry> getDataSet() {
        List<Entry> lineEntries = new ArrayList<>();
        for(int i=0;i<infors.size();i++)
        {
            lineEntries.add(new Entry(i, infors.get(i).getValueT()));
        }


        return lineEntries;
    }
}

