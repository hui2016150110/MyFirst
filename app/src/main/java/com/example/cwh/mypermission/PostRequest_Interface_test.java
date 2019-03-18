package com.example.cwh.mypermission;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by hui on 2019/2/20.
 */

public interface PostRequest_Interface_test {
    @Multipart
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    Observable<Translation1> getCall(@Part MultipartBody.Part photo, @Part("description")RequestBody requestBody);
    //targetSentence的值将作为

}
