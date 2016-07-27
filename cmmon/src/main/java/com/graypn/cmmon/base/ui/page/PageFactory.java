package com.graypn.cmmon.base.ui.page;

import java.util.List;

/**
 * 对 BasePage 的 封装 ，为了方便和 ViewPager 配合使用
 * <p>
 * Created by graypn on 15/4/19.
 */
public class PageFactory {

    private List<String> titleList;
    private List<BasePage> pageList;

    /**
     * 构造方法
     */
    public PageFactory(List<BasePage> pageList) {
        this.pageList = pageList;
    }

    public PageFactory(List<BasePage> pageList, List<String> titleList) {
        this.pageList = pageList;
        this.titleList = titleList;
    }

    /**
     * 对属性的操作一些方法
     */
    public BasePage getPageItem(int position) {
        return pageList.get(position);
    }

    public int getPageCount() {
        return pageList.size();
    }

    public CharSequence getTitle(int position) {
        if (hasTitles()) {
            return titleList.get(position);
        } else {
            throw new NullPointerException("TitleList Is Null");
        }
    }

    public boolean hasTitles() {
        return (titleList != null) && (titleList.size() != 0);
    }
}
