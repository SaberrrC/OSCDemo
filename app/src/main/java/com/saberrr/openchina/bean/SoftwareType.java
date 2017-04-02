package com.saberrr.openchina.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/2.
 */
@XStreamAlias("softwareType")
public class SoftwareType {
    @XStreamAlias("name")
    public String name;
    @XStreamAlias("tag")
    public int tag;
}
