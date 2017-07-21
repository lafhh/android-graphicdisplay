package com.js.graphicdisplay.data;

import com.js.graphicdisplay.util.SortState;

import static com.js.graphicdisplay.util.SortState.SORTABLE;

/**
 * Created by laf on 17-7-20.
 */

public class FundsTableData {

    private String[] labels = {
            "集团",
            "年月",
            "当月指标(万)",
            "当月完成数(万)",
            "指标达成率",
            "累计指标(万)",
            "累计完成数(万)",
            "累计达成率",
//                "完成说明"
    };

    private SortState[] sortStates = {
            SORTABLE,
            SORTABLE,
            SORTABLE,
            SORTABLE,
            SORTABLE,
            SORTABLE,
            SORTABLE,
            SORTABLE,
    };

    private int[] widths = {
            120,
            80,
            110,
            140,
            110,
            110,
            140,
            110,
//                160
    };


    public int getlabelLength() {
        return labels.length;
    }

    public void setSortState(int index, SortState sortState) {
        sortStates[index] = sortState;
    }

    public SortState getSortState(int index) {
        return sortStates[index];
    }

    public String getLabel(int index) {
        return labels[index];
    }

    public int getWidth(int index) {
        return widths[index];
    }

    public SortState[] getSortStates() {
        return sortStates;
    }
}
