package com.js.graphicdisplay.jsonutil;

import android.util.Log;
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
     *
     * @param json
     * @param groups
     * @return 返回json当中保存总记录数的total字段的值；0:如果没有返回任何数据;
     */
    public static int tableFromJson(String json, ArrayList<Group> groups) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String jsonString = rows.toString();
            Log.d(TAG, "tableFromJson() json = " + json);
            parseJsonArray(jsonString, groups);

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

    public static int fundsJson2HashMap(String json, HashMap<String, Object> map) {
        int totalRows;

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray rows = obj.getJSONArray("rows");
            if (rows.length() == 0) return 0;

            totalRows = obj.getInt("total");
            String[] titles = { "集团", "年月", "当月指标(万)", "当月完成数(万)", "指标达成率", "累计指标(万)", "累计完成数(万)", "累计达成率", };
            int[] width = { 120, 80, 110, 140, 110, 110, 140, 110, };
            int[] sortState = { 1, 0, 0, 0, 0, 0, 0, 0 };
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
            String[] titles = { "集团", "年月", "亩数(亩)", "总价(万)", "已付款(万)", "可建总面积", "储备建筑面积", };
            int[] width = { 120, 80, 110, 140, 110, 110, 140, };
            int[] sortState = { 1, 0, 0, 0, 0, 0, 0, 0 };
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
                list.add(String.valueOf(6)); // message.what 处理获取集团下公司数据的逻辑
                list.add(String.valueOf(o.getInt("orgId"))); //如果有下一级，将id存入数组最后一个元素
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
