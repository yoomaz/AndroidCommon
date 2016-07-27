package com.graypn.cmmon.net;

import android.content.Context;

import com.graypn.cmmon.utils.ToastUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 封装网络部分
 * <p/>
 * Created by graypn on 15/3/8.
 */
public class NetManager {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static Context mContext;

    static {
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 同步直接获取字符串
     * <p/>
     * Get方式
     */
    public static String getString(String url) throws IOException {
        if (NetworkUtils.isConnected(mContext)) {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = mOkHttpClient.newCall(request)
                    .execute();
            if (response.isSuccessful()) {
                String responseUrl = response.body().string();
                return responseUrl;
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } else {
            ToastUtils.showToast(mContext, "网络未连接");
            return null;
        }
    }

    /**
     * 异步访问网络
     * <p/>
     * Get方式
     */
    public static void getDataAsync(String url, Callback responseCallback) {
        if (NetworkUtils.isConnected(mContext)) {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            mOkHttpClient.newCall(request)
                    .enqueue(responseCallback);
        } else {
            ToastUtils.showToast(mContext, "网络未连接");
        }
    }

    /**
     * 异步访问网络
     * <p/>
     * POST方式
     */
    public static void getDataAsyncInPost(String url, Map<String, String> params, Callback responseCallback) {
        if (NetworkUtils.isConnected(mContext)) {
            FormEncodingBuilder builder = new FormEncodingBuilder();
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.add(key, params.get(key));
                }
            }
            RequestBody requestBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            mOkHttpClient.newCall(request)
                    .enqueue(responseCallback);
        } else {
            ToastUtils.showToast(mContext, "网络未连接");
        }
    }

}
