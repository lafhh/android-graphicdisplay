package com.js.graphicdisplay.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.data.Tuple3;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/31.
 */

public class GridMenuAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Tuple3<Integer, Integer, Class<?>>> list;

    public GridMenuAdapter(Context context, ArrayList<Tuple3<Integer, Integer, Class<?>>> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridmenu, parent, false);
            GridView gridView = (GridView) parent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { //获取gridview#columnWidth赋给item#height, 以显示为正方形
                ViewGroup.LayoutParams params = convertView.getLayoutParams();
                params.height = gridView.getColumnWidth(); //417
            }
        }

        ImageView imgView = (ImageView) convertView.findViewById(R.id.img_graph);
        TextView txtView = (TextView) convertView.findViewById(R.id.txt_graph);

        Tuple2<Integer, Integer> t2 = list.get(position);
        imgView.setImageResource(t2._1);
        txtView.setText(t2._2);
        return convertView;
    }
}
