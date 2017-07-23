package com.js.graphicdisplay.mpchart.customization;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.mpchart.components.FundsMarkerView;

/**
 * Created by apple on 2017/7/11.
 */

public class BarChartCustomization {

    public static BarChart customBarChart(BarChart chart, Typeface typeface) {
        chart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);

        chart.setDrawGridBackground(false);


        FundsMarkerView mv = new FundsMarkerView(chart.getContext(), R.layout.markerview_funds);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setDrawInside(false);
        l.setTypeface(typeface);
        l.setYOffset(2f);
        l.setYEntrySpace(0f);
        l.setTextSize(10f);
        l.setWordWrapEnabled(true);
        l.setMaxSizePercent(0.88f);
//        l.setFormSize(9f);
//        l.setXEntrySpace(4f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setLabelRotationAngle(33f);
        xAxis.setTypeface(typeface);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
//        xAxis.setDrawGridLines(false); //设置垂直网格线
        xAxis.setGridColor(Color.parseColor("#ECEFF0"));
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("laf", String.valueOf((int) v));
                return String.valueOf((int) v);
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(typeface);
//        leftAxis.setValueFormatter(new LargeValueFormatter());

        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("laf", String.valueOf((int) v));
                return String.valueOf((int) v);
            }
        });

//        leftAxis.setDrawGridLines(false);
        leftAxis.setGridColor(Color.parseColor("#DCC6D1")); //设置水平网格线
        leftAxis.setDrawZeroLine(true); //设置坐标为0的水平线
        leftAxis.setZeroLineColor(Color.parseColor("#DCC6D1"));
        leftAxis.setZeroLineWidth(1f);
        leftAxis.setAxisLineColor(Color.parseColor("#DCC6D1"));
        leftAxis.setAxisLineWidth(1f);
        leftAxis.setLabelCount(18, false);
//        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        return chart;
    }

}
