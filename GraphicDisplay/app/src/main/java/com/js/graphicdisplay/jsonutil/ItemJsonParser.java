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
     * 项目的销售日表，通过【某公司下所有的项目信息】接口，拿到第一个项目名称，将上一级公司数据的【公司名称】一列替换掉，
     * 拼出的假数据。
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
            String[] titles = ((String[]) map.get("title")).clone();
            int[] widths = ((int[]) map.get("width")).clone();
            int[] states = ((int[]) map.get("state")).clone();
            ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) ((ArrayList<ArrayList<String>>) map.get("data")).clone();
            ArrayList<ArrayList<String>> temp = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                temp.add((ArrayList<String>) data.get(i).clone());
            }

            data.clear();
            data.addAll(temp);
            map.put("title", titles);
            map.put("width", widths);
            map.put("state", states);
            map.put("data", data);

            titles[0] = "项目";
            String itemName = rows.getJSONObject(0).getString("itemName");
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> l = data.get(i);
                l.set(0, itemName);
                l.set(l.size() - 2, String.valueOf(-1));
                l.remove(l.size() - 1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return length;
    }
}
