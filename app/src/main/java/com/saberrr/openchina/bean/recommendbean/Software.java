package com.saberrr.openchina.bean.recommendbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/2.
 */
@XStreamAlias("software")
public class Software {
    @XStreamAlias("id")
    public String id;
    @XStreamAlias("name")
    public String name;
    @XStreamAlias("description")
    public String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XStreamAlias("url")

    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
