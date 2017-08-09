package com.js.graphicdisplay.adapter;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.js.graphicdisplay.util.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by apple on 2017/8/9.
 */

public class ChartAdapter {

    private ArrayList<IBarDataSet> data;

    public ChartAdapter(ArrayList<IBarDataSet> list) {
        data = list;
    }

    public BarData getData() {
        int size = data.size();

        if (size == 0) {
            return getOneGroup();
        } else {
            return getGroups();
        }
   }

    public BarData getOneGroup() {
        BarDataSet barDataSet = (BarDataSet) data.get(0);
        barDataSet.setColors(
                ColorTemplate.TEMPLETE_COLOR1[0],
                ColorTemplate.COLOR_YELLOW);
//            barDataSet.setStackLabels();

        BarData barData = new BarData(barDataSet);
//        barData.setValueTypeface(mTfLight);
        barData.setDrawValues(false); //不显示y轴的值
        return barData;
    }

    public BarData getGroups() {

        int barCountPerGroup = data.size();

        float groupSpace = 0.08f;
        float perBarSpaceWidth = (1.00f - groupSpace) / barCountPerGroup;
        float barSpace = 0.00f;
        float barWidth = perBarSpaceWidth - barSpace;

        for (int i = 0; i < data.size(); i++) {
            BarDataSet barDataSet = (BarDataSet) data.get(i);
            barDataSet.setColors(
                    ColorTemplate.TEMPLETE_COLOR1[i % ColorTemplate.TEMPLETE_COLOR1.length],
                    ColorTemplate.COLOR_YELLOW);
//            barDataSet.setStackLabels();
        }

        BarData barData = new BarData(data);
        barData.setBarWidth(barWidth);
//        barData.setValueTypeface(mTfLight);
        barData.setDrawValues(false); //不显示y轴的值
        return barData;
    }

}
