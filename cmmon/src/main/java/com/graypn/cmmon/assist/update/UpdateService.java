package com.graypn.cmmon.assist.update;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.graypn.cmmon.R;
import com.graypn.cmmon.net.NetManager;
import com.graypn.cmmon.net.okhttp.DownloadCallBack;
import com.graypn.cmmon.utils.PackageUtil;
import com.graypn.cmmon.utils.ToastUtils;

import java.io.File;

/**
 * App 更新服务, 需要在 Intent 里 传入 UPDATE_TITLE（可选） 和 UPDATE_URL
 * Created by ZhuLei on 2017/2/9.
 * Email: zhuleineuq@gmail.com
 */

public class UpdateService extends Service {

    // 通知栏标题
    public static final String UPDATE_TITLE = "updateTitle";
    // 新的apk下载地址
    public static final String UPDATE_URL = "updateUrl";
    // 下载路径
    public static final String UPDATE_FILE_PATH = "updateFilePath";
    // 文件名
    public static final String UPDATE_FILE_NAME = "updateFileName";

    private final int NOTIFY_ID = 1;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mNotificationBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationBuilder = new NotificationCompat.Builder(this)
                    .setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_back)
                    .setContentTitle(getString(R.string.update_title))
                    .setContentText(getString(R.string.update_content) + "0%")
                    .setProgress(100, 0, false);
            mNotificationManager.notify(NOTIFY_ID, mNotificationBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        final String updateUrl = "http://download.voicecloud.cn/100IME/iFlyIME_v7.0.4405.apk";
        final String updateUrl = intent.getStringExtra(UPDATE_URL);
        final String updateTitle = intent.getStringExtra(UPDATE_TITLE);
        String updateFilePath = intent.getStringExtra(UPDATE_FILE_PATH);
        String updateFileName = intent.getStringExtra(UPDATE_FILE_NAME);
        if (TextUtils.isEmpty(updateFilePath)) {
            updateFilePath = PackageUtil.getSDCardPath();
        }
        if (TextUtils.isEmpty(updateFileName)) {
            updateFileName = "demo.apk";
        }
        if (!TextUtils.isEmpty(updateTitle)) {
            mNotificationBuilder.setContentTitle(updateTitle);
        }
        if (!TextUtils.isEmpty(updateUrl)) {
            NetManager.download(updateUrl, updateFilePath, updateFileName, new DownloadCallBack() {
                @Override
                public void onFinish(File file) {
                    mNotificationManager.cancel(NOTIFY_ID);
                    if (file != null) {
                        PackageUtil.install(UpdateService.this, file);
                    }
                }

                @Override
                public void onProgress(long currentBytes, long totalBytes) {
                    int progress = (int) ((currentBytes * 100) / totalBytes);
                    mNotificationBuilder.setContentText(getString(R.string.update_content) + progress + "%")
                            .setProgress(100, progress, false)
                    ;
                    mNotificationManager.notify(NOTIFY_ID, mNotificationBuilder.build());
                }

                @Override
                public void onFailure(String error) {
                    ToastUtils.showToast(UpdateService.this, getString(R.string.update_error) + error);
                    mNotificationManager.cancel(NOTIFY_ID);
                }
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
