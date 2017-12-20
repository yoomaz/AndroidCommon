package com.graypn.android_common.app;

import com.graypn.cmmon.base.application.BaseApplication;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by ZhuLei on 2017/1/9.
 * Email: zhuleineuq@gmail.com
 */

public class CommonApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initX5WebCore();
    }

    /**
     * 初始化腾讯X5浏览器
     */
    private void initX5WebCore() {
        QbSdk.initX5Environment(this, null);
    }
}
