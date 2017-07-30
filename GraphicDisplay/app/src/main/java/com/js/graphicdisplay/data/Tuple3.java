package com.js.graphicdisplay.data;

import java.io.Serializable;

/**
 * Created by laf on 17-7-30.
 */

public class Tuple3<T extends Serializable, K extends Serializable, U extends Serializable> extends Tuple2<T, K> {
    public final U _3;

    public Tuple3(T t, K k, U u) {
        super(t, k);
        _3 = u;
    }
}
