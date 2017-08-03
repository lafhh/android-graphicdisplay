package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Group implements Infermation {

    private static final String TAG = "Group";

    /**
     * 集团id
     */
    private int id; //group id

    /**
     * 集团名称
     */
    private String groupName;

    /**
     * 集团code
     */
    private String groupCode;

    /**
     * 集团上缴资金数据 (用于图)
     */
    private FundsData fundsData;

    /**
     * 集团土地存储数据 (用于图)
     */
    private ArrayList<ReserveData> reserveData;

    /**
     * 集团回款达成率数据 (用于图)
     */
    private ArrayList<ReturnedMoneyData> rmData;

    /**
     * 集团销售日数据 (用于图)
     */
    private ArrayList<SalesData> salesData;

    /**
     * 不动产出租数据 (用于图)
     */
    private ArrayList<RERData> rerData;

    /**
     * 集团所有相关数据(用于表格)
     */
    private HashMap<String, Object> data;

    private Object tag = null;

    private int keyColor;










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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public FundsData getFundsData() {
        return fundsData;
    }

    public void setFundsData(FundsData fundsData) {
        this.fundsData = fundsData;
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

//    public HashMap<String, ArrayList<Tuple3>> getMaps() {
//        return maps;
//    }
//
//    public void setMaps(HashMap<String, ArrayList<Tuple3>> maps) {
//        this.maps = maps;
//    }


    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public ArrayList<ReserveData> getReserveData() {
        return reserveData;
    }

    public void setReserveData(ArrayList<ReserveData> reserveData) {
        this.reserveData = reserveData;
    }

    public ArrayList<ReturnedMoneyData> getRmData() {
        return rmData;
    }

    public void setRmData(ArrayList<ReturnedMoneyData> rmData) {
        this.rmData = rmData;
    }

    public ArrayList<SalesData> getSalesData() {
        return salesData;
    }

    public void setSalesData(ArrayList<SalesData> salesData) {
        this.salesData = salesData;
    }

    public ArrayList<RERData> getRerData() {
        return rerData;
    }

    public void setRerData(ArrayList<RERData> rerData) {
        this.rerData = rerData;
    }
}
