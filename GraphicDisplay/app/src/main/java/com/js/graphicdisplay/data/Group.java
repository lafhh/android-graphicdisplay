package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Group implements Infermation {
    private int id; //group id

    private String groupName;

    private String groupCode;

    private ArrayList<Company> child;

    private ArrayList<String> months;

    private ArrayList<Data4FundsPerMonth> fundsPerMonth; //每月上缴资金相关数据

    private Data4FundsCumulated fundsCumulated; //累计上缴资金相关数据

    private String descUnfinished; //未完成情况说明












    public static Group fromJson(JSONObject jGroup, Group group) throws JSONException {
        if (jGroup == null) return null;

        int groupId = jGroup.getInt("groupId");
        String groupCode = jGroup.getString("groupCode");
        String groupName = jGroup.getString("groupName");
        group.setId(groupId);
        group.setGroupCode(groupCode);
        group.setGroupName(groupName);
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

    public Data4FundsCumulated getFundsCumulated() {
        return fundsCumulated;
    }

    public void setFundsCumulated(Data4FundsCumulated fundsCumulated) {
        this.fundsCumulated = fundsCumulated;
    }

    public String getDescUnfinished() {
        return descUnfinished;
    }

    public void setDescUnfinished(String descUnfinished) {
        this.descUnfinished = descUnfinished;
    }
}
