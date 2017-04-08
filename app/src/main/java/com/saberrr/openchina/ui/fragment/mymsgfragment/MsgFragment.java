package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.mymsgcenter.FansBean;
import com.saberrr.openchina.bean.mymsgcenter.MsgBean;
import com.saberrr.openchina.bean.mymsgcenter.TweetLikeBean;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.fragment.BaseFragment;
import com.saberrr.openchina.ui.fragment.LoginFragment;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;
import com.saberrr.openchina.utils.XmlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by 2017 on 2017/4/3.
 */

public class MsgFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.rv)
    RecyclerView mRvTweet;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.ly_error)
    LinearLayout mLyerror;
    private String mCookie;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    private List<MsgBean.Message> mItemList = new ArrayList();
    private FinalRecycleAdapter mRecycleAdapter;
    private int[] genderRid = {0, R.mipmap.ic_male, R.mipmap.ic_female};
    private String mUserid;

    @Override
    protected boolean needRefresh() {

        return false;
    }

    @Override
    public View createView() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tweetlike, null);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {


        mCookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
        mUserid = SpUtil.getString(getContext(), Constant.USERID, "");
        mLyerror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTvResult.getText().toString().equals("当前未登录")) {
                    ShowActivity.startFragment(LoginFragment.class, null);

                } else {
                    ThreadUtils.runSub(new Runnable() {
                        @Override
                        public void run() {
                            getNetData(true);
                        }
                    });
                }

            }
        });
        Map<Class, Integer> map = new HashMap<>();
        map.put(MsgBean.Message.class, R.layout.item_msg);
        mRecycleAdapter = new FinalRecycleAdapter(mItemList, map, this);
        mRvTweet.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvTweet.setAdapter(mRecycleAdapter);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ThreadUtils.runSub(new Runnable() {
                    @Override
                    public void run() {
                        getNetData(true);

                    }
                });
            }
        });
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mRvTweet.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mItemList.size() - 1 && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    ThreadUtils.runBigSub(new Runnable() {
                        @Override
                        public void run() {
                            getNetData(false);
                            ToastUtils.showToast("加载更多数据");
                        }
                    });
                }
            }
        });

    }

    @Override
    public Object getData() {

        getNetData(true);

        return "";
    }

    //子线程
    private void getNetData(final boolean isRefresh) {

        if (TextUtils.isEmpty(mCookie)) {
            ThreadUtils.runMain(new Runnable() {
                @Override
                public void run() {
                    mLyerror.setVisibility(View.VISIBLE);
                    mSrl.setVisibility(View.GONE);
                    mTvResult.setText("当前未登录");
                    mSrl.setRefreshing(false);
                }
            });

        } else {
            HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
            try {
                Response<ResponseBody> response = httpServiceApi.getMsgs(mCookie, mItemList.size()/Constant.PAGESIZE, mUserid , Constant.PAGESIZE).execute();
                String result = response.body().string();
//                System.out.println(result);

                MsgBean msgBean = XmlUtils.toBean(MsgBean.class, result.getBytes());
                final List<MsgBean.Message> messages = msgBean.getMessage();
//                System.out.println(messages.size());
                ThreadUtils.runMain(new Runnable() {
                    @Override
                    public void run() {


                        if (isRefresh && messages.size() != 0) {
                            mItemList.clear();
                        }

                        mItemList.addAll(messages);
                        if (mItemList.size() == 0) {
                            mLyerror.setVisibility(View.VISIBLE);
                            mSrl.setVisibility(View.GONE);
                            mTvResult.setText("当前无数据");
                        } else {

                            mLyerror.setVisibility(View.GONE);
                            mSrl.setVisibility(View.VISIBLE);
                            mRecycleAdapter.notifyDataSetChanged();
                            ToastUtils.showToast("数据加载完成");
                        }
                        mSrl.setRefreshing(false);
                    }

                });

            } catch (IOException e) {
                e.printStackTrace();

                if (mItemList.size() == 0) {
                    mLyerror.setVisibility(View.VISIBLE);
                    mSrl.setVisibility(View.GONE);
                    mTvResult.setText("网络错误");
                    mSrl.setRefreshing(false);
                }


            }
        }


    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, final int position, Object itemData) {

        View rootView = holder.getRootView();
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了条目，跳转到聊天对话框" + mItemList.get(position).getSenderid());

            }
        });
        TextView username = (TextView) holder.getViewById(R.id.tv_username);
        username.setText(mItemList.get(position).getFriendname());


        final ImageView ivheader = (ImageView) holder.getViewById(R.id.iv_header);
        ivheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showToast("点击了头像，跳转进账户中心" + mItemList.get(position).getFriendid());
            }
        });
        if (TextUtils.isEmpty(mItemList.get(position).getPortrait())) {
            Glide.with(getContext()).load(R.mipmap.widget_dface).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivheader) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivheader.setImageDrawable(circularBitmapDrawable);
                }

            });
        } else {
            Glide.with(getContext()).load(mItemList.get(position).getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivheader) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivheader.setImageDrawable(circularBitmapDrawable);
                }

            });
        }
//方向
        TextView direction = (TextView) holder.getViewById(R.id.tv_direction);
        if (mItemList.get(position).getFriendid().equals(mItemList.get(position).getSenderid())) {
            direction.setVisibility(View.GONE);
        } else {
            direction.setVisibility(View.VISIBLE);
        }


        TextView content = (TextView) holder.getViewById(R.id.tv_chat_content);
        String text = mItemList.get(position).getContent();
        Spannable spannable = Utils.displayEmoji(getContext().getResources(), text);
        content.setText(spannable);



        TextView time = (TextView) holder.getViewById(R.id.tv_time);
        time.setText(StringUtils.friendly_time(mItemList.get(position).getPubDate()));

        TextView count = (TextView) holder.getViewById(R.id.tv_chat_count);
        count.setText(mItemList.get(position).getMessageCount()+"条私信");


    }


}
