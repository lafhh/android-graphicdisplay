package com.js.mpchartexample.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.js.mpchartexample.DemoBase;
import com.js.mpchartexample.MyAxisValueFormatter;
import com.js.mpchartexample.R;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/4.
 */

public class StackedBarActivity extends DemoBase {

    private BarChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(false);
        mChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);

        // setting data

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        setData();

    }

    /**
     * 测试分组的stacked bar能否正常显示
     */
    private void setData() {

        float groupSpace = 0.08f;
        float barSpace = 0.0f; // x2 DataSet
        float barWidth = 0.46f; // x2 DataSet
        // (0.0 + 0.46) * 2 + 0.08 = 1.00 -> interval per "group"

        float mult = 100;
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            float val1 = (float) (Math.random() * mult) + mult / 2;
            float val2 = (float) (Math.random() * mult) + mult / 2;
//            Log.d("laf", "val1 = " + val1);
//            Log.d("laf", "val2 = " + val2);
            yVals1.add(new BarEntry(
                    i,
                    new float[]{val1, val2}
            ));
        }

        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        yVals2.add(new BarEntry(0, new float[] {24, 66}));
        yVals2.add(new BarEntry(1, new float[] {120, 22}));
        yVals2.add(new BarEntry(2, new float[] {44, 66}));
        yVals2.add(new BarEntry(3, new float[] {70, 99}));
        yVals2.add(new BarEntry(4, new float[] {33, 76}));
        yVals2.add(new BarEntry(5, new float[] {14, 120}));

        BarDataSet set1 = new BarDataSet(yVals1, "Statistics Vienna 2014");
        set1.setColors(new int[] {Color.rgb(139, 234, 255), Color.rgb(255, 210, 139)});
        set1.setStackLabels(new String[]{"Births", "Divorces", });

        BarDataSet set2 = new BarDataSet(yVals2, "Statistics Vienna 2015");
        set2.setColors(new int[] {Color.rgb(139, 234, 255), Color.rgb(255, 210, 139)});
        set2.setStackLabels(new String[]{"Births", "Divorces", });

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);
        mChart.setData(data);

        mChart.getBarData().setBarWidth(barWidth);
        mChart.getXAxis().setAxisMinimum(0);
        mChart.getXAxis().setAxisMaximum(mChart.getBarData().getGroupWidth(groupSpace, barSpace) * 6);
        mChart.groupBars(0, groupSpace, barSpace);

        mChart.setFitBars(true);
        mChart.invalidate();
    }
}
