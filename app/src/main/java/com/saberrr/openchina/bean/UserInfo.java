package com.saberrr.openchina.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by 2017 on 2017/4/2.
 */

/*<?xml version="1.0" encoding="UTF-8"?>
<oschina>
<user>
<name>
<![CDATA[Cookie获取失败]]>
</name>
<portrait>
<![CDATA[https://static.oschina.net/uploads/user/115/231738_100.jpg?t=1488277735000]]>
</portrait>
<jointime>2012-01-23 19:37:51</jointime>
<gender>1</gender>
<score>0</score>
<from>
<![CDATA[浙江 杭州]]>
</from>
<devplatform>
<![CDATA[<无>]]>
</devplatform>
<expertise>
<![CDATA[<无>]]>
</expertise>
<favoritecount>6</favoritecount>
<fans>15</fans>
<followers>8</followers>
</user>
<notice>
<atmeCount>0</atmeCount>
<msgCount>0</msgCount>
<reviewCount>0</reviewCount>
<newFansCount>0</newFansCount>
<newLikeCount>0</newLikeCount>
</notice>
</oschina>*/
@XStreamAlias("oschina")
public class UserInfo {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @XStreamAlias("user")
    private User user;
    @XStreamAlias("notice")
    private Notice notice;

    @XStreamAlias("notice")
    public class Notice {
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

        /* <atmeCount>0</atmeCount>
                 <msgCount>0</msgCount>
                 <reviewCount>0</reviewCount>
                 <newFansCount>0</newFansCount>
                 <newLikeCount>0</newLikeCount>*/
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


    @XStreamAlias("user")
    public class User {
        /*<name>
        <![CDATA[Cookie获取失败]]>
        </name>
        <portrait>
        <![CDATA[https://static.oschina.net/uploads/user/115/231738_100.jpg?t=1488277735000]]>
        </portrait>
        <jointime>2012-01-23 19:37:51</jointime>
        <gender>1</gender>
        <score>0</score>
        <from>
        <![CDATA[浙江 杭州]]>
        </from>
        <devplatform>
        <![CDATA[<无>]]>
        </devplatform>
        <expertise>
        <![CDATA[<无>]]>
        </expertise>
        <favoritecount>6</favoritecount>
        <fans>15</fans>
        <followers>8</followers>*/

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getJointime() {
            return jointime;
        }

        public void setJointime(String jointime) {
            this.jointime = jointime;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getDevplatform() {
            return devplatform;
        }

        public void setDevplatform(String devplatform) {
            this.devplatform = devplatform;
        }

        public String getExpertise() {
            return expertise;
        }

        public void setExpertise(String expertise) {
            this.expertise = expertise;
        }

        public String getFavoritecount() {
            return favoritecount;
        }

        public void setFavoritecount(String favoritecount) {
            this.favoritecount = favoritecount;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

        @XStreamAlias("name")
        private String name;
        @XStreamAlias("portrait")
        private String portrait;
        @XStreamAlias("jointime")
        private String jointime;
        @XStreamAlias("gender")
        private String gender;
        @XStreamAlias("score")
        private String score;
        @XStreamAlias("from")
        private String from;
        @XStreamAlias("devplatform")
        private String devplatform;
        @XStreamAlias("expertise")
        private String expertise;
        @XStreamAlias("favoritecount")
        private String favoritecount;
        @XStreamAlias("fans")
        private String fans;
        @XStreamAlias("followers")
        private String followers;

    }


}
