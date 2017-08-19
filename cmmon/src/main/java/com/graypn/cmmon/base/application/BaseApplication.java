package com.graypn.cmmon.base.application;

import android.app.Application;

import com.graypn.cmmon.cache.DataCacheManager;

/**
 * 基础 Application 类 ，用来初始化common里的类库
 * Created by ZhuLei on 2017/1/9.
 * Email: zhuleineuq@gmail.com
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initDataCache();

    }

    /**
     * 初始化数据缓存
     */
    private void initDataCache() {
        DataCacheManager.init(this);
    }


}
