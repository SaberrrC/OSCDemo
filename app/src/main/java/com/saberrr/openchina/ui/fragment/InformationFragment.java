package com.saberrr.openchina.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.InformationHearBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public  class InformationFragment extends BaseFragment {

    private RollPagerView mRollPagerView;
    private RecyclerView mRecyclerView;
    private List<InformationHearBean>datas = new ArrayList<>();

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
        mRollPagerView.setAdapter(new TestNormalAdapter());
    }

    @Override
    public Object getData() {

        return "";
    }
    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.share_icon_qq,
                R.mipmap.share_icon_sinaweibo,
                R.mipmap.share_icon_wechat

        };


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            container.addView(view);
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
