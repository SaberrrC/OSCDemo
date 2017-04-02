package com.saberrr.openchina.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.LoginBean;
import com.saberrr.openchina.event.LoginBeanEvent;
import com.saberrr.openchina.manager.netmanager.RetrofitUtil;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

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
    private int[] genderRid = {R.mipmap.ic_male, R.mipmap.ic_female};
    private LoginBean mLoginBean;


    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frag_my, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
//        Glide.with(getContext()).load(R.mipmap.).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                mIvAvtarOffline.setImageDrawable(circularBitmapDrawable);
//            }
//        });

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLoginEvent(LoginBeanEvent event) {
        if (LoginBeanEvent.cookie != null) {

            mRlOffline.setVisibility(View.GONE);
            mRlOnline.setVisibility(View.VISIBLE);
            // TODO: 2017/4/2 获取参数，设置ui
            mLoginBean = event.mLoginBean;

            mTvUsername.setText(mLoginBean.getUser().getName());
            mTvScoreCount.setText(mLoginBean.getUser().getScore());
            mTvFavoriteCount.setText(mLoginBean.getUser().getFavoritecount());
            mTvFollowersCount.setText(mLoginBean.getUser().getFollowers());
            mTvFansCount.setText(mLoginBean.getUser().getFans());
            mIvGender.setImageResource(genderRid[Integer.parseInt(mLoginBean.getUser().getGender()) -1]);
            System.out.println(mLoginBean.getUser().getPortrait());
            Glide.with(getContext()).load(mLoginBean.getUser().getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIvAvtarOffline) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mIvAvtarOnline.setImageDrawable(circularBitmapDrawable);
                }
            });


        } else {
            mRlOffline.setVisibility(View.VISIBLE);
            mRlOnline.setVisibility(View.GONE);
        }



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



    @OnClick({R.id.rl_offline, R.id.iv_avtar_online, R.id.iv_mark_online,  R.id.ll_score, R.id.ll_favorite, R.id.ll_followers, R.id.ll_fans, R.id.rl_my_msg, R.id.rl_my_blog, R.id.rl_my_team, R.id.rl_my_event, R.id.rl_my_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_offline:
                ShowActivity.startFragmentWithTitle(LoginFragment.class, null  , "登录");
                break;
            case R.id.iv_avtar_online:
                Bundle bundle = new Bundle();
                bundle.putSerializable("UERINFO" , mLoginBean);
                ShowActivity.startFragmentWithTitle(MyInfoFragment.class, bundle , "我的资料");

                break;
            case R.id.iv_mark_online:
                break;
            case R.id.ll_score:
                ToastUtils.showToast("积分");
                break;
            case R.id.ll_favorite:
                ToastUtils.showToast("收藏");
                break;
            case R.id.ll_followers:
                ToastUtils.showToast("关注");
                break;
            case R.id.ll_fans:
                ToastUtils.showToast("粉丝");

                break;
            case R.id.rl_my_msg:
                ToastUtils.showToast("我的消息");
                break;
            case R.id.rl_my_blog:
                ToastUtils.showToast("我的微博");
                break;
            case R.id.rl_my_team:
                ToastUtils.showToast("我的团队");
                break;
            case R.id.rl_my_event:
                ToastUtils.showToast("我的活动");
                break;
            case R.id.rl_my_setting:
                ToastUtils.showToast("设置");
                break;
        }
    }
}
