package com.example.cwh.mypermission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cwh on 2018/8/6.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
