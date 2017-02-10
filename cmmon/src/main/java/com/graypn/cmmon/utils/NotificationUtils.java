package com.graypn.cmmon.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

/**
 * 通知管理工具类
 * Created by ZhuLei on 2017/2/10.
 * Email: zhuleineuq@gmail.com
 */

public class NotificationUtils {

    public static void notify(Context context, int id, Notification notification) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.notify(id, notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
