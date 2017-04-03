package com.saberrr.openchina.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saberrr.openchina.R;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.ExtendedSSLSession;

/**
 * Created by liuqi on 2017/4/2.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<String> datas = new ArrayList<>();

    public CategoryAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, null);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.mTextView.setText(datas.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnCategoryItemClickListener != null) {
                    mOnCategoryItemClickListener.onCategoryItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_category_item);
        }
    }

    public interface OnCategoryItemClickListener {
        void onCategoryItemClick(int position);
    }

    private OnCategoryItemClickListener mOnCategoryItemClickListener;

    public void setOnCategoryItemClickListener(OnCategoryItemClickListener onCategoryItemClickListener) {
        mOnCategoryItemClickListener = onCategoryItemClickListener;
    }
}
