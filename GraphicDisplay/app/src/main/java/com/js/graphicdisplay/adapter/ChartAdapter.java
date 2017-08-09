package com.js.graphicdisplay.adapter;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.js.graphicdisplay.util.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by apple on 2017/8/9.
 */

public class ChartAdapter {

    private ArrayList<BarDataSet> data;

    public ChartAdapter(ArrayList<BarDataSet> list) {
        data = list;
    }

    public BarData getData() {

        int barCountPerGroup = data.size();

        float groupSpace = 0.08f;
        float perBarSpaceWidth = (1.00f - groupSpace) / barCountPerGroup;
        float barSpace = 0.00f;
        float barWidth = perBarSpaceWidth - barSpace;

        BarData barData = new BarData();

        for (int i = 0; i < data.size(); i++) {
            BarDataSet barDataSet = data.get(i);
            barDataSet.setColors(
                    ColorTemplate.TEMPLETE_COLOR1[i % ColorTemplate.TEMPLETE_COLOR1.length],
                    ColorTemplate.COLOR_YELLOW);
            barDataSet.setStackLabels();
        }

        barData.
    }

}
