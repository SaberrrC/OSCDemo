package com.saberrr.openchina.bean;


import android.support.v4.app.Fragment;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class FragmentInfo {
    public Fragment mFragment;
    public String   title;

    public FragmentInfo(Fragment fragment, String title) {
        mFragment = fragment;
        this.title = title;
    }
}
