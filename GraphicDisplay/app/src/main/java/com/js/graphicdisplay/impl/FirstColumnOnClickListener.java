package com.js.graphicdisplay.impl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.NameValuePair;
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
    private TableFixHeaders table;

//    public FirstColumnOnClickListener(Context context) {
//        this.context = context;
//        table =(TableFixHeaders) ((Activity) context).findViewById(R.id.table);
//    }

    @Override
    public void onClick(View v) {
        int orgId = (int) v.getTag(R.id.item_value);
Log.d(TAG, "orgid = " + orgId);
        ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
        list.add(new NameValuePair<>(NetUtil.KEY_ORGID, String.valueOf(orgId)));
        list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(10)));
        list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(0)));
        list.add(new NameValuePair<>(NetUtil.KEY_ORDER, "asc"));
        list.add(new NameValuePair<>(NetUtil.KEY_SORT, NetUtil.COMPNAME));
        HttpManager.doPost(NetUtil.URL_FUNDSTURNEDOVER_COMP_TABLE,
                list,
                Request.ContentType.KVP,
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();

                        if (response.isSuccessful()) {
//                            CompanyJsonParser.tableFromJson(body, );

                        } else {
                            throw new IOException("Unexpected code " + response);
                        }

                    }
                });
    }
}
