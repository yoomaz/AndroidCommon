package com.graypn.cmmon.base.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graypn on 15/2/28.
 */
public class FragmentFactory {

    private List<String> titleList;
    private List<Fragment> fragmentList;

    /**
     * 三种构造方法
     */
    public FragmentFactory() {
        titleList = new ArrayList<String>();
        fragmentList = new ArrayList<Fragment>();
    }

    public FragmentFactory(List<Fragment> fragmentList) {
        titleList = new ArrayList<String>();
        this.fragmentList = fragmentList;
    }

    public FragmentFactory(List<String> titleList, List<Fragment> fragmentList) {
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    /**
     * 对属性的操作一些方法
     */
    public Fragment getFragmentItem(int position) {
        return fragmentList.get(position);
    }

    public int getFragmentCount() {
        return fragmentList.size();
    }

    public CharSequence getTitle(int position) {
        return titleList.get(position);
    }

    public FragmentFactory addFragment(Fragment fragment) {
        fragmentList.add(fragment);
        return this;
    }

    public FragmentFactory addFragment(Fragment fragment, String title) {
        titleList.add(title);
        addFragment(fragment);
        return this;
    }

    public FragmentFactory changeFragmentlist(List<String> titleList, List<Fragment> fragmentList) {
        this.titleList = titleList;
        this.fragmentList = fragmentList;
        return this;
    }

    public boolean hasTitles() {
        return titleList.size() != 0;
    }
}
