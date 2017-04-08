package com.saberrr.openchina.faces;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saberrr on 2017-04-08.
 */

public class FaceBackBean {
    public String value;
    public int    fount;
    public int    tail;
    public static List<FaceBean> allByType1 = DisplayRules.getAllByType(0);
    public static List<FaceBean> allByType2 = DisplayRules.getAllByType(1);
    public static List<FaceBean> allByType  = new ArrayList<>();

    static {
        allByType.addAll(allByType1);
        allByType.addAll(allByType2);
    }


    public FaceBackBean(String value, int fount, int tail) {
        this.value = value;
        this.fount = fount;
        this.tail = tail;
    }
}
