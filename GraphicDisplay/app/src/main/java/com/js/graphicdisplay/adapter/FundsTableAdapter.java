package com.js.graphicdisplay.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.data.FundsTableMateData;
import com.js.graphicdisplay.data.Group;
import com.js.graphicdisplay.data.Tuple2;
import com.js.graphicdisplay.impl.FirstColumnOnClickListener;
import com.js.graphicdisplay.impl.SortHeaderListener;
import com.js.graphicdisplay.util.SortState;
import com.js.graphicdisplay.util.SortStateViewProvider;

import java.util.ArrayList;

/**
 * Created by apple on 2017/7/21.
 */

public class FundsTableAdapter extends BaseTableAdapter {
    private static final String TAG = "FundsTableAdapter";

    private ArrayList<Group> groups;
    private Context context;
    private float density;
    private LayoutInflater inflater;

    private FundsTableMateData tableMetaData = new FundsTableMateData();
    private final SparseArray<ImageView> sortViews = new SparseArray<>();
    private SortHeaderListener listener;

    public FundsTableAdapter(Context context, ArrayList<Group> list) {
        this.groups = list;
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;
        inflater = LayoutInflater.from(context);
        listener = new SortHeaderListener(sortViews, tableMetaData);
    }
    int _2;
    @Override
    public int getRowCount() {
        Log.d(TAG, "getRowCount() " + ++_2);
        int size = 0;
        for (int i = 0; i < groups.size(); i++) {
            size += groups.get(i).getFundsData().getMonths().size();
        }
        return size;
    }

    int _1;
    @Override
    public int getColumnCount() {
        Log.d(TAG, "getColumnCount() " + ++_1);
        return tableMetaData.getlabelLength() - 1;
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
        Log.d(TAG, "getWidth() " + ++_3);
        int width = Math.round(tableMetaData.getWidth(column + 1) * density);
        return width;
    }
    int _4;
    @Override
    public int getHeight(int row) {
        Log.d(TAG, "getWidth() " + ++_4);
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
        txtView.setText(tableMetaData.getLabel(0));
        return convertView;
    }

    private View getHeader(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_table_header, parent, false);
            convertView.setOnClickListener(listener);

            ImageView sortView = (ImageView) convertView.findViewById(R.id.img_sort);
            sortView.setTag(R.id.sort_state, SortState.SORTABLE);
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_header);

            convertView.setTag(R.id.child,
                    new ViewHolder()
                            .setImgView(sortView)
                            .setTxtView(txtView));
        }
        convertView.setTag(R.id.header_column, column);
        ViewHolder holder = (ViewHolder) convertView.getTag(R.id.child);
        holder.getTxtView().setText(tableMetaData.getLabel(column + 1));

        SortState sortState = tableMetaData.getSortState(column + 1);
        holder.getImgView().setImageResource(SortStateViewProvider.getSortStateViewResource(sortState));
        return convertView;
    }

    private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_table_first, parent, false);
            convertView.setOnClickListener(new FirstColumnOnClickListener());
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_first);

            convertView.setTag(R.id.child,
                    new ViewHolder()
                            .setTxtView(txtView));

        }
        int index = (int) getRow(row)._1;

        ViewHolder holder = (ViewHolder) convertView.getTag(R.id.child);
        holder.getTxtView().setText(groups.get(index).getName());
        convertView.setTag(R.id.item_value, groups.get(index).getId());

        return convertView;
    }

    private View getBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_table, parent, false);
            TextView txtView = (TextView) convertView.findViewById(R.id.txt_table);

            convertView.setTag(R.id.child,
                    new ViewHolder()
                            .setTxtView(txtView));
        }
        TextView txtView = ((ViewHolder) convertView.getTag(R.id.child)).getTxtView();
        int index = (int) getRow(row)._1;
        row = (int) getRow(row)._2;

        if (column == 0) {
            txtView.setText(groups.get(index).getFundsData().getMonths().get(row));
        } else {
            txtView.setText(groups.get(index).getFundsData().getFundsPerMonth().get(row).data[column - 1]);
        }

        return convertView;
    }

    private Tuple2 getRow(int row) {
        int groupIndex = 0;

        while (row >= 0) {
            row -= groups.get(groupIndex).getFundsData().getMonths().size();
            groupIndex++;
        }
        groupIndex--;

        Tuple2 t2 = new Tuple2(groupIndex, row + groups.get(groupIndex).getFundsData().getMonths().size());
        return t2;
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
}
