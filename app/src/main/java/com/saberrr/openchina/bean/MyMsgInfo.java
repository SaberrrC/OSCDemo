package com.saberrr.openchina.bean;

import android.support.v4.app.Fragment;

/**
 * Created by 2017 on 2017/4/3.
 */

public class MyMsgInfo {
    public MyMsgInfo(Fragment fragment, String title) {
        mFragment = fragment;
        this.title = title;
    }

    public Fragment mFragment;
    public String title;
}
