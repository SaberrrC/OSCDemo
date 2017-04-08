package com.saberrr.openchina.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.saberrr.openchina.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuqi on 2017/4/1.
 */

public class OswViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentInfo> datas = new ArrayList<>();

    public OswViewPagerAdapter(FragmentManager fm,List<FragmentInfo> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).title;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
