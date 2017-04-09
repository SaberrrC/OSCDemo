package com.saberrr.openchina.net;

import com.saberrr.openchina.bean.categorybean.CategoryBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Saberrr on 2017-04-01.
 */

public interface HttpServiceApi {
    Call<CategoryBean> requestCategory();

    @FormUrlEncoded
    @POST(Urls.LOGIN)
    Call<ResponseBody> preLogin(@Field("keep_login") String keep_lonin, @Field("username") String username, @Field("pwd") String pwd);
    @FormUrlEncoded
    @POST(Urls.COMMENT_PUB)
    Call<ResponseBody> comment(@Header("cookie")String cookie, @Field("sourceId")String sourceId,@Field("type")String type,@Field("content")String content);

    @GET(Urls.USERINFO)
    Call<ResponseBody> getUserInfo(@Header("cookie") String cookie, @Query("uid") String uid);

    @GET(Urls.TWEETLIKE)
    Call<ResponseBody> getTweetlike(@Header("cookie") String cookie, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);


    @GET(Urls.FANS)
    Call<ResponseBody> getFans(@Header("cookie") String cookie, @Query("pageIndex") int pageIndex, @Query("uid") String uid, @Query("relation") String relation, @Query("pageSize") int pageSize);


    @GET(Urls.MESSAGE)
    Call<ResponseBody> getMsgs(@Header("cookie") String cookie, @Query("pageIndex") int pageIndex,@Query("uid") String uid , @Query("pageSize") int pageSize);

    @GET(Urls.COMMENT)
    Call<ResponseBody> getComment(@Header("cookie") String cookie, @Query("catalog") String catalog , @Query("pageIndex") int pageIndex,@Query("uid") String uid , @Query("pageSize") int pageSize);

    @GET(Urls.USERCENTER)
    Call<ResponseBody> getUserCenter(@Query("pageIndex") int pageIndex, @Query("hisname") String hisname, @Query("uid") String uid, @Query("pageSize") int pageSize, @Query("hisuid") String hisuid);



}
