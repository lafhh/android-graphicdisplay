package com.js.graphicdisplay.util;

import com.js.graphicdisplay.R;

/**
 * Created by apple on 2017/7/19.
 */

public final class SortStateViewProvider {

    private static int NO_IMAGE = -1;

    private SortStateViewProvider() {
        // no instance
    }

    public static int getSortStateViewResource(final int state) {
        switch (state) {
            case 0:
                return R.mipmap.ic_dark_sortable; //没有排序状态
            case 1:
                return R.mipmap.ic_dark_sorted_asc; //升序
            case 2:
                return R.mipmap.ic_dark_sorted_desc; //降序
            default:
                return NO_IMAGE;
        }
    }
}
