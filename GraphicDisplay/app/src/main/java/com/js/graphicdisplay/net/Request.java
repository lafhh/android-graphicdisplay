package com.js.graphicdisplay.net;

import com.js.graphicdisplay.data.NameValuePair;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/14.
 */

public class Request {

    private String url;

    private ArrayList<NameValuePair<String, String>> data;

    private String body;

    private ContentType contentType = ContentType.JSON;

    private RequestMethod method = RequestMethod.GET;

    public enum ContentType {
        JSON, XML, FILE, KVP; //KVP 键值对
    }

    public enum RequestMethod {
        POST, GET;
    }

    public ArrayList<NameValuePair<String, String>> getData() {
        return data;
    }

    public void setData(ArrayList<NameValuePair<String, String>> data) {
        this.data = data;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }
}
