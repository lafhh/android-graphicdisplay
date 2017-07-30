package com.js.graphicdisplay.impl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.NameValuePair;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.jsonutil.CompanyJsonParser;
import com.js.graphicdisplay.jsonutil.GroupJsonParser;
import com.js.graphicdisplay.net.HttpManager;
import com.js.graphicdisplay.net.NetUtil;
import com.js.graphicdisplay.net.Request;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
