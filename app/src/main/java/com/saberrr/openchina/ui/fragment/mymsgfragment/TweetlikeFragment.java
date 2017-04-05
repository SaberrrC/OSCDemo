package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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

public class TweetlikeFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.rv)
    RecyclerView mRvTweet;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.ly_error)
    LinearLayout mLyerror;
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

    }

    @Override
    public Object getData() {

        getNetData(true);

        return "";
    }

    //子线程
    private void getNetData(boolean isRefresh) {
        if (isRefresh) {
            mItemList.clear();
        }
        if (TextUtils.isEmpty(mCookie)) {
            ThreadUtils.runMain(new Runnable() {
                @Override
                public void run() {
                    mLyerror.setVisibility(View.VISIBLE);
                    mRvTweet.setVisibility(View.GONE);
                    mTvResult.setText("当前未登录");
                }
            });

        } else {
            HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
            try {
                Response<ResponseBody> response = httpServiceApi.getTweetlike(mCookie, 0, 20).execute();
                String result = response.body().string();
                System.out.println(result);


                TweetLikeBean tweetLikeBean = XmlUtils.toBean(TweetLikeBean.class, result.getBytes());
                List<TweetLikeBean.Mytweet> likeList = tweetLikeBean.getLikeList();
                System.out.println(likeList.size());
                mItemList.addAll(likeList);
                System.out.println(mItemList.size());

                ThreadUtils.runMain(new Runnable() {
                    @Override
                    public void run() {
                        mLyerror.setVisibility(View.GONE);
                        mRvTweet.setVisibility(View.VISIBLE);
                        mRecycleAdapter.notifyDataSetChanged();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();

            }
        }


    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, final int position, Object itemData) {


        TextView username = (TextView) holder.getViewById(R.id.tv_username);
        username.setText(mItemList.get(position).getUser().getName());
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了用户名，跳转进账户中心" +mItemList.get(position).getUser().getUid());

            }
        });


        final ImageView ivheader = (ImageView) holder.getViewById(R.id.iv_header);
        ivheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showToast("点击了头像，跳转进账户中心" +mItemList.get(position).getUser().getUid());
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

        String author = mItemList.get(position).getTweet().getAuthor();
        String string = author + ":" + mItemList.get(position).getTweet().getBody();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, author.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        myself.setMovementMethod(LinkMovementMethod.getInstance());
        spannableStringBuilder.setSpan(new TextClick() {
            @Override
            public void onClick(View widget) {
                super.onClick(widget);
                ToastUtils.showToast("点击了自己，跳转进账户中心" +  mItemList.get(position).getTweet().getAuthor());
            }
        }, 0, author.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        myself.setText(spannableStringBuilder);


//        myself.setText(mItemList.get(position).getTweet().getAuthor() + ":" + mItemList.get(position).getTweet().getBody());


        TextView time = (TextView) holder.getViewById(R.id.tv_time);
        time.setText(StringUtils.friendly_time(mItemList.get(position).getDatatime()));


    }

    private class TextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) {


        }
    }
}
