package com.coinmarket.info;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> pages = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    void addFragment(Fragment fragment, String s) {
        pages.add(fragment);
        titles.add(s);
    }

    SlideFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i < 0 || i >= pages.size()) {
            return null;
        } else return pages.get(i);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
