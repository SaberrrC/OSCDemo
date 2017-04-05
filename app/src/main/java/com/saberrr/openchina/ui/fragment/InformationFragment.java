package com.saberrr.openchina.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
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
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class InformationFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {

    private RecyclerView mRecyclerView;
    private List<Object>              mDatas  = new ArrayList<>();
    private List<InformationBodyBean> bodys   = new ArrayList<>();
    private HashMap<Class, Integer>   layouts = new HashMap<>();
    private ImageView           mImageView;
    private TextView            mTextView;
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private String nextPageToken = "";
    private Bitmap recordBitmap;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragmeng_information, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_information);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_information);
        init();
        return view;
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initSwipeRefreshLayout();

        layouts.put(InformationHearBean.class, R.layout.news_head);
        layouts.put(InformationBodyBean.ResultBean.ItemsBean.class, R.layout.information_body_item);
        mFinalRecycleAdapter = new FinalRecycleAdapter(mDatas, layouts, this);
        mRecyclerView.setAdapter(mFinalRecycleAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mDatas.size() - 1 && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    mLoadingPager.showViewDely(1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recordBitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.mipmap.ic_label_today);

    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.GREEN, Color.BLUE, Color.RED);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadingPager.showViewDely(1000);
            }
        });
    }

    //    private void initViewPager() {
    //        mRollPagerView.setPlayDelay(2000);
    //        //设置透明度
    //        mRollPagerView.setAnimationDurtion(500);
    //        mTestNormalAdapter = new TestNormalAdapter();
    //        mRollPagerView.setAdapter(mTestNormalAdapter);
    //
    //        mRollPagerView.setHintView(new ColorPointHintView(getContext(), Color.GREEN, Color.WHITE));
    //        mRollPagerView.setHintPadding(20, 20, 20, 20);
    //    }

    @Override
    public Object getData() {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mDatas.clear();
            nextPageToken = "";
        }
        InformationHearBean informationHearBean = JsonCacheManager.getInstance().getDataBean(Urls.BANNER, InformationHearBean.class);
        InformationBodyBean informationBodyBean = JsonCacheManager.getInstance().getDataBean(Urls.NEWS + nextPageToken, InformationBodyBean.class);
        if (informationBodyBean == null) {
            if (mDatas == null || mDatas.size() == 0) {
                return null;
            } else {
                ToastUtils.showToast("没有更多数据");
            }
        } else {
            List<InformationBodyBean.ResultBean.ItemsBean> badyBeanList = informationBodyBean.getResult().getItems();
            nextPageToken = informationBodyBean.getResult().getNextPageToken();
            if (mDatas == null || mDatas.size() == 0) {

                mDatas.add(informationHearBean);
                mDatas.addAll(badyBeanList);
            } else {
                mDatas.addAll(badyBeanList);
            }
        }

        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.requestLayout();
                mFinalRecycleAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return mDatas;
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        if (itemData instanceof InformationHearBean) {
            InformationHearBean hearBean = (InformationHearBean) itemData;
            List<InformationHearBean.ResultBean.ItemsBean> list = hearBean.getResult().getItems();
            RollPagerView rollPagerView = (RollPagerView) holder.getViewById(R.id.rollPagerView_information);
            TestNormalAdapter testNormalAdapter = new TestNormalAdapter(list);
            rollPagerView.setAdapter(testNormalAdapter);
            rollPagerView.setPlayDelay(2000);
            //设置透明度
            rollPagerView.setAnimationDurtion(500);

            rollPagerView.setHintView(new ColorPointHintView(getContext(), Color.GREEN, Color.WHITE));
            rollPagerView.setHintPadding(20, 20, 20, 20);
        }
        if (itemData instanceof InformationBodyBean.ResultBean.ItemsBean) {
            TextView tvTitleInformation = (TextView) holder.getViewById(R.id.tv_title_information);
            TextView tvBodyInformation = (TextView) holder.getViewById(R.id.tv_body_information);
            TextView tvTimeInformation = (TextView) holder.getViewById(R.id.tv_time_information);
            LinearLayout llCommentInformation = (LinearLayout) holder.getViewById(R.id.ll_comment_information);
            TextView tvCommentInformation = (TextView) holder.getViewById(R.id.tv_comment_information);
            final InformationBodyBean.ResultBean.ItemsBean itemsBean = (InformationBodyBean.ResultBean.ItemsBean) itemData;

            ImageSpan recordImg = new ImageSpan(getContext(),
                    recordBitmap);
            String text = "[icon]" + itemsBean.getTitle();
            SpannableString spannableString = new SpannableString(text);
            if (StringUtils.isToday(itemsBean.getPubDate())) {
                spannableString.setSpan(recordImg, 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            } else {
                spannableString.setSpan("", 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            tvTitleInformation.setText(spannableString);
            tvBodyInformation.setText(itemsBean.getBody());
            tvTimeInformation.setText(StringUtils.friendly_time(itemsBean.getPubDate()));
            tvCommentInformation.setText(itemsBean.getCommentCount() + "");
            llCommentInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.BLOGDETAILSFRAGMENT.HREF, itemsBean.getHref());
                    bundle.putString(Constant.BLOGDETAILSFRAGMENT.COMMENTCOUNT, itemsBean.getCommentCount() + "");
                    ShowActivity.startFragmentWithTitle(BlogDetailsFragment.class, bundle, "博客详情", ShowActivity.TITLE_COMMENT);
                }
            });
        }


    }

    private class TestNormalAdapter extends PagerAdapter {


        private final List<InformationHearBean.ResultBean.ItemsBean> datas;

        public TestNormalAdapter(List<InformationHearBean.ResultBean.ItemsBean> list) {
            this.datas = list;
        }

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
                    ToastUtils.showToast("我被点击了" + position);
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
