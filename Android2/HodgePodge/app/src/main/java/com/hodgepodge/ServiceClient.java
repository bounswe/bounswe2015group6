package com.hodgepodge;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.HttpEntity;


/**
 * Created by apple on 27/11/15.
 */
public class ServiceClient {
    private static final String BASE_URL = "http://ec2-52-27-36-39.us-west-2.compute.amazonaws.com:8080/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "application/json");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandlerInterface) {
        client.post(context, getAbsoluteUrl(url), entity, contentType, responseHandlerInterface);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

