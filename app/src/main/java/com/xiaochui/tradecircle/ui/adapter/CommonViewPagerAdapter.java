package com.xiaochui.tradecircle.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CommonViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> mTitleList;//页卡标题集合
    private List<Fragment> mViewList;//页卡视图集合
    private FragmentManager fm;

    public CommonViewPagerAdapter(FragmentManager fm, List<String> mTitleList, List<Fragment> mViewList) {
        super(fm);
        this.fm = fm;
        this.mTitleList = mTitleList;
        this.mViewList = mViewList;
    }

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> mViewList) {
        super(fm);
        this.fm = fm;
        this.mViewList = mViewList;
    }

    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList != null ? mTitleList.get(position) : mViewList.get(position).getTag();
    }


    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    public void setFragments(List<Fragment> fragments) {
        if (this.mViewList != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.mViewList) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.mViewList = fragments;
        notifyDataSetChanged();
    }
}
