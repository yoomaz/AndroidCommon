package com.graypn.cmmon.base.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity的封装类
 *
 * @author Graypn
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables(savedInstanceState);
        initViews();
        initData();
    }

    /**
     * 初始化变量（包括传递过来的数据等）
     */
    protected abstract void initVariables(Bundle savedInstanceState);

    /**
     * 初始化视图（对控件的寻找，点击事件等）
     */
    protected abstract void initViews();

    /**
     * 初始化数据（业务逻辑部分）
     */
    protected abstract void initData();

}
