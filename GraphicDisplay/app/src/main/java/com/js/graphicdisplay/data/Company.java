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

//    private ArrayList<Project> child;

    private FundsData fundsData;













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
}
