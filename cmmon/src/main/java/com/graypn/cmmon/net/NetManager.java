package com.graypn.cmmon.net;

import android.content.Context;

import com.graypn.cmmon.utils.NetworkUtils;
import com.graypn.cmmon.utils.ToastUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装网络部分
 * <p/>
 * 基于 Okhttp3
 * Dependence：compile 'com.squareup.okhttp3:okhttp:3.4.1'
 * <p/>
 * Created by graypn on 16/8/4.
 */
public class NetManager {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 同步直接获取字符串
     * <p/>
     * Get方式
     */
    public static String getData(String url) throws IOException {
        if (NetworkUtils.isConnected(mContext)) {
            Request request = new Request
                    .Builder()
                    .url(url)
                    .build();
            Response response = mOkHttpClient
                    .newCall(request)
                    .execute();
            if (response.isSuccessful()) {
                return response.body().string();
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
            FormBody.Builder builder = new FormBody.Builder();
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
