package com.saberrr.openchina.bean.searchfindbean;

/**
 * Created by liuqi on 2017/4/8.
 */

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;


@XStreamAlias("oschina")
public class XmlSearchFindBean {
    @XStreamAlias("users")
    private List<FindUser> mUsers;

    public List<FindUser> getUsers() {
        return mUsers;
    }

    public void setUsers(List<FindUser> users) {
        mUsers = users;
    }
}
