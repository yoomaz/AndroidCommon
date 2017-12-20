package com.graypn.cmmon.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * @author ZhuLei
 * @date 2017/12/13
 */

public class ClipBoardUtils {

    /**
     * 获取剪贴板里的数据
     */
    public static String getClipDate(Context context) {
        ClipboardManager cm = null;
        try {
            cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cm != null) {
            ClipData data = cm.getPrimaryClip();
            //  ClipData 里保存了一个ArryList 的 Item 序列， 可以用 getItemCount() 来获取个数
            if (data != null) {
                ClipData.Item item = data.getItemAt(0);
                if (item != null && item.getText() != null) {
                    return item.getText().toString();
                }
            }
        }
        return null;
    }

    /**
     * 向剪贴板里添加数据
     */
    public static void setClipDate(Context context, String data) {
        if (TextUtils.isEmpty(data)) {
            return;
        }
        ClipboardManager cm = null;
        try {
            cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cm != null) {
            cm.setPrimaryClip(ClipData.newPlainText(null, data));
        }
    }
}
