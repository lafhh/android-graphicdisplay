package com.js.graphicdisplay.mpchart.impl;

import android.util.Log;
import android.view.MotionEvent;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

/**
 * Created by apple on 2017/7/12.
 */

public class OnChartGestureImpl implements OnChartGestureListener {
    private static final String TAG = "OnChartGestureImpl";

    @Override
    public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {
       Log.d(TAG, "");
    }

    @Override
    public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {
        Log.d(TAG, "");
    }

    @Override
    public void onChartLongPressed(MotionEvent motionEvent) {
        Log.d(TAG, "");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent motionEvent) {
        Log.d(TAG, "");
    }

    @Override
    public void onChartSingleTapped(MotionEvent motionEvent) {
        Log.d(TAG, "");
    }

    @Override
    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "");
    }

    @Override
    public void onChartScale(MotionEvent motionEvent, float v, float v1) {
        Log.d(TAG, "");
    }

    @Override
    public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {
        Log.d(TAG, "");
    }
}
