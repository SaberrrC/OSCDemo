package com.saberrr.openchina.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saberrr.openchina.ui.adapter.interfaces.BodyType;
import com.saberrr.openchina.ui.adapter.interfaces.FootType;
import com.saberrr.openchina.ui.adapter.interfaces.HeadType;

import java.util.List;


/**
 * Created by admin on 2017/2/22.
 */

public class MultiRecycleAdapter extends RecyclerView.Adapter<MultiRecycleAdapter.ViewHolder> {
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_BODY = 1;
    private static final int TYPE_FOOT = 2;
    private Context              mContext;
    private List                 mDatas;
    private int                  mHeadLayout;
    private int                  mBodyLayout;
    private int                  mFootLayout;
    private FinalAdapterListener mMultiRecycleAdapter;

    public MultiRecycleAdapter(List datas, int bodyLayout, FinalAdapterListener listener) {
        this(datas, -1, bodyLayout, -1, listener);
    }

    public MultiRecycleAdapter(List datas, int headLayout, int bodyLayout, int footLayout, FinalAdapterListener multiRecycleAdapter) {
        mDatas = datas;
        mHeadLayout = headLayout;
        mBodyLayout = bodyLayout;
        mFootLayout = footLayout;
        mMultiRecycleAdapter = multiRecycleAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position) instanceof HeadType) {
            return TYPE_HEAD;
        } else if (mDatas.get(position) instanceof BodyType) {
            return TYPE_BODY;
        } else if (mDatas.get(position) instanceof FootType) {
            return TYPE_FOOT;
        }
        return -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        switch (viewType) {
            case TYPE_HEAD:
                View headView = LayoutInflater.from(mContext).inflate(mHeadLayout, parent, false);
                ViewHolder holder = new ViewHolder(headView);
                return holder;
            case TYPE_BODY:
                View bodyView = LayoutInflater.from(mContext).inflate(mBodyLayout, parent, false);
                return new ViewHolder(bodyView);
            case TYPE_FOOT:
                View footView = LayoutInflater.from(mContext).inflate(mFootLayout, parent, false);
                return new ViewHolder(footView);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        mMultiRecycleAdapter.onBindViewHolder(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface FinalAdapterListener {
        void onBindViewHolder(ViewHolder holder, Object obj);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> mShowItems;
        ViewHolder(View view) {
            super(view);
            mShowItems = new SparseArray();
        }

        public View getViewById(int id) {
            View view = mShowItems.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mShowItems.put(id, view);
            }
            return view;
        }

        public TextView getTextView(int id) {
            View view = getViewById(id);
            if (view instanceof TextView) {
                return ((TextView) view);
            }
            throw new RuntimeException("MultiRecycleAdapter: id类型不匹配");
        }

        public ImageView getImageView(int id) {
            View view = getViewById(id);
            if (view instanceof ImageView) {
                return ((ImageView) view);
            }
            throw new RuntimeException("MultiRecycleAdapter: id类型不匹配");
        }

        public ViewPager getViewPager(int id) {
            View view = getViewById(id);
            if (view instanceof ViewPager) {
                return ((ViewPager) view);
            } else {
                throw new RuntimeException("MultiRecycleAdapter: id类型不匹配");
            }
        }

    }
}
