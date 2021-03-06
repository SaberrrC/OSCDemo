package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.categorybean.EventBusBean2;
import com.saberrr.openchina.bean.recommendbean.RecommendBean;
import com.saberrr.openchina.bean.recommendbean.RecommendItemBean;
import com.saberrr.openchina.bean.recommendbean.Software;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.XmlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuqi on 2017/4/2.
 */

public class CategoryThreeItemFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.recommend_recyclerview)
    RecyclerView mRecommendRecyclerview;
    @BindView(R.id.srl_recommend)
    SwipeRefreshLayout mSrlRecommend;
    private List<RecommendItemBean> datas = new ArrayList<>();
    private ArrayList<String> idList = new ArrayList<>();
    private HashMap<Class, Integer> mHashMap = new HashMap<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSrlRecommend.setRefreshing(false);
        }
    };
    private int index = 0;
    private ArrayList<Integer> tagList = new ArrayList<>();
    private int position = 0;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        mCurrentFragment = new CategoryThreeItemFragment();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(EventBusBean2 eventBusBean2) {
        tagList.addAll(eventBusBean2.mIntegerArrayList);
        position = eventBusBean2.position;
    }

    private void init() {
        mHashMap.put(RecommendItemBean.class, R.layout.recommend_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, mHashMap, this);
        mRecommendRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecommendRecyclerview.setAdapter(mFinalRecycleAdapter);
        mSrlRecommend.setColorSchemeColors(Color.GREEN);
        mSrlRecommend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        mHandler.sendEmptyMessage(0);
                        datas.clear();
                        getData();
                    }
                }).start();

            }
        });

        mRecommendRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //上拉加载更多的功能
                //1. 底部显示
                //2. 去加载数据
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //获取最后一位可见的条目
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                //最后一个可见条目是集合条目最后一位
                if (lastVisibleItemPosition == datas.size() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE
                        ) {
                    System.out.println("上拉加载更多");
                    index++;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getData();

                        }
                    }).start();

                }


            }
        });
    }

    @Override
    public Object getData() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Urls.THREECATEGORY+index+"&searchTag="+tagList.get(position)+Urls.PAGESIZE).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String xml = response.body().string();
//            System.out.println(xml);
            RecommendBean recommendBean = XmlUtils.toBean(RecommendBean.class, xml.getBytes());
            List<Software> softwares = recommendBean.getSoftwares();
            for (int i = 0; i < softwares.size(); i++) {
                String name = softwares.get(i).getName();
                String description = softwares.get(i).getDescription();
                String id = softwares.get(i).getId();
                RecommendItemBean recommendItemBean = new RecommendItemBean();
                recommendItemBean.title = name;
                recommendItemBean.desc = description;
                idList.add(id);
                datas.add(recommendItemBean);

//                System.out.println(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mFinalRecycleAdapter.notifyDataSetChanged();
            }
        });

        return "";
    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, final int position, Object itemData) {
        LinearLayout ll_recommend_item = (LinearLayout) holder.getViewById(R.id.ll_recommend_item);
        TextView tv_title = (TextView) holder.getViewById(R.id.tv_recommend_item_title);
        TextView tv_desc = (TextView) holder.getViewById(R.id.tv_recommend_item_desc);
        if (itemData instanceof RecommendItemBean) {
            RecommendItemBean recommendItemBean = (RecommendItemBean) itemData;
            tv_title.setText(recommendItemBean.title);
            tv_desc.setText(recommendItemBean.desc);
        }
        ll_recommend_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtils.showToast("被点击了"+ position);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listName", idList);
                bundle.putInt("position", position);
                ShowActivity.startFragmentWithTitle(SoftwareDetailFragment.class, bundle, "软件详情");
            }
        });

    }


}
