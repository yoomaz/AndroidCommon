package com.graypn.cmmon.cache.data;

import android.content.Context;

/**
 * Created by graypn on 15/11/18
 */
public class ObjectCacheManager {

    public static DataKeeper dataKeeper;

    /**
     * 需要在程序刚打开的时候进行初始化，建议放在 Application 内
     *
     * @param context
     */
    public static void init(Context context, String password) {
        dataKeeper = new DataKeeper(context, DataKeeper.KEY_PK_HOME);
//        Hawk.init(context, password);
    }

    public static <T> void put(String key, T value) {
//        Hawk.put(key, value);
        dataKeeper.put(key,value);
    }

    public static boolean contains(String key) {
        Object obj = dataKeeper.get(key);
        if (obj != null) {
            return true;
        } else {
            return false;
        }
//        return Hawk.contains(key);
    }

    public static <T> T get(String key) {
        Object obj = dataKeeper.get(key);
        return (T) obj;
    }

    public static void remove(String key) {
        dataKeeper.put(key, null);
//        Hawk.remove(key);
    }


}
