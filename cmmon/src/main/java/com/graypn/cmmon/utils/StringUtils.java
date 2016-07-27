package com.graypn.cmmon.utils;

import android.text.TextUtils;

/**
 * 字符串常用方法
 *
 * @author Graypn
 */
public class StringUtils {

    /**
     * 用正则表达式去除字符串里面的html标签
     */
    public static String removeHtml(String input) {
        if (TextUtils.isEmpty(input)) {
            return "传入字符串为空";
        }
        return input.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
                .replaceAll("</[a-zA-Z]+[1-9]?>", "");
    }
}
