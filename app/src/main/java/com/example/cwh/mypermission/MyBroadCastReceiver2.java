package com.example.cwh.mypermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cwh on 2018/8/1.
 */

public class MyBroadCastReceiver2 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context,"动态注册广播",Toast.LENGTH_SHORT).show();
        Log.i("info","broadcast2");
    }

}
