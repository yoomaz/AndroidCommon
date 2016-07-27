package com.graypn.cmmon.base.ui.page;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by graypn on 15/4/19.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private PageFactory pageFactory;

    public ViewPagerAdapter(PageFactory pageFactory) {
        this.pageFactory = pageFactory;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(pageFactory.getPageItem(position).getRootView(), 0);
        return pageFactory.getPageItem(position).getRootView();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= pageFactory.getPageCount())
            return;
        ((ViewPager) container).removeView(pageFactory.getPageItem(position)
                .getRootView());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (pageFactory.hasTitles()) {
            return pageFactory.getTitle(position);
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return pageFactory.getPageCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
