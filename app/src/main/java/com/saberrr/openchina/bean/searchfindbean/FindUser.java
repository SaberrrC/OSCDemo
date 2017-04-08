package com.saberrr.openchina.bean.searchfindbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/8.
 */

/**
 * <user>
 * <name>
 * <![CDATA[ 白菜100 ]]>
 * </name>
 * <uid>2312019</uid>
 * <portrait/>
 * <gender>
 * <![CDATA[ 男 ]]>
 * </gender>
 * <from>
 * <![CDATA[ 上海 闸北 ]]>
 * </from>
 */

@XStreamAlias("user")
public class FindUser {
    @XStreamAlias("name")
    private String name;
    @XStreamAlias("uid")
    private String uid;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @XStreamAlias("portrait")

    private String portrait;
    @XStreamAlias("gender")
    private String gender;
    @XStreamAlias("from")
    private String from;
}
