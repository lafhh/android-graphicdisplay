package com.js.graphicdisplay.data;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/26.
 */

public class FundsData {

    private ArrayList<String> months;

    private ArrayList<Data4FundsPerMonth> fundsPerMonth; //每月上缴资金相关数据



    public ArrayList<String> getMonths() {
        return months;
    }

    public void setMonths(ArrayList<String> months) {
        this.months = months;
    }

    public ArrayList<Data4FundsPerMonth> getFundsPerMonth() {
        return fundsPerMonth;
    }

    public void setFundsPerMonth(ArrayList<Data4FundsPerMonth> fundsPerMonth) {
        this.fundsPerMonth = fundsPerMonth;
    }
}
