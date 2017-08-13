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

    private ArrayList<IBarDataSet> iBarDataSets; //分组数据

    private BarDataSet dataSet; //未分组数据

    private boolean isGroups = false; //是否分组

    private boolean isStacked = false;

    public ChartAdapter(ArrayList<IBarDataSet> list) {
        iBarDataSets = list;
        isGroups = true;
    }

    public ChartAdapter(BarDataSet dataSet) {
        this.dataSet = dataSet;
        isGroups = false;
    }

    public boolean isGroups() {
        return isGroups;
    }

    public boolean isStacked() {
        return isStacked;
    }

    public int getDataSetsCount() {
        if (iBarDataSets != null) return iBarDataSets.size();
        return 1;
    }

    public BarData getBarData() {
        if (!isGroups) return getOneGroup();
        else return getGroups();
    }

    /**
     * 没有分组的柱状图也可以设置柱子的尺寸，每个间隔里只有一根柱子
     *
     * @return
     */
    public BarData getOneGroup() {
        dataSet.setColors(
                ColorTemplate.TEMPLETE_COLOR1[0],
                ColorTemplate.COLOR_YELLOW);
//            barDataSet.setStackLabels();
        isStacked = dataSet.isStacked();

        BarData barData = new BarData(dataSet);
//        barData.setValueTypeface(mTfLight);
        barData.setDrawValues(false); //不显示y轴的值
        return barData;
    }

    /**
     * 通过bardataset获取柱状图每个间隔内的柱子，和柱子间的间隔的尺寸,柱子的颜色
     *
     * @return
     */
    public BarData getGroups() {
        for (int i = 0; i < iBarDataSets.size(); i++) {
            BarDataSet barDataSet = (BarDataSet) iBarDataSets.get(i);
            barDataSet.setColors(
                    ColorTemplate.TEMPLETE_COLOR1[i % ColorTemplate.TEMPLETE_COLOR1.length],
                    ColorTemplate.COLOR_YELLOW);
//            barDataSet.setStackLabels();
            boolean b = barDataSet.isStacked();
            if (b) isStacked = b;
        }

        BarData barData = new BarData(iBarDataSets);
        
        barData.setBarWidth(getBarWidth());
//        barData.setValueTypeface(mTfLight);
        barData.setDrawValues(false); //不显示y轴的值
        return barData;
    }

    public float getGroupSpace() {
        return 0.08f;
    }

    public float getBarSpace() {
        return 0.00f;
    }

    public float getBarWidth() {
        int barCountPerGroup = iBarDataSets.size();
        float perBarSpaceWidth = (1.00f - getGroupSpace()) / barCountPerGroup;
        float barWidth = perBarSpaceWidth - getBarSpace();

        return barWidth;
    }

    public int getMaxEntryCount() {
        int max = 0;
        for (int i = 0; i < iBarDataSets.size(); i++) {
            BarDataSet barDataSet = (BarDataSet) iBarDataSets.get(i);
            int entryCount = barDataSet.getEntryCount();
            if (max < entryCount) max = entryCount;
        }
        return max;
    }

    public void setBarDataSets(ArrayList<IBarDataSet> sets) {
        iBarDataSets = sets;
    }

    public void setBarDataSet(BarDataSet dataSet) {
        this.dataSet = dataSet;
    }

//    public int getGroupWidth() {
//
//    }
}
