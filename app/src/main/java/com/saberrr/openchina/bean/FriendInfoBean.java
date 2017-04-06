package com.saberrr.openchina.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Saberrr on 2017-04-06.
 */
@XStreamAlias("oschina")
public class FriendInfoBean {
    @XStreamAlias("friend")
    private Friends friend;

    public Friends getFriend() {
        return friend;
    }

    public void setFriend(Friends friend) {
        this.friend = friend;
    }

    @XStreamAlias("friend")
    private class Friends {
        /**
         * <name>
         * <![CDATA[ MLeo ]]>
         * </name>
         * <userid>728238</userid>
         * <portrait>
         * <![CDATA[
         * https://static.oschina.net/uploads/user/364/728238_100.jpg?t=1386642566000
         * ]]>
         * </portrait>
         * <from>
         * <![CDATA[ 上海 普陀 ]]>
         * </from>
         * <expertise>
         * <![CDATA[ WEB开发,网页设计/UI/UED,DBA/数据库 ]]>
         * </expertise>
         * <gender>1</gender>
         */

        @XStreamAlias("name")
        private String name;
        @XStreamAlias("userid")
        private String userid;
        @XStreamAlias("portrait")
        private String portrait;
        @XStreamAlias("from")
        private String from;
        @XStreamAlias("expertise")
        private String expertise;

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

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getExpertise() {
            return expertise;
        }

        public void setExpertise(String expertise) {
            this.expertise = expertise;
        }
    }
}
