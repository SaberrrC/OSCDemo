package com.saberrr.openchina.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by 2017 on 2017/4/2.
 */

/*<?xml version="1.0" encoding="UTF-8"?>
<oschina>
<result>
<errorCode>1</errorCode>
<errorMessage>
<![CDATA[登录成功]]>
</errorMessage>
</result>
<user>
<uid>3343078</uid>
<location>
<![CDATA[安徽 蚌埠]]>
</location>
<name>
<![CDATA[YUBean]]>
</name>
<followers>0</followers>
<fans>0</fans>
<score>0</score>
<portrait>https://static.oschina.net/uploads/user/1671/3343078_100.jpg?t=1489125210000</portrait>
<favoritecount>0</favoritecount>
<gender>1</gender>
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
public class LoginBean implements Serializable{
    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @XStreamAlias("notice")
    private Notice notice;
    @XStreamAlias("user")
    private User user;
    @XStreamAlias("result")
    private Result result;
    @XStreamAlias("notice")
    public class Notice implements Serializable{
        /*<atmeCount>0</atmeCount>
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

        public String getNewLikeCount() {
            return newLikeCount;
        }

        public void setNewLikeCount(String newLikeCount) {
            this.newLikeCount = newLikeCount;
        }

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

        @XStreamAlias("newLikeCount")
        private String newLikeCount;

    }


    @XStreamAlias("user")
    public class User implements Serializable {
        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public void setFavoritecount(String favoritecount) {
            this.favoritecount = favoritecount;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUid() {
            return uid;
        }

        public String getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public String getFollowers() {
            return followers;
        }

        public String getFans() {
            return fans;
        }

        public String getScore() {
            return score;
        }

        public String getPortrait() {
            return portrait;
        }

        public String getFavoritecount() {
            return favoritecount;
        }

        public String getGender() {
            return gender;
        }

        /*  <uid>3343078</uid>
                          <location>
                          <![CDATA[安徽 蚌埠]]>
                          </location>
                          <name>
                          <![CDATA[YUBean]]>
                          </name>
                          <followers>0</followers>
                          <fans>0</fans>
                          <score>0</score>
                          <portrait>https://static.oschina.net/uploads/user/1671/3343078_100.jpg?t=1489125210000</portrait>
                          <favoritecount>0</favoritecount>
                          <gender>1</gender>*/
        @XStreamAlias("uid")
        private String uid;
        @XStreamAlias("location")
        private String location;
        @XStreamAlias("name")
        private String name;
        @XStreamAlias("followers")
        private String followers;
        @XStreamAlias("fans")
        private String fans;
        @XStreamAlias("score")
        private String score;
        @XStreamAlias("portrait")
        private String portrait;
        @XStreamAlias("favoritecount")
        private String favoritecount;
        @XStreamAlias("gender")
        private String gender;

    }

    @XStreamAlias("result")
    public class Result implements Serializable {
        /*<errorCode>1</errorCode>
        <errorMessage>
        <![CDATA[登录成功]]>
        </errorMessage>*/

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @XStreamAlias("errorCode")
        private String errorCode;
        @XStreamAlias("errorMessage")
        private String errorMessage;

    }


}
