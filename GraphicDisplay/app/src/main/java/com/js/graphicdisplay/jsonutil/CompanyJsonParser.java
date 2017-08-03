package com.js.graphicdisplay.jsonutil;

import android.util.Log;
import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.FundsData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/7/26.
 * 公司相关数据解析
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
            int[] width = { 140, 80, 110, 140, 110, 110, 140, 110, };
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
            map.put("state", sortState);
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
            map.put("state", sortState);
            map.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    /**
     * 回款达成率数据的解析，用于表
     * @param json
     * @param map
     * @return
     */
    public static int returnedMoneyJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = {"区域公司", "项目", "年月", "本月计划回款额(万元)", "本月实际回款额(万元)", "本月计划达成率",
                    "累计计划回款额(万元)", "累计实际回款额(万元)", "累计计划达成率",};
            int[] widths = {120, 120, 80, 140, 140, 140, 140, 140, 140,};
            int[] sortState ={1, 0, 0, 0, 0, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> list = new ArrayList<>();
                list.add(o.getString("orgName"));
                list.add(o.getString("itemName"));
                list.add(o.getString("ym"));
                list.add(o.getString("monthIncomePlan"));
                list.add(o.getString("monthIncomeReal"));
                list.add(o.getString("monthIncomeAch"));
                list.add(o.getString("cumulativeIncomePlan"));
                list.add(o.getString("cumulativeIncomeReal"));
                list.add(o.getString("cumulativeIncomeAch"));
                list.add(String.valueOf(-1)); //-1标记没有下一级，存入数组最后一个元素
                data.add(list);
            }
            map.put("title", titles);
            map.put("width", widths);
            map.put("state", sortState);
            map.put("data", data);

        } catch(JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    /**
     * 销售日数据的解析，用于表
     * @param json
     * @param map
     * @return
     */
    public static int salesJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = {"区域公司", "日期", "签约套数(套)", "签约金额(万元)", "回款金额(万元)"};
            int[] widths = {140, 80, 130, 130, 130};
            int[] sortState = {1, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> data = new ArrayList<>();
                data.add(o.getString("compName"));
                data.add(o.getString("ymd"));
                data.add(o.getString("dayContractedQuantity"));
                data.add(o.getString("dayContractedAmount"));
                data.add(o.getString("dayIncome"));
                data.add(String.valueOf(8)); //message.what 处理获取集团下公司数据的标记
                data.add(String.valueOf(o.getInt("compId"))); //如果有下一级，将id存入数组最后一个元素
                list.add(data);
            }
            map.put("title", titles);
            map.put("width", widths);
            map.put("state", sortState);
            map.put("data", list);

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    /**
     * 各物业不动产出租情况数据的解析，用于表
     * @param json
     * @param map
     * @return
     */
    public static int rerJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = {"物业形态", "业态", "年月", "总建筑面积(㎡)", "可出租面积(㎡)", "本月出租面积(㎡)", "累计已出租面积(㎡)", "出租率(%)", "合同总额(元)"};
            int[] widths = {110, 120, 80, 130, 130, 130, 130, 130, 130};
            int[] sortState = {1, 0, 0, 0, 0, 0, 0, 0, 0};

            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> data = new ArrayList<>();

                int propertyType = Integer.parseInt(o.getString("propertyType"));
                if (propertyType == 0) data.add("自有物业");
                else if (propertyType == 1) data.add("酒店");
                data.add(o.getString("bizFmtName"));
                data.add(o.getString("ym"));
                data.add(o.getString("totalConstructionArea"));
                data.add(o.getString("leasableArea"));
                data.add(o.getString("monthlyRentalArea"));
                data.add(o.getString("leasedArea"));
                data.add(o.getString("lettingRate"));
                data.add(o.getString("totalContract"));
                data.add(String.valueOf(-1)); //-1标记没有下一级，存入数组最后一个元素
                list.add(data);
            }
            map.put("title", titles);
            map.put("width", widths);
            map.put("state", sortState);
            map.put("data", list);

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

}
