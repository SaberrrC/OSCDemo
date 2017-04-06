package com.saberrr.openchina.ui.fragment.mymsgfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.categorybean.CategoryBean;
import com.saberrr.openchina.bean.categorybean.CategoryItemBean;
import com.saberrr.openchina.bean.categorybean.EventBusBean;
import com.saberrr.openchina.bean.categorybean.EventBusBean2;
import com.saberrr.openchina.bean.categorybean.SoftwareType;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.fragment.BaseFragment;
import com.saberrr.openchina.ui.fragment.CategoryThreeItemFragment;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
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
 * Created by liuqi on 2017/4/1.
 */

public class CategoryItemFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener{

    @BindView(R.id.category_recyclerview)
    RecyclerView mCategoryRecyclerview;
    private List<CategoryItemBean> datas = new ArrayList<>();
    private HashMap<Class, Integer> classIntegerHashMap = new HashMap<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private ArrayList<Integer> tagList = new ArrayList<>();
    private int position = 0;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_category_second, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(EventBusBean eventBusBean) {
        tagList.addAll(eventBusBean.mIntegerArrayList);
        position = eventBusBean.position;
    }

    private void init() {
        classIntegerHashMap.put(CategoryItemBean.class,R.layout.category_item_layout);
        mFinalRecycleAdapter = new FinalRecycleAdapter(datas, classIntegerHashMap, this);
        mCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mCategoryRecyclerview.setAdapter(mFinalRecycleAdapter);
    }

    @Override
    public Object getData() {

        OkHttpClient okHttpClient = new OkHttpClient();

        System.out.println(tagList.size());
        Request request = new Request.Builder().url(Urls.DETAIL+tagList.get(position)).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String xml = response.body().string();
                    //System.out.println(xml);
                    CategoryBean categoryBean = XmlUtils.toBean(CategoryBean.class, xml.getBytes());
                    List<SoftwareType> mSoftwareTypes = categoryBean.getSoftwareTypes();
                    for (int i = 0; i < mSoftwareTypes.size(); i++) {
                        String name = mSoftwareTypes.get(i).name;
                        int tag = mSoftwareTypes.get(i).tag;
                        CategoryItemBean categoryItemBean = new CategoryItemBean();
                        categoryItemBean.title = name;
                        categoryItemBean.tag = tag;
                        datas.add(categoryItemBean);
                        tagList.add(tag);
                        //System.out.println(name);
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
        TextView textView = (TextView) holder.getViewById(R.id.tv_category_item);
        LinearLayout ll_category_item = (LinearLayout) holder.getViewById(R.id.ll_category_item);
        if (itemData instanceof CategoryItemBean) {
            CategoryItemBean categoryItemBean = (CategoryItemBean) itemData;
            textView.setText(categoryItemBean.title);
        }


        ll_category_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new EventBusBean2(tagList, position));
                getFragmentManager().beginTransaction().replace(R.id.fl_categorySecond,new CategoryThreeItemFragment()).addToBackStack(null).commit();
            }
        });


    }

}
