package com.js.mpchartexample.exercise;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.js.mpchartexample.DemoBase;
import com.js.mpchartexample.R;

import java.util.ArrayList;

/**
 * Created by apple on 2017/6/25.
 */

public class PieChartActivity extends DemoBase {
    private PieChart pChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        pChart = (PieChart) findViewById(R.id.chart1);

        pChart.setUsePercentValues(true);
        pChart.getDescription().setEnabled(false);
        pChart.setExtraOffsets(5, 10, 5, 5);

        pChart.setDragDecelerationFrictionCoef(0.95f);

        pChart.setCenterTextTypeface(mTfLight);
        pChart.setCenterText(generateCenterSpannableText());

        pChart.setDrawHoleEnabled(true);
        pChart.setHoleColor(Color.WHITE);

        pChart.setTransparentCircleColor(Color.WHITE);
        pChart.setTransparentCircleAlpha(110);

        pChart.setHoleRadius(58f);
        pChart.setTransparentCircleRadius(61f);

        pChart.setDrawCenterText(true);

        pChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pChart.setRotationEnabled(true);
        pChart.setHighlightPerTapEnabled(true);

        setData(4, 100);

        pChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = pChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pChart.setEntryLabelColor(Color.WHITE);
        pChart.setEntryLabelTypeface(mTfRegular);
        pChart.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    private void setData(int count, int range) {
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            float value = (float) ((Math.random() * mult)); //这里给了随意值，他总能按照百分比划分，不知道是怎么实现的
//            Log.d("laf", "entry value = " + value);
            entries.add(new PieEntry(value,
                    mParties[i % mParties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        pChart.setData(data);

        pChart.highlightValues(null);
    }
}
