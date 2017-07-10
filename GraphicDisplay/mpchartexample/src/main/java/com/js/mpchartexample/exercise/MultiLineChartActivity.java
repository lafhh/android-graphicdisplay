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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.js.mpchartexample.DemoBase;
import com.js.mpchartexample.R;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/7.
 */

public class MultiLineChartActivity extends DemoBase {

    private LineChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_linechart);
        mChart = (LineChart) findViewById(R.id.chart1);

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
//        xAxis.setCenterAxisLabels(true);
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
        leftAxis.setLabelCount(10, false);
//        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);

        setData();
        /*** test ***/
//        String file = "chart";
//        String json = FileUtil.readToString(file);
//        Log.d(TAG, "test json from file================" + json);
//        groupChartFromJson(json);
//        setChartData();
        /*** test ***/
    }

    private void setData() {
        ArrayList<ILineDataSet> dataSet = new ArrayList<>();

        int start = 1981;
        int count = 6;
        int end = start + count;

        for (int i = 0; i < 4; i++) {
            ArrayList<Entry> list = new ArrayList<>();

            for (int j = start; j < end; j++) {
                double val = (Math.random() * 4) + 4;
                list.add(new Entry(j, (float) val));
            }
            LineDataSet d = new LineDataSet(list, "DataSet " + i + 1);
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = mColors[i % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSet.add(d);
        }
        // make the first DataSet dashed
        ((LineDataSet) dataSet.get(0)).enableDashedLine(10, 10, 0);
        ((LineDataSet) dataSet.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
        ((LineDataSet) dataSet.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        lineData.setValueTypeface(mTfLight);

        mChart.getXAxis().setAxisMinimum(start);
//        mChart.getXAxis().setAxisMaximum();



        mChart.invalidate();

    }

    private int[] mColors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };
}
