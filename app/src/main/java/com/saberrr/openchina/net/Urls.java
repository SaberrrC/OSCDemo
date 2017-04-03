package com.saberrr.openchina.net;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class Urls {
    public static String BASE_URL = "http://www.oschina.net/";
    public static String BANNER =BASE_URL+ "action/apiv2/banner?catalog=1";
    public static String NEWS =BASE_URL+ "action/apiv2/news?pageToken=";

    public static String MOVE_NEW = BASE_URL + "action/apiv2/tweets?type=1";
    public static String MOVE_HOT = BASE_URL + "action/apiv2/tweets?type=2";
    public static String MOVE_MY = BASE_URL + "action/apiv2/tweets?type=3";

    public static final String CATEGORY = BASE_URL + "action/api/softwarecatalog_list?tag=0";
    public static final String DETAIL = BASE_URL + "action/api/softwarecatalog_list?tag=";
    public static final String RECOMMEND = BASE_URL + "action/api/software_list?pageIndex=0&searchTag=recommend&pageSize=20";
    public static final String BESTNEW = BASE_URL + "action/api/software_list?pageIndex=0&searchTag=time&pageSize=20";
    public static final String HOT = BASE_URL + "action/api/software_list?pageIndex=0&searchTag=view&pageSize=20";
    public static final String DOMESTIC = BASE_URL + "action/api/software_list?pageIndex=0&searchTag=list_cn&pageSize=20";


}
