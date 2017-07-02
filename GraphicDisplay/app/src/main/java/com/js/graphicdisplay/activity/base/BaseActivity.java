package com.js.graphicdisplay.activity.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by js_gg on 2017/6/16.
 */

public class BaseActivity extends AppCompatActivity {

    public static final int MESSAGE_ERROR = 0;
    public static final int MESSAGE_FAILED = 1;
    public static final int MESSAGE_SUCCESS = 2;

    protected Typeface mTfLight;
    protected Handler mHandler = new MHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
    }

    private static class MHandler extends Handler {
        WeakReference<BaseActivity> activity;

        public MHandler(BaseActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            activity.get().handleUIMessage(msg);
        }
    }

    protected void handleUIMessage(Message msg) {}


}
