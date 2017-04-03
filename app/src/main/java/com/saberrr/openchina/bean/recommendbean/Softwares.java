package com.saberrr.openchina.bean.recommendbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/2.
 */
@XStreamAlias("softwares")
public class Softwares {
    @XStreamAlias("software")
    public Software mSoftware;

    public Software getSoftware() {
        return mSoftware;
    }

    public void setSoftware(Software software) {
        mSoftware = software;
    }
}
