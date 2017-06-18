package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

/**
 * Created by js_gg on 2017/6/18.
 */

public class Project implements Infermation {
    private int id;

    private String projectName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
