package com.js.graphicdisplay.jsonutil;

import android.util.Log;
import com.js.graphicdisplay.data.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/7/26.
 *
 * 集团相关数据解析
 *
 */

public class GroupJsonParser {
    private static final String TAG = "GroupJsonParser";

    public static ArrayList<Group> parseJsonArray(String json, ArrayList<Group> groups) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray.length() == 0) return null;

            String preGroupCode = "";
            Group group = null;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String groupCode = jsonObject.getString("groupCode");

                if (!groupCode.equals(preGroupCode)) {
                    preGroupCode = groupCode;
                    group = new Group();
                    groups.add(group);
                }

                parseGroupInfo(jsonObject, group);
            }

//            Log.d("laf", "end"); //检查数据结构
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return groups;
    }

    /**
     * 土地存储数据的解析，用于图
     * @param json
     * @param groups
     * @return
     */
    private static ArrayList<Group> parseReserveFromJson(String json, ArrayList<Group> groups) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray.length() == 0) return null;

            int preOrgId = -1;
            Group group = null;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject o = (JSONObject) jsonArray.get(i);
                int orgId = o.getInt("orgId");

                if (orgId != preOrgId) {
                    preOrgId = orgId;
                    group = new Group();
                    groups.add(group);
                }

                String orgName = o.getString("orgName");
                group.setId(orgId);
                group.setGroupName(orgName);

                ArrayList<ReserveData> list = group.getReserveData();
                if (list == null) {
                    list = new ArrayList<>();
                    group.setReserveData(list);
                }
                ReserveData data = new ReserveData();
                data.setDate(o.getString("ym"));
                data.setAcre(Float.valueOf(o.getString("acre")));
                data.setTotalPrice(Float.valueOf(o.getString("totalPrice")));
                data.setPaid(Float.valueOf(o.getString("paid")));
                data.setBuildableArea(Float.valueOf(o.getString("buildableArea")));
                data.setReserveBuildingArea(Float.valueOf(o.getString("reserveBuildingArea")));
                list.add(data);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return groups;
    }

    /**
     * 回款达成率数据的解析，用于图
     * @param json
     * @param groups
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int parseReturnedMoneyFromJson(String json, ArrayList<Group> groups) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");

            int preOrgId = -1;
            Group group = null;
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                int orgId = o.getInt("orgId");

                if (orgId != preOrgId) {
                    group = new Group();
                    groups.add(group);
                    preOrgId = orgId;
                }
                group.setId(orgId);
                group.setGroupName(o.getString("orgName"));

                ArrayList<ReturnedMoneyData> list = group.getRmData();
                if (list == null) {
                    list = new ArrayList<>();
                    group.setRmData(list);
                }
                ReturnedMoneyData data = new ReturnedMoneyData();
                data.setDate(o.getString("ym"));
                data.setMonthIncomePlan(Float.parseFloat(o.getString("monthIncomePlan")));
                data.setMonthIncomeReal(Float.parseFloat(o.getString("monthIncomeReal")));
                data.setMonthIncomeAch(Float.parseFloat(o.getString("monthIncomeAch")));
                data.setCumulativeIncomePlan(Float.parseFloat(o.getString("cumulativeIncomePlan")));
                data.setCumulativeIncomeReal(Float.parseFloat(o.getString("cumulativeIncomeReal")));
                data.setCumulativeIncomeAch(Float.parseFloat(o.getString("cumulativeIncomeAch")));
                list.add(data);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }

        return totalRows;
    }

    /**
     * 销售日数据的解析，用于图
     * @param json
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int parseSalesFromJson(String json, ArrayList<Group> groups) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");

            int preGroupId = -1;
            Group group = null;
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                int groupId = o.getInt("groupId");

                if (preGroupId != groupId) {
                    preGroupId = groupId;
                    group = new Group();
                    groups.add(group);
                }

                group.setId(groupId);
                group.setGroupCode(o.getString("groupCode"));
                group.setGroupName(o.getString("groupName"));

                ArrayList<SalesData> list = group.getSalesData();
                if (list == null) {
                    list = new ArrayList<>();
                    group.setSalesData(list);
                }
                SalesData data = new SalesData();
                data.setDate(o.getString("ymd"));
                data.setDayContractedQuantity(Float.parseFloat(o.getString("dayContractedQuantity")));
                data.setDayContractedAmount(Float.parseFloat(o.getString("dayContractedAmount")));
                data.setDayIncome(Float.parseFloat(o.getString("dayIncome")));
                list.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }

        return totalRows;
    }

    public static int parseRERFromJson(String json, ArrayList<Group> groups) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");

            int preGroupId = -1;
            Group group = null;
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                int groupId = o.getInt("groupId");

                if (preGroupId != groupId) {
                    preGroupId = groupId;
                    group = new Group();
                    groups.add(group);
                }

                group.setId(groupId);
                group.setGroupName(o.getString("groupName"));
                group.setGroupCode(o.getString("groupCode"));

                ArrayList<RERData> list = group.getRerData();
                if (list == null) {
                    list = new ArrayList<>();
                    group.setRerData(list);
                }
                RERData data = new RERData();
                data.setDate(o.getString("ym"));
                data.setLeasableArea(Float.parseFloat(o.getString("leasableArea")));
                data.setLeasedArea(Float.parseFloat(o.getString("leasedArea")));
                data.setLettingRate(Float.parseFloat(o.getString("lettingRate")));
                data.setMonthlyRentalArea(Float.parseFloat(o.getString("monthlyRentalArea")));
                data.setTotalConstructionArea(Float.parseFloat(o.getString("totalConstructionArea")));
                data.setTotalContract(Float.parseFloat(o.getString("totalContract")));
                list.add(data);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    /**
     * @param json
     * @param groups
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int tableFromJson(String json, ArrayList<Group> groups, int flag) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String jsonString = rows.toString();
            Log.d(TAG, "tableFromJson() json = " + json);
            if (flag == 0) parseJsonArray(jsonString, groups); //解析资金上缴数据
            else if (flag == 1) parseReserveFromJson(jsonString, groups); //解析土地存储数据

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return totalRows;
    }

    private static void parseGroupInfo(JSONObject jGroup, Group group) throws JSONException {
        if (group == null) return;

        int groupId = jGroup.get("groupId") instanceof String ? -1 : jGroup.getInt("groupId");
        String groupCode = jGroup.getString("groupCode");
        String groupName = jGroup.getString("groupName");

        if (groupId != -1) group.setId(groupId);
        group.setGroupCode(groupCode);
        group.setGroupName(groupName);

        parseFundsData(group, jGroup);
    }

    private static void parseFundsData(Group group, JSONObject jGroup) throws JSONException {
        FundsData fundsData = group.getFundsData();
        if (fundsData == null) {
            fundsData = new FundsData();
            group.setFundsData(fundsData);
        }
        FundsDataJsonParser.FundsDataFromJson(jGroup, fundsData);
    }

    /**
     * 资金上缴数据的解析 ，用于表
     * @param json
     * @param map
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int fundsJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = {"集团", "年月", "当月指标(万)", "当月完成数(万)", "指标达成率", "累计指标(万)", "累计完成数(万)", "累计达成率",};
            int[] width = {120, 80, 110, 140, 110, 110, 140, 110,};
            int[] sortState = {1, 0, 0, 0, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> list = new ArrayList<>();
                list.add(o.getString("groupName"));
                list.add(o.getString("ym"));
                list.add(o.getString("monthIndex"));
                list.add(o.getString("monthFulfilQuantity"));
                list.add(o.getString("monthAch"));
                list.add(o.getString("cumulativeIndex"));
                list.add(o.getString("cumulativeFulfilQuantity"));
                list.add(o.getString("cumulativeAch"));
                list.add(o.getString("incompleteDescription"));
                list.add(String.valueOf(6)); // message.what 处理获取集团下公司数据的逻辑
                list.add(String.valueOf(o.getInt("groupId"))); //如果有下一级，将id存入数组最后一个元素
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
     * 土地存储数据的解析，用于表
     * @param json
     * @param map
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int reserveJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = {"集团", "年月", "亩数(亩)", "总价(万)", "已付款(万)", "可建总面积(m²)", "储备建筑面积(m²)",};
            int[] width = {120, 80, 110, 140, 110, 110, 140,};
            int[] sortState = {1, 0, 0, 0, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> list = new ArrayList<>();
                list.add(o.getString("orgName"));
                list.add(o.getString("ym"));
                list.add(o.getString("acre"));
                list.add(o.getString("totalPrice"));
                list.add(o.getString("paid"));
                list.add(o.getString("buildableArea"));
                list.add(o.getString("reserveBuildingArea"));
                list.add(String.valueOf(6)); // message.what 处理获取集团下公司数据的标记
                list.add(String.valueOf(o.getInt("orgId"))); //如果有下一级，将id存入数组最后一个元素
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
            String[] titles = {"集团", "年月", "本月计划回款额(万元)", "本月实际回款额(万元)", "本月计划达成率",
                    "累计计划回款额(万元)", "累计实际回款额(万元)", "累计计划达成率",};
            int[] widths = {120, 80, 140, 140, 140, 140, 140, 140,};
            int[] sortState ={1, 0, 0, 0, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> list = new ArrayList<>();
                list.add(o.getString("orgName"));
                list.add(o.getString("ym"));
                list.add(o.getString("monthIncomePlan"));
                list.add(o.getString("monthIncomeReal"));
                list.add(o.getString("monthIncomeAch"));
                list.add(o.getString("cumulativeIncomePlan"));
                list.add(o.getString("cumulativeIncomeReal"));
                list.add(o.getString("cumulativeIncomeAch"));
                list.add(String.valueOf(6)); //message.what 处理获取集团下公司数据的标记
                list.add(String.valueOf(o.getInt("orgId"))); //如果有下一级，将id存入数组最后一个元素
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
            String[] titles = {"集团", "日期", "签约套数(套)", "签约金额(万元)", "回款金额(万元)"};
            int[] widths = {120, 80, 130, 130, 130};
            int[] sortState = {1, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> data = new ArrayList<>();
                data.add(o.getString("groupName"));
                data.add(o.getString("ymd"));
                data.add(o.getString("dayContractedQuantity"));
                data.add(o.getString("dayContractedAmount"));
                data.add(o.getString("dayIncome"));
                data.add(String.valueOf(6)); //message.what 处理获取集团下公司数据的标记
                data.add(String.valueOf(o.getInt("groupId"))); //如果有下一级，将id存入数组最后一个元素
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
     * 不动产出租情况数据的解析，用于表
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
            String[] titles = {"集团", "年月", "总建筑面积(㎡)", "可出租面积(㎡)", "本月出租面积(㎡)", "累计已出租面积(㎡)", "出租率(%)", "合同总额(元)"};
            int[] widths = {120, 80, 130, 130, 130, 130, 80, 130};
            int[] sortState = {1, 0, 0, 0, 0, 0, 0, 0};
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject o = rows.getJSONObject(i);
                ArrayList<String> data = new ArrayList<>();
                data.add(o.getString("groupName"));
                data.add(o.getString("ym"));
                data.add(o.getString("totalConstructionArea"));
                data.add(o.getString("leasableArea"));
                data.add(o.getString("monthlyRentalArea"));
                data.add(o.getString("leasedArea"));
                data.add(o.getString("lettingRate"));
                data.add(o.getString("totalContract"));
                data.add(String.valueOf(6)); //message.what 处理获取集团下公司数据的标记
                data.add(String.valueOf(o.getInt("groupId"))); //如果有下一级，将id存入数组最后一个元素
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
