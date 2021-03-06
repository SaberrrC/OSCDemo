package com.saberrr.openchina.ui.adapter.interfaces;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.faces.FaceBean;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Saberrr on 2017-04-04.
 */

public class FacesPagerAdapter extends PagerAdapter implements FinalRecycleAdapter.OnViewAttachListener {

    private List<FaceBean> allByType0 = new ArrayList<>();
    private List<FaceBean> allByType1 = new ArrayList<>();


    public FacesPagerAdapter(List<FaceBean> allByType0, List<FaceBean> allByType1) {
        this.allByType0 = allByType0;
        this.allByType1 = allByType1;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_face_view, null, false);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 7, OrientationHelper.VERTICAL, false));
        Map<Class, Integer> map = new HashMap<>();
        map.put(FaceBean.class, R.layout.layout_item_face);
        List<FaceBean> mDatas = new ArrayList<>();
        if (position == 0) {
            mDatas.addAll(allByType0);
        } else {
            mDatas.addAll(allByType1);
        }
        FinalRecycleAdapter finalRecycleAdapter = new FinalRecycleAdapter(mDatas, map, this);
        recyclerView.setAdapter(finalRecycleAdapter);
        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        final FaceBean faceBean = (FaceBean) itemData;
        ImageView face = (ImageView) holder.getViewById(R.id.iv_face);
        face.setImageResource(faceBean.resId);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(faceBean);
                }
            }
        });
    }

    public interface OnClickListener {
        void onClick(FaceBean faceBean);
    }

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

}
