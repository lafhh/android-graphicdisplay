package com.js.graphicdisplay.net;


import java.io.IOException;
import java.util.ArrayList;

import com.js.graphicdisplay.data.NameValuePair;
import com.js.graphicdisplay.net.Request.ContentType;

import okhttp3.Callback;

/**
 * Created by js_gg on 2017/6/14.
 */

public class HttpManager {
    private static final String TAG = "HttpManager";

    //url要不要检查
    public static void doPost(String url,
                              ArrayList<NameValuePair<String, String>> data,
                              ContentType type,
                              Callback callback) {
        Request request = new Request();
        request.setUrl(url);
        request.setData(data);
        request.setContentType(type);
        HttpClient client = HttpClient.build();

        try {
            client.post(request, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doPost(String url,
                              String data,
                              ContentType type,
                              Callback callback) {
        Request request = new Request();
        request.setUrl(url);
        request.setBody(data);
        request.setContentType(type);
        HttpClient client = HttpClient.build();

        try {
            client.post(request, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    public static void doGet(final String url) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                HttpClient client = HttpClient.build();
//                try {
//                    String result = client.get(url);
//                    Log.d(TAG, "打印响应体" + result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        sFixedThreadPool.execute(runnable);
//    }
    public static void doGet(final String url, Callback callback) {
        HttpClient client = HttpClient.build();
        try {
//            String result = client.get(url);
            client.get(url, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
