package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;
import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Company implements Infermation {
    private int id; // company id

    private String companyName;

    private String companyCode;

    private ArrayList<Project> child;

    private ArrayList<String> months;

    private ArrayList<Data4FundsPerMonth> fundsPerMonth; //每月上缴资金相关数据

    private String descUnfinished; //未完成情况说明













    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<Project> getChild() {
        return child;
    }

    public void setChild(ArrayList<Project> child) {
        this.child = child;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

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

    public String getDescUnfinished() {
        return descUnfinished;
    }

    public void setDescUnfinished(String descUnfinished) {
        this.descUnfinished = descUnfinished;
    }
}
