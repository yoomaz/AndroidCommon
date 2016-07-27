package com.graypn.cmmon.net;

/**
 * Created by graypn on 16/1/7.
 */
public interface NetCallBack<T> {

    void onFailure();

    void onSuccess(T t);
}
