package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.InformationBodyBean;
import com.saberrr.openchina.bean.InformationHearBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class InformationFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private RollPagerView mRollPagerView;
    private RecyclerView  mRecyclerView;
    private List<InformationHearBean.ResultBean.ItemsBean> datas     = new ArrayList<>();
    private List<InformationBodyBean.ResultBean.ItemsBean> bodyDatas = new ArrayList<>();
    private HashMap<Class, Integer>                        layouts   = new HashMap<>();
    private ImageView           mImageView;
    private TextView            mTextView;
    private TestNormalAdapter   mTestNormalAdapter;
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return true;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragmeng_information, null);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.rollPagerView_information);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_information);
        init();
        return view;
    }

    private void init() {
        mRollPagerView.setPlayDelay(2000);
        //设置透明度
        mRollPagerView.setAnimationDurtion(500);
        mTestNormalAdapter = new TestNormalAdapter();
        mRollPagerView.setAdapter(mTestNormalAdapter);
        mRollPagerView.setHintView(new ColorPointHintView(getContext(), Color.GREEN, Color.WHITE));
        mRollPagerView.setHintPadding(20, 20, 20, 20);
        layouts.put(InformationBodyBean.class, R.layout.information_body_item);
        List<Object> list = new ArrayList<>();
        Collections.addAll(list,bodyDatas);
        mFinalRecycleAdapter = new FinalRecycleAdapter(list, layouts, this);
        mRecyclerView.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {
        InformationHearBean informationHearBean = JsonCacheManager.getInstance().getDataBean(Urls.BANNER, InformationHearBean.class);
        InformationBodyBean informationBodyBean = JsonCacheManager.getInstance().getDataBean(Urls.NEWS, InformationBodyBean.class);
        if (informationHearBean == null || informationBodyBean == null) {
            return null;
        }
        List<InformationHearBean.ResultBean.ItemsBean> itemsBeanList = informationHearBean.getResult().getItems();
        List<InformationBodyBean.ResultBean.ItemsBean> badyBeanList = informationBodyBean.getResult().getItems();
        datas.addAll(itemsBeanList);
        bodyDatas.addAll(badyBeanList);
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mTestNormalAdapter.notifyDataSetChanged();
                mFinalRecycleAdapter.notifyDataSetChanged();
            }
        });

        return datas;
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {

        TextView tvTitleInformation = (TextView) holder.getViewById(R.id.tv_title_information);
        TextView tvBodyInformation = (TextView) holder.getViewById(R.id.tv_body_information);
        TextView tvTimeInformation = (TextView) holder.getViewById(R.id.tv_time_information);
        LinearLayout llCommentInformation = (LinearLayout) holder.getViewById(R.id.ll_comment_information);
        TextView tvCommentInformation = (TextView) holder.getViewById(R.id.tv_comment_information);
        InformationBodyBean.ResultBean.ItemsBean itemsBean= (InformationBodyBean.ResultBean.ItemsBean) itemData;
        tvTitleInformation.setText(itemsBean.getTitle());
        tvBodyInformation.setText(itemsBean.getBody());
        tvTimeInformation.setText(itemsBean.getPubDate());
        tvCommentInformation.setText(itemsBean.getCommentCount());
        llCommentInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private class TestNormalAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.viewpager_item_information, null);
            mImageView = (ImageView) view.findViewById(R.id.iv_item_viewpager_information);
            mTextView = (TextView) view.findViewById(R.id.tv_item_viewpager_information);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(AppApplication.appContext).load(datas.get(position).getImg()).into(mImageView);
            mTextView.setText(datas.get(position).getName());
            mTextView.setTextColor(Color.WHITE);

            return view;
        }


        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
