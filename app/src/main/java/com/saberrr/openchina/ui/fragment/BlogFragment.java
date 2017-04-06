package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
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

        mFinalRecycleAdapter = new FinalRecycleAdapter(mDatas, map, this);
        mRecyclerViewBlog.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewBlog.setAdapter(mFinalRecycleAdapter);
        mRecyclerViewBlog.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mDatas.size()-1&& newState == RecyclerView.SCROLL_STATE_DRAGGING){
                        mLoadingPager.showViewDely(1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mSwipeRefreshLayoutBlog.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        mSwipeRefreshLayoutBlog.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadingPager.showViewDely(1000);
            }
        });
    }

    @Override
    public Object getData() {

        if (mSwipeRefreshLayoutBlog.isRefreshing()) {
            mDatas.clear();
            nextPageToken = "";
        }
        BlogBodyBean blogBodyBean = JsonCacheManager.getInstance().getDataBean(Urls.BLOG + catalog + Urls.BLOGUP+nextPageToken, BlogBodyBean.class);
       if (blogBodyBean == null){
           if (mDatas == null || mDatas.size() == 0){
               return null;
           }else {
               ToastUtils.showToast("没有更多数据");
           }
       }else {
        List<BlogBodyBean.ResultBean.ItemsBean> bodyItem = blogBodyBean.getResult().getItems();
        nextPageToken = blogBodyBean.getResult().getNextPageToken();
            if (mDatas == null || mDatas.size() == 0){

                mDatas.add(new BlogHradBean());
                mDatas.addAll(bodyItem);
            }else {
                mDatas.addAll(bodyItem);
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
            Button button = (Button) holder.getViewById(R.id.bt1_blog);
            Button button2 = (Button) holder.getViewById(R.id.bt2_blog);
            Button button3 = (Button) holder.getViewById(R.id.bt3_blog);
            button.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);

        }
        if (itemData instanceof BlogBodyBean.ResultBean.ItemsBean) {

            TextView tvTitleBlog = (TextView) holder.getViewById(R.id.tv_title_blog);
            TextView tvBodyBlog = (TextView) holder.getViewById(R.id.tv_body_blog);
            TextView tvNameBlog = (TextView) holder.getViewById(R.id.tv_name_blog);
            TextView tvTimeBlog = (TextView) holder.getViewById(R.id.tv_time_blog);
            TextView tvVisitBlog = (TextView) holder.getViewById(R.id.tv_visit_blog);
            TextView tvCommentBlog = (TextView) holder.getViewById(R.id.tv_comment_blog);
            ImageView iv_originate = (ImageView) holder.getViewById(R.id.iv_originate);
            ImageView iv_recommend = (ImageView) holder.getViewById(R.id.iv_recommend);
            BlogBodyBean.ResultBean.ItemsBean bean = (BlogBodyBean.ResultBean.ItemsBean) itemData;
            if (bean.isOriginal()){
                iv_originate.setVisibility(View.VISIBLE);
            }
            if (bean.isRecommend()){
                iv_recommend.setVisibility(View.VISIBLE);
            }
            tvTitleBlog.setText(bean.getTitle());
            tvBodyBlog.setText(bean.getBody());
            tvNameBlog.setText(bean.getAuthor());
            tvTimeBlog.setText(StringUtils.friendly_time(bean.getPubDate()));
            tvVisitBlog.setText(bean.getViewCount() + "");
            tvCommentBlog.setText(bean.getCommentCount() + "");

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1_blog:
                catalog = 3;
                mSwipeRefreshLayoutBlog.setRefreshing(true);
                mLoadingPager.showViewDely(1000);
                break;
            case R.id.bt2_blog:
                catalog = 2;
                mSwipeRefreshLayoutBlog.setRefreshing(true);
                mLoadingPager.showViewDely(1000);
                break;
            case R.id.bt3_blog:
                catalog = 1;
                mSwipeRefreshLayoutBlog.setRefreshing(true);
                mLoadingPager.showViewDely(1000);
                break;
        }
    }

}
