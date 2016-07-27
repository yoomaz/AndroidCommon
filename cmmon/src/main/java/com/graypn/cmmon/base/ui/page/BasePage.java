package com.graypn.cmmon.base.ui.page;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 基础页面类
 *
 * @author Graypn
 */
public abstract class BasePage {

    private Context context;
    private View rootView;

    public BasePage(Context context) {
        this(context, null);
    }

    public BasePage(Context context, Bundle bundle) {
        this.context = context;
        initVariables(bundle);
        rootView = initView(LayoutInflater.from(context));
        initData();
    }

    /**
     * 初始化变量（包括传递过来的数据等）
     */
    protected abstract void initVariables(Bundle bundle);

    /**
     * 子类实现初始化View操作
     */
    protected abstract View initView(LayoutInflater inflater);

    /**
     * 子类实现初始化数据操作
     */
    public abstract void initData();

    /**
     * 方便子类获得根View对象
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * 方便子类获得Context对象
     */
    public Context getContext() {
        return context;
    }

}
