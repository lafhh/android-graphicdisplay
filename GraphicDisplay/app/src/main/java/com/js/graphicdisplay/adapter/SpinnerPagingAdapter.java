package com.js.graphicdisplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.api.Infermation;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/18.
 */

public class SpinnerPagingAdapter extends BaseAdapter {

    private Integer[] mData;
    private Context mContext;

    public SpinnerPagingAdapter(Context context, Integer[] list) {
        this.mContext = context;
        this.mData = list;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_spinner, null);
            holder = new ViewHolder((TextView) convertView.findViewById(R.id.txt_paging));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(String.format(mContext.getResources().getString(R.string.spinner_page),
                String.valueOf(mData[position])));

        return convertView;
    }

    private class ViewHolder {
        public final TextView textView;

        public ViewHolder(TextView tv) {
            textView = tv;
        }
    }

//    public void setData(ArrayList<T> list) {
//        mData = list;
//    }

//    public ArrayList<T> getData() {
//        return mData;
//    }
}
