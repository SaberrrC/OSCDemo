package com.saberrr.openchina.contact;

/**
 * Created by Saberrr on 2017-03-26.
 */

public interface Contact {

    String BASEURL                        = "http://127.0.0.1:8090";
    String SUBJECT                        = BASEURL + "/subject?index=";
    String HOMEURL                        = BASEURL + "/home?index=";
    String BASEPICURL                     = BASEURL + "/image?name=";
    String RECOMMENDURL                   = BASEURL + "/recommend?index=0";
    String CATEGORYURL                    = BASEURL + "/category?index=";
    String HOTS                           = BASEURL + "/hot?index=";
    String DETAIL                         = BASEURL + "/detail?packageName=";
    String BASE_DOWNLOAD_APK_URL          = BASEURL + "/download?name=";
    String BASE_DOWNLOAD_APK_CONTINUE_URL = "&range=";
}
