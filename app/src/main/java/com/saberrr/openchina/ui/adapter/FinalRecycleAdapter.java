package com.saberrr.openchina.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2017/2/22.
 */

public class FinalRecycleAdapter extends RecyclerView.Adapter<FinalRecycleAdapter.ViewHolder> {
    private List<? extends Object>  mDatas;
    private OnViewAttachListener    mMultiRecycleAdapter;
    private HashMap<Class, Integer> mClassIntegerHashMap;
    private static final int     LOADMORE     = 0;
    private              boolean needLoadMore = true;

    /**
     * 是否需要加载更多 默认需要
     *
     * @param needLoadMore
     */
    public void setNeedLoadMore(boolean needLoadMore) {
        this.needLoadMore = needLoadMore;
    }

    /**
     * @param datas               数据
     * @param classIntegerHashMap Class键 数据类型 对应 条目类型，Integer值对应条目布局id
     */
    public FinalRecycleAdapter(List<? extends Object> datas, HashMap<Class, Integer> classIntegerHashMap, OnViewAttachListener onViewAttachListener) {
        mClassIntegerHashMap = classIntegerHashMap;
        mDatas = datas;
        mMultiRecycleAdapter = onViewAttachListener;
    }

    @Override
    public int getItemViewType(int position) {
        Class key = mDatas.get(position).getClass();
        if (needLoadMore && position == getItemCount() - 1) {
            return LOADMORE;
        }
        if (mClassIntegerHashMap.containsKey(key)) {
            return mClassIntegerHashMap.get(key);
        } else {
            throw new RuntimeException("未添加进Map！");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (needLoadMore) {
            View headView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        }
        View headView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        ViewHolder holder = new ViewHolder(headView);
        return holder;
    }

    public void addItemType(Class clzz, int layoutID) {
        mClassIntegerHashMap.put(clzz, layoutID);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mMultiRecycleAdapter.onBindViewHolder(holder, position, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnViewAttachListener {
        void onBindViewHolder(ViewHolder holder, int position, Object itemData);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> mShowItems = new SparseArray<>();

            ViewHolder(View view) {
            super(view);
        }

        public View getViewById(int id) {
            View view = mShowItems.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mShowItems.put(id, view);
            }
            return view;
        }
    }
}
