package com.saberrr.openchina.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.searchbean.Result;
import com.saberrr.openchina.bean.searchbean.SearchSoftwareBean;
import com.saberrr.openchina.bean.searchbean.XmlSearchSoftwareBean;
import com.saberrr.openchina.bean.searchfindbean.FindUser;
import com.saberrr.openchina.bean.searchfindbean.SearchFindBean;
import com.saberrr.openchina.bean.searchfindbean.XmlSearchFindBean;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.XmlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kymjs.kjframe.Core;

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
 * Created by liuqi on 2017/4/1.
 */

public class FindUserFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.rv_search_find)
    RecyclerView mRvSearchFind;
    private String mKeyword;
    private List<SearchFindBean> datas = new ArrayList<>();
    private HashMap<Class, Integer> mHashMap = new HashMap<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    /*@Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_finduser, null);
        ButterKnife.bind(this,view);

        init();

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
        ThreadUtils.runBigSub(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });

        EventBus.getDefault().removeStickyEvent(keyword);
    }

    private void init() {
        mHashMap.put(SearchFindBean.class, R.layout.search_find_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, mHashMap, this);
        mRvSearchFind.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSearchFind.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.oschina.net/action/api/find_user?name="+mKeyword).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String xml = response.body().string();
            //System.out.println(xml);
            XmlSearchFindBean xmlSearchFindBean = XmlUtils.toBean(XmlSearchFindBean.class, xml.getBytes());
            List<FindUser> findUsers = xmlSearchFindBean.getUsers();
            datas.clear();
            for (int i = 0; i < findUsers.size(); i++) {
                FindUser findUser = findUsers.get(i);
                SearchFindBean searchFindBean = new SearchFindBean();
                searchFindBean.name = findUser.getName();
                searchFindBean.gender = findUser.getGender();
                searchFindBean.address = findUser.getFrom();
                searchFindBean.portrait = findUser.getPortrait();
                datas.add(searchFindBean);
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
        ImageView iv_find_touxiang = (ImageView) holder.getViewById(R.id.iv_find_touxiang);
        ImageView iv_find_gendar = (ImageView) holder.getViewById(R.id.iv_find_gendar);
        TextView tv_find_name = (TextView) holder.getViewById(R.id.tv_find_name);
        TextView tv_find_address = (TextView) holder.getViewById(R.id.tv_find_address);
        if(itemData instanceof SearchFindBean) {
            SearchFindBean findBean = (SearchFindBean) itemData;
            tv_find_name.setText(findBean.name);
            tv_find_address.setText(findBean.address);
            new Core.Builder().view(iv_find_touxiang).url(findBean.portrait)
                    .loadBitmapRes(R.mipmap.widget_dface).doTask();
            String gendar = findBean.gender.trim();
            if("男".equals(gendar)) {
                iv_find_gendar.setImageResource(R.mipmap.ic_male);
            }else if("女".equals(gendar)) {
                iv_find_gendar.setImageResource(R.mipmap.ic_female);
            }
        }
    }
}
