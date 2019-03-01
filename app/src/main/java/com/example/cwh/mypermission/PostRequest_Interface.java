package com.example.cwh.mypermission;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by hui on 2019/2/20.
 */

public interface PostRequest_Interface {
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Translation1> getCall(@Field("i") String targetSentence);
    //targetSentence的值将作为
}
