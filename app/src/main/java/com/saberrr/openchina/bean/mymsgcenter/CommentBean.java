package com.saberrr.openchina.bean.mymsgcenter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by 2017 on 2017/4/6.
 */
@XStreamAlias("oschina")
public class CommentBean {
    public String getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(String activeCount) {
        this.activeCount = activeCount;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public List<Active> getActivies() {
        return activies;
    }

    public void setActivies(List<Active> Activies) {
        this.activies = activies;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @XStreamAlias("activeCount")
    private String activeCount;
    @XStreamAlias("pagesize")
    private String pagesize;
    @XStreamAlias("activies")
    private List<Active> activies;
    @XStreamAlias("notice")
    private Notice notice;


    /* <?xml version="1.0" encoding="UTF-8"?>
     <oschina>
     <activeCount>0</activeCount>
     <!-- 总动弹数 -->
     <pagesize>16</pagesize>
     <activies>
     <active>
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
     <objectreply>
     <objectname>
     <![CDATA[Cookie获取失败]]>
     </objectname>
     <objectbody>
     <![CDATA[gyhuhuhu]]>

     </objectbody>
     </objectreply>
     <url></url>
     <objectID>10768392</objectID>
     <message>
     <![CDATA[dddddd]]>

     </message>
     <commentCount>0</commentCount>
     <pubDate>2016-10-20 11:46:48</pubDate>
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
        private String objecttype;
        @XStreamAlias("objectcatalog")
        private String objectcatalog;
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
        private Objectreply objectreply;

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

        public String getObjecttype() {
            return objecttype;
        }

        public void setObjecttype(String objecttype) {
            this.objecttype = objecttype;
        }

        public String getObjectcatalog() {
            return objectcatalog;
        }

        public void setObjectcatalog(String objectcatalog) {
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

        public Objectreply getObjectreply() {
            return objectreply;
        }

        public void setObjectreply(Objectreply objectreply) {
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
