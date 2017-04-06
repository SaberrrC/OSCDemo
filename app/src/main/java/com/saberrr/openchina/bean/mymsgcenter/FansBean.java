package com.saberrr.openchina.bean.mymsgcenter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.security.PrivateKey;
import java.util.List;

/**
 * Created by 2017 on 2017/4/6.
 */
@XStreamAlias("oschina")
public class FansBean {
    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public Notice getNotioe() {
        return notioe;
    }

    public void setNotioe(Notice notioe) {
        this.notioe = notioe;
    }

    /*<?xml version="1.0" encoding="UTF-8"?>
        <oschina>
        <friends>

        <friend>
        <name>
        <![CDATA[jixianfeng]]>
        </name>
        <userid>2923198</userid>
        <portrait></portrait>
        <from>
        <![CDATA[湖南 长沙]]>
        </from>
        <expertise>
        <![CDATA[<无>]]>
        </expertise>
        <gender>2</gender>
        </friend>

        </friends>
        <notice>
        <atmeCount>0</atmeCount>
        <msgCount>0</msgCount>
        <reviewCount>0</reviewCount>
        <newFansCount>0</newFansCount>
        <newLikeCount>0</newLikeCount>
        </notice>
        </oschina>*/
@XStreamAlias("friends")
    private List<Friend> friends;
    @XStreamAlias("notioe")
    private Notice notioe;
@XStreamAlias("friend")
    public class Friend {
    /*<friend>
    <name>
    <![CDATA[jixianfeng]]>
    </name>
    <userid>2923198</userid>
    <portrait></portrait>
    <from>
    <![CDATA[湖南 长沙]]>
    </from>
    <expertise>
    <![CDATA[<无>]]>
    </expertise>
    <gender>2</gender>
    </friend>*/
    @XStreamAlias("name")
    private String name;
    @XStreamAlias("userid")
    private String userid;
    @XStreamAlias("portrait")
    private String portrait;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XStreamAlias("from")
    private String from;
    @XStreamAlias("expertise")
    private String expertise;
    @XStreamAlias("gender")
    private String gender;


    }
@XStreamAlias("notice")
    public class Notice {
        /*<notice>
        <atmeCount>0</atmeCount>
        <msgCount>0</msgCount>
        <reviewCount>0</reviewCount>
        <newFansCount>0</newFansCount>
        <newLikeCount>0</newLikeCount>
        </notice>*/

    public String getAtmeCount() {
        return atmeCount;
    }

    public void setAtmeCount(String atmeCount) {
        this.atmeCount = atmeCount;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getNewFansCount() {
        return newFansCount;
    }

    public void setNewFansCount(String newFansCount) {
        this.newFansCount = newFansCount;
    }

    public String getNewLikeCount() {
        return newLikeCount;
    }

    public void setNewLikeCount(String newLikeCount) {
        this.newLikeCount = newLikeCount;
    }

    @XStreamAlias("atmeCount")
    private String atmeCount;
    @XStreamAlias("msgCount")
    private String msgCount;
    @XStreamAlias("reviewCount")
    private String reviewCount;
    @XStreamAlias("newFansCount")
    private String newFansCount;
    @XStreamAlias("newLikeCount")
    private String newLikeCount;
    }


}
