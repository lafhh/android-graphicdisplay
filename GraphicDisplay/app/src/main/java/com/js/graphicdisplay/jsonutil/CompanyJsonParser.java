package com.js.graphicdisplay.jsonutil;

import android.util.Log;

import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.FundsData;
import com.js.graphicdisplay.data.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/26.
 */

public class CompanyJsonParser {
    private static final String TAG = "CompanyJsonParser";

    /**
     *
     * @param json
     * @param companies
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int tableFromJson(String json, ArrayList<Company> companies) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String jsonString = rows.toString();
            Log.d(TAG, "tableFromJson() json = " + json);
            parseJsonArray(jsonString, companies);

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    public static ArrayList<Company> parseJsonArray(String json, ArrayList<Company> companies) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray.length() == 0) return null;

            String preCompCode = "";
            Company company = null;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String compCode = jsonObject.getString("compCode");

                if (!compCode.equals(preCompCode)) {
                    preCompCode = compCode;
                    company = new Company();
                    companies.add(company);
                }

                parseCompanyInfo(jsonObject, company);
            }

//            Log.d("laf", "end"); //检查数据结构
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return companies;
    }

    private static void parseCompanyInfo(JSONObject jCompany, Company company) throws JSONException {
        if (company == null) return;

        int compId = jCompany.get("compId") instanceof String ? -1 : jCompany.getInt("compId");
        String compCode = jCompany.getString("compCode");
        String compName = jCompany.getString("compName");

        if (compId != -1) company.setId(compId);
        company.setCompanyCode(compCode);
        company.setCompanyName(compName);

        parseFundsData(company, jCompany);
    }

    private static void parseFundsData(Company company, JSONObject jGroup) throws JSONException {
        FundsData fundsData = company.getFundsData();
        if (fundsData == null) {
            fundsData = new FundsData();
            company.setFundsData(fundsData);
        }
        FundsDataJsonParser.FundsDataFromJson(jGroup, fundsData);
    }
}
