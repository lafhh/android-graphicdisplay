package com.js.graphicdisplay.impl;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.js.graphicdisplay.R;
import com.js.graphicdisplay.data.FundsData;
import com.js.graphicdisplay.data.FundsTableMateData;
import com.js.graphicdisplay.util.SortState;
import com.js.graphicdisplay.util.SortStateViewProvider;

import static com.js.graphicdisplay.util.SortState.SORTABLE;
import static com.js.graphicdisplay.util.SortState.SORTED_ASC;
import static com.js.graphicdisplay.util.SortState.SORTED_DESC;

/**
 * Created by laf on 17-7-19.
 */

public class SortHeaderListener implements View.OnClickListener {

    private final SparseArray<ImageView> sortViews;
    private int[] sortState;

    public SortHeaderListener(SparseArray<ImageView> views, int[] s) {
        sortViews = views;
        sortState = s;
    }

    @Override
    public void onClick(View v) {
        int columnIndex = (int) v.getTag(R.id.header_column);

        for (int i = 0; i < sortViews.size(); i++) {
            int key = sortViews.keyAt(i);
            ImageView view = sortViews.get(key);

            //如果上一次点击列与当前一致，只是升序变为降序/降序变为升序
            if (columnIndex == key) {
                int sort = sortState[columnIndex + 1]; //columnIndex起始为-1
                if (sort == 1) { //升序
                    view.setImageResource(R.mipmap.ic_dark_sorted_desc);
                    sortState[columnIndex + 1] = 2;
                } else if (sort == 2) { //降序
                    view.setImageResource(R.mipmap.ic_dark_sorted_asc);
                    sortState[columnIndex + 1] = 1;
                }
            } else {
                view.setImageResource(R.mipmap.ic_dark_sortable);
                sortState[key + 1] = 0; //没有排序状态
                sortViews.remove(key);
            }
        }
        if (sortViews.get(columnIndex) == null) { //每一列第一次被点击默认是升序
            ImageView view = (ImageView) v.findViewById(R.id.img_sort);
            sortViews.put(columnIndex, view);
            view.setImageResource(R.mipmap.ic_dark_sorted_asc);
            sortState[columnIndex + 1] = 1;
        }

        switch (columnIndex) {
            case -1:

                break;
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }

    }

    public SparseArray<ImageView> getSortViews() {
        return sortViews;
    }

    public int[] getSortState() {
        return sortState;
    }

    public void setSortState(int[] sortState) {
        this.sortState = sortState;
    }
}
