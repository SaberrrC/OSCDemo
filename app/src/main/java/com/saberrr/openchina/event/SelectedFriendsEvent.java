package com.saberrr.openchina.event;

import com.saberrr.openchina.bean.FriendInfoBean;

import java.util.List;

/**
 * Created by Saberrr on 2017-04-07.
 */

public class SelectedFriendsEvent {
    public List<FriendInfoBean.Friends> names;

    public SelectedFriendsEvent(List<FriendInfoBean.Friends> names) {
        this.names = names;
    }
}
