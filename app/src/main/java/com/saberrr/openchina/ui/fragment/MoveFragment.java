package com.saberrr.openchina.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FragmentInfo;
import com.saberrr.openchina.bean.MoveNewBean;
import com.saberrr.openchina.ui.adapter.MoveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tt on 2017/4/1.
 */

public class MoveFragment extends BaseFragment {

    private RecyclerView mRecyclerView;


    List<FragmentInfo> pages = new ArrayList<>();
    List<MoveNewBean> mShowItems = new ArrayList();

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        String[] title = {"最新动弹", "热门动弹", "每日乱弹", "我的动弹"};
        View view = View.inflate(getContext(), R.layout.fragment_move, null);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.move_new_tabLayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.move_new_viewpager);
        pages.add(new FragmentInfo(new MoveNewFragment(), title[0]));
        pages.add(new FragmentInfo(new MoveNewFragment(), title[1]));
        pages.add(new FragmentInfo(new MoveNewFragment(), title[2]));
        pages.add(new FragmentInfo(new MoveNewFragment(), title[3]));
        viewPager.setAdapter(new MoveAdapter(getChildFragmentManager(), pages));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public Object getData() {

        return "";
    }
}
