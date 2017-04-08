package com.saberrr.openchina.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FootBean;
import com.saberrr.openchina.bean.comprehensivebean.BlogBodyBean;
import com.saberrr.openchina.bean.comprehensivebean.BlogHradBean;
import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 丁银晨 on 2017/4/3.
 */

public class BlogFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener, View.OnClickListener {

    @BindView(R.id.recyclerView_blog)
    RecyclerView mRecyclerViewBlog;
    final List<Object> mDatas = new ArrayList<>();
    @BindView(R.id.swipeRefreshLayout_blog)
    SwipeRefreshLayout mSwipeRefreshLayoutBlog;
    private int catalog = 3;
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private String nextPageToken = "";
    private Bitmap mOriginateBitmap;
    private Bitmap mRecommendBitmap;
    private Button mButton;
    private Button mButton2;
    private Button mButton3;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_blog, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {


        HashMap<Class, Integer> map = new HashMap<>();
        map.put(BlogHradBean.class, R.layout.blog_head);
        map.put(BlogBodyBean.ResultBean.ItemsBean.class, R.layout.item_blog_body);
        map.put(FootBean.class,R.layout.foot_item);
        mFinalRecycleAdapter = new FinalRecycleAdapter(mDatas, map, this);
        mRecyclerViewBlog.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewBlog.setAdapter(mFinalRecycleAdapter);
        mRecyclerViewBlog.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mDatas.size() - 1 ) {
                    mLoadingPager.showViewDely(1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mSwipeRefreshLayoutBlog.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSwipeRefreshLayoutBlog.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadingPager.showViewDely(1000);
            }
        });

        mOriginateBitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.mipmap.ic_label_originate);
        mRecommendBitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.mipmap.ic_label_recommend);
    }

    @Override
    public Object getData() {

        if (mSwipeRefreshLayoutBlog.isRefreshing()) {
            nextPageToken = "";
        }
        BlogBodyBean blogBodyBean = JsonCacheManager.getInstance().getDataBean(Urls.BLOG + catalog + Urls.BLOGUP + nextPageToken, BlogBodyBean.class);
        if (mSwipeRefreshLayoutBlog.isRefreshing()){
            mDatas.clear();
        }
        if (blogBodyBean == null) {
            if (mDatas == null || mDatas.size() == 0) {
                return null;
            } else {
                ToastUtils.showToast("没有更多数据");
                isLast = true;
            }
        } else {
            List<BlogBodyBean.ResultBean.ItemsBean> bodyItem = blogBodyBean.getResult().getItems();
            nextPageToken = blogBodyBean.getResult().getNextPageToken();
            if (mDatas == null || mDatas.size() == 0) {

                mDatas.add(new BlogHradBean());
                mDatas.addAll(bodyItem);
                mDatas.add(new FootBean());
                isLast = false;
            } else {
                mDatas.addAll(mDatas.size()-1,bodyItem);
                isLast = false;
            }
        }


        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mFinalRecycleAdapter.notifyDataSetChanged();
                mSwipeRefreshLayoutBlog.setRefreshing(false);
            }
        });
        return mDatas;
    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        if (itemData instanceof BlogHradBean) {
            mButton = (Button) holder.getViewById(R.id.bt1_blog);
            mButton2 = (Button) holder.getViewById(R.id.bt2_blog);
            mButton3 = (Button) holder.getViewById(R.id.bt3_blog);
            mButton.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);

        }
        if (itemData instanceof BlogBodyBean.ResultBean.ItemsBean) {

            TextView tvTitleBlog = (TextView) holder.getViewById(R.id.tv_title_blog);
            TextView tvBodyBlog = (TextView) holder.getViewById(R.id.tv_body_blog);
            TextView tvNameBlog = (TextView) holder.getViewById(R.id.tv_name_blog);
            TextView tvTimeBlog = (TextView) holder.getViewById(R.id.tv_time_blog);
            TextView tvVisitBlog = (TextView) holder.getViewById(R.id.tv_visit_blog);
            TextView tvCommentBlog = (TextView) holder.getViewById(R.id.tv_comment_blog);
            //            ImageView iv_originate = (ImageView) holder.getViewById(R.id.iv_originate);
            //            ImageView iv_recommend = (ImageView) holder.getViewById(R.id.iv_recommend);
            BlogBodyBean.ResultBean.ItemsBean bean = (BlogBodyBean.ResultBean.ItemsBean) itemData;
            ImageSpan originateSpan = new ImageSpan(getContext(), mOriginateBitmap);
            ImageSpan recommendSpan = new ImageSpan(getContext(), mRecommendBitmap);


            if (bean.isOriginal()) {
                if (bean.isRecommend()) {
                    String text = "[jin] [jian] " + bean.getTitle();
                    SpannableString spannableString = new SpannableString(text);
                    spannableString.setSpan(originateSpan, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(recommendSpan, 6, 12, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    tvTitleBlog.setText(spannableString);
                }else {
                    String text = "[jin] " + bean.getTitle();
                    SpannableString spannableString = new SpannableString(text);
                    spannableString.setSpan(originateSpan, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    tvTitleBlog.setText(spannableString);
                }
            } else {
                if (bean.isRecommend()){
                    String text = "[jin] " + bean.getTitle();
                    SpannableString spannableString = new SpannableString(text);
                    spannableString.setSpan(recommendSpan, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    tvTitleBlog.setText(spannableString);
                }else {
                    tvTitleBlog.setText(bean.getTitle());
                }

            }

            tvBodyBlog.setText(bean.getBody());
            tvNameBlog.setText(bean.getAuthor());
            tvTimeBlog.setText(StringUtils.friendly_time(bean.getPubDate()));
            tvVisitBlog.setText(bean.getViewCount() + "");
            tvCommentBlog.setText(bean.getCommentCount() + "");

        }
        if (itemData instanceof FootBean){
            TextView tvFoot = (TextView) holder.getViewById(R.id.tv_foot);
            ImageView ivFoot = (ImageView) holder.getViewById(R.id.iv_foot);
            if (isLast){
                tvFoot.setText("没有更多数据");
                ivFoot.setVisibility(View.GONE);
            }else {
                tvFoot.setText("正在加载...");
                ivFoot.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1_blog:
                catalog = 3;
                mSwipeRefreshLayoutBlog.setRefreshing(true);
                mLoadingPager.showViewDely(1000);
                mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_press));
                mButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                mButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_normal));
                mButton2.setTextColor(getResources().getColor(R.color.button_normol));
                mButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_normal));
                mButton3.setTextColor(getResources().getColor(R.color.button_normol));
                break;
            case R.id.bt2_blog:
                catalog = 2;
                mSwipeRefreshLayoutBlog.setRefreshing(true);
                mLoadingPager.showViewDely(1000);
                mButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_press));
                mButton2.setTextColor(getResources().getColor(R.color.colorPrimary));
                mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_normal));
                mButton.setTextColor(getResources().getColor(R.color.button_normol));
                mButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_normal));
                mButton3.setTextColor(getResources().getColor(R.color.button_normol));
                break;
            case R.id.bt3_blog:
                catalog = 1;
                mSwipeRefreshLayoutBlog.setRefreshing(true);
                mLoadingPager.showViewDely(1000);
                mButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_press));
                mButton3.setTextColor(getResources().getColor(R.color.colorPrimary));
                mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_normal));
                mButton.setTextColor(getResources().getColor(R.color.button_normol));
                mButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.blog_button_normal));
                mButton2.setTextColor(getResources().getColor(R.color.button_normol));

                break;
        }
    }

}
