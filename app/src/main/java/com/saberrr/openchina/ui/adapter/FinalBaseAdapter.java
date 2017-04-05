package com.saberrr.openchina.ui.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.saberrr.openchina.ui.adapter.interfaces.ItemType;
import com.saberrr.openchina.utils.Utils;

import java.util.List;

/**
 * Created by Saberrr on 2017-03-29.
 */

public class FinalBaseAdapter extends BaseAdapter {
    public static final float SCALE = 0.5f;
    private int                      itemLayout;
    private List<? extends ItemType> datas;

    public interface AdapterListener {
        void bindView(FinalViewHolder viewHolder, ItemType itemType, int position);
    }

    private AdapterListener mAdapterListener;

    public FinalBaseAdapter(List<? extends ItemType> datas, int itemLayout, AdapterListener adapterListener) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.mAdapterListener = adapterListener;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public ItemType getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FinalViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, null, false);
            viewHolder = new FinalViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FinalViewHolder) convertView.getTag();
        }
        mAdapterListener.bindView(viewHolder, datas.get(position), position);
        Utils.setiTemAnim(convertView);
        return convertView;
    }

    public static class FinalViewHolder {
        private View mview;

        public FinalViewHolder(View convertView) {
            mview = convertView;
        }

        private SparseArray<View> viewList = new SparseArray<>();

        public View getViewById(int id) {
            View view = viewList.get(id);
            if (view == null) {
                view = mview.findViewById(id);
                viewList.put(id, view);
            }
            return view;
        }
    }
}
