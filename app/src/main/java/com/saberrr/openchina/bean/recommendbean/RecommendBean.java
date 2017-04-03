package com.saberrr.openchina.bean.recommendbean;

/**
 * Created by liuqi on 2017/4/2.
 */

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;



@XStreamAlias("oschina")
public class RecommendBean {
    @XStreamAlias("softwarecount")
    public String mSoftwarecount;
    @XStreamAlias("pagesize")
    public String mPagesize;
    @XStreamAlias("softwares")
    public List<Software> mSoftwares;

    public String getSoftwarecount() {
        return mSoftwarecount;
    }

    public void setSoftwarecount(String softwarecount) {
        mSoftwarecount = softwarecount;
    }

    public String getPagesize() {
        return mPagesize;
    }

    public void setPagesize(String pagesize) {
        mPagesize = pagesize;
    }

    public List<Software> getSoftwares() {
        return mSoftwares;
    }

    public void setSoftwares(List<Software> softwares) {
        mSoftwares = softwares;
    }
}
