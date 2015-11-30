package com.hodgepodge;

import com.loopj.android.http.*;


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

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

