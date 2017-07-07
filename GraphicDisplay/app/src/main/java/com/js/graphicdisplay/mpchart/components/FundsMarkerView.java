package com.js.graphicdisplay.mpchart.components;

import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.js.graphicdisplay.R;

/**
 * Created by apple on 2017/7/6.
 */

public class FundsMarkerView extends MarkerView {


    public FundsMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

//        tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true))
        BarEntry entry = (BarEntry) e;
        float[] yVals = entry.getYVals();

        TextView tvIndicatrix = (TextView) findViewById(R.id.tv_funds_indicatrix);
        TextView tvCompletion = (TextView) findViewById(R.id.tv_funds_completion);
        TextView tvBalance = (TextView) findViewById(R.id.tv_funds_balance);
        tvIndicatrix.setText(String.format(
                getResources().getString(R.string.funds_indicatrix_permonth),
                String.valueOf(entry.getY()))
        );
        tvCompletion.setText(String.format(
                getResources().getString(R.string.funds_completion_permonth),
                String.valueOf(yVals[0])
        ));
        tvBalance.setText(String.format(
                getResources().getString(R.string.funds_balance_permonth),
                String.valueOf(yVals[1])
        ));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
