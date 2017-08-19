package com.graypn.android_common.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by graypn on 16/1/8.
 */
public class HUDUtils {

    private static KProgressHUD progressToast;

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
