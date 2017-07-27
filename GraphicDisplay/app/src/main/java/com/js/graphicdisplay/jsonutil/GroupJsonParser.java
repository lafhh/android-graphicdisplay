package com.js.graphicdisplay.jsonutil;

import android.util.Log;
import com.js.graphicdisplay.data.FundsData;
import com.js.graphicdisplay.data.Group;
import com.js.graphicdisplay.data.Tuple2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/26.
 */

public class GroupJsonParser {
    private static final String TAG = "GroupJsonParser";

    public static ArrayList<Group> chartFromJson(String json, ArrayList<Group> groups) {
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
     * @return 0:如果没有任何数据;
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
            chartFromJson(jsonString, groups);


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
}
