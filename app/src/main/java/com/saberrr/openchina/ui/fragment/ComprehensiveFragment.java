package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FragmentInfo;
import com.saberrr.openchina.ui.adapter.ComprehensiveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class ComprehensiveFragment extends BaseFragment {

    private TabLayout mTabComprehen;
    private ViewPager mVpComprehen;
    private List<FragmentInfo>datas= new ArrayList<>();

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_comprehensive, null);
        mTabComprehen = (TabLayout) view.findViewById(R.id.tab_comprehen);
        mVpComprehen = (ViewPager) view.findViewById(R.id.vp_comprehen);
        init();
        return view;
    }

    private void init() {
        String[]title = new String[]{"资讯","博客","问答","活动"};
        datas.add(new FragmentInfo(new InformationFragment(),title[0]));
        datas.add(new FragmentInfo(new InformationFragment(),title[1]));
        datas.add(new FragmentInfo(new InformationFragment(),title[2]));
        datas.add(new FragmentInfo(new InformationFragment(),title[3]));
        mVpComprehen.setAdapter(new ComprehensiveAdapter(getFragmentManager(),datas));
        mTabComprehen.setupWithViewPager(mVpComprehen);
        int normalColor = Color.parseColor("#9c9c9c");
        int selectedColor = Color.parseColor("#188FE4");
        mTabComprehen.setTabTextColors(normalColor,selectedColor);
        mTabComprehen.setSelectedTabIndicatorColor(selectedColor);

    }

    @Override
    public Object getData() {
        return "";
    }


}
