package com.js.graphicdisplay.data;

import com.js.graphicdisplay.util.SortState;

import static com.js.graphicdisplay.util.SortState.SORTABLE;

/**
 * Created by laf on 17-7-20.
 */

public class FundsTableHeaderTitle {

    public final String[] titles = {
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

    public FundsTableHeaderTitle() {
    }
}
