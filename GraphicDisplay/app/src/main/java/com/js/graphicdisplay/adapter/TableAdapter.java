package com.js.graphicdisplay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.impl.FirstColumnOnClickListener;
import com.js.graphicdisplay.impl.SortHeaderListener;
import com.js.graphicdisplay.util.SortStateViewProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/7/21.
 */

public class TableAdapter extends BaseTableAdapter {
    private static final String TAG = "TableAdapter";

    private HashMap<String, Object> maps;
    private ArrayList<ArrayList<String>> data;
    private String[] titles;
    private int[] widths;
    private int[] sortState;

    private float density;
    private Context context;
    private LayoutInflater inflater;

    //    private SparseArray<ImageView> sortViews;
    private ArrayList<View> headerViews;
    private SortHeaderListener listener;
    private View convertView4FirstHeader;

    public TableAdapter(Context context, HashMap<String, Object> maps) {
        super();
        setData(maps);
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;
        inflater = LayoutInflater.from(context);
    }

    int _2;

    @Override
    public int getRowCount() {
//        Log.d(TAG, "getRowCount() " + ++_2);
        int size = data.size();
        return size;
    }

    int _1;

    @Override
    public int getColumnCount() {
//        Log.d(TAG, "getColumnCount() " + ++_1);
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

    int _3;

    @Override
    public int getWidth(int column) {
//        Log.d(TAG, "getWidth() " + ++_3);
        int width = Math.round(widths[column + 1] * density);
        return width;
    }

    int _4;

    @Override
    public int getHeight(int row) {
//        Log.d(TAG, "getWidth() " + ++_4);
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

    int i;

    //-1,-1 会执行两次，并且convertView = null,
    private View getFirstHeader(int column, View convertView, ViewGroup parent) {
        if (convertView4FirstHeader == null) {
            convertView4FirstHeader = inflater.inflate(R.layout.item_table_header_first, parent, false);
            convertView4FirstHeader.setOnClickListener(listener);
            ImageView sortView = (ImageView) convertView4FirstHeader.findViewById(R.id.img_sort);
            TextView txtView = (TextView) convertView4FirstHeader.findViewById(R.id.txt_header_first);

            convertView4FirstHeader.setTag(R.id.child,
                    new ViewHolder()
                            .setImgView(sortView)
                            .setTxtView(txtView));

            headerViews.add(convertView4FirstHeader);
        }
        Log.d(TAG, "first header is =====" + convertView4FirstHeader.hashCode() + " " + ++i);
        convertView4FirstHeader.setTag(R.id.header_column, column);
        ViewHolder holder = (ViewHolder) convertView4FirstHeader.getTag(R.id.child);
        sortState[column + 1] = 1;
        holder.getImgView().setImageResource(SortStateViewProvider.getSortStateViewResource(sortState[column + 1])); //znn
        holder.getTxtView().setText(titles[column + 1]);

        return convertView4FirstHeader;
    }

    private View getHeader(int row, int column, View convertView, ViewGroup parent) {
        Log.d(TAG, " header column is " + column);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_table_header, parent, false);
            convertView.setOnClickListener(listener);

            ImageView sortView = (ImageView) convertView.findViewById(R.id.img_sort);
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_header);

            convertView.setTag(R.id.child,
                    new ViewHolder()
                            .setImgView(sortView)
                            .setTxtView(txtView));
        }
        convertView.setTag(R.id.header_column, column);
        ViewHolder holder = (ViewHolder) convertView.getTag(R.id.child);
        holder.getTxtView().setText(titles[column + 1]);  //列名

        int sort = sortState[column + 1]; //排序状态
        holder.getImgView().setImageResource(SortStateViewProvider.getSortStateViewResource(sort));

        return convertView;
    }

    //从标题往下的第一列，row 起始为 0, column = -1
    private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {

        int size = data.get(row).size();
        int value = Integer.parseInt(data.get(row).get(size - 1));

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_table_first, parent, false);

            convertView.setOnClickListener(new FirstColumnOnClickListener(context));

            TextView txtView = (TextView) convertView.findViewById(R.id.txt_first);

            convertView.setTag(R.id.child,
                    new ViewHolder()
                            .setTxtView(txtView));
        }

        //没在这处理第一列不用点击的情况，而是在监听里处理  znn
        if (value != -1) {
        }

        ViewHolder holder = (ViewHolder) convertView.getTag(R.id.child);
        if (value != -1) holder.getTxtView().setTextColor(Color.parseColor("#FF3F51B5"));
        holder.getTxtView().setText(data.get(row).get(column + 1));

        // _1: 名称(集团/公司/项目); _2: 对应_1的id
        Tuple2<String, Integer> t2 = new Tuple2<>(
                data.get(row).get(column + 1),
                Integer.parseInt(data.get(row).get(size - 1)));
        convertView.setTag(R.id.item_value, t2);
        convertView.setTag(R.id.what, data.get(row).get(size - 2)); //message.what

        return convertView;
    }

    //除标题行和第一列以外的其他数据,row起始为0, column起始为0
    private View getBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_table, parent, false);
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_table);

            convertView.setTag(R.id.child,
                    new ViewHolder()
                            .setTxtView(txtView));
        }
        TextView txtView = ((ViewHolder) convertView.getTag(R.id.child)).getTxtView();
        txtView.setText(data.get(row).get(column + 1));

        return convertView;
    }

    private class ViewHolder {
        private TextView txtView;
        private ImageView imgView;

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
    }

    public void setData(HashMap<String, Object> map) {
        maps = map;
        titles = (String[]) maps.get("title");
        widths = (int[]) maps.get("width");
        sortState = (int[]) maps.get("state");
        data = (ArrayList<ArrayList<String>>) maps.get("data");

        if (listener == null) {
//            sortViews = new SparseArray<>();
//            listener = new SortHeaderListener(sortViews, sortState);
        } else {
            listener.setSortState(sortState);
        }
    }
}
