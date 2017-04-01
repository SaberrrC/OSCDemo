package com.saberrr.openchina.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.InformationHearBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class RollPagerViewAdapter extends PagerAdapter {
    private List<InformationHearBean>mDatas = new ArrayList<>();
    private ImageView mImageView;
    private TextView mTextView;

    public RollPagerViewAdapter(List<InformationHearBean> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.viewpager_item_information, null);
        mImageView = (ImageView) view.findViewById(R.id.iv_item_viewpager_information);
        mTextView = (TextView) view.findViewById(R.id.tv_item_viewpager_information);
        container.addView(mImageView);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
