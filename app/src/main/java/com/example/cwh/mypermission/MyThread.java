package com.example.cwh.mypermission;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by hui on 2018/11/15.
 */

public class MyThread extends Thread{

    private Handler mHandler;
    public MyThread(){

    }
    @Override
    public void run() {

        super.run();
        Looper.prepare();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.i("ims","收到");
            }
        };
        Looper.loop();
    }

    public Handler getHandler() {
        return mHandler;
    }
}
