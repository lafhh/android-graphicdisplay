package com.js.graphicdisplay.mpchart.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by apple on 2017/8/4.
 */

public class XAxisValueFormatter implements IAxisValueFormatter {

    private String[] data;

    public XAxisValueFormatter(String[] s) {
        data = s;
    }

    int flag = 0; //检查v是从-1还是0开始的,如果-1，data[index+1]; 如果0，data[index]
    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        int index = (int) v;
        String str = "";

        if (index >= data.length) return ""; //从v = 0开始画

        if (index == -1) flag = -1;
        if (flag == -1) {
            if (index >= data.length - 1) return "";
            str = data[index + 1];
        } else if (flag == 0) {
            if (index >= data.length) return "";
            str = data[index];
        }

        str = str.substring(2); //截掉"201701"的"20"
        return str;
    }
}
