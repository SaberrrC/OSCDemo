package com.saberrr.openchina.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.InformationHearBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public  class InformationFragment extends BaseFragment {

    private RollPagerView mRollPagerView;
    private RecyclerView mRecyclerView;
    private List<InformationHearBean.ResultBean.ItemsBean>datas = new ArrayList<>();
    private ImageView mImageView;
    private TextView mTextView;
    private TestNormalAdapter mTestNormalAdapter;

    @Override
    protected boolean needRefresh() {
        return true;
    }

    @Override
    public View createView() {
        View view = View.inflate(getContext(), R.layout.fragmeng_information, null);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.rollPagerView_information);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_information);
        init();
        return view;
    }

    private void init() {
//        int [] images = new int[]{R.mipmap.share_icon_qq, R.mipmap.share_icon_sinaweibo,R.mipmap.share_icon_wechat};
//        String [] texts = new String[]{"QQ","微博","微信"};
//        datas.add(new InformationHearBean(images[0],texts[0]));
//        datas.add(new InformationHearBean(images[1],texts[1]));
//        datas.add(new InformationHearBean(images[2],texts[2]));
        mRollPagerView.setPlayDelay(1000);
        //设置透明度
        mRollPagerView.setAnimationDurtion(1000);
        mTestNormalAdapter = new TestNormalAdapter();
        mRollPagerView.setAdapter(mTestNormalAdapter);
        //mRecyclerView.setAdapter(new FinalRecycleAdapter());
    }

    @Override
    public Object getData() {
        InformationHearBean informationHearBean = JsonCacheManager.getInstance().getDataBean(Urls.BANNER, InformationHearBean.class);
        if (informationHearBean == null){
            return null;
        }
        List<InformationHearBean.ResultBean.ItemsBean> itemsBeanList = informationHearBean.getResult().getItems();
        datas.addAll(itemsBeanList);
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                mTestNormalAdapter.notifyDataSetChanged();
            }
        });

        return datas;
    }
    private class TestNormalAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.viewpager_item_information, null);
            mImageView = (ImageView) view.findViewById(R.id.iv_item_viewpager_information);
            mTextView = (TextView) view.findViewById(R.id.tv_item_viewpager_information);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(AppApplication.appContext).load(datas.get(position).getImg()).into(mImageView);
            mTextView.setText(datas.get(position).getName());
            mTextView.setTextColor(Color.WHITE);

            return view;
        }



        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
