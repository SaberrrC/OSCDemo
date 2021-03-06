package com.saberrr.openchina.net;

/**
 * Created by Saberrr on 2017-04-01.
 */

public class Urls {
    public static String BASE_URL = "http://www.oschina.net/";
    public static String BANNER =BASE_URL+ "action/apiv2/banner?catalog=1";
    public static String NEWS =BASE_URL+ "action/apiv2/news?pageToken=";
    public static String CONTENT =BASE_URL+ "action/apiv2/news?id=";
    public static String COMMENT1 =BASE_URL+ "action/apiv2/comment?parts=refer,reply&sourceId=";
    public static String COMMENTTYEP ="&type=";

    public static String BLOG =BASE_URL+ "action/apiv2/blog?catalog=";
    public static String BLOGUP ="& pageToken=";



    public static String MOVE_NEW = BASE_URL + "action/apiv2/tweets?type=";
    public static String MOVE_HOT = BASE_URL + "action/apiv2/tweets?type=2";

    public static String MOVE_MY = BASE_URL + "action/apiv2/tweets?type=3";
    public static final String CATEGORY = BASE_URL + "action/api/softwarecatalog_list?tag=0";

    public static final String DETAIL = BASE_URL + "action/api/softwarecatalog_list?tag=";
    public static final String PAGEINDEX = "action/api/software_list?pageIndex=";
    public static final String RECOMMEND = "&searchTag=recommend&pageSize=20";
    public static final String BESTNEW = "&searchTag=time&pageSize=20";
    public static final String HOT = "&searchTag=view&pageSize=20";
    public static final String DOMESTIC = "&searchTag=list_cn&pageSize=20";
    public static final String SOFTWAREDETAIL = BASE_URL + "action/api/software_detail?id=";
    public static final String THREECATEGORY = BASE_URL + "action/api/softwaretag_list?pageIndex=";
    public static final String PAGESIZE = "&pageSize=20";
    //登录
    public static final String LOGIN = "/action/api/login_validate";
    public static final String COMMENT_PUB = "/action/apiv2/comment_pub ";


    //用户信息
    public static final String USERINFO = "/action/api/my_information";
    //赞过我
    public static  final String TWEETLIKE = "/action/api/my_tweet_like_list";
    //发表动弹
    public static final String SEND_JUMP = BASE_URL + "action/apiv2/";
    public static final String SEND_JUMP_TEXT = BASE_URL + "action/apiv2/tweet";
    public static final String SEND_JUMP_IMAGE = BASE_URL + "action/apiv2/resource_image";

    //获取apk版本信息
    public static final String MOBILEAPPVERSION = BASE_URL + "MobileAppVersion.xml";
    public static final String ROCK = BASE_URL + "action/api/rock_rock";


    //粉丝
    public static final String FANS = "/action/api/friends_list";
    //消息
    public static final String MESSAGE = "/action/api/message_list";
    //评论 ， @我
    public static final String COMMENT = "/action/api/active_list";

    //@好友
    public static final String ATFRIENDS = BASE_URL + "action/api/friends_list?";
    //个人中心
    public static final String USERCENTER ="/action/api/user_information";

}
