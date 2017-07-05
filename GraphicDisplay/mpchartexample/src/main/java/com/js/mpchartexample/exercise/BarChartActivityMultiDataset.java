package com.js.mpchartexample.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.js.mpchartexample.DemoBase;
import com.js.mpchartexample.R;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/23.
 */

public class BarChartActivityMultiDataset extends DemoBase {

    private static final String TAG = "BarChartActivityMultiDataset";
    private BarChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setYOffset(2f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
//        xAxis.setDrawGridLines(false);
        xAxis.setGridColor(Color.parseColor("#BDBDBD"));
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                Log.d("laf", String.valueOf((int) v));
                return String.valueOf((int) v);
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setZeroLineColor(Color.parseColor("#DCC6D1"));
        leftAxis.setZeroLineWidth(1f);
        leftAxis.setAxisLineColor(Color.parseColor("#BDBDBD"));
//        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);

        setData();
    }

    private void setData() {
        float groupSpace = 0.1f;
        float barSpace = 0.0f; // x3 DataSet
        float barWidth = 0.3f; // x3 DataSet
        //(0.25 + 0.05) * 3 + 0.1 = 1.00 -> interval per "group"

        int groupCount = 6;
        int startYear = 1980;
        int endYear = startYear + groupCount;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals4 = new ArrayList<BarEntry>();

        float radomMultiplier = 1000f;
        for (int i = startYear; i < endYear; i++) {
            yVals1.add(new BarEntry(i, (float) (Math.random() * radomMultiplier)));
            yVals2.add(new BarEntry(i, (float) (Math.random() * radomMultiplier)));
            yVals3.add(new BarEntry(i, (float) (Math.random() * radomMultiplier)));
//            yVals4.add(new BarEntry(i, (float) (Math.random() * radomMultiplier)));
        }

        BarDataSet set1, set2, set3, set4;
        set1 = new BarDataSet(yVals1, "Company 1");
        set1.setColor(Color.rgb(104, 241, 175));
        set2 = new BarDataSet(yVals2, "Company 2");
        set2.setColor(Color.rgb(164, 228, 251));
        set3 = new BarDataSet(yVals3, "Company 3");
        set3.setColor(Color.rgb(242, 247, 158));
//        set4 = new BarDataSet(yVals4, "Company 4");
//        set4.setColor(Color.rgb(255, 102, 0));

        BarData data = new BarData(set1, set2, set3);
        data.setBarWidth(barWidth);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        mChart.getXAxis().setAxisMinimum(startYear);
//        mChart.getXAxis().setAxisMaximum(1986);
        mChart.getXAxis().setAxisMaximum(startYear + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        mChart.groupBars(startYear, groupSpace, barSpace);

        mChart.setData(data);
    }
}
