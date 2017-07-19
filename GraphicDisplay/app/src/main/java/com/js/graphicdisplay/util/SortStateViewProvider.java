package com.js.graphicdisplay.util;

import com.js.graphicdisplay.R;

/**
 * Created by apple on 2017/7/19.
 */

public final class SortStateViewProvider {

    private static int NO_IMAGE = 0;

    private SortStateViewProvider() {
        // no instance
    }

    public static int getSortStateViewResource(final SortState state) {
        switch (state) {
            case SORTABLE:
                return R.mipmap.ic_dark_sortable;
            case SORTED_ASC:
                return R.mipmap.ic_dark_sorted_asc;
            case SORTED_DESC:
                return R.mipmap.ic_dark_sorted_desc;
            default:
                return NO_IMAGE;
        }
    }
}
