package com.example.cwh.mypermission;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hui on 2019/2/27.
 */
//将请求过程进行封装
public class HttpMethods {
    public static final String BASE_URL = "http://fanyi.youdao.com/";
    public static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private PostRequest_Interface mPostRequest_interface;

    private HttpMethods(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        //创建retrofit实例
        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //创建网络接口实例
        mPostRequest_interface = mRetrofit.create(PostRequest_Interface.class);
    }

    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void fangyi(Observer<Translation1> observer, String targetSentence){
        //发送网络请求
        mPostRequest_interface.getCall(targetSentence)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
