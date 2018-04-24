package com.saberrr.openchina.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saberrr.openchina.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class ComprehensiveAdapter extends FragmentStatePagerAdapter {
    private List<FragmentInfo> mDatas = new ArrayList<>();

    public ComprehensiveAdapter(FragmentManager fm, List<FragmentInfo> datas) {
        super(fm);
        mDatas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return mDatas.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position).title;
    }
}
