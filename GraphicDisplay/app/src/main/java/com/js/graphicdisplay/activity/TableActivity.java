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
import com.js.graphicdisplay.data.Group;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.impl.SortHeaderListener;
import com.js.graphicdisplay.util.SortState;
import com.js.graphicdisplay.util.SortStateViewProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by apple on 2017/7/13.
 */

public class TableActivity extends Activity {
    private final String TAG = "TableActivity";
    private TableFixHeaders table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        table = (TableFixHeaders) findViewById(R.id.table);
        table.setAdapter(new MyAdapter(this));

    }

    /********* adapter ********************************************************************/
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
//                "完成说明"
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
//                160
        };

        private ArrayList<Group> list;
        private Context context;
        private final float density;
        private LayoutInflater inflater;

        public static final int SORT_STATE = 0;
        private final SparseArray<ImageView> sortViews = new SparseArray<>();
        private SortHeaderListener listener;

        public MyAdapter(Context context) {
            list = initData();
            this.context = context;
            density = context.getResources().getDisplayMetrics().density;
            inflater = LayoutInflater.from(context);
            listener = new SortHeaderListener(sortViews);
            Log.d(TAG, "density: " + density);
        }

        @Override
        public int getRowCount() {
            int size = 0;
            for (int i = 0; i < list.size(); i++) {
                size += list.get(i).getMonths().size();
            }
            return size;
        }

        @Override
        public int getColumnCount() {
            return titles.length - 1;
        }

        @Override
        public View getView(int row, int column, View convertView, ViewGroup parent) {
            final View view;
            switch (getItemViewType(row, column)) {
                case 0:
                    view = getFirstHeader(column, convertView, parent);
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
                default:
                    throw new RuntimeException("wtf?");
            }
            return view;
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
            else height = 32;
            return Math.round(height * density);
        }

        @Override
        public int getItemViewType(int row, int column) {
            final int itemViewType;
            if (row == -1) {
                if (column == -1) itemViewType = 0;
                else itemViewType = 1;
            } else {
                if (column == -1) itemViewType = 2;
                else itemViewType = 3;
            }
            return itemViewType;
        }

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        private View getFirstHeader(int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table_header_first, parent, false);
                convertView.setOnClickListener(listener);

                ImageView sortView = (ImageView) convertView.findViewById(R.id.img_sort);
                sortView.setImageResource(SortStateViewProvider.getSortStateViewResource(SortState.SORTED_ASC));
                sortViews.put(column, sortView);
                sortView.setTag(R.id.sort_state, SortState.SORTED_ASC);
            }
            convertView.setTag(R.id.header_column, column);
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_header_first);
            txtView.setText(titles[0]);
            return convertView;
        }

        private View getHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table_header, parent, false);
                convertView.setOnClickListener(listener);

                ImageView sortView = (ImageView) convertView.findViewById(R.id.img_sort);
                sortView.setTag(R.id.sort_state, SortState.SORTABLE);
                TextView txtView = (TextView) convertView.findViewById(R.id.txt_header);

                convertView.setTag(R.id.header_child,
                        new ViewHolder()
                                .setImgView(sortView)
                                .setTxtView(txtView));
            }
            convertView.setTag(R.id.header_column, column);
            ViewHolder holder = (ViewHolder) convertView.getTag(R.id.header_child);
            holder.getTxtView().setText(titles[column + 1]);
            return convertView;
        }

        private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table_first, parent, false);
            }
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_first);
            int index = (int) getRow(row)._1;
            txtView.setText(list.get(index).getName());
            return convertView;
        }

        private View getBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table, parent, false);
            }

            TextView txtView = (TextView) convertView.findViewById(R.id.txt_table);
            int index = (int) getRow(row)._1;
            row = (int) getRow(row)._2;

            if (column == 0) {
                txtView.setText(list.get(index).getMonths().get(row));
            } else {
                txtView.setText(list.get(index).getFundsPerMonth().get(row).data[column - 1]);
            }

            return convertView;
        }

        private Tuple2 getRow(int row) {
            int index = 0;

            while (row >= 0) {
                row -= list.get(index).getMonths().size();
                index++;
            }
            index--;

            Tuple2 t2 = new Tuple2(index, row + list.get(index).getMonths().size());
            return t2;
        }

        private class ViewHolder {
            private TextView txtView;

            public TextView getTxtView() {
                return txtView;
            }

            public ViewHolder setTxtView(TextView txtView) {
                this.txtView = txtView;
                return this;
            }

            public ImageView getImgView() {
                return imgView;
            }

            public ViewHolder setImgView(ImageView imgView) {
                this.imgView = imgView;
                return this;
            }

            private ImageView imgView;
        }
    }

    /********* adapter ********************************************************************/

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
