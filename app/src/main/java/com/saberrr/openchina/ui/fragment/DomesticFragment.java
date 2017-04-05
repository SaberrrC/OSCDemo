package com.saberrr.openchina.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.recommendbean.RecommendBean;
import com.saberrr.openchina.bean.recommendbean.RecommendItemBean;
import com.saberrr.openchina.bean.recommendbean.Software;
import com.saberrr.openchina.net.Urls;
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

public class DomesticFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.domestic_recyclerview)
    RecyclerView mDomesticRecyclerview;
    private List<RecommendItemBean> datas = new ArrayList<>();
    private HashMap<Class, Integer> mHashMap = new HashMap<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_domestic, null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        mHashMap.put(RecommendItemBean.class,R.layout.domestic_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, mHashMap, this);
        mDomesticRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mDomesticRecyclerview.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Urls.DOMESTIC).build();
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
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        TextView tv_title = (TextView) holder.getViewById(R.id.tv_domestic_item_title);
        TextView tv_desc = (TextView) holder.getViewById(R.id.tv_domestic_item_desc);
        if(itemData instanceof RecommendItemBean) {
            RecommendItemBean recommendItemBean = (RecommendItemBean) itemData;
            tv_title.setText(recommendItemBean.title);
            tv_desc.setText(recommendItemBean.desc);
        }
    }
}
