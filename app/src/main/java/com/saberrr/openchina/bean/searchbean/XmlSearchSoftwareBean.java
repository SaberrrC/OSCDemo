package com.saberrr.openchina.bean.searchbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by liuqi on 2017/4/7.
 */

@XStreamAlias("oschina")
public class XmlSearchSoftwareBean {
    @XStreamAlias("pagesize")
    private int pagesize;
    @XStreamAlias("results")
    private List<Result> mResults;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }
}
