package com.graypn.cmmon.base.application;

import android.app.Application;

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

    }

    private void initX5WebCore() {
        QbSdk.initX5Environment(this, null);
    }
}
