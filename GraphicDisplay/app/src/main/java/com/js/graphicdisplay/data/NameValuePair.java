package com.js.graphicdisplay.data;

import java.io.Serializable;

/**
 * Created by js_gg on 2017/6/15.
 */

public class NameValuePair<K extends Serializable, V extends Serializable> {
    public final K key;
    public final V value;

    public NameValuePair(K k, V v) {
        key = k;
        value = v;
    }
}
