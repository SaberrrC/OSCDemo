package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.mymsgcenter.TweetLikeBean;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.fragment.BaseFragment;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.Utils;
import com.saberrr.openchina.utils.XmlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by 2017 on 2017/4/3.
 */

public class TweetlikeFragment extends BaseFragment {
    @BindView(R.id.rv_tweet)
    RecyclerView mRvTweet;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.ll_result)
    LinearLayout mLlResult;
    private String mCookie;
    private List<String> mItemList = new ArrayList();

    @Override
    protected boolean needRefresh() {

        return true;
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


//        mRvTweet.setAdapter(new FinalRecycleAdapter());

    }

    @Override
    public Object getData() {
        getNetData(true);


        return "";
    }

    //子线程
    private void getNetData(boolean isRefresh) {
        if (TextUtils.isEmpty(mCookie)) {
            ThreadUtils.runMain(new Runnable() {
                @Override
                public void run() {
                    mLlResult.setVisibility(View.VISIBLE);
                    mRvTweet.setVisibility(View.GONE);
                    mTvResult.setText("当前未登录");
                }
            });

        } else {
            HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
            try {
                Response<ResponseBody> response = httpServiceApi.getTweetlike(mCookie, mItemList.size() + "", Constant.PAGESIZE).execute();

                String result = response.body().string();

                XmlUtils.toBean(TweetLikeBean.class, result.getBytes());

            } catch (IOException e) {
                e.printStackTrace();

            }
        }


    }


}
