package com.js.graphicdisplay.impl;

import android.content.Context;
import android.view.View;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.data.Tuple2;

/**
 * Created by apple on 2017/7/28.
 */

public class FirstColumnOnClickListener implements View.OnClickListener {
    private static final String TAG = "FirstColumnOnClickListener";

    private Context context;

    public FirstColumnOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        int id =(int) ((Tuple2) v.getTag(R.id.item_value))._2;
        int what = Integer.parseInt((String) v.getTag(R.id.what));
        if (id == -1) return;
        ((BaseActivity) context).sendMessage(what, v.getTag(R.id.item_value));
    }
}
