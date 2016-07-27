package com.graypn.cmmon.base.dao;


import com.graypn.cmmon.cache.data.ObjectCacheManager;

/**
 * Created by graypn on 16/1/7
 */
public abstract class BaseDao {

    protected <T> void save(String key, T value) {
        ObjectCacheManager.put(key, value);
    }

    protected boolean contains(String key) {
        return ObjectCacheManager.contains(key);
    }

    protected void remove(String key) {
        ObjectCacheManager.remove(key);
    }

    public static <T> T get(String key) {
        return ObjectCacheManager.get(key);
    }

}
