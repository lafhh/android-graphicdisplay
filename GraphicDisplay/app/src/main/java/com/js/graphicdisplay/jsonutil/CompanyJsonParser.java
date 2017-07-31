package com.js.graphicdisplay.jsonutil;

import android.util.Log;

import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.FundsData;
import com.js.graphicdisplay.data.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static int fundsJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = { "区域公司", "年月", "当月指标(万)", "当月完成数(万)", "指标达成率", "累计指标(万)", "累计完成数(万)", "累计达成率", };
            int[] width = { 160, 80, 110, 140, 110, 110, 140, 110, };
            int[] sortState = { 1, 0, 0, 0, 0, 0, 0, 0 };
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> list = new ArrayList<>();
                list.add(o.getString("compName"));
                list.add(o.getString("ym"));
                list.add(o.getString("monthIndex"));
                list.add(o.getString("monthFulfilQuantity"));
                list.add(o.getString("monthAch"));
                list.add(o.getString("cumulativeIndex"));
                list.add(o.getString("cumulativeFulfilQuantity"));
                list.add(o.getString("cumulativeAch"));
                list.add(o.getString("incompleteDescription"));
                list.add(String.valueOf(-1)); //-1标记没有下一级，存入数组最后一个元素
                data.add(list);
            }
            map.put("title", titles);
            map.put("width", width);
            map.put("sort", sortState);
            map.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    public static int reserveJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = { "公司", "项目名称", "年月", "亩数(亩)", "总价(万)", "已付款(万)", "可建总面积(m²)", "储备建筑面积(m²)", };
            int[] width = { 160, 130, 110, 140, 110, 110, 140, 140 };
            int[] sortState = { 1, 0, 0, 0, 0, 0, 0, 0 };
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> list = new ArrayList<>();
                list.add(o.getString("orgName"));
                list.add(o.getString("itemName"));
                list.add(o.getString("ym"));
                list.add(o.getString("acre"));
                list.add(o.getString("totalPrice"));
                list.add(o.getString("paid"));
                list.add(o.getString("buildableArea"));
                list.add(o.getString("reserveBuildingArea"));
                list.add(String.valueOf(-1)); //-1标记没有下一级，存入数组最后一个元素
                data.add(list);
            }
            map.put("title", titles);
            map.put("width", width);
            map.put("sort", sortState);
            map.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }
}
