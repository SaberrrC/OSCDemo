package com.saberrr.openchina.bean.mymsgcenter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by 2017 on 2017/4/5.
 */

@XStreamAlias("oschina")
public class TweetLikeBean {
    public List<Mytweet> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Mytweet> likeList) {
        this.likeList = likeList;
    }

    /* <? xml version = "1.0"
         encoding="UTF-8"?>
         <oschina>
         <likeList>
         <mytweet>
         <user>
         <name>2973901695</name>
         <uid>2765296</uid>
         <portrait>https://static.oschina.net/uploads/user/1382/2765296_50.jpg?t=1486863567000</portrait>
         </user>
         <tweet>
         <id>10768174</id>
         <body>
         <![CDATA[dffff[困][困][困][困][困]]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-10-20 11:23:44</datatime>
         </mytweet>
         <mytweet>
         <user>
         <name>1001y</name>
         <uid>2613907</uid>
         <portrait></portrait>
         </user>
         <tweet>
         <id>10765280</id>
         <body>
         <![CDATA[
         gygxycg ybut
         ]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-10-20 09:16:43</datatime>
         </mytweet>
         <mytweet>
         <user>
         <name>Cookie获取失败</name>
         <uid>231738</uid>
         <portrait>https://static.oschina.net/uploads/user/115/231738_50.jpg?t=1488277735000</portrait>
         </user>
         <tweet>
         <id>10762722</id>
         <body>
         <![CDATA[请叫我柳神,]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-10-19 20:24:00</datatime>
         </mytweet>
         <mytweet>
         <user>
         <name>it奔跑在路上</name>
         <uid>2965599</uid>
         <portrait></portrait>
         </user>
         <tweet>
         <id>10757873</id>
         <body>
         <![CDATA[我来了...hehe]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-10-19 15:32:58</datatime>
         </mytweet>
         <mytweet>
         <user>
         <name>you123</name>
         <uid>2957454</uid>
         <portrait></portrait>
         </user>
         <tweet>
         <id>10753300</id>
         <body>
         <![CDATA[####输入软件名#输入软件名#输入软件名#输入软件名#]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-10-19 12:23:36</datatime>
         </mytweet>
         <mytweet>
         <user>
         <name>DaryYang</name>
         <uid>2923356</uid>
         <portrait></portrait>
         </user>
         <tweet>
         <id>10496975</id>
         <body>
         <![CDATA[oschina#heima#[酷]]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-09-28 17:22:55</datatime>
         </mytweet>
         <mytweet>
         <user>
         <name>jixianfeng</name>
         <uid>2923198</uid>
         <portrait></portrait>
         </user>
         <tweet>
         <id>10496975</id>
         <body>
         <![CDATA[oschina#heima#[酷]]]>
         </body>
         <author>Cookie获取失败</author>
         </tweet>
         <datatime>2016-09-23 11:31:13</datatime>
         </mytweet>
         </likeList>
         </oschina>*/
    @XStreamAlias("likeList")
    private List<Mytweet> likeList;

    @XStreamAlias("mytweet")
    public class Mytweet {

        @XStreamAlias("user")
        private User user;
        @XStreamAlias("tweet")
        private Tweet tweet;
        @XStreamAlias("datatime")
        private String datatime;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Tweet getTweet() {
            return tweet;
        }

        public void setTweet(Tweet tweet) {
            this.tweet = tweet;
        }

        public String getDatatime() {
            return datatime;
        }

        public void setDatatime(String datatime) {
            this.datatime = datatime;
        }

        @XStreamAlias("user")
        public class User {

            @XStreamAlias("name")
            private String name;
            @XStreamAlias("uid")
            private String uid;
            @XStreamAlias("portrait")
            private String portrait;

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

            public String getName() {

                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        @XStreamAlias("tweet")
        public class Tweet {
            @XStreamAlias("id")
            private String id;
            @XStreamAlias("body")
            private String body;
            @XStreamAlias("author")
            private String author;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }


    }


}
