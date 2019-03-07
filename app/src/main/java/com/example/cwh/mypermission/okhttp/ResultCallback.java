package com.example.cwh.mypermission.okhttp;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by hui on 2019/3/7.
 */

public abstract class ResultCallback {
    public abstract void onError(Request requset, Exception e);
    public abstract void onResponse(String result)throws IOException;
}
