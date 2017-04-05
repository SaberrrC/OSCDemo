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

    @GET(Urls.USERINFO)
    Call<ResponseBody> getUserInfo(@Header("cookie") String cookie, @Query("uid") String uid);

    @GET(Urls.TWEETLIKE)
    Call<ResponseBody> getTweetlike(@Header("cookie") String cookie, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

}
