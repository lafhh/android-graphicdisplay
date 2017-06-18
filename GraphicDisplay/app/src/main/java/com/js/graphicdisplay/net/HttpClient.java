package com.js.graphicdisplay.net;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import com.js.graphicdisplay.data.NameValuePair;
import com.js.graphicdisplay.net.Request.ContentType;

/**
 * Created by js_gg on 2017/6/14.
 */

public class HttpClient {


    private OkHttpClient client;

    private static HttpClient instance;

    public static HttpClient build() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }

    //设定超时
    private HttpClient() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public void post(com.js.graphicdisplay.net.Request req, Callback callback) throws IOException {
        ContentType type = req.getContentType();
        RequestBody body = null;

        switch (type) {
            case JSON:
                body = createBodyFromJson(req.getBody());
                break;

            case KVP:
                body = createBodyFromKeyValue(req.getData());
                break;
        }
        Request request = new Request.Builder()
                .url(req.getUrl())
                .post(body)
                .build();

//        Response response = client.newCall(request).execute();
        client.newCall(request).enqueue(callback);
    }

    //已测试get请求，http,https都能访问
    public void get(String url, Callback callback) throws IOException {
        Request request = new Request.Builder().url(url).build();
//        Response response = client.newCall(request).execute();
        client.newCall(request).enqueue(callback);
    }

    private RequestBody createBodyFromJson(String json) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(JSON, json);
    }

    //没有进行编码
    private RequestBody createBodyFromKeyValue(ArrayList<NameValuePair<String, String>> data) {
        FormBody.Builder builder = new FormBody.Builder();
        for (NameValuePair<String, String> nvp : data) {
            builder.add(nvp.key, nvp.value);
        }
        return builder.build();
    }
}
