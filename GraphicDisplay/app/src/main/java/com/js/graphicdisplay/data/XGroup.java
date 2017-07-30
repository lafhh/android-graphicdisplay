package com.js.graphicdisplay.data;

import java.util.HashMap;

/**
 * Created by laf on 17-7-30.
 */

public class XGroup {

    private int id = 0;

    private HashMap<String, Object> maps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, Object> maps) {
        this.maps = maps;
    }
}
