package com.saberrr.openchina.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

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
        friends.add(friend);
    }

    public List<Friends> friends = new ArrayList<>();

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }

    @XStreamAlias("friend")
    public class Friends {
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
        public boolean checked = false;

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

        @Override
        public String toString() {
            return "Friends{" +
                    "name='" + name + '\'' +
                    ", userid='" + userid + '\'' +
                    ", portrait='" + portrait + '\'' +
                    ", from='" + from + '\'' +
                    ", expertise='" + expertise + '\'' +
                    '}';
        }
    }
}
