package com.js.graphicdisplay.impl;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.TableActivity;
import com.js.graphicdisplay.data.FundsTableData;
import com.js.graphicdisplay.util.SortState;
import com.js.graphicdisplay.util.SortStateViewProvider;

import javax.xml.parsers.SAXParser;

import static com.js.graphicdisplay.util.SortState.SORTABLE;
import static com.js.graphicdisplay.util.SortState.SORTED_ASC;
import static com.js.graphicdisplay.util.SortState.SORTED_DESC;

/**
 * Created by laf on 17-7-19.
 */

public class SortHeaderListener implements View.OnClickListener {

    private final SparseArray<ImageView> sortViews;
    private final FundsTableData tableData;

    public SortHeaderListener(SparseArray<ImageView> views, FundsTableData tableData) {
        sortViews = views;
        this.tableData = tableData;
    }

    @Override
    public void onClick(View v) {
        int columnIndex = (int) v.getTag(R.id.header_column);

        for (int i = 0; i < sortViews.size(); i++) {
            int key = sortViews.keyAt(i);
            ImageView sortView = sortViews.get(key);

            if (key == columnIndex) {
//                SortState sortState = tableData.getSortState(columnIndex + 1);
                SortState sortState = (SortState) sortView.getTag(R.id.sort_state);
                if (sortState == SORTED_ASC) {
                    sortView.setImageResource(SortStateViewProvider.getSortStateViewResource(SORTED_DESC));
                    sortView.setTag(R.id.sort_state, SORTED_DESC);
                    tableData.setSortState(key + 1, SORTED_DESC);

                } else if (sortState == SORTED_DESC) {
                    sortView.setImageResource(SortStateViewProvider.getSortStateViewResource(SORTED_ASC));
                    sortView.setTag(R.id.sort_state, SORTED_ASC);
                    tableData.setSortState(key + 1, SORTED_ASC);
                }
            } else {
                sortView.setImageResource(SortStateViewProvider.getSortStateViewResource(SORTABLE));
                sortView.setTag(R.id.sort_state, SORTABLE);
                sortViews.remove(key);
                tableData.setSortState(key + 1, SORTABLE);
            }
        }

        if (sortViews.get(columnIndex) == null) {
            ImageView sortView = (ImageView) v.findViewById(R.id.img_sort);
            sortView.setImageResource(SortStateViewProvider.getSortStateViewResource(SORTED_ASC));
            sortView.setTag(R.id.sort_state, SORTED_ASC);
            sortViews.put(columnIndex, sortView);
            tableData.setSortState(columnIndex + 1, SORTED_ASC);
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

    private void resetSortViewState() {

    }
}
