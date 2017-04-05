package com.saberrr.openchina.bean;

import com.saberrr.openchina.ui.adapter.interfaces.ItemType;

/**
 * Created by Saberrr on 2017-04-05.
 */

public class SelectedImageBean implements ItemType {
    public String path;

    public SelectedImageBean(String path) {
        this.path = path;
    }
}
