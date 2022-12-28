package com.example.myapplication;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class asset_detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_asset);
        LineChart lineChart = findViewById(R.id.chart);
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
        yAxis.setAxisMaximum(24);

        chart.getAxisRight().setEnabled(false);

        chart.invalidate();

    }

    private List<Entry> getDataSet() {
        List<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(0, 4));
        lineEntries.add(new Entry(1, 3));
        lineEntries.add(new Entry(2, 6));
        lineEntries.add(new Entry(3, 8));
        lineEntries.add(new Entry(4, 2));
        lineEntries.add(new Entry(5, 3));
        lineEntries.add(new Entry(6, 1));
        return lineEntries;
    }
}

