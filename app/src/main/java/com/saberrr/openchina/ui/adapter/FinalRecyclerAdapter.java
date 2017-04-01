package com.saberrr.openchina.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.ui.adapter.interfaces.ItemType;

import java.util.List;

/**
 * 单类条目展示的最终RecyclerView.Adapter
 */

public class FinalRecyclerAdapter extends RecyclerView.Adapter<FinalRecyclerAdapter.ViewHolder> {

    private static final int HEAD_TYPE = 100;
    private static final int BODY_TYPE = 101;
    private List<ItemType>       mDatas;
    private int                  mItemLayout;
    private FinalAdapterListener mListener;
    private SparseArray<Integer> mClassSparseArray;

    public FinalRecyclerAdapter(List<ItemType> datas, int itemLayout, FinalAdapterListener listener) {
        mDatas = datas;
        mItemLayout = itemLayout;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        switch (viewType) {
            case HEAD_TYPE:
                return new ViewHolder(itemView);
            case BODY_TYPE:
                return new ViewHolder(itemView);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return mClassSparseArray.get(position);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mListener != null) {
            mListener.onBindViewHolder(holder, mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface FinalAdapterListener<T> {
        void onBindViewHolder(ViewHolder holder, T item);
    }

    /*
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
//        加载条目时的动画
        holder.itemView.setScaleX(.9f);
        ViewCompat.animate(holder.itemView).setDuration(300).scaleX(1).start();
    }*/


    public static class ViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> mViews = new SparseArray();

        ViewHolder(View view) {
            super(view);
        }

        public View getViewById(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViews.put(id, view);
            }
            return view;
        }

        public TextView getTextView(int id) {
            View view = getViewById(id);
            if (view instanceof TextView) {
                return ((TextView) view);
            } else {
                throw new RuntimeException("id类型不匹配");
            }
        }

        public ImageView getImageView(int id) {
            View view = getViewById(id);
            if (view instanceof ImageView) {
                return ((ImageView) view);
            } else {
                throw new RuntimeException("id类型不匹配");
            }
        }


    }
}
