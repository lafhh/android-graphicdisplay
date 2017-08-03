package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

import java.util.HashMap;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Item implements Infermation {
    private int id; //project id

    private String itemName;

    private String itemCode;

    private HashMap<String, Object> data;













    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
