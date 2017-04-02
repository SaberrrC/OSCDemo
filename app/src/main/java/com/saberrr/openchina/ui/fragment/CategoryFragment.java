package com.saberrr.openchina.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.CategoryBean;
import com.saberrr.openchina.bean.SoftwareType;
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
import butterknife.internal.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuqi on 2017/4/1.
 */

public class CategoryFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener{

    @BindView(R.id.category_recyclerview)
    RecyclerView mCategoryRecyclerview;
    private List<String> datas = new ArrayList<>();
    private HashMap<Class, Integer> classIntegerHashMap = new HashMap<>();
    private List<SoftwareType> mSoftwareTypes;
    private FinalRecycleAdapter mFinalRecycleAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        classIntegerHashMap.put(String.class,R.layout.category_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, classIntegerHashMap, this);
        mCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mCategoryRecyclerview.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(Urls.CATEGORY).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String xml = response.body().string();
                    System.out.println(xml);
                    CategoryBean categoryBean = XmlUtils.toBean(CategoryBean.class, xml.getBytes());
                    mSoftwareTypes = categoryBean.getSoftwareTypes();
                    for (int i = 0; i < mSoftwareTypes.size(); i++) {
                        String name = mSoftwareTypes.get(i).name;
                        datas.add(name);
                        System.out.println(name);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mCategoryRecyclerview.requestLayout();
                mFinalRecycleAdapter.notifyDataSetChanged();
            }
        });

        return mSoftwareTypes;
    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        TextView textView = (TextView) holder.getViewById(R.id.tv_category_item);
        String name = (String) itemData;
        textView.setText(name);
    }

}
