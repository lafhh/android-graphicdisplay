package com.js.graphicdisplay.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.data.Data4FundsPerMonth;
import com.js.graphicdisplay.data.FundsTableData;
import com.js.graphicdisplay.data.Group;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.impl.SortHeaderListener;
import com.js.graphicdisplay.util.SortState;
import com.js.graphicdisplay.util.SortStateViewProvider;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by apple on 2017/7/13.
 */

public class TableActivity extends Activity {
    private final String TAG = "TableActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

    }

    public ArrayList<Group> initData() {
        ArrayList<Group> list = new ArrayList<>();
        Group g1 = new Group();
        g1.setGroupName("安徽集团");
        g1.setDescUnfinished("未完成未完成未完成未完成未完成");
        g1.setMonths(new ArrayList<String>());
        g1.setFundsPerMonth(new ArrayList<Data4FundsPerMonth>());
        for (int i = 0; i < 30; i++) {
            g1.getMonths().add("20170" + (i + 1));
            Data4FundsPerMonth data = new Data4FundsPerMonth(700 + i, 1010 + i, 10 + i, 1010 + i, 700 + i, 10 + i);
            data.setIndicatrixPerMonth(new BigDecimal(700 + i));
            data.setCompletionPerMonth(new BigDecimal(1010 + i));
            data.setRateCompletedPerMonth(new BigDecimal(10 + i));
            data.setIndicatrixCumulated(new BigDecimal(1010 + i));
            data.setCompletionCumulated(new BigDecimal(700 + i));
            data.setRateCompletedCumulated(new BigDecimal(10 + i));
            g1.getFundsPerMonth().add(data);
        }
        list.add(g1);

        Group g2 = new Group();
        g2.setGroupName("狮子集团");
        g1.setDescUnfinished("未完成未完成未完成未完成未完成");
        g2.setMonths(new ArrayList<String>());
        g2.setFundsPerMonth(new ArrayList<Data4FundsPerMonth>());
        for (int i = 0; i < 26; i++) {
            g2.getMonths().add("20170" + (i + 1));
            Data4FundsPerMonth data = new Data4FundsPerMonth(800 + i, 2010 + i, 30 + i, 3010 + i, 800 + i, 10 + i);
            data.setIndicatrixPerMonth(new BigDecimal(800 + i));
            data.setCompletionPerMonth(new BigDecimal(2010 + i));
            data.setRateCompletedPerMonth(new BigDecimal(30 + i));
            data.setIndicatrixCumulated(new BigDecimal(3010 + i));
            data.setCompletionCumulated(new BigDecimal(800 + i));
            data.setRateCompletedCumulated(new BigDecimal(40 + i));
            g2.getFundsPerMonth().add(data);
        }
        list.add(g2);

        Group g3 = new Group();
        g3.setGroupName("上海集团");
        g1.setDescUnfinished("未完成未完成未完成未完成未完成");
        g3.setMonths(new ArrayList<String>());
        g3.setFundsPerMonth(new ArrayList<Data4FundsPerMonth>());
        for (int i = 0; i < 11; i++) {
            g3.getMonths().add("20170" + (i + 1));
            Data4FundsPerMonth data = new Data4FundsPerMonth(700 + i, 1010 + i, 10 + i, 1010 + i, 700 + i, 10 + i);
            data.setIndicatrixPerMonth(new BigDecimal(700 + i));
            data.setCompletionPerMonth(new BigDecimal(1010 + i));
            data.setRateCompletedPerMonth(new BigDecimal(10 + i));
            data.setIndicatrixCumulated(new BigDecimal(1010 + i));
            data.setCompletionCumulated(new BigDecimal(700 + i));
            data.setRateCompletedCumulated(new BigDecimal(10 + i));
            g3.getFundsPerMonth().add(data);
        }
        list.add(g3);

        Group g4 = new Group();
        g4.setGroupName("航程集团");
        g1.setDescUnfinished("未完成未完成未完成未完成未完成");
        g4.setMonths(new ArrayList<String>());
        g4.setFundsPerMonth(new ArrayList<Data4FundsPerMonth>());
        for (int i = 0; i < 10; i++) {
            g4.getMonths().add("20170" + (i + 1));
            Data4FundsPerMonth data = new Data4FundsPerMonth(800 + i, 9010 + i, 10 + i, 4010 + i, 800 + i, 20 + i);
            data.setIndicatrixPerMonth(new BigDecimal(800 + i));
            data.setCompletionPerMonth(new BigDecimal(9010 + i));
            data.setRateCompletedPerMonth(new BigDecimal(10 + i));
            data.setIndicatrixCumulated(new BigDecimal(4010 + i));
            data.setCompletionCumulated(new BigDecimal(800 + i));
            data.setRateCompletedCumulated(new BigDecimal(20 + i));
            g4.getFundsPerMonth().add(data);
        }
        list.add(g4);

        return list;
    }


}
