package com.saberrr.openchina.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;

import com.saberrr.openchina.gloab.AppApplication;

import butterknife.ButterKnife;

/**
 * Created by Saberrr on 2017-03-26.
 */

public abstract class BaseViewHolder<T> {

    private View                view;

    public BaseViewHolder() {
        view = createView();
        ButterKnife.bind(this, view);
        view.setTag(this);
    }

    public abstract View createView();

    public abstract void bindView(T t);

    public View getView() {
        return view;
    }
    public View createViewFromLayout(int id) {
        return LayoutInflater.from(AppApplication.appContext).inflate(id, null, false);
    }
}
