package com.example.cwh.mypermission;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by hui on 2018/11/15.
 */

public class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        Log.i("ims","hhh");
        Looper.prepare();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("ims","myThread");
            }
        };
        Looper.loop();


    }
}
