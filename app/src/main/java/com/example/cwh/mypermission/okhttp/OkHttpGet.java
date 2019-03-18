package com.example.cwh.mypermission.okhttp;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hui on 2019/3/7.
 */

public class OkHttpGet {
    //OkhttpGet的实例
    private static volatile OkHttpGet mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public static OkHttpGet getInstance(Context context){
        if(mInstance==null){
            synchronized (OkHttpGet.class){
                if(mInstance==null){
                    mInstance = new OkHttpGet(context);
                }
            }
        }
        return mInstance;

    }


    private OkHttpGet(Context context){
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10*1024*1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(),cacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler();
    }

    public void getAsynHttp(String url, ResultCallback resultCallback){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call,resultCallback);
    }

    private void dealResult(Call call,final ResultCallback resultCallback){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request(),e,resultCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response.body().string(),resultCallback);
            }
        });
    }

    private void sendSuccessCallback(final String str,final ResultCallback resultCallback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(resultCallback!=null){
                    try {
                        resultCallback.onResponse(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void sendFailedCallback(final Request request,final Exception e,final ResultCallback resultCallback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(resultCallback!=null){
                    resultCallback.onError(request,e);
                }
            }
        });
    }
}
