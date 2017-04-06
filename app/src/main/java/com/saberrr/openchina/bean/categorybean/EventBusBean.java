package com.saberrr.openchina.bean.categorybean;

import java.util.ArrayList;

/**
 * Created by liuqi on 2017/4/6.
 */

public class EventBusBean {
    public ArrayList<Integer> mIntegerArrayList;
    public int position;

    public EventBusBean(ArrayList<Integer> integerArrayList, int position) {
        mIntegerArrayList = integerArrayList;
        this.position = position;
    }
}
