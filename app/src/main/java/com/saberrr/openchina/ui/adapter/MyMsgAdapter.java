package com.saberrr.openchina.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saberrr.openchina.bean.MyMsgInfo;

import java.util.List;

/**
 * Created by 2017 on 2017/4/3.
 */

public class MyMsgAdapter extends FragmentStatePagerAdapter {

    private List<MyMsgInfo> mData;
    public MyMsgAdapter(FragmentManager fm , List<MyMsgInfo> data) {
        super(fm);
        this.mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).title;
    }
}
