package com.js.graphicdisplay.util;

/**
 * Created by js_gg on 2017/6/14.
 */

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
