package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Group implements Infermation {

    private static final String TAG = "Group";

    private int id; //group id

    private String groupName;

    private String groupCode;

    private ArrayList<Company> child;

    private ArrayList<String> months;

    private ArrayList<Data4FundsPerMonth> fundsPerMonth; //每月上缴资金相关数据

    private String descUnfinished; //未完成情况说明

    private Object tag = null;

    private int keyColor;

    public static Group fromJson(JSONObject jGroup, Group group) throws JSONException {
        if (jGroup == null) return null;

        //先不传id
//        int groupId = -1;
//        Log.d(TAG, jGroup.get("groupId").toString());
//
//        if (jGroup.get("groupId") != null) {
//            Log.d(TAG, "enter");
//            groupId = jGroup.getInt("groupId");
//        }

        String groupCode = jGroup.getString("groupCode");
        String groupName = jGroup.getString("groupName");

        String descUnfinished = "";
        if (jGroup.get("incompleteDescription") != null) {
            descUnfinished = jGroup.getString("incompleteDescription");
        }

//        group.setId(groupId);
        group.setGroupCode(groupCode);
        group.setGroupName(groupName);
        group.setDescUnfinished(descUnfinished);

        ArrayList<String> months = group.getMonths();
        if (months == null) {
            months = new ArrayList<>();
            group.setMonths(months);
        }
        months.add(jGroup.getString("ym"));

        ArrayList<Data4FundsPerMonth> fundsPerMonths = group.getFundsPerMonth();
        if (fundsPerMonths == null) {
            fundsPerMonths = new ArrayList<>();
            group.setFundsPerMonth(fundsPerMonths);
        }
        double monthIndex = jGroup.getDouble("monthIndex");
        double monthfulfilQuantity = jGroup.getDouble("monthFulfilQuantity");
        double monthAch = jGroup.getDouble("monthAch");
        double cumulativeIndex = jGroup.getDouble("cumulativeIndex");
        double cumulativeFulfilQuantity = jGroup.getDouble("cumulativeFulfilQuantity");
        double cumulativeAch = jGroup.getDouble("cumulativeAch");

        Data4FundsPerMonth fundsPerMonth = new Data4FundsPerMonth(
            monthIndex, monthfulfilQuantity, monthAch, cumulativeIndex, cumulativeFulfilQuantity, cumulativeAch
        );
        fundsPerMonth.setIndicatrixPerMonth(
                new BigDecimal(monthIndex));
        fundsPerMonth.setCompletionPerMonth(
                new BigDecimal(monthfulfilQuantity));
        fundsPerMonth.setRateCompletedPerMonth(
                new BigDecimal(monthAch));
        fundsPerMonth.setIndicatrixCumulated(
                new BigDecimal(cumulativeIndex));
        fundsPerMonth.setCompletionCumulated(
                new BigDecimal(cumulativeFulfilQuantity));
        fundsPerMonth.setRateCompletedCumulated(
                new BigDecimal(cumulativeAch));
        fundsPerMonths.add(fundsPerMonth);

        return group;
    }










    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Company> getChild() {
        return child;
    }

    public void setChild(ArrayList<Company> child) {
        this.child = child;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public int getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(int keyColor) {
        this.keyColor = keyColor;
    }
}
