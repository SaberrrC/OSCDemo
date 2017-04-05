package com.saberrr.openchina.bean.softwaredetailbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/5.
 */
@XStreamAlias("oschina")
public class SoftwareDetailBean {
    @XStreamAlias("software")
    private Software mSoftware;

    public Software getSoftware() {
        return mSoftware;
    }

    public void setSoftware(Software software) {
        mSoftware = software;
    }
}
