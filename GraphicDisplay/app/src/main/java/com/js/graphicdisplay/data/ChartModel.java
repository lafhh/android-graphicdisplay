package com.js.graphicdisplay.data;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.js.graphicdisplay.util.ColorTemplate;

import java.util.ArrayList;

/**
 *  将model数据转换为chart可以呈现的数据
 *
 * Created by apple on 2017/8/8.
 */

public class ChartModel {

    //ArrayList<Entry>
    //BarDataSet
    //ArrayList<BarDataSet> groups
    //BarData

    private BarDataSet dataSet;

    private ArrayList<BarDataSet> dataSets;

    private int stackSize = 1;

    public boolean isStacked() {
        return stackSize > 1;
    }


    String[] date = {"201707", "201708"}; //回款
    public BarDataSet getDataSet(Group data) {
        String name = data.getName();
        data.setKeyColor(1);

        ArrayList<ReturnedMoneyData> list = data.getRmData();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            BarEntry entry;
            entry = get2StacksBarEntry(j, list.get(j));
            barEntries.add(entry);
        }
        dataSet = new BarDataSet(barEntries, name);

        return dataSet;
    }

    //资金上缴
    public ArrayList<BarDataSet> getDataSets(ArrayList<Group> datas) {
        dataSets = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {
            Group group = datas.get(i);
            String name = group.getName();
            group.setKeyColor((i + 1) % ColorTemplate.TEMPLETE_COLOR.length);

            ArrayList<ReturnedMoneyData> list = group.getRmData();
            ArrayList<BarEntry> barEntries = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                BarEntry entry;
                entry = get2StacksBarEntry(j, list.get(j));
                barEntries.add(entry);
            }

            BarDataSet dataSet = new BarDataSet(barEntries, name);
            dataSet.setStackLabels();
            dataSets.add(dataSet);
        }

        return dataSets;
    }

    private BarEntry get2StacksBarEntry(int x, ReturnedMoneyData data) {
        float val1 = data.getMonthIncomeReal();
        float val2 = data.getMonthIncomePlan();
        if (val1 < val2) {
            val2 = val2 - val1;

        } else if (val1 == val2) {
            val2 = 0;
        }
        return new BarEntry(x, new float[]{val1, val2});
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }
}
