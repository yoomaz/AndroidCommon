package com.graypn.cmmon.cache;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

/**
 * 数据缓存管理：依赖 Hawk 进行实现
 *
 * Created by ZhuLei on 2017/1/21.
 * Email: zhuleineuq@gmail.com
 */
public class DataCacheManager {

    /**
     * 初始化
     *
     * @param context Activity 和 Application 都可以，最终都会转换成 ApplicationContext
     */
    public static void init(Context context) {
        Hawk.init(context).build();
    }

    /**
     * 保存数据
     */
    public static <T> boolean put(String key, T value) {
        return Hawk.put(key, value);
    }

    /**
     * 取出数据
     */
    public static <T> T get(String key) {
        return Hawk.get(key);
    }
}
