package com.js.graphicdisplay.activity.base;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by js_gg on 2017/6/16.
 */

public class BaseActivity extends AppCompatActivity {

    protected Handler mHandler = new MHandler(this);

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
