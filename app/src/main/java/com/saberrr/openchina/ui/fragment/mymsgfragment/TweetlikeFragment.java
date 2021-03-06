package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
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
import com.saberrr.openchina.bean.mymsgcenter.TweetLikeBean;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.activity.UserCenterActivity;
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

public class TweetlikeFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.rv)
    RecyclerView mRvTweet;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.ly_error)
    LinearLayout mLyerror;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    private String mCookie;
    private List<TweetLikeBean.Mytweet> mItemList = new ArrayList();
    private FinalRecycleAdapter mRecycleAdapter;

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
        map.put(TweetLikeBean.Mytweet.class, R.layout.item_tweetlike);
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
                if (lastVisibleItemPosition == mItemList.size() - 1 && newState == RecyclerView.SCROLL_STATE_DRAGGING&&mItemList.size()%Constant.PAGESIZE==0) {
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
                    mRvTweet.setVisibility(View.GONE);
                    mTvResult.setText("当前未登录");
                    mSrl.setRefreshing(false);
                }
            });

        } else {
            HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
            try {
                int pageIndex = 0;
                if (!isRefresh) {
                    pageIndex =mItemList.size() / (Constant.PAGESIZE);
                }
                Response<ResponseBody> response = httpServiceApi.getTweetlike(mCookie, pageIndex, Constant.PAGESIZE).execute();
                String result = response.body().string();
//                System.out.println(result);


                TweetLikeBean tweetLikeBean = XmlUtils.toBean(TweetLikeBean.class, result.getBytes());
                final List<TweetLikeBean.Mytweet> likeList = tweetLikeBean.getLikeList();
//                System.out.println(likeList.size());
                ThreadUtils.runMain(new Runnable() {
                    @Override
                    public void run() {

                        if (isRefresh && likeList.size() != 0) {
                            mItemList.clear();
                        }

                        mItemList.addAll(likeList);
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
                    ThreadUtils.runMain(new Runnable() {
                        @Override
                        public void run() {
                            mLyerror.setVisibility(View.VISIBLE);
                            mRvTweet.setVisibility(View.GONE);
                            mTvResult.setText("网络错误");
                            mSrl.setRefreshing(false);
                        }
                    });

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
                ToastUtils.showToast("点击了条目，跳转到动弹详情" + mItemList.get(position).getTweet().getId());

            }
        });
        TextView username = (TextView) holder.getViewById(R.id.tv_username);
        username.setText(mItemList.get(position).getUser().getName());



        final ImageView ivheader = (ImageView) holder.getViewById(R.id.iv_header);
        ivheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showToast("点击了头像，跳转进账户中心" + mItemList.get(position).getUser().getUid());
                Intent userCenterVeiw = new Intent(getContext(), UserCenterActivity.class);
                userCenterVeiw.putExtra(Constant.USERID, mItemList.get(position).getUser().getUid());
                startActivity(userCenterVeiw);
            }
        });

        if (TextUtils.isEmpty(mItemList.get(position).getUser().getPortrait())) {
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
            Glide.with(getContext()).load(mItemList.get(position).getUser().getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivheader) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivheader.setImageDrawable(circularBitmapDrawable);
                }

            });
        }

        TextView myself = (TextView) holder.getViewById(R.id.tv_myself);


        SpannableStringBuilder text = Utils.parseActiveReply(mItemList.get(position).getTweet().getAuthor(), mItemList.get(position).getTweet().getBody());
        Spannable spannable = Utils.displayEmoji(getContext().getResources(), text);

        myself.setText(spannable);

        /*String author = mItemList.get(position).getTweet().getAuthor();
        String string = author + ":" + mItemList.get(position).getTweet().getBody();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, author.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        myself.setText(spannableStringBuilder);*/


//        myself.setText(mItemList.get(position).getTweet().getAuthor() + ":" + mItemList.get(position).getTweet().getBody());


        TextView time = (TextView) holder.getViewById(R.id.tv_time);
        time.setText(StringUtils.friendly_time(mItemList.get(position).getDatatime()));


    }



}
