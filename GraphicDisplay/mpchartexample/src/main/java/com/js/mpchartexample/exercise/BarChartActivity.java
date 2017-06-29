package com.js.mpchartexample.exercise;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.js.mpchartexample.DayAxisValueFormatter;
import com.js.mpchartexample.DemoBase;
import com.js.mpchartexample.MyAxisValueFormatter;
import com.js.mpchartexample.R;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by js_gg on 2017/6/19.
 */

public class BarChartActivity extends DemoBase {

    private static final int BAR_CHART_MAX_VALUE_COUNT = 60;

    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    private ArrayList<ChartBean> chartData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart);

        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

        mChart = (BarChart) findViewById(R.id.chart1);

        mChart.setDrawBarShadow(false);
//        barChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(BAR_CHART_MAX_VALUE_COUNT);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);

        //Set a minimum interval for the axis when zooming in.
        // The axis is not allowed to go below that limit.
        // This can be used to avoid label duplicating when zooming in.
        xAxis.setGranularity(1f); // only intervals of 1 day

        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(6, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        leftAxis.setAxisMaximum(60f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(6, false); //znn
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        rightAxis.setAxisMaximum(60f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

//        initialChartData();
        int valueCount = chartData.size() - 1;
        int range = getMaxValue();
        setData();
    }

    private int getMaxValue() {
        int max = 0;
        for (Iterator<ChartBean> i = chartData.iterator(); i.hasNext(); ) {
            ChartBean bean = i.next();
            int value = bean.getValue();
            if (value > max) max = value;
        }
        return max;
    }

    private ArrayList<BarEntry> setBarEntryForBarChart() {
        ArrayList<BarEntry> yvals = new ArrayList<>();

        for (int i = 0; i < chartData.size(); i++) {
            int value = chartData.get(i).getValue();
            yvals.add(new BarEntry(i + 1, value));
        }
        return yvals;
    }

    private void setData() {
        ArrayList<BarEntry> entries = setBarEntryForBarChart();

        BarDataSet barDataSet = new BarDataSet(entries, "The year 2017");
        barDataSet.setDrawIcons(false);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        ArrayList<IBarDataSet> dataSet = new ArrayList<>();
        dataSet.add(barDataSet);

        BarData data = new BarData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTfLight);
        data.setBarWidth(0.9f);

        //Enables / disables drawing values (value-text) for all DataSets this data object contains.
        data.setDrawValues(false);

        mChart.setData(data);
    }

}
