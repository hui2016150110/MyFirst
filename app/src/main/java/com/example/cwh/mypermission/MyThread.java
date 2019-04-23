package com.example.cwh.mypermission;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by hui on 2018/11/15.
 */

public class MyThread extends Thread{

    private Handler mHandler;
    public Looper mLooper;
    public MyThread(){

    }
    @Override
    public void run() {

        super.run();
        Looper.prepare();
        mLooper = Looper.myLooper();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.i("ims","收到");
            }
        };
        Looper.loop();
        Log.i("TAG","quit");
    }

    public Handler getHandler() {
        return mHandler;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void exit(){
        if(mLooper!=null){
            mLooper.quitSafely();;
            mLooper = null;
        }
    }
}
