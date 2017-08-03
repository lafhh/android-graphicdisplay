package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Company implements Infermation {
    private int id; // company id

    private String companyName;

    private String companyCode;

    /**
     * 公司上缴资金数据 (用于图)
     */
    private FundsData fundsData;

    /**
     * 公司所有相关数据(用于表格)
     */
    private HashMap<String, Object> data;













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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public FundsData getFundsData() {
        return fundsData;
    }

    public void setFundsData(FundsData fundsData) {
        this.fundsData = fundsData;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
