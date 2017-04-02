package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.ui.activity.ShowActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 2017 on 2017/4/1.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.iv_avtar_offline)
    ImageView mIvAvtarOffline;
    @BindView(R.id.rl_offline)
    RelativeLayout mRlOffline;
    @BindView(R.id.iv_avtar_online)
    ImageView mIvAvtarOnline;
    @BindView(R.id.iv_gender)
    ImageView mIvGender;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.iv_mark_online)
    ImageView mIvMarkOnline;
    @BindView(R.id.tv_score_count)
    TextView mTvScoreCount;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.ll_score)
    LinearLayout mLlScore;
    @BindView(R.id.tv_favorite_count)
    TextView mTvFavoriteCount;
    @BindView(R.id.tv_favorite)
    TextView mTvFavorite;
    @BindView(R.id.ll_favorite)
    LinearLayout mLlFavorite;
    @BindView(R.id.tv_followers_count)
    TextView mTvFollowersCount;
    @BindView(R.id.tv_followers)
    TextView mTvFollowers;
    @BindView(R.id.ll_followers)
    LinearLayout mLlFollowers;
    @BindView(R.id.tv_fans_count)
    TextView mTvFansCount;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.ll_fans)
    LinearLayout mLlFans;
    @BindView(R.id.rl_online)
    LinearLayout mRlOnline;
    @BindView(R.id.fl_top)
    RelativeLayout mFlTop;
    @BindView(R.id.iv_my_msg)
    ImageView mIvMyMsg;
    @BindView(R.id.tv_my_msg)
    TextView mTvMyMsg;
    @BindView(R.id.rl_my_msg)
    RelativeLayout mRlMyMsg;
    @BindView(R.id.iv_my_blog)
    ImageView mIvMyBlog;
    @BindView(R.id.tv_my_blog)
    TextView mTvMyBlog;
    @BindView(R.id.rl_my_blog)
    RelativeLayout mRlMyBlog;
    @BindView(R.id.iv_my_team)
    ImageView mIvMyTeam;
    @BindView(R.id.tv_my_team)
    TextView mTvMyTeam;
    @BindView(R.id.rl_my_team)
    RelativeLayout mRlMyTeam;
    @BindView(R.id.iv_my_event)
    ImageView mIvMyEvent;
    @BindView(R.id.tv_my_event)
    TextView mTvMyEvent;
    @BindView(R.id.rl_my_event)
    RelativeLayout mRlMyEvent;
    @BindView(R.id.iv_my_setting)
    ImageView mIvMySetting;
    @BindView(R.id.tv_my_setting)
    TextView mTvMySetting;
    @BindView(R.id.rl_my_setting)
    RelativeLayout mRlMySetting;

    private boolean isOnline;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frag_my, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLoginEvent(LoginBeanEvent event) {

        mRlOffline.setVisibility(View.GONE);
        mRlOnline.setVisibility(View.VISIBLE);
        // TODO: 2017/4/2 获取参数，设置ui


        EventBus.getDefault().removeAllStickyEvents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public Object getData() {
        return "";
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.rl_offline:
//                ToastUtils.showToast("点击了登录");
//
//                ShowActivity.startFragment(LoginFragment.class , null);
//                break;
//
//            case R.id.rl_my_msg:
//                ToastUtils.showToast("点击了我的消息");
//                ShowActivity.startFragment(MyMsgFragment.class , null);
//                break;
//        }


//    @Override
//    public void onResume() {
//        super.onResume();
//        // TODO: 2017/4/2 每次重新显示时要判断当前是否登录
//        if (isOnline) {
//            //登录状态，显示登录的头条目 ， 显示相关信息
//            mRlOffline.setVisibility(View.GONE);
//            mRlOnline.setVisibility(View.VISIBLE);
//
//
//        } else {
//            mRlOffline.setVisibility(View.VISIBLE);
//            mRlOnline.setVisibility(View.GONE);
//        }
//    }


    @OnClick({R.id.rl_offline, R.id.iv_avtar_online, R.id.iv_mark_online, R.id.tv_score_count, R.id.ll_score, R.id.tv_favorite_count, R.id.ll_favorite, R.id.tv_followers_count, R.id.ll_followers, R.id.tv_fans_count, R.id.ll_fans, R.id.rl_my_msg, R.id.rl_my_blog, R.id.rl_my_team, R.id.rl_my_event, R.id.rl_my_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_offline:
                break;
            case R.id.iv_avtar_online:
                ShowActivity.startFragment(MyInfoFragment.class, null);
                break;
            case R.id.iv_mark_online:
                ShowActivity.startFragment(LoginFragment.class, null);
                break;
            case R.id.tv_score_count:
                break;
            case R.id.ll_score:
                break;
            case R.id.tv_favorite_count:
                break;
            case R.id.ll_favorite:
                break;
            case R.id.tv_followers_count:
                break;
            case R.id.ll_followers:
                break;
            case R.id.tv_fans_count:
                break;
            case R.id.ll_fans:
                break;
            case R.id.rl_my_msg:
                break;
            case R.id.rl_my_blog:
                break;
            case R.id.rl_my_team:
                break;
            case R.id.rl_my_event:
                break;
            case R.id.rl_my_setting:
                break;
        }
    }
}
