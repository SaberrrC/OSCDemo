package com.saberrr.openchina.net;

import com.saberrr.openchina.bean.CategoryBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Saberrr on 2017-04-01.
 */

public interface HttpServiceApi {
    Call<CategoryBean> requestCategory();
}
