package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.os.Bundle;
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
import com.saberrr.openchina.bean.searchbean.Result;
import com.saberrr.openchina.bean.searchbean.SearchSoftwareBean;
import com.saberrr.openchina.bean.searchbean.XmlSearchSoftwareBean;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.fragment.BaseFragment;
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
 * Created by liuqi on 2017/4/7.
 */

public class SearchSoftwareFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.rv_search_software)
    RecyclerView mRvSearchSoftware;
    @BindView(R.id.srl_search_software)
    SwipeRefreshLayout mSrlSearchSoftware;
    private String mKeyword;
    private List<SearchSoftwareBean> datas = new ArrayList<>();
    private HashMap<Class, Integer> mHashMap = new HashMap<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_search_software, null);
        ButterKnife.bind(this,view);
        init();
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void OnEvent(String keyword) {
        mKeyword = keyword;
        EventBus.getDefault().removeAllStickyEvents();
    }

    private void init() {
        mHashMap.put(SearchSoftwareBean.class, R.layout.search_software_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, mHashMap, this);
        mRvSearchSoftware.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSearchSoftware.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {
        System.out.println(mKeyword);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.oschina.net/action/api/search_list?catalog=software&pageIndex=0&content="+mKeyword+"&pageSize=20").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String xml = response.body().string();
            //System.out.println(xml);
            XmlSearchSoftwareBean xmlSearchSoftwareBean = XmlUtils.toBean(XmlSearchSoftwareBean.class, xml.getBytes());
            List<Result> results = xmlSearchSoftwareBean.getResults();
            for (int i = 0; i < results.size(); i++) {
                Result result = results.get(i);
                //System.out.println(result.getTitle());
                SearchSoftwareBean searchSoftwareBean = new SearchSoftwareBean();
                searchSoftwareBean.title = result.getTitle();
                searchSoftwareBean.desc = result.getDescription();
                datas.add(searchSoftwareBean);
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
        TextView tv_title = (TextView) holder.getViewById(R.id.tv_search_software_item_title);
        TextView tv_desc = (TextView) holder.getViewById(R.id.tv_search_software_item_desc);
        if (itemData instanceof SearchSoftwareBean) {
            SearchSoftwareBean searchSoftwareBean = (SearchSoftwareBean) itemData;
            tv_title.setText(searchSoftwareBean.title);
            tv_desc.setText(searchSoftwareBean.desc);
        }
    }
}
