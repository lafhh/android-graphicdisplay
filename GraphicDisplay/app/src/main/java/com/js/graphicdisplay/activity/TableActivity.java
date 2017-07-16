package com.js.graphicdisplay.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import com.js.graphicdisplay.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by apple on 2017/7/13.
 */

public class TableActivity extends Activity {
    private final String TAG = "TableActivity";
    String[] months = {"201701", "201702", "201703", "201704", "201705", "201706", "201707",};
    private TableFixHeaders table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        table = (TableFixHeaders) findViewById(R.id.table);

    }

    public class MyAdapter extends BaseTableAdapter {
        private String[] titles = {
                "集团",
                "年月",
                "当月指标(万)",
                "当月完成数(万)",
                "指标达成率",
                "累计指标(万)",
                "累计完成数(万)",
                "累计达成率",
        };

        private final int[] widths = {
                120,
                80,
                110,
                140,
                110,
                110,
                140,
                110,
        };

        private ArrayList<Group> list;
        private Context context;
        private final float density;
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            list = initData();
            this.context = context;
            density = context.getResources().getDisplayMetrics().density;
            inflater = LayoutInflater.from(context);
            Log.d(TAG, "density: " + density);
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return 8 - 1;
        }

        @Override
        public View getView(int row, int column, View convertView, ViewGroup parent) {
            final View view;
            switch(getItemViewType(row, column)) {
                case 0:
                    view = getFirstHeader(convertView, parent);
                    break;
                case 1:
                    view = getHeader(row, column, convertView, parent);
                    break;
                case 2:
                    view = getFirstBody(row, column, convertView, parent);
                    break;
                case 3:
                    view = getBody(row, column, convertView, parent);
                    break;
            }
            return null;
        }

        @Override
        public int getWidth(int column) {
            Log.d(TAG, "getWidth()====column: " + column);
            int width = Math.round(widths[column + 1] * density);
            return width;
        }

        @Override
        public int getHeight(int row) {
            Log.d(TAG, "getHeight()====row: " + row);
            int height;
            if (row == -1) height = 44;
            else           height = 32;
            return Math.round(height * density);
        }

        @Override
        public int getItemViewType(int row, int column) {
            final int itemViewType;
            if (row == -1) {
                if (column == -1) itemViewType = 0;
                else              itemViewType = 1;
            } else {
                if (column == -1) itemViewType = 2;
                else              itemViewType = 3;
            }
            return itemViewType;
        }

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        private View getFirstHeader(View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table_header_first, parent, false);
            }
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_header_first);
            txtView.setText(titles[0]);
            return convertView;
        }

        private View getHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table_header, parent, false);
            }
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_header);
            txtView.setText(titles[column + 1]);
            return convertView;
        }

        private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table_first, parent, false);
            }
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_first);
            txtView.setText(list.get(row + 1).name);
            return convertView;
        }

        private View getBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table, parent, false);
            }
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_first);

        }
    }

    private class Group {
        String name;
        String month;
        int c1;
        int c2;
        int c3;
        int a1;
        int a2;
        int a3;
    }

    public ArrayList<Group> initData() {
        ArrayList<Group> list = new ArrayList<>();
        for (int i = 0; i < months.length; i++) {
            Group g = new Group();
            g.name = "安徽集团";
            g.month = months[i];
            g.c1 = 1000;
            g.c2 = 800;
            g.c3 = 30;
            g.a1 = 2000;
            g.a2 = 1500;
            g.a3 = 80;
            list.add(g);
        }
        for (int i = 0; i < months.length; i++) {
            Group g = new Group();
            g.name = "杭城集团";
            g.month = months[i];
            g.c1 = 1000;
            g.c2 = 800;
            g.c3 = 30;
            g.a1 = 2000;
            g.a2 = 1500;
            g.a3 = 80;
            list.add(g);
        }
        for (int i = 0; i < months.length; i++) {
            Group g = new Group();
            g.name = "申城集团";
            g.month = months[i];
            g.c1 = 1000;
            g.c2 = 800;
            g.c3 = 30;
            g.a1 = 2000;
            g.a2 = 1500;
            g.a3 = 80;
            list.add(g);
        }
        for (int i = 0; i < months.length; i++) {
            Group g = new Group();
            g.name = "香港集团";
            g.month = months[i];
            g.c1 = 1000;
            g.c2 = 800;
            g.c3 = 30;
            g.a1 = 2000;
            g.a2 = 1500;
            g.a3 = 80;
            list.add(g);
        }
        return list;
    }
}
