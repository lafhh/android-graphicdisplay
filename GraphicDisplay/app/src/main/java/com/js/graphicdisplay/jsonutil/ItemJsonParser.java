package com.js.graphicdisplay.jsonutil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/8/1.
 * 项目相关数据解析
 */

public class ItemJsonParser {

    /**
     * 销售日数据的解析，用于表
     *
     * @param json
     * @param map
     * @return
     */
    public static int salesJson2HashMap(String json, HashMap<String, Object> map) {
        int length;
        try {
            JSONArray rows = new JSONArray(json);
            if (rows.length() == 0) return 0;

            length = rows.length();
            String[] titles = (String[]) map.get("title");
            titles[0] = "项目";
            ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) map.get("data");
            String itemName = rows.getJSONObject(0).getString("itemName");
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> l = data.get(i);
                l.set(0, itemName);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return length;
    }
}
