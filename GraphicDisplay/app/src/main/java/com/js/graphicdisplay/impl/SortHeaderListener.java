package com.js.graphicdisplay.impl;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.TableActivity;
import com.js.graphicdisplay.util.SortState;

import static com.js.graphicdisplay.util.SortState.SORTED_ASC;

/**
 * Created by laf on 17-7-19.
 */

public class SortHeaderListener implements View.OnClickListener {

    private final SparseArray<ImageView> sortViews;
    private int columnIndex;

    public SortHeaderListener(SparseArray<ImageView> views) {
        sortViews = views;
    }

    @Override
    public void onClick(View v) {

        for (int i = 0; i < sortViews.size(); i++) {
            int key = sortViews.keyAt(i);
            if (key == columnIndex) {
                ImageView sortView = sortViews.get(key);
                sortView.getTag(TableActivity.MyAdapter.SORT_STATE) == SORTED_ASC ?

            } else {

            }




            if (sortViews.get(columnIndex) != null) {
                sortViews.get(columnIndex).getTag()
            } else {
                sortViews.keyAt(i);
            }
        }
        //自己把自己记住

        sortViews.put(column, v);
    }
}
