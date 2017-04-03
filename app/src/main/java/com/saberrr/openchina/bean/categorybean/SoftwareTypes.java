package com.saberrr.openchina.bean.categorybean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/2.
 */
@XStreamAlias("softwareTypes")
public class SoftwareTypes {
    @XStreamAlias("softwareType")
    public SoftwareType mSoftwareType;

    public SoftwareType getSoftwareType() {
        return mSoftwareType;
    }

    public void setSoftwareType(SoftwareType softwareType) {
        mSoftwareType = softwareType;
    }
}
