package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Group implements Infermation {

    private static final String TAG = "Group";

    private int id; //group id

    private String groupName;

    private String groupCode;

//    private ArrayList<Company> child;

    private FundsData fundsData;

    private Object tag = null;

    private int keyColor;












    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public FundsData getFundsData() {
        return fundsData;
    }

    public void setFundsData(FundsData fundsData) {
        this.fundsData = fundsData;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public int getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(int keyColor) {
        this.keyColor = keyColor;
    }
}
