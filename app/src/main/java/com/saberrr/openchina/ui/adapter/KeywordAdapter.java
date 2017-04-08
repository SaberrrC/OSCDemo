package com.saberrr.openchina.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuqi on 2017/4/7.
 */

public class KeywordAdapter extends BaseAdapter {
    private List<String> datas = new ArrayList<>();
    public KeywordAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String keyword = datas.get(position);
        holder.mTvSearchKeyword.setText(keyword);
        holder.mLlSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(keyword);
                if(mOnKeywordItemClickListener != null) {
                    mOnKeywordItemClickListener.onKeywordItemClick(position,keyword);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_search_keyword)
        TextView mTvSearchKeyword;
        @BindView(R.id.ll_search_item)
        LinearLayout mLlSearchItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnKeywordItemClickListener {
        void onKeywordItemClick(int position,String keyword);
    }
    private OnKeywordItemClickListener mOnKeywordItemClickListener;

    public void setOnKeywordItemClickListener(OnKeywordItemClickListener onKeywordItemClickListener) {
        mOnKeywordItemClickListener = onKeywordItemClickListener;
    }
}
