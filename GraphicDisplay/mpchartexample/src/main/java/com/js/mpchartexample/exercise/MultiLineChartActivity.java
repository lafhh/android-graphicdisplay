package com.js.mpchartexample.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.js.mpchartexample.DemoBase;
import com.js.mpchartexample.R;

/**
 * Created by apple on 2017/7/7.
 */

public class MultiLineChartActivity extends DemoBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_linechart);
        LineChart mChart = (LineChart) findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(false);
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
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
        l.setWordWrapEnabled(true);
        l.setMaxSizePercent(0.77f);
//        l.setFormSize(9f);
//        l.setXEntrySpace(4f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false); //设置垂直网格线
//        xAxis.setGridColor(Color.parseColor("#ECEFF0"));
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("laf", String.valueOf((int) v));
                return String.valueOf((int) v);
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                Log.d("laf", String.valueOf((int) v));
                return String.valueOf((int) v + "%");
            }
        });
        leftAxis.setGridColor(Color.parseColor("#DCC6D1")); //设置水平网格线
        leftAxis.setDrawZeroLine(true); //设置坐标为0的水平线
        leftAxis.setZeroLineColor(Color.parseColor("#DCC6D1"));
        leftAxis.setZeroLineWidth(1f);
        leftAxis.setAxisLineColor(Color.parseColor("#DCC6D1"));
        leftAxis.setAxisLineWidth(1f);
        leftAxis.setLabelCount(18, false);
//        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);

        /*** test ***/
//        String file = "chart";
//        String json = FileUtil.readToString(file);
//        Log.d(TAG, "test json from file================" + json);
//        groupChartFromJson(json);
//        setChartData();
        /*** test ***/
    }
}
