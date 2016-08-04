package com.graypn.cmmon.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by ZhuLei on 16/8/4.
 * Email: zhuleineuq@gmail.com
 */
public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String message) {
        if (TextUtils.isEmpty(message)) return;
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
