package com.saberrr.openchina.bean.mymsgcenter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by 2017 on 2017/4/6.
 */
@XStreamAlias("oschina")
public class MsgBean {
   /* <?xml version="1.0" encoding="UTF-8"?>
    <oschina>
    <messageCount>8</messageCount>
    <pagesize>8</pagesize>
    <messages>
    <message>
    <id>287053</id>
    <portrait>https://static.oschina.net/uploads/user/0/12_50.jpg?t=1421200584000</portrait>
    <friendid>12</friendid>
    <friendname>
    <![CDATA[红薯]]>
    </friendname>
    <sender>
    <![CDATA[红薯]]>
    </sender>
    <senderid>12</senderid>
    <content>
    <![CDATA[您好，我是开源中国社区OsChina的站长红薯，非常欢迎您的加入。OsChina是一个提供全面、快捷更新的用来检索开源项目以及交流使用经验的平台，如果您有任何的意见和建议请直接跟我联系。再次感谢您对开源中国社区的支持，我们倡议：所有OSChina的会员使用真实头像。]]>
    </content>
    <messageCount>1</messageCount>
    <pubDate>2012-01-23 19:38:06</pubDate>
    </message>
    </messages>
    <notice>
    <atmeCount>0</atmeCount>
    <msgCount>0</msgCount>
    <reviewCount>0</reviewCount>
    <newFansCount>0</newFansCount>
    <newLikeCount>0</newLikeCount>
    </notice>
    </oschina>*/


    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @XStreamAlias("messageCount")
    private String messageCount;
    @XStreamAlias("pagesize")
    private String pagesize;
    @XStreamAlias("messages")
    private List<Message> message;

    @XStreamAlias("notice")
    private Notice notice;

    @XStreamAlias("notice")

    public class Notice {
        /*<notice>
        <atmeCount>0</atmeCount>
        <msgCount>0</msgCount>
        <reviewCount>0</reviewCount>
        <newFansCount>0</newFansCount>
        <newLikeCount>0</newLikeCount>
        </notice>*/
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
    }


    @XStreamAlias("message")
    public class Message {
      /*  <message>
        <id>287053</id>
        <portrait>https://static.oschina.net/uploads/user/0/12_50.jpg?t=1421200584000</portrait>
        <friendid>12</friendid>
        <friendname>
        <![CDATA[红薯]]>
        </friendname>
        <sender>
        <![CDATA[红薯]]>
        </sender>
        <senderid>12</senderid>
        <content>
        <![CDATA[您好，我是开源中国社区OsChina的站长红薯，非常欢迎您的加入。OsChina是一个提供全面、快捷更新的用来检索开源项目以及交流使用经验的平台，如果您有任何的意见和建议请直接跟我联系。再次感谢您对开源中国社区的支持，我们倡议：所有OSChina的会员使用真实头像。]]>
        </content>
        <messageCount>1</messageCount>
        <pubDate>2012-01-23 19:38:06</pubDate>
        </message>*/

        @XStreamAlias("id")
        private String id;
        @XStreamAlias("portrait")
        private String portrait;
        @XStreamAlias("friendid")
        private String friendid;
        @XStreamAlias("friendname")
        private String friendname;
        @XStreamAlias("sender")
        private String sender;
        @XStreamAlias("senderid")
        private String senderid;
        @XStreamAlias("content")
        private String content;
        @XStreamAlias("messageCount")
        private String messageCount;
        @XStreamAlias("pubDate")
        private String pubDate;

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

        public String getFriendid() {
            return friendid;
        }

        public void setFriendid(String friendid) {
            this.friendid = friendid;
        }

        public String getFriendname() {
            return friendname;
        }

        public void setFriendname(String friendname) {
            this.friendname = friendname;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getSenderid() {
            return senderid;
        }

        public void setSenderid(String senderid) {
            this.senderid = senderid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(String messageCount) {
            this.messageCount = messageCount;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }
    }


}
