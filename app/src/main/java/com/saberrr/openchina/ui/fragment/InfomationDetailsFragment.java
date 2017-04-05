package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.comprehensivebean.InfomationDetailBean;
import com.saberrr.openchina.bean.comprehensivebean.TitleBean;
import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 丁银晨 on 2017/4/2.
 */

public class InfomationDetailsFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    @BindView(R.id.recyclerView_informationDetail)
    RecyclerView mRecyclerViewInformationDetail;
    private String mUrl;
    private String mCount;
    private int    mId;
    private List<Object> mDatas = new ArrayList();
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;

    }

    @Override
    public View createView() {
        Bundle bundle = getArguments();
        mUrl = bundle.getString(Constant.BLOGDETAILSFRAGMENT.HREF);
        mCount = bundle.getString(Constant.BLOGDETAILSFRAGMENT.COMMENTCOUNT);
        mId = bundle.getInt(Constant.BLOGDETAILSFRAGMENT.ID);
        setCommentCount(mCount);
        View view = View.inflate(getContext(), R.layout.fragment_blogdatails, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        HashMap<Class, Integer> map = new HashMap<>();
        map.put(InfomationDetailBean.class, R.layout.informationdetailwap);
        map.put(TitleBean.class, R.layout.informationdetailtitle);
        map.put(InfomationDetailBean.ResultBean.AboutsBean.class, R.layout.informationdetailrecommend);
        mFinalRecycleAdapter = new FinalRecycleAdapter(mDatas, map, this);
        mRecyclerViewInformationDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewInformationDetail.setAdapter(mFinalRecycleAdapter);

    }


    //    private void init() {
    //        WebSettings settings = mWbBolgdetails.getSettings();
    //        settings.setJavaScriptEnabled(true);
    //        settings.setBuiltInZoomControls(true);
    //        settings.setUseWideViewPort(true);
    //        mWbBolgdetails.loadUrl(mUrl);
    //        mWbBolgdetails.setWebViewClient(new WebViewClient() {
    //
    //
    //        });


    @Override
    public Object getData() {
        InfomationDetailBean infomationDetailBean = JsonCacheManager.getInstance().getDataBean(Urls.CONTENT + mId, InfomationDetailBean.class);
        if (infomationDetailBean == null) {
            return null;
        }
        List<InfomationDetailBean.ResultBean.AboutsBean> aboutsBeen = infomationDetailBean.getResult().getAbouts();
        mDatas.add(infomationDetailBean);
        mDatas.add(new TitleBean());
        mDatas.addAll(aboutsBeen);

        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mFinalRecycleAdapter.notifyDataSetChanged();
            }
        });
        return mDatas;
    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        if (itemData instanceof InfomationDetailBean) {
            InfomationDetailBean bean = (InfomationDetailBean) itemData;
            TextView tvTitleInformationdetailwap = (TextView) holder.getViewById(R.id.tv_title_informationdetailwap);
            TextView tvTimeInformationdetailwap = (TextView) holder.getViewById(R.id.tv_time_informationdetailwap);
            WebView wbInformationdetailwap = (WebView) holder.getViewById(R.id.wb_informationdetailwap);
            tvTitleInformationdetailwap.setText(bean.getResult().getTitle());
            tvTimeInformationdetailwap.setText(bean.getTime() + "");
            WebSettings settings = wbInformationdetailwap.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(true);
            settings.setUseWideViewPort(true);
            wbInformationdetailwap.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent ev) {

                    ((WebView) v).requestDisallowInterceptTouchEvent(true);


                    return false;
                }
            });
            wbInformationdetailwap.setWebViewClient(new WebViewClient() {


            });
            wbInformationdetailwap.loadDataWithBaseURL("", bean.getResult().getBody(), "text/html", "utf-8", "");
        }
        if (itemData instanceof TitleBean) {
            TextView title = (TextView) holder.getViewById(R.id.tv_informationdetailtitle);
            title.setText("相关内容");
        }
        if (itemData instanceof InfomationDetailBean.ResultBean.AboutsBean) {
            InfomationDetailBean.ResultBean.AboutsBean bean = (InfomationDetailBean.ResultBean.AboutsBean) itemData;

            RelativeLayout relativeLayoutInforecommend = (RelativeLayout) holder.getViewById(R.id.relativeLayout_inforecommend);
            TextView tvTitelInforecommend = (TextView) holder.getViewById(R.id.tv_titel_inforecommend);
            ImageView ivInforecommend = (ImageView) holder.getViewById(R.id.iv_inforecommend);
            TextView tvCountInforecommend = (TextView) holder.getViewById(R.id.tv_count_inforecommend);
            tvTitelInforecommend.setText(bean.getTitle());
            tvCountInforecommend.setText(bean.getCommentCount()+"");
        }

    }
}

