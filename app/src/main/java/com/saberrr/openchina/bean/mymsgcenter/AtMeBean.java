package com.saberrr.openchina.bean.mymsgcenter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by 2017 on 2017/4/6.
 */

public class AtMeBean {

    @XStreamAlias("activeCount")
    private String activeCount;

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(String activeCount) {
        this.activeCount = activeCount;
    }

    public List<Active> getActivies() {
        return activies;
    }

    public void setActivies(List<Active> activies) {
        this.activies = activies;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @XStreamAlias("pagesize")
    private String pagesize;
    @XStreamAlias("activies")
    private List<Active> activies;
    @XStreamAlias("notice")
    private Notice notice;

    @XStreamAlias("active")
    public class Active {
        @XStreamAlias("id")
        private String id;
        @XStreamAlias("portrait")
        private String portrait;
        @XStreamAlias("author")
        private String author;
        @XStreamAlias("authorid")
        private String authorid;
        @XStreamAlias("catalog")
        private String catalog;
        @XStreamAlias("objecttype")
        private int objecttype;
        @XStreamAlias("objectcatalog")
        private int objectcatalog;
        @XStreamAlias("objecttitle")
        private String objecttitle;
        @XStreamAlias("appclient")
        private String appclient;
        @XStreamAlias("url")
        private String url;
        @XStreamAlias("objectID")
        private String objectID;
        @XStreamAlias("message")
        private String message;
        @XStreamAlias("commentCount")
        private String commentCount;
        @XStreamAlias("pubDate")
        private String pubDate;
        @XStreamAlias("tweetimage")
        private String tweetimage;
        @XStreamAlias("tweetattach")
        private String tweetattach;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public int getObjecttype() {
            return objecttype;
        }

        public void setObjecttype(int objecttype) {
            this.objecttype = objecttype;
        }

        public int getObjectcatalog() {
            return objectcatalog;
        }

        public void setObjectcatalog(int objectcatalog) {
            this.objectcatalog = objectcatalog;
        }

        public String getObjecttitle() {
            return objecttitle;
        }

        public void setObjecttitle(String objecttitle) {
            this.objecttitle = objecttitle;
        }

        public String getAppclient() {
            return appclient;
        }

        public void setAppclient(String appclient) {
            this.appclient = appclient;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getTweetimage() {
            return tweetimage;
        }

        public void setTweetimage(String tweetimage) {
            this.tweetimage = tweetimage;
        }

        public String getTweetattach() {
            return tweetattach;
        }

        public void setTweetattach(String tweetattach) {
            this.tweetattach = tweetattach;
        }
    }


    /*<?xml version="1.0" encoding="UTF-8"?>
    <oschina>
    <activeCount>0</activeCount>
    <!-- 总动弹数 -->
    <pagesize>5</pagesize>
    <activies>
    <active>
    <id>10744607</id>
    <portrait></portrait>
    <author>
    <![CDATA[骑着猪的绿皮车]]>
    </author>
    <authorid>2958135</authorid>
    <catalog>1</catalog>
    <objecttype>16</objecttype>
    <objectcatalog>0</objectcatalog>
    <objecttitle>
    <![CDATA[OVM-V1.2 版发布，新增实时监控、支持一键升级]]>
    </objecttitle>
    <appclient>0</appclient>
    <url></url>
    <objectID>78170</objectID>
    <message>
    <![CDATA[引用来自“android_自由”的评论呵呵哒。。。奥特曼qeruiihs]]>

    </message>
    <commentCount>25</commentCount>
    <pubDate>2016-10-18 15:51:03</pubDate>
    <tweetimage></tweetimage>
    <tweetattach></tweetattach>
    </active>

    </activies>
    <notice>
    <atmeCount>0</atmeCount>
    <msgCount>0</msgCount>
    <reviewCount>0</reviewCount>
    <newFansCount>0</newFansCount>
    <newLikeCount>0</newLikeCount>
    </notice>
    </oschina>*/
    @XStreamAlias("notice")
    public class Notice {
      /*  <notice>
        <atmeCount>0</atmeCount>
        <msgCount>0</msgCount>
        <reviewCount>0</reviewCount>
        <newFansCount>0</newFansCount>
        <newLikeCount>0</newLikeCount>
        </notice>*/

        public void setAtmeCount(String atmeCount) {
            this.atmeCount = atmeCount;
        }

        public void setMsgCount(String msgCount) {
            this.msgCount = msgCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public void setNewFansCount(String newFansCount) {
            this.newFansCount = newFansCount;
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
