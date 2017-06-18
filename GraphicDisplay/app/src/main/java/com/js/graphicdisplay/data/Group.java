package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Group implements Infermation {
    private int id;

    private String groupName;

    private ArrayList<Company> child;

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

    public ArrayList<Company> getChild() {
        return child;
    }

    public void setChild(ArrayList<Company> child) {
        this.child = child;
    }
}
