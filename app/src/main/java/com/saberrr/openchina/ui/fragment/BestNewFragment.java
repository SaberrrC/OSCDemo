package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.recommendbean.RecommendBean;
import com.saberrr.openchina.bean.recommendbean.RecommendItemBean;
import com.saberrr.openchina.bean.recommendbean.Software;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.XmlUtils;

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

public class BestNewFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.bestnew_recyclerview)
    RecyclerView mBestnewRecyclerview;
    @BindView(R.id.srl_bestnew)
    SwipeRefreshLayout mSrlBestnew;
    private List<RecommendItemBean> datas = new ArrayList<>();
    private HashMap<Class, Integer> mHashMap = new HashMap<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSrlBestnew.setRefreshing(false);
        }
    };
    private int index = 0;
    private ArrayList<String> idList = new ArrayList<>();


    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bestnew, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mHashMap.put(RecommendItemBean.class, R.layout.bestnew_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, mHashMap, this);
        mBestnewRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mBestnewRecyclerview.setAdapter(mFinalRecycleAdapter);
        mSrlBestnew.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        mHandler.sendEmptyMessage(0);
                    }
                }).start();

            }
        });


        mBestnewRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        Request request = new Request.Builder().url(Urls.BASE_URL+Urls.PAGEINDEX+index+Urls.BESTNEW).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String xml = response.body().string();
//            System.out.println(xml);
            RecommendBean recommendBean = XmlUtils.toBean(RecommendBean.class, xml.getBytes());
            List<Software> softwares = recommendBean.getSoftwares();
            for (int i = 0; i < softwares.size(); i++) {
                String name = softwares.get(i).getName();
                String description = softwares.get(i).getDescription();
                RecommendItemBean recommendItemBean = new RecommendItemBean();
                recommendItemBean.title = name;
                recommendItemBean.desc = description;
                idList.add(softwares.get(i).getId());

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
        TextView tv_title = (TextView) holder.getViewById(R.id.tv_bestnew_item_title);
        TextView tv_desc = (TextView) holder.getViewById(R.id.tv_bestnew_item_desc);
        LinearLayout ll_bestnew_item = (LinearLayout) holder.getViewById(R.id.ll_bestnew_item);
        if (itemData instanceof RecommendItemBean) {
            RecommendItemBean recommendItemBean = (RecommendItemBean) itemData;
            tv_title.setText(recommendItemBean.title);
            tv_desc.setText(recommendItemBean.desc);
        }

        ll_bestnew_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listName", idList);
                bundle.putInt("position", position);
                ShowActivity.startFragmentWithTitle(SoftwareDetailFragment.class, bundle, "软件详情");
            }
        });
    }

}
