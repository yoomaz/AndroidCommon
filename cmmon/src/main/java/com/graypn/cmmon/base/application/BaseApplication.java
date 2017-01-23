package com.graypn.cmmon.base.application;

import android.app.Application;

import com.graypn.cmmon.cache.DataCacheManager;
import com.orhanobut.hawk.Hawk;
import com.tencent.smtt.sdk.QbSdk;

/**
 * 基础 Application 类 ，用来初始化common里的类库
 * Created by ZhuLei on 2017/1/9.
 * Email: zhuleineuq@gmail.com
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initX5WebCore();
        initDataCache();

    }

    /**
     * 初始化数据缓存
     */
    private void initDataCache() {
        DataCacheManager.init(this);
    }

    /**
     * 初始化腾讯X5浏览器
     */
    private void initX5WebCore() {
        QbSdk.initX5Environment(this, null);
    }
}
