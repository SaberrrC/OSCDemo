package com.saberrr.openchina.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saberrr.openchina.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tt on 2017/4/1.
 */

public class MoveAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mPagers = new ArrayList();

    public MoveAdapter(FragmentManager fm, List<FragmentInfo> pager) {
        super(fm);
        mPagers = pager;
    }

    @Override
    public Fragment getItem(int position) {
        return mPagers.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return mPagers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPagers.get(position).title;
    }

}
