package com.graypn.cmmon.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * 封装Gson
 *
 * Created by ZhuLei on 2017/1/6.
 * Email: zhuleineuq@gmail.com
 */
public class GsonUtils {

    public static <T> T changeJsonToBean(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, cls);
    }

    public static <T> List<T> changeJsonToList(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
    }

    public static String createJsonString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> List<Map<String, T>> changeJsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new Gson();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeJsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

}
