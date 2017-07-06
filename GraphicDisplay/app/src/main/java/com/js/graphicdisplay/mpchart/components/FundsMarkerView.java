package com.js.graphicdisplay.mpchart.components;

import android.content.Context;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by apple on 2017/7/6.
 */

public class FundsMarkerView extends MarkerView {

    public FundsMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
    }


}
