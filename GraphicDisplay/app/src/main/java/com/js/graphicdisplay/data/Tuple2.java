package com.js.graphicdisplay.data;

import java.io.Serializable;

/**
 * Created by apple on 2017/7/17.
 */

public class Tuple2 <T extends Serializable, K extends Serializable> {

    public final T _1;

    public final K _2;

    public Tuple2(T t, K k) {
        _1 = t;
        _2 = k;
    }
}
