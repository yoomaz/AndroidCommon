package com.graypn.cmmon.base.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * Created by graypn on 15/2/28.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private FragmentFactory fragmentFactory;

    public FragmentPagerAdapter(FragmentManager fm, FragmentFactory fragmentFactory) {
        super(fm);
        this.fragmentFactory = fragmentFactory;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentFactory.getFragmentItem(position);
    }

    @Override
    public int getCount() {
        return fragmentFactory.getFragmentCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (fragmentFactory.hasTitles()) {
            return fragmentFactory.getTitle(position);
        }
        return super.getPageTitle(position);
    }
}
