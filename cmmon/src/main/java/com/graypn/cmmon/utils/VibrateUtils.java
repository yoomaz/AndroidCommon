package com.graypn.cmmon.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * 震动操作工具类
 *
 * Created by ZhuLei on 2016/9/27.
 * Email: zhuleineuq@gmail.com
 */

public final class VibrateUtils {

    // 震动默认时间为 30ms
    private static int DEFAULT_DURATION = 30;

    private VibrateUtils() {
    }

    public static void vibrate(Context context) {
        vibrate(context, DEFAULT_DURATION);
    }

    public static void vibrate(Context context, long duration) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(duration);
        }
    }

    public static void vibrate(Context context, long[] pattern, int repeatCount) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(pattern, repeatCount);
        }
    }
}