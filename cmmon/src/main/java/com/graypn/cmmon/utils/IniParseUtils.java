package com.graypn.cmmon.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Ini 配置文件解析
 * <p>
 * Created by ZhuLei on 2017/3/9.
 * Email: zhuleineuq@gmail.com
 */

public class IniParseUtils {

    /**
     * ini 文件解析
     */
    public static HashMap<String, HashMap<String, String>> parseIni(Context context, String fileName) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = context.getAssets().open(fileName);
            if (inputStream != null) {
                inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset().name());
                bufferedReader = new BufferedReader(inputStreamReader);

                HashMap<String, HashMap<String, String>> propertiesMap = new HashMap<>();
                HashMap<String, String> properties = null;

                String str;
                boolean isFirstLine = true;
                while ((str = bufferedReader.readLine()) != null) {
                    str = str.trim();
                    // 在Windows下用文本编辑器创建的文本文件，如果选择以UTF-8等Unicode格式保存，会在文件头（第一个字符）加入一个BOM标识
                    // http://www.07net01.com/2015/07/888222.html
                    if (isFirstLine) {
                        if (str.startsWith("\ufeff")) {
                            str = str.substring(1);
                        }
                        isFirstLine = false;
                    }
                    if (!str.startsWith("#")) {
                        if (str.startsWith("[") && str.endsWith("]")) {
                            String mapKey = str.substring(1, str.length() - 1);
                            properties = new HashMap<>();
                            propertiesMap.put(mapKey, properties);
                            continue;
                        }
                        if (str.contains("=")) {
                            int index = str.indexOf("=");
                            String key = str.substring(0, index);
                            String value = str.substring(index + 1);
                            if (!TextUtils.isEmpty(key) && properties != null) {
                                properties.put(key, value);
                            }
                        }
                    }
                }
                return propertiesMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
