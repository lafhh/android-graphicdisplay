package com.js.graphicdisplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.GridMenuAdapter;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.data.Tuple3;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/31.
 */

public class MenuActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private GridView gridMenu;
    private ArrayList<Tuple3<Integer, Integer, Class<?>>> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        gridMenu = (GridView) findViewById(R.id.grid_menu);
        gridMenu.setAdapter(new GridMenuAdapter(this, setData()));
        gridMenu.setOnItemClickListener(this);
    }

    private ArrayList<Tuple3<Integer, Integer, Class<?>>> setData() {
        Tuple3<Integer, Integer, Class<?>> t1 = new Tuple3<Integer, Integer, Class<?>>(
                R.drawable.ic_equalizer_black_48dp,
                R.string.label_funds,
                GraphicActivity.class);
        Tuple3<Integer, Integer, Class<?>> t2 = new Tuple3<Integer, Integer, Class<?>>(
                R.drawable.ic_grid_on_black_48dp,
                R.string.label_reserve,
                ReserveGraphicActivity.class);
        Tuple3<Integer, Integer, Class<?>> t3 = new Tuple3<Integer, Integer, Class<?>>(
                R.drawable.ic_rate_review_black_48dp,
                R.string.label_rer,
                RERGraphicActivity.class);
        Tuple3<Integer, Integer, Class<?>> t4 = new Tuple3<Integer, Integer, Class<?>>(
                R.drawable.ic_show_chart_black_48dp,
                R.string.label_rm,
                RMGraphicActivity.class);
        Tuple3<Integer, Integer, Class<?>> t5 = new Tuple3<Integer, Integer, Class<?>>(
                R.drawable.ic_tune_black_48dp,
                R.string.label_sales,
                SalesGraphicActivity.class);
        list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tuple3<Integer, Integer, Class<?>> t3 = list.get(position);

        Intent intent = new Intent(this, t3._3);
        startActivity(intent);
    }
}

