package com.js.graphicdisplay.adapter;

import android.content.Context;
import android.util.Log;
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

public class SpinnerAdapter<T extends Infermation> extends BaseAdapter {

    private ArrayList<T> mData;
    private Context mContext;

    public SpinnerAdapter(Context context, ArrayList<T> list) {
        this.mContext = context;
        this.mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

//        Log.d("SpinnerAdapter", "getView()");
        if (convertView == null) {
//            Log.d("SpinnerAdapter", "getView()1");
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_selector, null);
            holder = new ViewHolder((TextView) convertView.findViewById(R.id.txt_name));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String name = mData.get(position).getName();
        holder.textView.setText(name);

        return convertView;
    }

    private class ViewHolder {
        public final TextView textView;

        public ViewHolder(TextView tv) {
            textView = tv;
        }
    }

    public void setData(ArrayList<T> list) {
        mData = list;
    }
}
