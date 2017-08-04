package com.js.graphicdisplay.mpchart.components;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.js.graphicdisplay.R;

/**
 * Created by laf on 17-8-4.
 */

public class ReserveMarkerView extends MarkerView {

    public ReserveMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

//        tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true))
        BarEntry entry = (BarEntry) e;

        TextView tvReserve = (TextView) findViewById(R.id.tv_reverse);
        tvReserve.setText(String.format(
                getResources().getString(R.string.reserve_building_area),
                "储备建筑面积",
                String.valueOf(entry.getY()))
        );
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
