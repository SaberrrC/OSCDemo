package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.InformationBodyBean;
import com.saberrr.openchina.bean.InformationHearBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
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
    private List<InformationBodyBean> bodys = new ArrayList<>();
    private HashMap<Class, Integer>                        layouts   = new HashMap<>();
    private ImageView           mImageView;
    private TextView            mTextView;
    private TestNormalAdapter   mTestNormalAdapter;
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
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

        layouts.put(InformationBodyBean.ResultBean.ItemsBean.class, R.layout.information_body_item);
        mFinalRecycleAdapter = new FinalRecycleAdapter(bodyDatas, layouts, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {
        String nextPageToken = "";
        if (bodys == null || bodys.size()==0){

        }else {
            nextPageToken = bodys.get(0).getResult().getNextPageToken();
        }
        InformationHearBean informationHearBean = JsonCacheManager.getInstance().getDataBean(Urls.BANNER, InformationHearBean.class);
        InformationBodyBean informationBodyBean = JsonCacheManager.getInstance().getDataBean(Urls.NEWS+nextPageToken, InformationBodyBean.class);
        if (informationHearBean == null || informationBodyBean == null) {
            return null;
        }
        bodys.add(0,informationBodyBean);
        List<InformationHearBean.ResultBean.ItemsBean> itemsBeanList = informationHearBean.getResult().getItems();
        List<InformationBodyBean.ResultBean.ItemsBean> badyBeanList = informationBodyBean.getResult().getItems();
        datas.addAll(itemsBeanList);
        bodyDatas.addAll(badyBeanList);
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.requestLayout();
                mRollPagerView.requestLayout();

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
        final InformationBodyBean.ResultBean.ItemsBean itemsBean= (InformationBodyBean.ResultBean.ItemsBean) itemData;
        tvTitleInformation.setText(itemsBean.getTitle());
        tvBodyInformation.setText(itemsBean.getBody());
        tvTimeInformation.setText(itemsBean.getPubDate());
        tvCommentInformation.setText(itemsBean.getCommentCount()+"");
        llCommentInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BLOGDETAILSFRAGMENT.HREF,itemsBean.getHref() );
                bundle.putString(Constant.BLOGDETAILSFRAGMENT.COMMENTCOUNT,itemsBean.getCommentCount()+"");
                ShowActivity.startFragmentWithTitle(BlogDetailsFragment.class,bundle,"博客详情",ShowActivity.TITLE_COMMENT);
                mTestNormalAdapter.notifyDataSetChanged();
            }
        });

    }

    private class TestNormalAdapter extends PagerAdapter {

//        @Override
//        public View getView(ViewGroup container, final int position) {
//
//
////            mImageView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    ToastUtils.showToast("我被点击了"+position);
////                }
////            });
//
//            return view;
//        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(container.getContext(), R.layout.viewpager_item_information, null);
            mImageView = (ImageView) view.findViewById(R.id.iv_item_viewpager_information);
            mTextView = (TextView) view.findViewById(R.id.tv_item_viewpager_information);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(AppApplication.appContext).load(datas.get(position).getImg()).into(mImageView);
            mTextView.setText(datas.get(position).getName());
            mTextView.setTextColor(Color.WHITE);
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   ToastUtils.showToast("我被点击了"+position);
               }
           });
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
