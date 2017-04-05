package com.saberrr.openchina.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by admin on 2017/2/22.
 */

public class FinalRecycleAdapter extends RecyclerView.Adapter<FinalRecycleAdapter.ViewHolder> {
    private List<? extends Object> mDatas;
    private OnViewAttachListener   mOnViewAttachListener;
    private              Map<Class, Integer> mClassIntegerHashMap = new HashMap<>();
    private static final int                 LOADMORE             = 0;
    private              boolean             needLoadMore         = false;
    private              int                 loadLayout           = -1;

    /**
     * 获取map
     * @return
     */
    public Map<Class, Integer> getClassIntegerHashMap() {
        return mClassIntegerHashMap;
    }

    /**
     * 是否需要加载更多 默认不需要
     *
     * @param needLoadMore 是否需要上拉加载更多
     * @param loadLayout   上拉加载更多的布局，不要虚上拉加载，传入0
     */
    public void setNeedLoadMore(boolean needLoadMore, int loadLayout) {
        this.needLoadMore = needLoadMore;
        this.loadLayout = loadLayout;
    }

    public void notifu() {
        notifyDataSetChanged();
    }

    /**
     * @param datas               数据
     * @param classIntegerHashMap Class键 数据类型 对应 条目类型，Integer值对应条目布局id
     */
    public FinalRecycleAdapter(List<? extends Object> datas, Map<Class, Integer> classIntegerHashMap, OnViewAttachListener onViewAttachListener) {
        mClassIntegerHashMap = classIntegerHashMap;
        mDatas = datas;
        mOnViewAttachListener = onViewAttachListener;
    }


    @Override
    public int getItemViewType(int position) {
        if (needLoadMore && position == getItemCount() - 1) {
            return LOADMORE;
        }
        Class key = mDatas.get(position).getClass();
        if (mClassIntegerHashMap.containsKey(key)) {
            return mClassIntegerHashMap.get(key);
        } else {
            throw new RuntimeException("未添加进Map！");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOADMORE) {
            if (viewType == -1) {
                throw new RuntimeException("请传入加载更多的布局");
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(loadLayout, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }

    public void addItemType(Class clzz, int layoutID) {
        mClassIntegerHashMap.put(clzz, layoutID);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mDatas.size() == 0) {
            throw new RuntimeException("获取完数据请 notifyDataSetChanged()");
        }
        if (needLoadMore) {
            mOnViewAttachListener.onBindViewHolder(holder, position, new Object());
        } else {
            mOnViewAttachListener.onBindViewHolder(holder, position, mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (needLoadMore) {
            return mDatas.size() + 1;
        }
        return mDatas.size();
    }

    public void notifyDataSetChangedNew(List<Object> datas) {
        mDatas = datas;
        notifyDataSetChanged();
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
