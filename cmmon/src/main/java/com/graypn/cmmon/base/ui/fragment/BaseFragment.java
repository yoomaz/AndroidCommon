package com.graypn.cmmon.base.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment的封装类
 *
 * @author Graypn
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;
    private boolean hasInitView = false;
    private boolean hasInitData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null || !hasInitView) {
            rootView = initView(inflater);
            hasInitView = true;
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (hasInitView && !hasInitData) {
            initData();
            hasInitData = true;
        }
    }

    /**
     * 由于ViewPager的缓存机制，每次都会加载3页。
     * 例如：有四个 fragment 当滑动到第四页的时候 第一页执行onDestroyView(),但没有
     * 执行onDestroy。他依然和activity关联。当在滑动到第一页的时候又执行了
     * onCreateView(),会出现重复加载view的局面，所以需要进行一次 removeView()
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (rootView.getParent() != null) {
//            ((ViewGroup) rootView.getParent()).removeView(rootView);
//        }
    }

    /**
     * 初始化变量（包括传递过来的数据等）
     */
    protected abstract void initVariables(Bundle savedInstanceState);

    /**
     * 初始化视图（对控件的寻找，点击事件等）
     */
    protected abstract View initView(LayoutInflater inflater);

    /**
     * 子类实现初始化数据操作
     */
    protected abstract void initData();

}
