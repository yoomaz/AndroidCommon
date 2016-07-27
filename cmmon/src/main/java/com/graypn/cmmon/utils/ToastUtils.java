package com.graypn.cmmon.utils;

import android.content.Context;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by graypn on 16/1/8.
 */
public class ToastUtils {

    private static KProgressHUD progressToast;

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressToast(Context context, boolean cancleable) {
        progressToast = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(cancleable)
                .show();
    }

    public static void showProgressToast(Context context, String text, boolean cancleable) {
        progressToast = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(cancleable)
                .setLabel(text).show();
    }

    public static void dismissProgressToast() {
        if (progressToast != null) {
            progressToast.dismiss();
            progressToast = null;
        }
    }

}
