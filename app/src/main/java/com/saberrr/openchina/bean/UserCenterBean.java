package com.saberrr.openchina.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by 2017 on 2017/4/8.
 */
@XStreamAlias("oschina")
public class UserCenterBean {
    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Active> getActives() {
        return actives;
    }

    public void setActives(List<Active> actives) {
        this.actives = actives;
    }

    @XStreamAlias("pagesize")
    private String pagesize;


    @XStreamAlias("user")
    private User user;
    @XStreamAlias("activies")
    private List<Active> actives;

    @XStreamAlias("user")
    public class User {
        @XStreamAlias("name")
        private String name;
        @XStreamAlias("uid")
        private String uid;
        @XStreamAlias("portrait")
        private String portrait;
        @XStreamAlias("score")
        private String score;
        @XStreamAlias("fans")
        private String fans;
        @XStreamAlias("followers")
        private String followers;
        @XStreamAlias("jointime")
        private String jointime;
        @XStreamAlias("gender")
        private String gender;
        @XStreamAlias("from")
        private String from;
        @XStreamAlias("devplatform")
        private String devplatform;
        @XStreamAlias("expertise")
        private String expertise;
        @XStreamAlias("relation")
        private int relation;
        @XStreamAlias("latestonline")
        private String latestonline;

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

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getLatestonline() {
            return latestonline;
        }

        public void setLatestonline(String latestonline) {
            this.latestonline = latestonline;
        }
    }




/*    <?xml version="1.0" encoding="UTF-8"?>
    <oschina>
    <user>
    <name>
    <![CDATA[骑着猪的绿皮车]]>
    </name>
    <uid>2958135</uid>
    <portrait></portrait>
    <score>0</score>
    <fans>0</fans>
    <followers>1</followers>
    <jointime>2016-10-15 09:38:31</jointime>
    <gender>
    <![CDATA[保密]]>
    </gender>
    <from>
    <![CDATA[上海市 上海]]>
    </from>
    <devplatform>
    <![CDATA[<无>]]>
    </devplatform>
    <expertise>
    <![CDATA[<无>]]>
    </expertise>
    <relation>3</relation>
    <latestonline>2017-01-29 18:55:36</latestonline>
    </user>
    <pagesize>6</pagesize>
    <activies>



    </activies>
    <notice>
    <atmeCount>0</atmeCount>
    <msgCount>0</msgCount>
    <reviewCount>0</reviewCount>
    <newFansCount>0</newFansCount>
    <newLikeCount>0</newLikeCount>
    </notice>
    </oschina>*/


    @XStreamAlias("active")
    public class Active {
        /* <active>
         <id>10768680</id>
         <portrait>https://static.oschina.net/uploads/user/115/231738_50.jpg?t=1488277735000</portrait>
         <author>
         <![CDATA[Cookie获取失败]]>
         </author>
         <authorid>231738</authorid>
         <catalog>3</catalog>
         <objecttype>101</objecttype>
         <objectcatalog>0</objectcatalog>
         <objecttitle>
         <![CDATA[]]>
         </objecttitle>
         <appclient>0</appclient>

         <url></url>
         <objectID>10768392</objectID>
         <message>
         <![CDATA[dddddd]]>

         </message>
         <commentCount>0</commentCount>
         <pubDate>2016-10-20 11:46:48</pubDate>
         <tweetimage></tweetimage>
         <tweetattach></tweetattach>
         </active>*/
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
        @XStreamAlias("objectreply")
        private UserCenterBean.Active.Objectreply objectreply;

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

        public UserCenterBean.Active.Objectreply getObjectreply() {
            return objectreply;
        }

        public void setObjectreply(UserCenterBean.Active.Objectreply objectreply) {
            this.objectreply = objectreply;
        }

        @XStreamAlias("objectreply")
        public class Objectreply {
            /*<objectreply>
        <objectname>
        <![CDATA[Cookie获取失败]]>
        </objectname>
        <objectbody>
        <![CDATA[gyhuhuhu]]>

        </objectbody>
        </objectreply>*/

            @XStreamAlias("objectname")
            private String objectname;
            @XStreamAlias("objectbody")
            private String objectbody;

            public String getObjectname() {
                return objectname;
            }

            public void setObjectname(String objectname) {
                this.objectname = objectname;
            }

            public String getObjectbody() {
                return objectbody;
            }

            public void setObjectbody(String objectbody) {
                this.objectbody = objectbody;
            }
        }


    }
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
