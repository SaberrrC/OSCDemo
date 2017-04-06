package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FragmentInfo;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.OswViewPagerAdapter;
import com.saberrr.openchina.ui.fragment.mymsgfragment.CategoryItemFragment;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuqi on 2017/4/1.
 */

public class OpenSoftwareFragment extends BaseFragment {
    @BindView(R.id.osw_tablelayout)
    TabLayout mOswTablelayout;
    @BindView(R.id.osw_viewpager)
    ViewPager mOswViewpager;
    private List<FragmentInfo> datas = new ArrayList<>();

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_opensoftware, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }


    private void init() {
        String[] title = Utils.getStringArray(R.array.tab_names);
        datas.add(new FragmentInfo(new CategoryFragment(), title[4]));
        datas.add(new FragmentInfo(new RecommendFragment(), title[5]));
        datas.add(new FragmentInfo(new BestNewFragment(), title[6]));
        datas.add(new FragmentInfo(new HotFragment(), title[7]));
        datas.add(new FragmentInfo(new DomesticFragment(), title[8]));
        OswViewPagerAdapter oswViewPagerAdapter = new OswViewPagerAdapter(getFragmentManager(), datas);
        mOswViewpager.setAdapter(oswViewPagerAdapter);
        mOswTablelayout.setupWithViewPager(mOswViewpager);
        mOswTablelayout.setTabTextColors(Color.parseColor("#9F9F9F"), Color.parseColor("#0DB22E"));
        mOswTablelayout.setSelectedTabIndicatorColor(Color.parseColor("#0DB22E"));
        final ShowActivity showActivity = (ShowActivity) getContext();
        showActivity.setOnBackIconClickListener(new ShowActivity.OnBackIconClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFragment instanceof CategoryThreeItemFragment) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_right_out);
                    transaction.replace(R.id.fl_categoryThree, new CategoryItemFragment()).addToBackStack(null).commit();
                } else if (mCurrentFragment instanceof CategoryItemFragment) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_right_out);
                    transaction.replace(R.id.fl_categorySecond, new CategoryFragment()).addToBackStack(null).commit();
                } else {
                    showActivity.finish();
                }
            }
        });
    }

    @Override
    public Object getData() {
        return "";
    }


}
