package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Company implements Infermation {
    private int id;

    private String companyName;

    private ArrayList<Project> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<Project> getChild() {
        return child;
    }

    public void setChild(ArrayList<Project> child) {
        this.child = child;
    }
}
