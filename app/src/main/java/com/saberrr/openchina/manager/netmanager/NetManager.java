package com.saberrr.openchina.manager.netmanager;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class NetManager {
    private static NetManager sNetManager = new NetManager();

    private NetManager() {
    }

    public static NetManager getInstance() {
        return sNetManager;
    }



}
