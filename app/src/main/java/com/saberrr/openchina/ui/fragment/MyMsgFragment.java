package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MyMsgInfo;
import com.saberrr.openchina.ui.adapter.MyMsgAdapter;
import com.saberrr.openchina.ui.fragment.mymsgfragment.AtMeFragment;
import com.saberrr.openchina.ui.fragment.mymsgfragment.CommentFragment;
import com.saberrr.openchina.ui.fragment.mymsgfragment.FansFragment;
import com.saberrr.openchina.ui.fragment.mymsgfragment.MsgFragment;
import com.saberrr.openchina.ui.fragment.mymsgfragment.TweetlikeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 2017 on 2017/4/2.
 */

public class MyMsgFragment extends BaseFragment {
    private static String Title[] = {"@我", "评论", "私信", "粉丝", "赞过我"};

    @BindView(R.id.tl_title)
    TabLayout mTlTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    private List<MyMsgInfo> mDataList = new ArrayList<>();

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_msg, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        // TODO: 2017/4/2 展示数据
        mDataList.add(new MyMsgInfo(new CommentFragment(), Title[0]));
        mDataList.add(new MyMsgInfo(new CommentFragment(), Title[1]));
        mDataList.add(new MyMsgInfo(new MsgFragment(), Title[2]));
        mDataList.add(new MyMsgInfo(new FansFragment(), Title[3]));
        mDataList.add(new MyMsgInfo(new TweetlikeFragment(), Title[4]));


        mVpContent.setAdapter(new MyMsgAdapter(getChildFragmentManager() , mDataList));

        mTlTitle.setupWithViewPager(mVpContent);
        mTlTitle.setTabTextColors(Color.parseColor("#9c9c9c"), getResources().getColor(R.color.colorPrimary));
        mTlTitle.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        mVpContent.setCurrentItem(0);

    }

    @Override
    public Object getData() {
        return "";
    }



}
