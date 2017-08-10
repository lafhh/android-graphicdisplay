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

    /**
     * 没有分组的柱状图也可以设置柱子的尺寸，每个间隔里只有一根柱子
     * @return
     */
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

    /**
     * 通过bardataset获取柱状图每个间隔内的柱子，和柱子间的间隔的尺寸,柱子的颜色
     * @return
     */
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

    public float getSegmentSpace() {
        return 0.08f;
    }
}
